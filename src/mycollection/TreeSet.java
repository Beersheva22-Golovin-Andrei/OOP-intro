package mycollection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> extends AbstractCollection<T> implements Sorted<T> {
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
					while (comp.compare(current.obj, current.parent.obj)>0) {
						current = current.parent;
					}
					current = current.parent;
			} else current = leftBottom(current.right);
			res= current.obj;
			count++;
			return res;
		}	   
	   }
	   
	   private Node<T> leftBottom (Node <T> node) {
			Node<T> intermid = node;
			while (intermid.left !=null ) {
				intermid=intermid.left;
			}
			return intermid;				
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
		
		private Node<T> getNode(T element) {
			Node<T> current = root;
			Node<T> parent = null;
			int compRes;
			while(current != null && (compRes = comp.compare(element, current.obj)) != 0) {
				parent = current;
				current = compRes < 0 ? current.left : current.right;
			}
			return current == null ? parent : current;
		}
		

		@Override
		public boolean remove(T pattern) {
			
			Node<T> deletedNode = getNode(pattern);
			Node<T> replacedNode;
		
			if (deletedNode != null) {
			if (deletedNode.right != null && deletedNode.left!=null) {
				replacedNode  = leftBottom(deletedNode.right);			
				deletedNode.obj = replacedNode.obj;
					if (replacedNode.right!=null) {
						replacedNode.right.parent = replacedNode.parent;
						replacedNode.parent.left = replacedNode.right;
					} else {
						replacedNode.parent.left = null;
					}
			} else {
				replacedNode = deletedNode.right == null ? deletedNode.left : deletedNode.right;
		
			if (replacedNode != null) {
				replacedNode.parent = deletedNode.parent;
			}
				if (comp.compare(pattern, deletedNode.parent.obj)<0) {
					deletedNode.parent.left = replacedNode;
				} else {
					deletedNode.parent.right = replacedNode;
				}			
			
			}	
		}			
			return deletedNode==null ? false : true;
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
		
		@Override
		public T floor(T element) {
			
			
			
			return null;
		}
		@Override
		public T ceiling(T element) {
			Node<T> current = root;
			boolean next = true;
			if (comp.compare(current.obj, element) < 0) {
				while(comp.compare(element, current.obj) > 0) {
					current = current.right;
				}
			}
			
			while(next) {
				if (current.left!=null) {
				if(comp.compare(element, current.left.obj) < 0 && current.left.right != null) {
						current = current.left.right;
				
				} else {
					current = current.left;
					next = false;
				}
			} else next =false;
			}
			
			return current.obj;
		}
		@Override
		public T first() {
			return leftBottom(root).obj;
		}
		@Override
		public T last() {
			return rightBottom(root).obj;
		}
		
		private Node<T>rightBottom (Node <T> node) {
			Node<T> intermid = node;
			while (intermid.right !=null ) {
				intermid=intermid.right;
			}
			return intermid;				
		}
 

}
