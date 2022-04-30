package hw;
import java.util.*;

/**
 * 		CS 284-B HW4  4/25/2021 
 * @author Adam Woo
 *		I pledge my honor that I have abided by the Stevens Honor System.
 *
 * @param <E>
 */
public class Treap<E extends Comparable<E>> {
	
	private static class Node<E> {
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;
		
		public Node(E data, int priority) {
			if (data == null) {
				throw new IllegalArgumentException("Node: data null");
			}
			this.data = data;
			this.priority = priority;
			this.left = null;
			this.right = null;
		}
		
		public Node<E> rotateRight() {
			Node<E> B = this.left;
			this.left = B.right;
			B.right = this;
			return B;
		}
		
		public Node<E> rotateLeft() {
			Node<E> C = this.right;
			this.right = C.left;
			C.left = this;
			return C;
		}
		
		public String toString() {
			StringBuilder s = new StringBuilder();
			s.append("(key=" + this.data + ", priority=" + this.priority +")");
			return s.toString();
		}
	}
	
	private Random priorityGenerator;
	private Node<E> root;
	
	public Treap() {
		priorityGenerator = new Random();
		root = null;
	}
	
	public Treap(long seed) {
		priorityGenerator = new Random(seed);
		root = null;
	}
	
	boolean add(E key) {
		int k = priorityGenerator.nextInt();
		return add(key, k);
	}
	
	boolean add(E key, int priority) {
		Node<E> P = new Node<E>(key, priority);
		Stack<Node<E>> s = new Stack<Node<E>>();
		
		if (root == null) {
			root = P;
			return true;
		}
		
		Node<E> x = root;
		Node<E> y = null;
		
		while (x != null) {
			y=x;
			s.push(x);
			if (key.compareTo(x.data)<0) {
				x=x.left;
			} else if (key.compareTo(x.data)>0) {
				x=x.right;
			} else {
				return false;
			}
		}
		
		if (key.compareTo(y.data)<0) {
			y.left = P;
			s.pop();
			s.push(y);
		} else {
			y.right = P;
			s.pop();
			s.push(y);
		}
		reheap(s,P);
		
		return true;
	}
	
	void reheap(Stack<Node<E>> s, Node<E> P) {
		boolean x = true;
		while(x) {
			if (s.empty()) {
				break;
			}
			if (s.peek().left != null && s.peek().left.equals(P)) {
				if (s.peek().priority<P.priority) {
					Node<E> temp = s.pop().rotateRight();
					if (!s.empty()) {
						if (s.peek().left == temp.right) {
							s.peek().left = temp;
						} else if (s.peek().right == temp.right) {
							s.peek().right = temp;
						}
					} else {
						root = temp;
					}
				} else { x=false; }
				
			} else if (s.peek().right != null && s.peek().right.equals(P)) {
				if (s.peek().priority<P.priority) {
					Node<E> temp = s.pop().rotateLeft();
					if (!s.empty()) {
						if (s.peek().left == temp.left) {
							s.peek().left = temp;
						} else if (s.peek().right == temp.left) {
							s.peek().right = temp;
						}
					} else {
						root = temp;
					}
				} else { x=false; }
			}
		}
		return;
	}
	
	boolean delete(E key) {
		if (!find(key)) {
			return false;
		}
		deleteHelper(root,key);
		return true;
	}
	private boolean deleteHelper(Node<E> root, E key) {
		Node<E> prev = null;
		Node<E> current = root;
		//makes current = node with matching key
		while (current != null && current.data != key) {
			prev = current;
			if (key.compareTo(current.data) < 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		
		//checks if desired node is a leaf and removes it
		if (current.left == null && current.right == null) {
			if (current == root) {
				root = null;
			} else {
				if (prev.left == current) {
					prev.left = null;
				} else {
					prev.right = null;
				}
			}
		}
		//rotates left or right when node has two children
		else if (current.left != null && current.right != null) {
			if (current.left.data.compareTo(current.right.data) < 0) {
				if (prev.right == current) {
					prev.right = current.rotateLeft();
				} else {
					prev.left = current.rotateLeft();
				}
			}
			if (current.left.data.compareTo(current.right.data) > 0) {
				if (prev.right == current) {
					prev.right = current.rotateRight();
				} else {
					prev.left = current.rotateRight();
				}
			}
		}
		//rotates right when only left child
		else if (current.left != null && current.right == null) {
			if (prev.right == current) {
				prev.right = current.rotateRight();
			} else {
				prev.left = current.rotateRight();
			}
		}
		//rotates left when only right child
		else if (current.left == null && current.right != null) {
			if (prev.right == current) {
				prev.right = current.rotateLeft();
			} else {
				prev.left = current.rotateLeft();
			}
		}
		
		// calls helper again if still inside tree
		// once removed, returns true
		if (find(key)) {
			deleteHelper(root, key);
		}
		return true;
	}
	
	private boolean find(Node<E> root, E key) {
		if (root == null) {
			return false;
		}
		if (root.data==key) {
			return true;
		}
		if (root.left == null && root.right == null) {
			return false;
		}
		if (root.data.compareTo(key) < 0) {
			return find(root.right, key);
		} else {
			return find(root.left, key);
		}
	}
	
	public boolean find(E key) {
		return find(root,key);
	}
	
	private String toString(Node<E> current, int level) {
		StringBuilder s = new StringBuilder();
		for (int i=0; i<level;i++) {
			s.append("   ");
		}
		if (current==null) {
			s.append("null\n");
		} else {
			s.append(current.toString()+"\n");
			s.append(toString(current.left, level+1));
			s.append(toString(current.right,level+1));
		}
		return s.toString();
	}
	public String toString() {
		return toString(root,0);
	}
	
	public static void main(String[] args) {
		
	}
}
