package queues;

import stacks.StackSLL;

public class QueueSt<E> {
	
		// data fields
		private StackSLL<E> left;
		private StackSLL<E> right;
		private int size;
		
		// Constructor
		QueueSt() {
			left = new StackSLL();
			right = new StackSLL();
			size = 0;
		}
		
		public void add (E item) {
			//move left to right
			while (left!= null) {
				right.push(left.pop());
			}
			//push to left
			left.push(item);
			//push back to left
			while (!right.isEmpty()) {
				left.push(right.pop());
			}
			size++;

		}
		


		public E remove() {
			if(left == null) {
                throw new IllegalStateException("element: queuest is empty");
            }
			E x = left.peek();
			left.pop();
			return x;
			
		}
		
		public E element() {
            if(left == null) {
                throw new IllegalStateException("element: queuest is empty");
            }
            return left.peek();

		}
		
		public Boolean isEmpty() {
			return left.isEmpty();

		}
		
		public String toString() {
			StringBuilder s = new StringBuilder();
            StackSLL<E> current = left;
            s.append("[front] <=");
            while(current!=null) {
                s.append(current.peek().toString()+ " <= ");
                current.pop();
            }
            s.append("[tail]");
            return s.toString();

				
		}
		
		public static void main(String[] args) {
			QueueSt<String> q = new QueueSt<>();
			
			q.add("one");
			q.add("two");
			q.add("three");
			
			System.out.println(q);
			q.remove();
			System.out.println(q);
			
			
		}
		

	}

