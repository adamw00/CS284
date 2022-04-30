package rolodex;

import java.util.ArrayList;
/**
 * 
 * @author Adam Woo
 * I pledge my honor that I have abided by the Stevens Honor System.
 * CS 284-B
 *
 */
public class Rolodex {
	private Entry cursor;
	private final Entry[] index;

	// Constructor

	Rolodex() {
	    // TODO
		index = new Entry[26];
		for (int i=0; i<=25; i++) {
			if (i==0) {
				index[i] = new Separator(index[25],index[1], 'A');
			} else if(i==25) {
				index[i] = new Separator(index[24],index[0], 'Z');
				index[0].prev = index[i];
				index[i-1].next = index[i];
			} else {
				index[i] = new Separator(index[i-1],index[i+1], (char) (i+65));
				index[i-1].next = index[i];
			}
		}
		
	}

	public Boolean contains(String name) {
	    // TODO
		int start = findSeparators(name);
		
		Entry v = index[start];
		while (!v.next.isSeparator()) {
			if (v.next.getName().equals(name)) {
				return true;
			}
			v=v.next;
		}
		return false;
	}
	
	public int size() {
		    // TODO
		int total=0;
		
		for(int x=0; x<index.length; x++) {
			Entry v = index[x];
			while (!v.next.isSeparator()) {
				total++;
				v=v.next;
			}
		}
		return total;
	}

	public ArrayList<String> lookup(String name) {
		    // TODO
		ArrayList<String> f = new ArrayList<String>();
		if(!contains(name)) {
            throw new IllegalArgumentException("lookup: name not found");
        }
		int start = findSeparators(name);
		Entry v = index[start];
		while (!v.next.isSeparator()) {
			if (v.next.getName().equals(name)) {
				f.add(((Card)v.next).getCell());
			}
			v=v.next;
		}
		return f;
	}


	public String toString() {
		Entry current = index[0];

		StringBuilder b = new StringBuilder();
		while (current.next!=index[0]) {
			b.append(current.toString()+"\n");
			current=current.next;
		}
		b.append(current.toString()+"\n");		
		return b.toString();
	}




	public void addCard(String name, String cell) {
			    // TODO
		if(contains(name) && lookup(name).contains(cell)) {
            throw new IllegalArgumentException("addCard: duplicate entry");
        }
		
		int start = findSeparators(name);
		
		if (index[start].next.isSeparator()){
			Card p = new Card(index[start],index[start].next,name,cell);
			index[start].next = p;
			index[start+1].prev = p;
		} else if (name.compareTo(index[start].next.getName())<0) {
			Card p = new Card(index[start],index[start].next,name,cell);
			index[start].next.prev = p;
			index[start].next = p;
		} else {
			Entry v = index[start];
			while (!v.next.isSeparator()) {
				if (name.compareTo(index[start].next.getName())<0) {
					Card p = new Card(index[start],index[start].next,name,cell);
					index[start].next.prev = p;
					index[start].next = p;
					break;
				}
				v=v.next;
			}
			Card p = new Card(v,v.next,name,cell);
			index[start+1].prev.next = p;
			index[start+1].prev = p;	
		}
	}

	public void removeCard(String name, String cell) {
			    // TODO
		if (!contains(name)) {
			throw new IllegalArgumentException("removeCard: name does not exist");
		} else if (!lookup(name).contains(cell)) {
			throw new IllegalArgumentException("removeCard: cell for that name does not exist");
		}
		int start = findSeparators(name);
		
		Entry v = index[start].next;
		while (!v.isSeparator()) {
			if (v.getName().equals(name) && ((Card) v).getCell().equals(cell)) {
				v.prev.next=v.next;
				v.next.prev=v.prev;
			}
			v=v.next;
		}

	}
	
	public void removeAllCards(String name) {
		    // TODO
		if(!contains(name)) {
            throw new IllegalArgumentException("removeAllCards: name does not exist");
        }
		int start = findSeparators(name);
		Entry v = index[start].next;
		while (!v.isSeparator()) {
			if (v.getName().equals(name)) {
				v.prev.next=v.next;
				v.next.prev=v.prev;
			}
			v=v.next;
		}
	}

	// Cursor operations

	public void initializeCursor() {
		    // TODO
		cursor = index[0];
	}

	public void nextSeparator() {
		    // TODO
		Entry v = cursor;
		while(!v.next.isSeparator()) {
			v = v.next;
		}
		cursor = v.next;
	}

	public void nextEntry() {
		    cursor = cursor.next;

	}

	public String currentEntryToString() {
			    // TODO
		return cursor.toString();
	}


	public static void main(String[] args) {

		
		Rolodex r = new Rolodex();
		
		
		System.out.println(r);

		r.addCard("Chloe", "123");
		r.addCard("Chad", "23");
		r.addCard("Cris", "3");
		r.addCard("Cris", "4");
		r.addCard("Cris", "5");
		//			r.addCard("Cris", "4");
		r.addCard("Maddie", "23");

		System.out.println(r);

		System.out.println(r.contains("Albert"));

		r.removeAllCards("Cris");
		
		System.out.println(r);

		r.removeAllCards("Chad");
		r.removeAllCards("Chloe");

		r.addCard("Chloe", "123");
		r.addCard("Chad", "23");
		r.addCard("Cris", "3");
		r.addCard("Cris", "4");

		System.out.println(r);




	}
	
	//helper method
	private int findSeparators(String name) {
		char start = name.charAt(0);
		int f = -1;
		for(int i=0; i<index.length; i++) {
			if (index[i].isSeparator() && ((Separator)index[i]).getLetter().equals(start)) {
				f= i;
			}
		}
		return f;
	}

}
