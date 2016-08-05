package by.pwt.plipenko.payments.model.entities;

import java.util.Date;

public class Agreement extends Entity {

	private String number;
	private Date validFromDate;
	private Date validToDate;
	private Bank bank;
	private Client client;

	public Agreement() {
	}

	public Agreement(int id, String number, Date validFromDate, Date validToDate, Bank bank, Client client) {
		super(id);
		this.number = number;
		this.validFromDate = validFromDate;
		this.validToDate = validToDate;
		this.bank = bank;
		this.client = client;
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

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bank == null) ? 0 : bank.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((validFromDate == null) ? 0 : validFromDate.hashCode());
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
		Agreement other = (Agreement) obj;
		if (bank == null) {
			if (other.bank != null)
				return false;
		} else if (!bank.equals(other.bank))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (validFromDate == null) {
			if (other.validFromDate != null)
				return false;
		} else if (!validFromDate.equals(other.validFromDate))
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
		return "Agreement [id=" + getId() + ", number=" + number + ", validFromDate=" + validFromDate + ", validToDate="
				+ validToDate + ", bank=" + bank + ", client=" + client + "]";
	}

}
