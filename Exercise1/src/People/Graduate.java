package People;

public class Graduate extends Student{

	private String major;
	
	public Graduate(String name, int age, String address, int cwid, String major) {
		super(name, age, address, cwid);
		this.major = major;
	}
	
	public String getCredentials() {
		return this.getName()+"'s major is "+this.major;
	}
}
