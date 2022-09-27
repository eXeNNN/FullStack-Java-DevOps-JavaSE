// 1.2.2 Creation of the CurrentAccount and SavingsAccount

package components;

public class CurrentAccount extends Account {

	public CurrentAccount(String lab, Client cli) {
		super(lab, cli);
		balance = 0;
	}
}
