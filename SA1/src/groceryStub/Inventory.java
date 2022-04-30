package groceryStub;

public class Inventory {

	private SLL<Item> inventory = new SLL<Item>();
	
	public int getSize() {
		return inventory.getSize();
	}
	
	public Item getAt(int index) {
		if (index>=getSize() || index<0) {
			throw new IllegalArgumentException("getAt: index out of bounds");
		}
		return inventory.getAt(index);
	}
	
	public double getPrice(String name) {
		for (int x=0; x<getSize(); x++) {
			if (getAt(x).getName().equals(name)) {
				return getAt(x).getPrice();
			}
		}
		throw new IllegalArgumentException("getPrice: item not in inventory");
	}
	
	public void addItems(String name, int number) {
		for (int x=0; x<getSize(); x++) {
			if (getAt(x).getName().equals(name)) {
				for(int i=0; i<number; i++) {
					getAt(x).incrementCount();
				}
				return;
			}
		}
		throw new IllegalArgumentException("addItems: item not in inventory");
	}
	
	public void addNewItem(String name, double price, int count) {
		if (price<0) {
			throw new IllegalArgumentException("addNewItem: price must be >=0");
		}
		if (count<0) {
			throw new IllegalArgumentException("addNewItem: count must be >= 0");
		}
		if (isAvailable(name)) {
			addItems(name, count);
		}
		Item p = new Item(name, price);
		for(int i=0; i<count; i++) {
			p.incrementCount();
		}
		inventory.addLast(p);
	}
	
	public boolean isAvailable(String name) {
		for (int x=0; x<getSize(); x++) {
			if (getAt(x).getName().equals(name)) {
				if (getAt(x).getCount() > 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void takeItems(String name, int number) {
		for (int x=0; x<getSize(); x++) {
			if (getAt(x).getName().equals(name)) {
				if (number > getAt(x).getCount()) {
					throw new IllegalArgumentException("takeItems: not enough items in inventory");
				}
				for (int i=0; i<number; i++) {
					getAt(x).decrementCount();
				}
				if (getAt(x).getCount()<0) {
					inventory.remove(x);
				}
				return;
			}
		}
		throw new IllegalArgumentException("takeItems: name does not exist");
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		s.append("[");
		for (int x=0; x<getSize(); x++) {
			s.append("["+ getAt(x).getName() +","+ getAt(x).getPrice() +","+ getAt(x).getCount() +"];");
		}
		if(s.length()!=1) {s.deleteCharAt(s.length()-1);}
		s.append("]");
		return s.toString();
	}
}
