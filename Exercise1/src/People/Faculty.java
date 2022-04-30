package People;

public class Faculty extends Person{
	
	private String position;

	public Faculty(String name, int age, String address, String position) {
		super(name, age, address);
		this.position = position;
	}
	
	public String getCredentials() {
		return this.getName() +" is a "+ this.position;
	}
	
}
