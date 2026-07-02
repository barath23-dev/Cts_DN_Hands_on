-- =========================================================
-- PL/SQL Exercise 4: Functions
-- =========================================================

-- Scenario 1: Calculate age in years based on Date of Birth.
CREATE OR REPLACE FUNCTION CalculateAge (
    p_DOB IN DATE
) RETURN NUMBER IS
    v_Age NUMBER;
BEGIN
    IF p_DOB IS NULL THEN
        RETURN NULL;
    END IF;
    v_Age := FLOOR(MONTHS_BETWEEN(SYSDATE, p_DOB) / 12);
    RETURN v_Age;
END CalculateAge;
/

-- Scenario 2: Calculate the monthly installment (EMI) for a loan.
-- Formula: EMI = P * r * (1 + r)^n / ((1 + r)^n - 1)
-- where P = Principal (Loan Amount), r = Monthly Interest Rate (Annual Rate / 12 / 100), n = Months (Years * 12)
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_LoanAmount   IN NUMBER,
    p_InterestRate IN NUMBER, -- Annual percentage (e.g. 5.5 for 5.5%)
    p_DurationYears IN NUMBER
) RETURN NUMBER IS
    v_EMI NUMBER;
    v_MonthlyRate NUMBER;
    v_NumMonths NUMBER;
BEGIN
    -- Validations
    IF p_LoanAmount <= 0 OR p_InterestRate < 0 OR p_DurationYears <= 0 THEN
        RETURN 0;
    END IF;

    v_NumMonths := p_DurationYears * 12;

    -- If interest rate is 0%, simple division
    IF p_InterestRate = 0 THEN
        RETURN ROUND(p_LoanAmount / v_NumMonths, 2);
    END IF;

    v_MonthlyRate := (p_InterestRate / 100) / 12;
    v_EMI := p_LoanAmount * v_MonthlyRate * POWER(1 + v_MonthlyRate, v_NumMonths) / 
             (POWER(1 + v_MonthlyRate, v_NumMonths) - 1);

    RETURN ROUND(v_EMI, 2);
EXCEPTION
    WHEN OTHERS THEN
        RETURN 0;
END CalculateMonthlyInstallment;
/

-- Scenario 3: Check if a customer has sufficient balance.
CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_AccountID IN NUMBER,
    p_Amount    IN NUMBER
) RETURN BOOLEAN IS
    v_Balance NUMBER;
BEGIN
    SELECT Balance INTO v_Balance
    FROM Accounts
    WHERE AccountID = p_AccountID;

    IF v_Balance >= p_Amount THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
    WHEN OTHERS THEN
        RETURN FALSE;
END HasSufficientBalance;
/
