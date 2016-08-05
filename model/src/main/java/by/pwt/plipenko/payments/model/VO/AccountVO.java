package by.pwt.plipenko.payments.model.VO;

/**
 * Created by apilipenka on 8/5/2016.
 */
public class AccountVO extends EntityVO {

    private String number;
    private double amount;
    private int agreementID;
    private String agreementNumber;
    private String agreementBankName;
    private String agreementBankUNN;
    private int currencyID;
    private String currencyMnemoCode;
    private String currencyCode;

    public AccountVO() {
        super();
    }

    public AccountVO(int id, String number, double amount, int agreementID, String agreementNumber, String agreementBankName, String agreementBankUNN, int currencyID, String currencyMnemocode, String currencyCode) {
        super(id);
        this.number = number;
        this.amount = amount;
        this.agreementID = agreementID;
        this.agreementNumber = agreementNumber;
        this.agreementBankName = agreementBankName;
        this.agreementBankUNN = agreementBankUNN;

        this.currencyID = currencyID;
        this.currencyMnemoCode = currencyMnemocode;

        this.currencyCode = currencyCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getAgreementID() {
        return agreementID;
    }

    public void setAgreementID(int agreementID) {
        this.agreementID = agreementID;
    }

    public String getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreementNumber(String agreementNumber) {
        this.agreementNumber = agreementNumber;
    }

    public int getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(int currencyID) {
        this.currencyID = currencyID;
    }

    public String getCurrencyMnemocode() {
        return currencyMnemoCode;
    }

    public void setCurrencyMnemocode(String currencyMnemocode) {
        this.currencyMnemoCode = currencyMnemocode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getAgreementBankName() {
        return agreementBankName;
    }

    public void setAgreementBankName(String agreementBankName) {
        this.agreementBankName = agreementBankName;
    }

    public String getAgreementBankUNN() {
        return agreementBankUNN;
    }

    public void setAgreementBankUNN(String agreementBankUNN) {
        this.agreementBankUNN = agreementBankUNN;
    }
}
