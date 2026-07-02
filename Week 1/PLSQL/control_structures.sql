-- =========================================================
-- PL/SQL Exercise 1: Control Structures
-- =========================================================

-- Scenario 1: Apply a 1% discount to loan interest rates for customers over 60 years old.
DECLARE
    -- Cursor to fetch loans of customers older than 60
    CURSOR c_senior_loans IS
        SELECT l.LoanID, l.InterestRate, c.Name, c.DOB
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE MONTHS_BETWEEN(SYSDATE, c.DOB) / 12 > 60;
BEGIN
    FOR r_loan IN c_senior_loans LOOP
        -- Apply a 1% discount (subtraction of 1.0 from current interest percentage)
        UPDATE Loans
        SET InterestRate = InterestRate - 1
        WHERE LoanID = r_loan.LoanID;
        
        DBMS_OUTPUT.PUT_LINE('Senior Discount Applied: Loan ID ' || r_loan.LoanID || 
                             ' for customer ' || r_loan.Name || 
                             ' interest rate reduced from ' || r_loan.InterestRate || 
                             '% to ' || (r_loan.InterestRate - 1) || '%');
    END LOOP;
    COMMIT;
END;
/

-- Scenario 2: Promote customers to VIP status (IsVIP = 1) if their balance exceeds $10,000.
DECLARE
    CURSOR c_customers_vip IS
        SELECT CustomerID, Name, Balance
        FROM Customers
        WHERE Balance > 10000;
BEGIN
    FOR r_cust IN c_customers_vip LOOP
        UPDATE Customers
        SET IsVIP = 1
        WHERE CustomerID = r_cust.CustomerID;
        
        DBMS_OUTPUT.PUT_LINE('VIP Promotion: Customer ' || r_cust.Name || 
                             ' (ID: ' || r_cust.CustomerID || ') promoted to VIP. Current Balance: $' || r_cust.Balance);
    END LOOP;
    COMMIT;
END;
/

-- Scenario 3: Send reminders to customers whose loans are due within the next 30 days.
DECLARE
    CURSOR c_due_loans IS
        SELECT l.LoanID, l.EndDate, c.Name, c.CustomerID
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- LOAN PAYMENT REMINDERS (DUE IN NEXT 30 DAYS) ---');
    FOR r_due IN c_due_loans LOOP
        DBMS_OUTPUT.PUT_LINE('Dear ' || r_due.Name || ' (ID: ' || r_due.CustomerID || 
                             '), your loan ID ' || r_due.LoanID || 
                             ' is due on ' || TO_CHAR(r_due.EndDate, 'YYYY-MM-DD') || 
                             '. Please ensure payment is processed.');
    END LOOP;
END;
/
