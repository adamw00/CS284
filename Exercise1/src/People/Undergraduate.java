package People;

public class Undergraduate extends Student{

	private double gpa;
	
	public Undergraduate(String name, int age, String address, int cwid, double gpa) {
		super(name, age, address, cwid);
		this.gpa = gpa;
	}
	
	
	
	public double getGpa() {
		return gpa;
	}


	public void setGpa(double gpa) {
		this.gpa = gpa;
	}


	public String getCredentials() {
		return this.getName()+"'s GPA is "+ this.gpa;
	}

}
