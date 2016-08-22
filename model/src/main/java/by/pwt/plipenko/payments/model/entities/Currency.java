package by.pwt.plipenko.payments.model.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Currency extends Entity {

    private static final long serialVersionUID = -6049197255924255456L;
    private String mnemoCode;
    private String code;
    private String name;


    private Map<Date, ExchangeRate> rates;

    public Currency() {
        super();
        rates = new HashMap<Date, ExchangeRate>();
    }

    public Currency(int id, String mnemoCode, String code, String name) {
        super(id);
        this.mnemoCode = mnemoCode;
        this.code = code;
        this.name = name;

        rates = new HashMap<Date, ExchangeRate>();

    }

    public String getMnemoCode() {
        return mnemoCode;
    }

    public void setMnemoCode(String mnemoCode) {
        this.mnemoCode = mnemoCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Date, ExchangeRate> getRates() {
        return rates;
    }

    public void setRates(Map<Date, ExchangeRate> rates) {
        this.rates = rates;
    }

    public ExchangeRate getExchangeRate(Date date) {
        if (rates != null)
            return rates.get(date);
        return null;
    }

    public void addExchangeRate(Date date, ExchangeRate exchangeRate) {
        rates.put(date, exchangeRate);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((mnemoCode == null) ? 0 : mnemoCode.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Currency other = (Currency) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!mnemoCode.equals(other.mnemoCode))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "currency [id=" + getId() + ", mnemoCode=" + mnemoCode + ", code=" + code + ", name=" + name + ", rates=" + rates + "]";
    }

}
