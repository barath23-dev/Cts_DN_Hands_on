-- =========================================================
-- PL/SQL Exercise 2: Error Handling
-- =========================================================

-- Scenario 1: SafeTransferFunds checks balances and handles exceptions safely.
CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_SourceAccountID IN NUMBER,
    p_DestAccountID   IN NUMBER,
    p_Amount          IN NUMBER
) IS
    v_SourceBalance NUMBER;
    v_DestBalance   NUMBER;
    insufficient_funds EXCEPTION;
    negative_transfer  EXCEPTION;
BEGIN
    -- Input Validation
    IF p_Amount <= 0 THEN
        RAISE negative_transfer;
    END IF;

    -- Check source account existence and lock balance
    BEGIN
        SELECT Balance INTO v_SourceBalance
        FROM Accounts
        WHERE AccountID = p_SourceAccountID
        FOR UPDATE;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20001, 'Source account ID ' || p_SourceAccountID || ' does not exist.');
    END;

    -- Check destination account existence
    BEGIN
        SELECT Balance INTO v_DestBalance
        FROM Accounts
        WHERE AccountID = p_DestAccountID
        FOR UPDATE;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20002, 'Destination account ID ' || p_DestAccountID || ' does not exist.');
    END;

    -- Check balance
    IF v_SourceBalance < p_Amount THEN
        RAISE insufficient_funds;
    END IF;

    -- Perform transaction
    UPDATE Accounts SET Balance = Balance - p_Amount WHERE AccountID = p_SourceAccountID;
    UPDATE Accounts SET Balance = Balance + p_Amount WHERE AccountID = p_DestAccountID;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Success: Transferred $' || p_Amount || ' from Account ' || p_SourceAccountID || ' to Account ' || p_DestAccountID);

EXCEPTION
    WHEN negative_transfer THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Transfer amount must be positive. Transaction rolled back.');
    WHEN insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds in source account ' || p_SourceAccountID || '. Transaction rolled back.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('System Error: ' || SQLERRM || '. Transaction rolled back.');
END SafeTransferFunds;
/

-- Scenario 2: UpdateSalary increases employee salary. Handles non-existent ID.
CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_EmployeeID IN NUMBER,
    p_Percentage IN NUMBER
) IS
    v_CurrentSalary NUMBER;
    v_Name VARCHAR2(100);
    employee_not_found EXCEPTION;
BEGIN
    -- Verify employee existence
    BEGIN
        SELECT Salary, Name INTO v_CurrentSalary, v_Name
        FROM Employees
        WHERE EmployeeID = p_EmployeeID;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE employee_not_found;
    END;

    -- Update salary
    UPDATE Employees
    SET Salary = Salary * (1 + (p_Percentage / 100))
    WHERE EmployeeID = p_EmployeeID;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Salary Updated: Employee ' || v_Name || ' (ID: ' || p_EmployeeID || ') salary increased by ' || p_Percentage || '%');

EXCEPTION
    WHEN employee_not_found THEN
        DBMS_OUTPUT.PUT_LINE('Error: Employee with ID ' || p_EmployeeID || ' does not exist. Update cancelled.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Unexpected Error during salary update: ' || SQLERRM);
END UpdateSalary;
/

-- Scenario 3: AddNewCustomer inserts a new customer. Prevents duplicate ID insertion.
CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_CustomerID IN NUMBER,
    p_Name       IN VARCHAR2,
    p_DOB        IN DATE,
    p_Balance    IN NUMBER
) IS
    duplicate_customer EXCEPTION;
    PRAGMA EXCEPTION_INIT(duplicate_customer, -1); -- Map to primary key constraint violation
BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_CustomerID, p_Name, p_DOB, p_Balance, SYSDATE);

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Customer Created: ' || p_Name || ' (ID: ' || p_CustomerID || ') successfully added.');

EXCEPTION
    -- Handled via generic DUP_VAL_ON_INDEX or custom mapper
    WHEN DUP_VAL_ON_INDEX THEN
        DBMS_OUTPUT.PUT_LINE('Error: Customer with ID ' || p_CustomerID || ' already exists. Insertion skipped.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Unexpected Error during customer addition: ' || SQLERRM);
END AddNewCustomer;
/
