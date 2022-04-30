package People;

/**
 * This is for creating a person.
 * @author adamw
 *
 */
public class Person {
	//Data Fields
	private String name;
	private int age;
	private String address;
	
	//Constructor
	public Person(String name, int age, String address) {
		this.name = name;
		this.age = age;
		this.address = address;
	}
	public Person() {
		this.name = "";
		this.age = 0;
		this.address = "";
	}
	
	//Methods
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	//Main Mehtod
	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setName("adam");
		p1.setAddress("155 somewhere road");
		p1.setAge(18);
		Undergraduate s1 = new Undergraduate("ryan", 13, "123 your moms street", 12345, 2.4);
		
		System.out.println(p1.getName());
		System.out.println(p1.getAge());
		System.out.println(p1.getAddress());
		
		System.out.println(s1.getName());
		System.out.println(s1.getAge());
		System.out.println(s1.getAddress());
		System.out.println(s1.getCwid());
		System.out.println(s1.getCredentials());
	}


}
