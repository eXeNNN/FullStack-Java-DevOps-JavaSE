// 1.2.2 Creation of the CurrentAccount and SavingsAccount

package components;

public class SavingsAccount extends Account {

	public SavingsAccount(String lab, Client cli) {
		super(lab, cli);
		balance = 0;
	}
	
}
