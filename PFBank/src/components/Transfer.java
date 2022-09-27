package components;

import java.time.LocalDate;

public class Transfer extends Flow {

	private int accNumber;

	public Transfer(double amount, int targetAccountNumber, int accN, LocalDate ld) {
		super(amount, targetAccountNumber, ld);
		this.accNumber = accN;
	}
	
	
}
