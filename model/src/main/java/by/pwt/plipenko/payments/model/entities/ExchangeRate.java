package by.pwt.plipenko.payments.model.entities;

import java.util.Date;

public class ExchangeRate extends Entity {

    private Date rateDate;
    private float rate;
    private Currency currency;

    public ExchangeRate() {
    }

    public ExchangeRate(int id, Date rateDate, float rate, Currency currency) {
        super(id);
        this.rateDate = rateDate;
        this.rate = rate;
        this.currency = currency;
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((currency == null) ? 0 : currency.hashCode());
        result = prime * result + Float.floatToIntBits(rate);
        result = prime * result + ((rateDate == null) ? 0 : rateDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ExchangeRate other = (ExchangeRate) obj;
        if (currency == null) {
            if (other.currency != null)
                return false;
        } else if (!currency.equals(other.currency))
            return false;
        if (Float.floatToIntBits(rate) != Float.floatToIntBits(other.rate))
            return false;
        if (rateDate == null) {
            if (other.rateDate != null)
                return false;
        } else if (!rateDate.equals(other.rateDate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ExchangeRate [id=" + getId() + ", rateDate=" + rateDate + ", rate=" + rate + ", currency=" + currency
                + "]";
    }

}
