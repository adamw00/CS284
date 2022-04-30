package groceryStub;

public class GroceryStore {
	
	private Inventory inventory = new Inventory();
	private CheckoutLine checkout = new CheckoutLine();
	private SLL<ShoppingCart> shoppers = new SLL<ShoppingCart>();
	private double funds = 0;
	
	public double getFunds() {
		return funds;
	}
	
	public void addNewShopper(ShoppingCart s) {
		shoppers.addFirst(s);
	}

	public void insertInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public double checkoutNext(){
		if (checkout.getSize()==0) {
			throw new IllegalStateException("checkoutNext: line is empty");
		}
		double cost = checkout.getNextShoppingCart().checkout();
		funds += cost;
		return cost;
	}
	
	public double checkoutAll() {
		double total = 0;
		for (int x=0; x<checkout.getSize(); x++) {
			total += checkoutNext();
		}
		return total;
	}
	
	public void moveToQueue(int i) {
		if (i>=shoppers.getSize() || i<0) {
			throw new IllegalArgumentException("moveToQueue: index out of bounds");
		}
		checkout.addShoppingCart(shoppers.getAt(i));
		shoppers.remove(i);
	}

	public void takeFromInventory(int index, String name, int number) {
		if (!inventory.isAvailable(name)) {
			throw new IllegalArgumentException("takeFromInventory: item not in inventory");
		}
		if (index>=shoppers.getSize() || index<0) {
			throw new IllegalArgumentException("takeFromInventory: index of shopper out of bounds");
		}
		if (number<=0) {
			throw new IllegalArgumentException("takeFromInventory: number must be > 0");
		}
		
		int i = 0;
		for (int x=0; x<inventory.getSize(); x++) {
			if (inventory.getAt(x).getName().equals(name)) {
				i = x;
			}
		}
		if (inventory.getAt(i).getCount()<number) {
			shoppers.getAt(index).putItem(inventory.getAt(i), inventory.getAt(i).getCount());
		} else {
			shoppers.getAt(index).putItem(inventory.getAt(i), number);
		}
		inventory.takeItems(name, number);
	}
	
	public void putBack(int index, int number) {
		if (index>=shoppers.getSize() || index<0) {
			throw new IllegalArgumentException("putBack: index of shopper out of bounds");
		}
		if (shoppers.getAt(index).getSize() == 0) {
			throw new IllegalStateException("putBack: cart is empty");
		}
		if (!inventory.isAvailable(shoppers.getAt(index).getItem(0).getName())) {
			throw new IllegalStateException("putBack: item not in inventory");
		}
		
		inventory.addItems(shoppers.getAt(index).getItem(0).getName(), number);
		shoppers.getAt(index).getItem(number);
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		s.append("Inventory: \n");
		s.append(inventory+"\n\n");
		s.append("Shoppers: \n");
		s.append("[");
		for (int x=0; x<shoppers.getSize(); x++) {
			if (x+1<shoppers.getSize()) {
				s.append(shoppers.getAt(x) + ",\n");
			} else {
				s.append(shoppers.getAt(x));
			}
		}
		s.append("]\n\n");
		
		s.append("Checkout Line: \n");
		s.append(checkout+"\n");
		
		return s.toString();
	}
}
