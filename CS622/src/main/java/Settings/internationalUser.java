package Settings;

public class internationalUser extends user {

    String userCurrency;

    public internationalUser(String idInput, String currencyInput) throws InvalidCurrencyCodeException {
        super.userID = idInput;
        this.setCurrency(currencyInput);
    }

    public String getCurrency() {return this.userCurrency;}

    public void setCurrency(String currencyName) throws InvalidCurrencyCodeException {
        if(currencyName.equals("GBP")|currencyName.equals("EUR")|currencyName.equals("JPY"))
        {this.userCurrency = currencyName;}
        else
        {throw new InvalidCurrencyCodeException(currencyName);}
    }

    public domesticUser convertToDomestic() {
        domesticUser dm = new domesticUser(this.userID);
        return dm;
    }


}
