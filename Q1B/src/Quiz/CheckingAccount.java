package Quiz;

public class CheckingAccount extends BankAccount {
    private double transactions;
    public CheckingAccount(double initialBalance) {
        super(initialBalance);
        this.transactions = 0;
    }
    public void withdraw(double amount) {
        if (this.balance - amount < 0) {
            throw new IllegalArgumentException("balance below 0");
        }
        else {
            this.balance -= amount;
        }
        this.transactions++;
    }
    public void deposit(double amount) {
        this.balance += amount;
        this.transactions++;
    }
    public void transfer(double amount, BankAccount destination) {
    	 if (this.balance - amount < 0) {
             throw new IllegalArgumentException("balance below 0");
         }
         else {
        	 destination.deposit(amount);
        	 this.withdraw(amount);
         }
        this.transactions++;
    }
}