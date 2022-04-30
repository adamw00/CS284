package groceryStub;

public class ShoppingCart {
	
	private SLL<Item> stack = new SLL<Item>();
	
	public int getSize() {
		return stack.getSize();
	}
	
	public Item getItem(int number) {
		if (stack.getSize() == 0) {
			throw new IllegalStateException("getItem: cart is empty");
		}
		if (stack.getAt(getSize()-1).getCount() < number) {
			throw new IllegalArgumentException("getItem: number larger than amount in cart");
		} else {
			for (int x=0; x<number; x++) {
				stack.getAt(getSize()-1).decrementCount();
			}
			Item p = new Item(stack.getAt(getSize()-1).getName(), stack.getAt(getSize()-1).getPrice());
			for (int i=0; i<number; i++) {
				p.incrementCount();
			}
			return p;
		}
	}
	
	public void putItem(Item item, int number) {
		if (number<=0) {
			throw new IllegalArgumentException("putItem: number must be > 0");
		}
		Item t = new Item(item.getName(),item.getPrice());
		for (int x=0; x<number; x++) {
			t.incrementCount();
		}
		stack.addLast(t);
	}
	
	public double checkout() {
		double sum = 0;
		for (int x=getSize()-1; x>=0; x--) {
			sum += (stack.getAt(x).getPrice() * stack.getAt(x).getCount());
			stack.remove(x);
		}
		
		return (double) Math.round(sum*100) / 100;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		s.append("[");
		for (int x=getSize()-1; x>=0; x--) {
			s.append("["+ stack.getAt(x).getName() +","+ stack.getAt(x).getPrice() +","+ stack.getAt(x).getCount() +"];");
		}
		
		if(s.length()!=1) {s.deleteCharAt(s.length()-1);}
		s.append("]");
		return s.toString();
	}
}
