package Settings;

public class InvalidCurrencyCodeException extends Exception{
    public InvalidCurrencyCodeException(String currencyCode) {
        System.out.println("We currently do not support "+currencyCode);
    }
}
