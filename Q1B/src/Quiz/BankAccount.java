/*
 * Names: Adam Woo & Jack Chen
 * I pledge my honor that I have abided by the Stevens Honor System.
 */

package Quiz;

public class BankAccount {
	protected double balance;

	public BankAccount() {
		this.balance = 0;
	}
	
	public BankAccount(double initialBalance) {
		this.balance = initialBalance;
	}
	
	public void deposit(double amount) {
		this.balance += amount;
	}
	
	public void withdraw(double amount) {
		if (this.balance - amount <0) {
			throw new IllegalArgumentException("balance below 0");
		}
		else {
			this.balance -= amount;
		}
	}

	public double getBalance() {
		return balance;
	}

	public void transfer(double amount, BankAccount destination) {
		if (this.balance - amount <0) {
			throw new IllegalArgumentException("balance below 0");
		}
		else {
			this.withdraw(amount);
			destination.deposit(amount);
		}
	}
}
