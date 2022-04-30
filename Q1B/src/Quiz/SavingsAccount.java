package Quiz;

public class SavingsAccount extends BankAccount{
	private double InterestRate;
	public SavingsAccount(double rate) {
		super();
		this.InterestRate = rate;
	}
	
	private void addInterest() {
		this.deposit(this.balance*this.InterestRate);
	}
}
