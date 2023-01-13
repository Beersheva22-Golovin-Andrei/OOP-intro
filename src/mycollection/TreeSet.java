package mycollection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> extends AbstractCollection<T> implements Set<T> {
	   static private class Node<T> {
		   T obj;
		   Node<T> parent;
		   Node<T> left;
		   Node<T> right;
		   Node(T obj) {
			   this.obj = obj;
		   }
	   }
	   private class TreeSetIterator implements Iterator<T> {

		   Node<T> current;
		   int count;
		   
		@Override
		public boolean hasNext() {
			return count<size;
		}

		@Override
		public T next() {
			T res;		
			if(!hasNext()) { throw new NoSuchElementException(); }			 
			if (current == null) {
				current= leftBottom(root);				
			} else if (current.right == null) {
					if (comp.compare(current.obj, current.parent.obj)<0) {
						current = current.parent;
					} else current = current.parent.parent;
			} else current = leftBottom(current.right);
			res= current.obj;
			count++;
			return res;
		}
		
		private Node<T> leftBottom (Node <T> node) {
			Node<T> intermid = node;
			while (intermid.left !=null ) {
				intermid=intermid.left;
			}
			return intermid;				
		}		   
	   }
	   
	   private Node<T> root;
	   private Comparator<T> comp;
	   public TreeSet(Comparator<T> comp) {
		   this.comp = comp;
	   }
	   @SuppressWarnings("unchecked")
	   public TreeSet() {
		   this((Comparator<T>) Comparator.naturalOrder());
	   }
		@Override
		public boolean add(T element) {
			boolean res = false;
			if (root == null) {
				root = createNewNode(null, element);
				res = true;
			}
			Node<T> current = root;
			int resOfComparing;
			while (!res) {
				resOfComparing = comp.compare(element, current.obj);
				if (resOfComparing == 0) {
					break;
				} else if (resOfComparing<0) {
					if (current.left==null) {					
						current.left = createNewNode (current, element);
						res=true;
					} else current = current.left;
					
				} else { 
					if (current.right==null) {
						current.right = createNewNode (current, element);
						res=true;
				} else current = current.right;
				}
			}			
			return res;
		}
		
		private Node<T> createNewNode (Node<T> nodeParrent, T element) {
				Node<T> nodeNew = new Node<>(element);
				nodeNew.parent = nodeParrent;
				size++;
				return nodeNew;
		}
		

		@Override
		public boolean remove(T pattern) {
			// Not implemented yet
			return false;
		}

		@Override
		public boolean contains(T pattern) {
			boolean res = false;
			int resOfComparing;
			Node <T> intermid = root;
			while (!res && intermid!=null) {
				resOfComparing = comp.compare(pattern, intermid.obj);
				if (resOfComparing == 0) {
					res = true;
				} else if (resOfComparing<0) {
					intermid=intermid.left;
				}else intermid=intermid.right;
				}
			return res;
			}
	

		@Override
		public Iterator<T> iterator() {
			
			return new TreeSetIterator();
		}
 

}
