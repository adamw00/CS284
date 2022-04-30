/**
 * CS 284B
 * @author Adam Woo
 * I pledge my honor that I have abided by the Stevens Honor System.
 */

public class Complexity {
	
	public static void method1(int n) {
		for (int x=0; x<n; x++) {
			for (int y=0; y<n; y++) {
				System.out.print("\n" + x + ", " + y);
			}
		}
	}
	
	public static void method2(int n) {
		for (int x=0; x<n; x++) {
			for (int y=0; y<n; y++) {
				for (int z=0; z<n; z++) {
					System.out.print("\n" + x + ", " + y + ", " + z);
				}
			}
		}
	}
	
	public static void method3(int n) {
		for (int x=1; x<n; x*=2) {
			System.out.println(x);
		}
	}
	
	
	
/* Part 4:
 * Iteration	start	end
 * 1			0		31
 * 2			16		31
 * 3			24		31
 * 4			28		31
 * 5			30		31
 * 6			31		31
 * 
 * Iteration	start	end
 * 1			0		63
 * 2			32		63
 * 3			48		63
 * 4			56		63
 * 5			60		63
 * 6			62		63
 * 7			63		63
 * 
 * Part 5:
 * whenever n doubles, iterations increase by 1
 * 
 * Part 6:
 * time complexity: O(logn)
 */
	
	public static void method4(int n) {
		for(int x=0; x<n; x++) {
			for(int y=1; y<n; n*=2) {
				System.out.print("\n" + x + ", " + y);
			}
		}
	}
	
	public static void method5(int n) {
		for(int x=n; x>1; x = (int) Math.sqrt(x)) {
			System.out.println(x);
		}
	}
	
	public static void main(String[] args) {
		
	}
}