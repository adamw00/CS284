package People;

public class NonTenured extends Faculty{
	
	private int yearsTilTenure;

	public NonTenured(String name, int age, String address, String position, int yearsTilTenure) {
		super(name, age, address, position);
		this.yearsTilTenure = yearsTilTenure;
	}
	
	public String getCredentials() {
		return this.getName()+"'s Tenure starts in "+ this.yearsTilTenure + " years!";
	}
}
