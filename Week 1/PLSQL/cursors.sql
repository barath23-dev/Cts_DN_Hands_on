-- =========================================================
-- PL/SQL Exercise 6: Cursors
-- =========================================================

-- Scenario 1: Generate monthly statements for all customers.
DECLARE
    -- Cursor to fetch transactions for the current month
    CURSOR c_monthly_transactions IS
        SELECT t.TransactionID, t.AccountID, t.TransactionDate, t.Amount, t.TransactionType,
               a.CustomerID, c.Name
        FROM Transactions t
        JOIN Accounts a ON t.AccountID = a.AccountID
        JOIN Customers c ON a.CustomerID = c.CustomerID
        WHERE EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
          AND EXTRACT(YEAR FROM t.TransactionDate) = EXTRACT(YEAR FROM SYSDATE);
    
    v_CurrentCustomer NUMBER := -1;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== MONTHLY ACCOUNT STATEMENTS ===');
    FOR r_tx IN c_monthly_transactions LOOP
        -- Print header for customer if it's a new customer record
        IF v_CurrentCustomer != r_tx.CustomerID THEN
            v_CurrentCustomer := r_tx.CustomerID;
            DBMS_OUTPUT.PUT_LINE('Customer: ' || r_tx.Name || ' (ID: ' || r_tx.CustomerID || ')');
        END IF;
        
        DBMS_OUTPUT.PUT_LINE('  - TxID: ' || r_tx.TransactionID || 
                             ' | Account: ' || r_tx.AccountID || 
                             ' | Date: ' || TO_CHAR(r_tx.TransactionDate, 'YYYY-MM-DD') || 
                             ' | Type: ' || r_tx.TransactionType || 
                             ' | Amount: $' || r_tx.Amount);
    END LOOP;
END;
/

-- Scenario 2: Apply an annual maintenance fee ($20) to all accounts.
DECLARE
    v_MaintenanceFee CONSTANT NUMBER := 20.00;
    
    -- Cursor with FOR UPDATE to lock accounts while modifying them
    CURSOR c_all_accounts IS
        SELECT AccountID, Balance
        FROM Accounts
        FOR UPDATE;
BEGIN
    FOR r_acct IN c_all_accounts LOOP
        UPDATE Accounts
        SET Balance = Balance - v_MaintenanceFee,
            LastModified = SYSDATE
        WHERE CURRENT OF c_all_accounts;
        
        DBMS_OUTPUT.PUT_LINE('Fee Applied: Account ' || r_acct.AccountID || 
                             ' balance reduced from $' || r_acct.Balance || 
                             ' to $' || (r_acct.Balance - v_MaintenanceFee));
    END LOOP;
    COMMIT;
END;
/

-- Scenario 3: Update the interest rate for all loans based on a new policy.
-- Policy: If loan amount is greater than $10,000, increase interest rate by 0.5% (due to higher risk),
-- otherwise decrease it by 0.25% (promotional discount).
DECLARE
    CURSOR c_all_loans IS
        SELECT LoanID, LoanAmount, InterestRate
        FROM Loans
        FOR UPDATE;
    
    v_NewRate NUMBER;
BEGIN
    FOR r_loan IN c_all_loans LOOP
        IF r_loan.LoanAmount > 10000 THEN
            v_NewRate := r_loan.InterestRate + 0.5;
        ELSE
            v_NewRate := r_loan.InterestRate - 0.25;
        END IF;

        UPDATE Loans
        SET InterestRate = v_NewRate
        WHERE CURRENT OF c_all_loans;

        DBMS_OUTPUT.PUT_LINE('Loan ID ' || r_loan.LoanID || ' Interest Rate updated from ' || 
                             r_loan.InterestRate || '% to ' || v_NewRate || '%');
    END LOOP;
    COMMIT;
END;
/
