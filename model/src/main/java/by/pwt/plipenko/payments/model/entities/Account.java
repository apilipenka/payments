package by.pwt.plipenko.payments.model.entities;

import by.pwt.plipenko.payments.model.VO.AccountVO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Account extends Entity {

    private String number;
    private double amount;
    private Agreement agreement;
    private Currency currency;

    private Set<Card> cards;

    public Account() {
        super();
        cards = new HashSet<Card>();
    }

    public Account(int id, String number, double amount, Agreement agreement, Currency currency) {
        super(id);
        this.number = number;
        this.amount = amount;
        this.agreement = agreement;
        this.currency = currency;

        cards = new HashSet<Card>();


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

    public Agreement getAgreement() {
        return agreement;
    }

    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
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
        long temp;
        temp = Double.doubleToLongBits(amount);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((agreement == null) ? 0 : agreement.hashCode());
        result = prime * result + ((currency == null) ? 0 : currency.hashCode());
        result = prime * result + ((number == null) ? 0 : number.hashCode());
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
        Account other = (Account) obj;
        if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
            return false;
        if (agreement == null) {
            if (other.agreement != null)
                return false;
        } else if (!agreement.equals(other.agreement))
            return false;
        if (currency == null) {
            if (other.currency != null)
                return false;
        } else if (!currency.equals(other.currency))
            return false;
        if (number == null) {
            if (other.number != null)
                return false;
        } else if (!number.equals(other.number))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Account [id=" + getId() + ", number=" + number + ", amount=" + amount + ", agreement=" + agreement
                + ", currency=" + currency + "]";
    }

    public void addMoney(double amount) {
        this.amount += amount;
    }

    public void getMoney(double amount) {
        this.amount -= amount;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public void addCards(Set<Card> cards) {
        cards.addAll(cards);
    }


    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> findAccounts(Card card) {
        List<Card> finded = new ArrayList<Card>();
        for (Card icard : cards) {
            if ((card.getNumber() != null && card.getNumber().equals(icard.getNumber())))
                finded.add(icard);
        }
        return finded;
    }


    public AccountVO createAccountVO() {

        AccountVO accountVO = new AccountVO();

        accountVO.setId(getId());
        accountVO.setNumber(number);
        accountVO.setAmount(amount);

        accountVO.setAgreementID(agreement.getId());
        accountVO.setAgreementNumber(agreement.getNumber());
        accountVO.setAgreementBankid(agreement.getBank().getId());
        accountVO.setAgreementBankName(agreement.getBank().getName());
        accountVO.setAgreementBankUNN(agreement.getBank().getUNN());
        accountVO.setCurrencyID(currency.getId());
        accountVO.setCurrencyMnemocode(currency.getMnemoCode());
        return  accountVO;


    }
}
