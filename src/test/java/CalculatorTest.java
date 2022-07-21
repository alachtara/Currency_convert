import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    private static final Calculator CALCULATOR = new Calculator();
    @Test
    public void givenWrongFormatAmount_WhenConvertCurrency_ThenResultZero() {
        assertEquals("0.00", CALCULATOR.convertCurrency("incorrectAmount", "JPY"));
    }

    @Test
    public void givenWrongCurrency_WhenConvertCurrency_ThenResultZero() {
        assertEquals("0.00", CALCULATOR.convertCurrency("10", "Incorrect"));
    }

    @Test
    public void givenLowerCaseCurrency_WhenConvertCurrency_CorrectResult() {
        assertEquals("0.9911", CALCULATOR.convertCurrency("1", "chf"));

    }

    @Test
    public void whenConvertCurrency_CorrectResult() {
        assertEquals("0.9911", CALCULATOR.convertCurrency("1", "CHF"));
        assertEquals("9.9110", CALCULATOR.convertCurrency("10", "CHF"));
    }

}