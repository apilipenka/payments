package by.pwt.plipenko.payments.model.entities;

public class Bank extends Entity {
	private String UNN;
	private String name;

	public Bank() {
		super();
	}

	public Bank(int id, String name, String UNN) {
		super(id);
		this.name = name;
		this.UNN = UNN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((UNN == null) ? 0 : UNN.hashCode());
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
		Bank other = (Bank) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;

		if (UNN == null) {
			if (other.UNN != null)
				return false;
		} else if (!UNN.equals(other.UNN))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "Bank [id=" + getId() + ", name=" + name + ", UNN=" + UNN + "]";
	}

	public String getUNN() {
		return UNN;
	}

	public void setUNN(String UNN) {
		this.UNN = UNN;
	}
}
