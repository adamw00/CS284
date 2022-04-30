package groceryStub;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LibraryTest {

	@Test
	void SLLTest() {
		SLL<Integer> s = new SLL<Integer>();
		SLL<Integer> r = new SLL<Integer>();
		s.addFirst(5);
		s.addLast(6);
		s.addLast(7);
		s.removeItem(6);
		r.addFirst(5);
		r.addLast(7);
		assertEquals(s.toString(), r.toString());
	}

	@Test
	void ItemTest() {
		Item i = new Item("popcorn",2.98);
		assertEquals(i.getName(),"popcorn");
		assertEquals(i.getPrice(),2.98);
		assertEquals(i.getCount(), 0);
		i.decrementCount();
		assertEquals(i.getCount(), 0);
		i.incrementCount();
		i.incrementCount();
		i.incrementCount();
		assertEquals(i.getCount(), 3);
		i.decrementCount();
		assertEquals(i.getCount(), 2);
	}
	
	@Test
	void InventoryTest() {
		Inventory v = new Inventory();
		assertEquals(v.getSize(), 0);
		v.addNewItem("popcorn", 9.99, 90);
		assertEquals(v.getSize(), 1);
		assertEquals(v.getAt(0).getName(), "popcorn");
		assertThrows(IllegalArgumentException.class, () -> v.getAt(5));
		assertEquals(v.getPrice("popcorn"), 9.99);
		v.addItems("popcorn", 10);
		assertEquals(v.getAt(0).getCount(), 100);
		assertFalse(v.isAvailable("butter"));
		assertTrue(v.isAvailable("popcorn"));
		v.takeItems("popcorn", 20);
		assertEquals(v.getAt(0).getCount(), 80);
		v.addNewItem("butter", 4.99, 18);
		v.addNewItem("steak", 21.99, 45);
		assertEquals(v.toString(),"[[popcorn,9.99,80];[butter,4.99,18];[steak,21.99,45]]");
		assertThrows(IllegalArgumentException.class, () -> v.getPrice("poopy"));
		assertThrows(IllegalArgumentException.class, () -> v.addItems("poopy",9));
		assertThrows(IllegalArgumentException.class, () -> v.addNewItem("poopy",-1,9));
		assertThrows(IllegalArgumentException.class, () -> v.addNewItem("poopy",9,-1));
		assertThrows(IllegalArgumentException.class, () -> v.takeItems("poopy",5));
	}
	
	@Test
	void ShoppingCartTest() {
		ShoppingCart c = new ShoppingCart();
		assertEquals(c.getSize(), 0);
		c.putItem(new Item("popcorn",9.99), 10);
		c.getItem(5);
		assertEquals(c.getSize(), 1);
		assertThrows(IllegalArgumentException.class, () -> c.getItem(6));
		c.getItem(5);
		c.putItem(new Item("butter",4.99), 2);
		c.putItem(new Item("popcorn", 9.99), 1);
		assertEquals(c.checkout(),9.99+(4.99*2));
		c.putItem(new Item("butter",4.99), 2);
		c.putItem(new Item("popcorn", 9.99), 1);
		assertEquals(c.toString(), "[[popcorn,9.99,1];[butter,4.99,2]]");
		c.checkout();
		assertThrows(IllegalStateException.class, () -> c.getItem(5));
		assertThrows(IllegalArgumentException.class, () -> c.putItem(new Item("popcorn",9.99), -10));
	}
	
	@Test
	void CheckoutLineTest() {
		CheckoutLine l = new CheckoutLine();
		ShoppingCart j = new ShoppingCart();
		ShoppingCart k = new ShoppingCart();
		j.putItem(new Item("popcorn",9.99), 10);
		j.putItem(new Item("butter",4.99), 5);
		k.putItem(new Item("butter",4.99), 2);
		k.putItem(new Item("steak",21.99), 5);
		assertEquals(l.getSize(),0);
		l.addShoppingCart(j);
		assertEquals(l.getSize(),1);
		assertEquals(l.getNextShoppingCart(), j);
		l.addShoppingCart(j);
		l.addShoppingCart(k);
		assertEquals(l.processAll(), 99.9+ (4.99*7)+(21.99*5));
		assertThrows(IllegalStateException.class, () -> l.getNextShoppingCart());
	}
	
	@Test
	void GroceryStoreTest() {
		GroceryStore g = new GroceryStore();
		assertEquals(g.getFunds(), 0);
		ShoppingCart j = new ShoppingCart();
		j.putItem(new Item("butter",4.99), 5);
		j.putItem(new Item("popcorn",9.99), 10);
		g.addNewShopper(j);
		Inventory v = new Inventory();
		v.addNewItem("popcorn", 9.99, 90);
		g.insertInventory(v);
		g.moveToQueue(0);
		assertEquals(g.checkoutNext(), 124.85);
		assertEquals(g.getFunds(), 124.85);
		assertEquals(g.checkoutAll(), 0);
		assertEquals(g.getFunds(), 124.85);
		
		GroceryStore s = new GroceryStore();
		ShoppingCart k = new ShoppingCart();
		s.addNewShopper(k);
		Inventory w = new Inventory();
		w.addNewItem("popcorn", 9.99, 90);
		w.addNewItem("butter", 3.99, 100);
		s.insertInventory(w);
		s.takeFromInventory(0, "popcorn", 5);
		s.moveToQueue(0);
		assertEquals(s.checkoutNext(), 49.95);
		assertEquals(s.getFunds(), 49.95);
		s.addNewShopper(k);
		s.takeFromInventory(0, "popcorn", 10);
		s.putBack(0, 9);
		s.moveToQueue(0);
		assertEquals(s.checkoutNext(), 9.99);
		assertThrows(IllegalStateException.class, () -> s.checkoutNext());
		assertThrows(IllegalArgumentException.class, () -> s.moveToQueue(-1));
		assertThrows(IllegalArgumentException.class, () -> s.moveToQueue(2));
		assertThrows(IllegalArgumentException.class, () -> s.takeFromInventory(0,"popcorn",-2));
		assertThrows(IllegalArgumentException.class, () -> s.takeFromInventory(0,"corn",0));
		assertThrows(IllegalArgumentException.class, () -> s.takeFromInventory(10,"popcorn",0));
		assertThrows(IllegalArgumentException.class, () -> s.putBack(0,9));
		k.putItem(new Item("steak",21.99), 5);
		s.addNewShopper(k);
		assertThrows(IllegalStateException.class, () -> s.putBack(0,2));
		s.moveToQueue(0);
		s.addNewShopper(k);
		assertThrows(IllegalStateException.class, () -> s.putBack(0,2));
	}
}
