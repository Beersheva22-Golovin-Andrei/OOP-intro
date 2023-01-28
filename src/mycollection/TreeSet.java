package mycollection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;

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
	   private static final String SYMBOL = " ";
	   private static final int NUMBER_SYMBOLS_PER_LEVEL = 3;
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
			return floorCeiling(element, true);
		}
		
		
		@Override
		public T ceiling(T element) {
			return floorCeiling(element, false);
			}
		
		private T floorCeiling(T pattern, boolean isFloor) {
			T res = null;
			int compRes = 0;
			Node<T> current = root;
			while (current != null && (compRes = comp.compare(pattern, current.obj)) != 0) {
				if ((compRes < 0 && !isFloor) || (compRes > 0 && isFloor) ) {
					res = current.obj;
				} 
				current = compRes < 0 ? current.left : current.right;
			}
			return current == null ? res : current.obj;
			
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
		
		public void displayTreeRotated() {
			displayTreeRotated(root, 0);
		}
		private void displayTreeRotated(Node<T> root, int level) {
			if (root != null) {
				displayTreeRotated(root.right, level + 1);
				displayRoot(root, level);
				displayTreeRotated(root.left, level + 1);
			}
			
		}
		private void displayRoot(Node<T> root, int level) {
			System.out.printf("%s%s\n", SYMBOL.repeat(NUMBER_SYMBOLS_PER_LEVEL * level), root.obj);
			
		}
		public int height() {
			
			return height(root);
		}
		private int height(Node<T> root) {
			int res = 0;
			if (root != null) {
				int heightLeft = height(root.left);
				int heightRight = height(root.right);
				res = Math.max(heightLeft, heightRight) + 1;
			}
			return res;
			
		}
		
		
		public int width() {
					
			return width(root);
		}
		
		
		private int width(Node<T> root) {
			int res = 0;
			
			List<Integer> levelsHolder = new ArrayList<>();
				counter(root,0,levelsHolder);
				if (!levelsHolder.isEmpty()) {
				for (Integer num : levelsHolder) {
					if (res<num) res=num;
				}
				}
			return res;
		}
		
		private void counter(Node<T> current, int level, List<Integer> levelsHolder) {
			if (current != null) {
				 levelsHolder.set(level, levelsHolder.get(level)!=null ? levelsHolder.get(level)+1: 1);
				counter(current.left, level + 1, levelsHolder);
				counter(current.right, level + 1, levelsHolder);
			}	
		}
		
		
		public void inversion() {
			inversion(root,0);
			comp = comp.reversed();
			
		}
		private void inversion(Node<T> current, int level) {
			if (current != null) {
				swap(current);
				inversion(current.left,level + 1);
				inversion(current.right,level + 1);
			}
			
		}
		private void swap(Node<T> current) {
			Node<T> temp;		
			temp = current.left;
			current.left = current.right;
			current.right = temp;
		}
		
		public void balance() {
			Node<T>[] array = getNodesArray();
			root = balance(array, 0, array.length - 1, null);
			
		}
		private Node<T> balance(Node<T>[] array, int left, int right, Node<T>parent) {
			Node<T> root = null;
			if (left <= right) {
				final int rootIndex = (left + right) / 2;
				root = array[rootIndex];
				root.parent = parent;
				root.left = balance(array, left, rootIndex - 1, root);
				root.right = balance(array, rootIndex + 1, right, root);
			}
			return root;
		}
		
		
		@SuppressWarnings("unchecked")
		private Node<T>[] getNodesArray() {
			Node<T> res[] = new Node[size];
			int index = 0;
			if (root != null) {
				Node<T> current = getLeastNode(root);
				while (current != null) {
					res[index++] = current;
					current = getNextCurrent(current);
				} 
			}
			return res;
		}
		
		private Node<T> getLeastNode(Node<T> current) {
			while(current.left != null) {
				current = current.left;
			}
			return current;
		}
		
		   private Node<T> getNextCurrent(Node<T> current) {
				
				return current.right == null ? getGreaterParent(current) : getLeastNode(current.right);
			}
		   
		   private Node<T> getGreaterParent(Node<T> current) {
				while(current.parent != null && current.parent.left != current) {
					current = current.parent;
				}
				return current.parent;
			}

}
