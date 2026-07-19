-- =========================================================
-- PL/SQL Exercise 3: Stored Procedures
-- =========================================================

-- Scenario 1: Process monthly interest for all savings accounts (1% rate).
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
    v_InterestRate CONSTANT NUMBER := 0.01; -- 1%
BEGIN
    UPDATE Accounts
    SET Balance = Balance * (1 + v_InterestRate),
        LastModified = SYSDATE
    WHERE AccountType = 'Savings';

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Success: Applied 1% monthly interest to all Savings accounts.');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error processing monthly interest: ' || SQLERRM);
END ProcessMonthlyInterest;
/

-- Scenario 2: Implement a bonus scheme for employees based on department and bonus percentage.
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_Department IN VARCHAR2,
    p_BonusPercentage IN NUMBER
) IS
    v_UpdateCount NUMBER := 0;
BEGIN
    -- Verify input parameters
    IF p_BonusPercentage <= 0 THEN
        DBMS_OUTPUT.PUT_LINE('Error: Bonus percentage must be positive.');
        RETURN;
    END IF;

    -- Update salaries by adding the bonus percentage
    UPDATE Employees
    SET Salary = Salary * (1 + (p_BonusPercentage / 100))
    WHERE Department = p_Department;

    v_UpdateCount := SQL%ROWCOUNT;
    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('Success: Awarded ' || p_BonusPercentage || '% bonus to ' || 
                         v_UpdateCount || ' employees in the ' || p_Department || ' department.');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating employee bonus: ' || SQLERRM);
END UpdateEmployeeBonus;
/

-- Scenario 3: Transfer funds between customer accounts with pre-validation check.
CREATE OR REPLACE PROCEDURE TransferFunds (
    p_SourceAccountID IN NUMBER,
    p_DestAccountID   IN NUMBER,
    p_Amount          IN NUMBER
) IS
    v_SourceBalance NUMBER;
BEGIN
    -- Validation 1: Amount must be positive
    IF p_Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20101, 'Transfer amount must be positive.');
    END IF;

    -- Fetch source balance
    SELECT Balance INTO v_SourceBalance
    FROM Accounts
    WHERE AccountID = p_SourceAccountID;

    -- Validation 2: Check balance
    IF v_SourceBalance < p_Amount THEN
        RAISE_APPLICATION_ERROR(-20102, 'Insufficient funds in source account.');
    END IF;

    -- Perform updates
    UPDATE Accounts
    SET Balance = Balance - p_Amount, LastModified = SYSDATE
    WHERE AccountID = p_SourceAccountID;

    UPDATE Accounts
    SET Balance = Balance + p_Amount, LastModified = SYSDATE
    WHERE AccountID = p_DestAccountID;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transfer Complete: Transferred $' || p_Amount || 
                         ' from Account ' || p_SourceAccountID || 
                         ' to Account ' || p_DestAccountID);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: One or both accounts do not exist.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transaction failed: ' || SQLERRM);
END TransferFunds;
/
