package Settings;

public class domesticUser extends user {

    public domesticUser(String idInput) {
        super.userID = idInput;
    }

    public String getCurrency() {return "USD";}

    public internationalUser convertToInternational(String currencyName) throws InvalidCurrencyCodeException {
        internationalUser im = new internationalUser(this.userID, currencyName);
        return im;
    }
}
