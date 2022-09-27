// 1.1.2 Creation of the account class

package components;

public abstract class Account {

	protected String label;
	protected double balance;
	protected int accNumber;
	protected Client client;
	protected static int count = 0;
	
	Account(String lab, Client cli) {
		label = lab;
		client = cli;
		setAccNumber(++count);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(Flow f) {
		String p = f.getClass().getName();
		if (f.getClass().getName() == "components.Credit") {
			this.balance += f.getAmount();
		} else if (f.getClass().getName() == "components.Debit") {
			this.balance -= f.getAmount();
		} else {
			if (f.getTargetAccountNumber() == this.accNumber) {
				this.balance += f.getAmount();
			} else {
				this.balance -= f.getAmount();
			}
		}
	}

	public int getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(int accNumber) {
		this.accNumber = accNumber;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Account [label=" + label + ", balance=" + balance + ", accNumber=" + accNumber + ", client=" + client
				+ "]";
	}
}
