/**
 * 
 * @author Adam Woo   CS-284B
 * I pledge my honor that I have abided by the Stevens Honor System.
 * 
 */

public class BinaryNumber {
	//Data Fields
	private int data[];
	private int length;
			
	
	//Constructors
	public BinaryNumber(int length) {
		if (length < 0) {
			this.length = 0;
			this.data = new int[0];
			System.out.println("negative length given... Binary Number set to 0.");
		}
		else {
			this.length = length;
			this.data = new int[length];
		}
	}
	
	public BinaryNumber(String str) {
		if (! str.matches("[0-9]+")) {
			this.data = new int[0];
			this.length = 0;
			System.out.println("input must only contain numbers.");
			System.out.println("Binary Number set to 0.");
		}
		else {
			this.data = new int[str.length()];
			
			for(int i=0; i<str.length(); i++) {
				this.data[i] = Character.getNumericValue(str.charAt(i));
			}
			this.length = str.length();	
		}
	}


	//Methods
	public int getLength() {
		return this.length;
	}
	
	
	public int getDigit(int index) {
		if (index >= this.length) {
			System.out.println("Index out of bounds");
			return -1;
		}
		else{
			return this.data[index];
		}
	}
	
	
	public int[] getInnerArray() {
		return this.data;
	}
	
	
	public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) {
		BinaryNumber n = new BinaryNumber(bn1.getLength());
		if (bn1.getLength() == bn2.getLength()) {
			for (int i=0; i<bn1.getLength(); i++) {
				if(bn1.getDigit(i)==1 || bn2.getDigit(i)==1) {
					n.data[i] = 1;
				}
			}
			return n.getInnerArray();
		}
		else {
			System.out.println("not same length");
			return null;
		}
	}
	
	
	public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) {
		BinaryNumber n = new BinaryNumber(bn1.getLength());
		if (bn1.getLength() == bn2.getLength()) {
			for (int i=0; i<bn1.getLength(); i++) {
				if(bn1.getDigit(i)==1 && bn2.getDigit(i)==1) {
					n.data[i] = 1;
				}
			}
			return n.getInnerArray();
		}
		else {
			System.out.println("not same length");
			return null;
		}
	}
	
	
	public void bitShift(int direction, int amount) {
		if (direction == 1) {
			int[] f = new int[this.length - amount];
			for (int x=0; x<f.length; x++) {
				f[x] = this.data[x];
			}
			this.data = f;
			this.length = f.length;
			return;
		}
		else if (direction == -1) {
			int[] f = new int[this.length + amount];
			for (int x=0; x<this.length; x++) {
				f[x] = this.data[x];
			}
			for (int y=this.length; y<f.length; y++) {
				f[y] = 0;
			}
			this.data = f;
			this.length = f.length;
			return;
		}
		else {
			return;
		}
	}
	
	
	public void add(BinaryNumber aBinaryNumber) {
		if (aBinaryNumber.getLength() > this.length) {
			this.prepend(aBinaryNumber.getLength() - this.length);
		}
		else if (aBinaryNumber.getLength() < this.length) {
			aBinaryNumber.prepend(this.length-aBinaryNumber.getLength());
		}
		BinaryNumber n = new BinaryNumber(this.length);
		int carryOver = 0;
		for (int x=n.getLength()-1; x>=0; x--) {
			int temp = 0;
			temp += aBinaryNumber.getDigit(x) + this.data[x] + carryOver;
			carryOver=0;
			if (temp == 3) {
				carryOver+=1;
				temp = 1;
				n.data[x] = temp;
			}
			else if (temp == 2){
				carryOver+=1;
				temp = 0;
				n.data[x] = temp;
			}
			else if (temp == 1) {
				n.data[x] = temp;
			}
			else {
				n.data[x] = 0;
			}
		}
		if (carryOver == 1) {
			BinaryNumber t = new BinaryNumber(n.length+1);
			t.data[0] = 1;
			for (int y = 1; y<t.length; y++) {
				
				t.data[y] = n.data[y-1];
			}
			n.data = t.data;
			n.length = t.length;
		}
		this.data = n.data;
		this.length = n.length;
		return;
	}
	
	
	public void prepend(int amount) {
		int[] poopy = new int[this.length + amount];
		int j = 0;
		for (int k=0; k<amount; k++) {
			poopy[k] = 0;
		}
		for (int i=amount; i<poopy.length; i++) {
			poopy[i] = this.data[j];
			j++;
		}
		this.data = poopy;
		this.length = poopy.length;
		return;
	}
	
	
	public String toString() {
		String f = "\n";
		for (int i=0; i<this.length; i++) {
			f+=this.data[i];
		}
		return f;
	}
	
	
	public int toDecimal() {
		int sum = 0;
		int k = 0;
		for (int i = data.length-1; i>=0; i--) {
			sum += data[i] * Math.pow(2, k);
			k++;
		}
		return sum;
	}
	
	
	//Main method for testing
	public static void main(String[] args) {
		BinaryNumber q = new BinaryNumber(-1);
		BinaryNumber p = new BinaryNumber(4);
		System.out.println(p);
		BinaryNumber o = new BinaryNumber("1101");
		System.out.println(o);
		
		System.out.println(p.getLength());
		System.out.println(o.getLength());
		
		System.out.println(p.getDigit(0));
		System.out.println(o.getDigit(2));
		System.out.println(p.getDigit(8));
		
		int[] pp = o.getInnerArray();
		for (int m:pp) {
			System.out.print(m);
		}
		System.out.println();
		
		int[] k = BinaryNumber.bwor(new BinaryNumber("1010"), new BinaryNumber("1100"));
		for (int m:k) {
			System.out.print(m);
		}
		System.out.println();
		
		int[] i = BinaryNumber.bwand(new BinaryNumber("1010"), new BinaryNumber("1100"));
		for (int m:i) {
			System.out.print(m);
		}
		System.out.println();
		
		//adds 2 zeroes
		o.bitShift(-1, 2);
		System.out.println(o);
		
		//takes away the 2 zeroes
		o.bitShift(1, 2);
		System.out.println(o);
		
		p.add(new BinaryNumber("1"));
		System.out.println(p);
		
		
		System.out.println(o.toDecimal());
		System.out.println(o.getLength());
	}
}
