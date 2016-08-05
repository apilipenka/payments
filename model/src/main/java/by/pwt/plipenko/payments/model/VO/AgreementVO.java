package by.pwt.plipenko.payments.model.VO;

import java.util.Date;

/**
 * Created by apilipenka on 8/5/2016.
 */
public class AgreementVO {
    private int id;
    private String number;
    private Date validFromDate;
    private Date validToDate;
    private int bankID;
    private String bankName;
    private String bankUNN;
    private int userID;
    private String userFirstName;
    private String userLastName;
    private String userPersonanumber;


    public AgreementVO(int id, String number, Date validFromDate, Date validToDate, int bankID, String bankName, String bankUNN, int userID, String userFirstName, String userLastName, String userPersonanumber) {
        this.id = id;
        this.number = number;
        this.validFromDate = validFromDate;
        this.validToDate = validToDate;
        this.bankID = bankID;
        this.bankName = bankName;
        this.bankUNN = bankUNN;
        this.userID = userID;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userPersonanumber = userPersonanumber;
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

    public Date getValidFromDate() {
        return validFromDate;
    }

    public void setValidFromDate(Date validFromDate) {
        this.validFromDate = validFromDate;
    }

    public Date getValidToDate() {
        return validToDate;
    }

    public void setValidToDate(Date validToDate) {
        this.validToDate = validToDate;
    }

    public int getBankID() {
        return bankID;
    }

    public void setBankID(int bankID) {
        this.bankID = bankID;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankUNN() {
        return bankUNN;
    }

    public void setBankUNN(String bankUNN) {
        this.bankUNN = bankUNN;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPersonanumber() {
        return userPersonanumber;
    }

    public void setUserPersonanumber(String userPersonanumber) {
        this.userPersonanumber = userPersonanumber;
    }
}
