package groceryStub;
import basics.SLL.Node;

public class GroceryStore {
	
	private Inventory inventory = new Inventory();
	private CheckoutLine checkout = new CheckoutLine();
	private SLL<ShoppingCart> shoppers = new SLL<ShoppingCart>();
	private double funds = 0;
	
	public double getFunds() {
		//TODO
	}
	
	public void addNewShopper(ShoppingCart s) {
		//TODO
	}

	public void insertInventory(Inventory inventory) {
		//TODO
	}
	
	public double checkoutNext(){
		//TODO
	}
	
	public double checkoutAll() {
		//TODO
	}
	
	public void moveToQueue(int i) {
		//TODO
	}

	public void takeFromInventory(int index, String name, int number) {
		//TODO
	}
	
	public void putBack(int index, int number) {
		//TODO
	}
	
	public String toString() {
		//TODO
	}
}
