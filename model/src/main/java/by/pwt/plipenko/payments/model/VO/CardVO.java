package by.pwt.plipenko.payments.model.VO;

import java.util.Date;

/**
 * Created by apilipenka on 8/5/2016.
 */
public class CardVO {
    private int id;
    private String number;
    private String name;
    private Date validToDate;
    private int accountID;
    private String accountNumber;
    private String accountAgreementNumber;
    private String accountAgreementBankName;
    private String accountAgreementBankUNN;
    private String accountCurrencyMnemoCode;


    public CardVO(int id, String number, String name, Date validToDate, int accountID, String accountNumber, String accountAgreementNumber, String accountAgreementBankName, String accountAgreementBankUNN, String accountCurrencyMnemoCode) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.validToDate = validToDate;
        this.accountID = accountID;
        this.accountNumber = accountNumber;
        this.accountAgreementNumber = accountAgreementNumber;
        this.accountAgreementBankName = accountAgreementBankName;
        this.accountAgreementBankUNN = accountAgreementBankUNN;
        this.accountCurrencyMnemoCode = accountCurrencyMnemoCode;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getValidToDate() {
        return validToDate;
    }

    public void setValidToDate(Date validToDate) {
        this.validToDate = validToDate;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountCurrencyMnemoCode() {
        return accountCurrencyMnemoCode;
    }

    public void setAccountCurrencyMnemoCode(String accountCurrencyMnemoCode) {
        this.accountCurrencyMnemoCode = accountCurrencyMnemoCode;
    }

    public String getAccountAgreementNumber() {
        return accountAgreementNumber;
    }

    public void setAccountAgreementNumber(String accountAgreementNumber) {
        this.accountAgreementNumber = accountAgreementNumber;
    }

    public String getAccountAgreementBankName() {
        return accountAgreementBankName;
    }

    public void setAccountAgreementBankName(String accountAgreementBankName) {
        this.accountAgreementBankName = accountAgreementBankName;
    }

    public String getAccountAgreementBankUNN() {
        return accountAgreementBankUNN;
    }

    public void setAccountAgreementBankUNN(String accountAgreementBankUNN) {
        this.accountAgreementBankUNN = accountAgreementBankUNN;
    }
}
