package rolodex;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class RolodexTest {

	@Test
	void containsTest() {
		Rolodex r = new Rolodex();
		r.addCard("Joe", "1");
		assertTrue(r.contains("Joe"));
		assertFalse(r.contains("Joane"));
	}
	
	@Test
	void sizeTest() {
		Rolodex r = new Rolodex();
		r.addCard("Joe", "1");
		assertEquals(r.size(), 1);
	}
	
	@Test
	void lookupTest() {
		Rolodex r = new Rolodex();
		ArrayList<String> k = new ArrayList<String>();
		k.add("1");
		k.add("42");
		r.addCard("Joe", "1");
		r.addCard("Joe", "42");
		assertEquals(r.lookup("Joe"), k);
	}
	
	@Test
	void addCardTest() {
		Rolodex r = new Rolodex();
		r.addCard("Joe", "1");
		assertTrue(r.contains("Joe"));
	}
	
	@Test
	void removeCardTest() {
		Rolodex r = new Rolodex();
		r.addCard("Joe", "1");
		assertTrue(r.contains("Joe"));
		r.removeCard("Joe", "1");
		assertFalse(r.contains("Joe"));
	}
	
	@Test
	void removeAllCardsTest() {
		Rolodex r = new Rolodex();
		r.addCard("Joe", "1");
		r.addCard("Joe", "3");
		r.addCard("Joe", "5");
		assertTrue(r.contains("Joe"));
		r.removeAllCards("Joe");
		assertFalse(r.contains("Joe"));
	}
	
	@Test
	void initializeCursorTest() {
		Rolodex r = new Rolodex();
		r.initializeCursor();
		assertEquals(r.currentEntryToString(), "Separator A");
	}
	
	@Test
	void nextSeparatorTest() {
		Rolodex r = new Rolodex();
		r.initializeCursor();
		r.addCard("Adam", "1");
		r.addCard("Alex", "3");
		r.addCard("Allison", "5");
		assertEquals(r.currentEntryToString(), "Separator A");
		r.nextSeparator();
		assertEquals(r.currentEntryToString(), "Separator B");
	}
	
	@Test
	void nextEntryTest() {
		Rolodex r = new Rolodex();
		r.initializeCursor();
		r.addCard("Adam", "1");
		r.addCard("Alex", "3");
		r.addCard("Allison", "5");
		assertEquals(r.currentEntryToString(), "Separator A");
		r.nextEntry();
		assertEquals(r.currentEntryToString(), "Name: Adam, Cell: 1");
	}
	
	@Test
	void testExceptions() {
		Rolodex r = new Rolodex();
		r.addCard("Billy", "3");
		assertThrows(IllegalArgumentException.class, () -> r.lookup("Joe"));
		assertThrows(IllegalArgumentException.class, () -> r.addCard("Billy", "3"));
		assertThrows(IllegalArgumentException.class, () -> r.removeCard("Joe","2"));
		assertThrows(IllegalArgumentException.class, () -> r.removeCard("Billy", "77"));
		assertThrows(IllegalArgumentException.class, () -> r.removeAllCards("Joe"));
	}

}
