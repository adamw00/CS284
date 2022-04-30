package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TreapTest {
	private Treap<Integer> testTree = new Treap<Integer>();
	private Treap<Integer> testTree2 = new Treap<Integer>();
	
	
	
	@Test
	void addTest() {
		testTree.add(4,19);
		testTree.add(2,31);
		testTree.add(6,70);
		testTree.add(1,84);
		testTree.add(3,12);
		testTree.add(5,83);
		testTree.add(7,26);
		
		testTree2.add(5,83);
		testTree2.add(7,26);
		testTree2.add(4,19);
		testTree2.add(2,31);
		testTree2.add(1,84);
		testTree2.add(3,12);
		testTree2.add(6,70);
		assertEquals(testTree.toString(), testTree2.toString());
	}
	
	@Test
	void deleteTest() {
		testTree.add(4,19);
		testTree.add(2,31);
		testTree.add(6,70);
		testTree.add(1,84);
		testTree.add(3,12);
		testTree.add(5,83);
		testTree.add(7,26);
		
		testTree2.add(5,83);
		testTree2.add(7,26);
		testTree2.add(4,19);
		testTree2.add(2,31);
		testTree2.add(1,84);
		testTree2.add(3,12);
		testTree2.add(6,70);
		
		assertFalse(testTree.delete(9000));
		assertTrue(testTree.delete(4));
		assertNotEquals(testTree.toString(), testTree2.toString());
		testTree2.delete(4);
		assertEquals(testTree.toString(), testTree2.toString());
	}
	
	@Test 
	void findTest() {
		testTree.add(4,19);
		testTree.add(2,31);
		testTree.add(6,70);
		testTree.add(1,84);
		testTree.add(3,12);
		testTree.add(5,83);
		testTree.add(7,26);
		
		assertTrue(testTree.find(4));
		assertFalse(testTree.find(888477));
	}

}
