package by.pwt.plipenko.payments.model.entities;

import java.util.Date;

public class Card extends Entity {

    private String number;
    private String name;
    private Date validToDate;
    private Account account;

    public Card() {
        super();
    }

    public Card(int id, String number, String name, Date validToDate, Account account) {
        super(id);
        this.number = number;
        this.name = name;
        this.validToDate = validToDate;
        this.account = account;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((account == null) ? 0 : account.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        result = prime * result + ((validToDate == null) ? 0 : validToDate.hashCode());
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
        Card other = (Card) obj;
        if (account == null) {
            if (other.account != null)
                return false;
        } else if (!account.equals(other.account))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (number == null) {
            if (other.number != null)
                return false;
        } else if (!number.equals(other.number))
            return false;
        if (validToDate == null) {
            if (other.validToDate != null)
                return false;
        } else if (!validToDate.equals(other.validToDate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Card [id=" + getId() + ", number=" + number + ", name=" + name + ", validToDate=" + validToDate
                + ", account=" + account + "]";
    }

}
