package by.pwt.plipenko.payments.model.VO;

import java.util.Date;

/**
 * Created by apilipenka on 8/5/2016.
 */
public class ExchangeRateVO {
    private int id;
    private Date rateDate;
    private float rate;
    private int currencyID;
    private String currencyMnemoCode;
    private String currencyCode;

    public ExchangeRateVO(int id, Date rateDate, float rate, int currencyID, String currencyMnemoCode, String currencyCode) {
        this.id = id;
        this.rateDate = rateDate;
        this.rate = rate;
        this.currencyID = currencyID;
        this.currencyMnemoCode = currencyMnemoCode;
        this.currencyCode = currencyCode;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRateDate() {
        return rateDate;
    }

    public void setRateDate(Date rateDate) {
        this.rateDate = rateDate;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(int currencyID) {
        this.currencyID = currencyID;
    }

    public String getCurrencyMnemoCode() {
        return currencyMnemoCode;
    }

    public void setCurrencyMnemoCode(String currencyMnemoCode) {
        this.currencyMnemoCode = currencyMnemoCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
