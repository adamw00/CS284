package People;

public class Student extends Person{
	
	private int cwid;

	public Student(String name, int age, String address, int cwid) {
		super(name, age, address);
		this.cwid = cwid;
	}

	public double getCwid() {
		return cwid;
	}

	public void setCwid(int cwid) {
		this.cwid = cwid;
	}
	
	public String getCredentials() {
		return this.getName()+"'s CWID is "+ this.cwid;
	}
	
}
