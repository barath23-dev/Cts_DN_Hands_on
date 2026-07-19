-- =========================================================
-- PL/SQL Exercise 7: Packages
-- =========================================================

-- ==========================================
-- 1. CustomerManagement Package Specification
-- ==========================================
CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddNewCustomer(
        p_CustomerID IN NUMBER,
        p_Name       IN VARCHAR2,
        p_DOB        IN DATE,
        p_Balance    IN NUMBER
    );
    
    PROCEDURE UpdateCustomerDetails(
        p_CustomerID IN NUMBER,
        p_Name       IN VARCHAR2,
        p_Balance    IN NUMBER
    );
    
    FUNCTION GetCustomerBalance(
        p_CustomerID IN NUMBER
    ) RETURN NUMBER;
END CustomerManagement;
/

-- 1. CustomerManagement Package Body
CREATE OR REPLACE PACKAGE BODY CustomerManagement AS
    PROCEDURE AddNewCustomer(
        p_CustomerID IN NUMBER,
        p_Name       IN VARCHAR2,
        p_DOB        IN DATE,
        p_Balance    IN NUMBER
    ) IS
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_CustomerID, p_Name, p_DOB, p_Balance, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('CustomerManagement: Added customer ' || p_Name);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('CustomerManagement Error: Customer ID ' || p_CustomerID || ' already exists.');
    END AddNewCustomer;

    PROCEDURE UpdateCustomerDetails(
        p_CustomerID IN NUMBER,
        p_Name       IN VARCHAR2,
        p_Balance    IN NUMBER
    ) IS
    BEGIN
        UPDATE Customers
        SET Name = p_Name,
            Balance = p_Balance,
            LastModified = SYSDATE
        WHERE CustomerID = p_CustomerID;
        
        IF SQL%NOTFOUND THEN
            DBMS_OUTPUT.PUT_LINE('CustomerManagement Error: Customer ID ' || p_CustomerID || ' not found.');
        ELSE
            COMMIT;
            DBMS_OUTPUT.PUT_LINE('CustomerManagement: Updated details for Customer ID ' || p_CustomerID);
        END IF;
    END UpdateCustomerDetails;

    FUNCTION GetCustomerBalance(
        p_CustomerID IN NUMBER
    ) RETURN NUMBER IS
        v_Balance NUMBER := 0;
    BEGIN
        SELECT Balance INTO v_Balance
        FROM Customers
        WHERE CustomerID = p_CustomerID;
        RETURN v_Balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN -1;
    END GetCustomerBalance;
END CustomerManagement;
/


-- ==========================================
-- 2. EmployeeManagement Package Specification
-- ==========================================
CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(
        p_EmployeeID IN NUMBER,
        p_Name       IN VARCHAR2,
        p_Position   IN VARCHAR2,
        p_Salary     IN NUMBER,
        p_Department IN VARCHAR2
    );
    
    PROCEDURE UpdateEmployeeDetails(
        p_EmployeeID IN NUMBER,
        p_Position   IN VARCHAR2,
        p_Salary     IN NUMBER
    );
    
    FUNCTION CalculateAnnualSalary(
        p_EmployeeID IN NUMBER
    ) RETURN NUMBER;
END EmployeeManagement;
/

-- 2. EmployeeManagement Package Body
CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS
    PROCEDURE HireEmployee(
        p_EmployeeID IN NUMBER,
        p_Name       IN VARCHAR2,
        p_Position   IN VARCHAR2,
        p_Salary     IN NUMBER,
        p_Department IN VARCHAR2
    ) IS
    BEGIN
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_EmployeeID, p_Name, p_Position, p_Salary, p_Department, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('EmployeeManagement: Hired employee ' || p_Name);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('EmployeeManagement Error: Employee ID ' || p_EmployeeID || ' already exists.');
    END HireEmployee;

    PROCEDURE UpdateEmployeeDetails(
        p_EmployeeID IN NUMBER,
        p_Position   IN VARCHAR2,
        p_Salary     IN NUMBER
    ) IS
    BEGIN
        UPDATE Employees
        SET Position = p_Position,
            Salary = p_Salary
        WHERE EmployeeID = p_EmployeeID;
        
        IF SQL%NOTFOUND THEN
            DBMS_OUTPUT.PUT_LINE('EmployeeManagement Error: Employee ID ' || p_EmployeeID || ' not found.');
        ELSE
            COMMIT;
            DBMS_OUTPUT.PUT_LINE('EmployeeManagement: Updated details for Employee ID ' || p_EmployeeID);
        END IF;
    END UpdateEmployeeDetails;

    FUNCTION CalculateAnnualSalary(
        p_EmployeeID IN NUMBER
    ) RETURN NUMBER IS
        v_MonthlySalary NUMBER := 0;
    BEGIN
        SELECT Salary INTO v_MonthlySalary
        FROM Employees
        WHERE EmployeeID = p_EmployeeID;
        RETURN v_MonthlySalary * 12;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN -1;
    END CalculateAnnualSalary;
END EmployeeManagement;
/


-- ==========================================
-- 3. AccountOperations Package Specification
-- ==========================================
CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount(
        p_AccountID   IN NUMBER,
        p_CustomerID  IN NUMBER,
        p_AccountType IN VARCHAR2,
        p_Balance     IN NUMBER
    );
    
    PROCEDURE CloseAccount(
        p_AccountID IN NUMBER
    );
    
    FUNCTION GetTotalBalance(
        p_CustomerID IN NUMBER
    ) RETURN NUMBER;
END AccountOperations;
/

-- 3. AccountOperations Package Body
CREATE OR REPLACE PACKAGE BODY AccountOperations AS
    PROCEDURE OpenAccount(
        p_AccountID   IN NUMBER,
        p_CustomerID  IN NUMBER,
        p_AccountType IN VARCHAR2,
        p_Balance     IN NUMBER
    ) IS
    BEGIN
        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_AccountID, p_CustomerID, p_AccountType, p_Balance, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('AccountOperations: Opened account ' || p_AccountID || ' for customer ' || p_CustomerID);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('AccountOperations Error: Account ID ' || p_AccountID || ' already exists.');
    END OpenAccount;

    PROCEDURE CloseAccount(
        p_AccountID IN NUMBER
    ) IS
    BEGIN
        DELETE FROM Accounts WHERE AccountID = p_AccountID;
        IF SQL%NOTFOUND THEN
            DBMS_OUTPUT.PUT_LINE('AccountOperations Error: Account ID ' || p_AccountID || ' not found.');
        ELSE
            COMMIT;
            DBMS_OUTPUT.PUT_LINE('AccountOperations: Closed account ID ' || p_AccountID);
        END IF;
    END CloseAccount;

    FUNCTION GetTotalBalance(
        p_CustomerID IN NUMBER
    ) RETURN NUMBER IS
        v_TotalBalance NUMBER := 0;
    BEGIN
        SELECT SUM(Balance) INTO v_TotalBalance
        FROM Accounts
        WHERE CustomerID = p_CustomerID;
        
        RETURN NVL(v_TotalBalance, 0);
    END GetTotalBalance;
END AccountOperations;
/
