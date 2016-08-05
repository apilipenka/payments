package by.pwt.plipenko.payments.model.entities;

public class Account extends Entity {

	private String number;
	private double amount;
	private Agreement agreement;
	private Currency currency;

	public Account() {
	}

	public Account(int id, String number, double amount, Agreement agreement, Currency currency) {
		super(id);
		this.number = number;
		this.amount = amount;
		this.agreement = agreement;
		this.currency = currency;
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

}
