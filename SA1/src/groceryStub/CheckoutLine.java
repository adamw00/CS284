package groceryStub;


public class CheckoutLine {
	
	private SLL<ShoppingCart> checkout = new SLL<ShoppingCart>();
	
	public int getSize() {
		return checkout.getSize();
	}
	
	public void addShoppingCart(ShoppingCart cart) {
		checkout.addLast(cart);
	}
	
	public ShoppingCart getNextShoppingCart() {
		if (getSize()==0) {
			throw new IllegalStateException("getNextShoppingCart: line is empty");
		}
		ShoppingCart t = checkout.getAt(0);
		checkout.remove(0);
		return t;
	}
	
	public double processAll() {
		double total = 0;
		for (int x=0; x<=getSize(); x++) {
			total += getNextShoppingCart().checkout();
		}
		return total;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		s.append("[");
		for (int x=0; x<getSize(); x++) {
			if (x+1<getSize()) {
				s.append(checkout.getAt(x)+",\n");
			} else {
				s.append(checkout.getAt(x));
			}
		}
		if(s.length()!=1) {s.deleteCharAt(s.length()-1);}
		s.append("]");
		return s.toString();
	}
}