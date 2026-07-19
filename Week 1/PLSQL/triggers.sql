-- =========================================================
-- PL/SQL Exercise 5: Triggers
-- =========================================================

-- Scenario 1: Automatically update the last modified date when a customer's record is updated.
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :new.LastModified := SYSDATE;
END;
/

-- Scenario 2: Maintain an audit log for all transactions.
CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, Action, LogDate)
    VALUES (
        :new.TransactionID,
        'Inserted new ' || :new.TransactionType || ' transaction. Amount: $' || :new.Amount || ' for Account ID: ' || :new.AccountID,
        SYSDATE
    );
END;
/

-- Scenario 3: Enforce business rules on deposits and withdrawals.
-- Ensures withdrawals do not exceed the account balance, and deposits are positive.
CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_AccountBalance NUMBER;
BEGIN
    -- Rule 1: Deposits and withdrawals must have a positive amount
    IF :new.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20201, 'Transaction amount must be strictly greater than zero.');
    END IF;

    -- Rule 2: For withdrawals, verify that the account has sufficient balance
    IF :new.TransactionType = 'Withdrawal' THEN
        SELECT Balance INTO v_AccountBalance
        FROM Accounts
        WHERE AccountID = :new.AccountID;

        IF v_AccountBalance < :new.Amount THEN
            RAISE_APPLICATION_ERROR(-20202, 'Withdrawal Failed: Insufficient funds in Account ' || 
                                            :new.AccountID || '. Current Balance: $' || v_AccountBalance);
        END IF;
    END IF;
END;
/
