package People;

public class Tenured extends Faculty{

	private int tenureYearStart;

	public Tenured(String name, int age, String address, String position, int tenureYearStart) {
		super(name, age, address, position);
		this.tenureYearStart = tenureYearStart;
	}
	
	public String getCredentials() {
		return this.getName()+"'s tenure started on "+this.tenureYearStart;
	}
}
