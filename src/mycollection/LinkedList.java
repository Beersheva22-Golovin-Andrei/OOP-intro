package mycollection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {

	private static class Node<T> {
		T obj;
		Node<T> prev;
		Node<T> next;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private Node<T> head;
	private Node<T> tail;
	private int size;

	private class LinkedListIterator implements Iterator<T> {
		int current = 0;
		@Override
		public boolean hasNext() {
			return current <size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return get(current++);
		}
	}

	@Override
	public boolean add(T element) {
		Node<T> node = new Node<>(element);
		if (head == null) {
			head = tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}

		size++;
		return true;
	}
	
	private boolean isEquals (T t, T pattern) {
		return t == null ? t == pattern : t.equals(pattern);
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		int i =0;
		Node<T> current = head;
		while (i<size && !res) {				
			if (isEquals(current.obj, pattern)) {
				removeNode(current);
				res=true;
			}
			i++;
			current = current.next;
		}	
		return res;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		boolean res = false;
		int i =0;
		Node<T> current = head;
		while (i<size) {				
			if (predicate.test(current.obj)) {
				removeNode(current);
				res=true;
			} else {
			i++;
			
			}
			current = current.next;
		}	
		return res;
	}

	@Override
	public int size() {		
		return size;
	}

	@Override
	public T[] toArray(T[] ar) {
		if(ar.length < size) {
			ar = Arrays.copyOf(ar, size);
		}
		Node<T> current = head;
		for(int i = 0; i < size; i++) {
			ar[i] = current.obj;
			current = current.next;
		}
		Arrays.fill(ar, size, ar.length, null);
		return ar;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index, true);
		if (index == size) {
			add(element);
		} else if(index == 0) {
			addHead(element);
		} else {
			addMiddle(index, element);
		}
	}

	private void addMiddle(int index, T element) {
		Node<T> node = new Node<>(element);
		Node<T> nodeIndex = getNode(index);
		Node<T> nodePrev = nodeIndex.prev;
		nodePrev.next = node;
		node.prev = nodePrev;
		nodeIndex.prev = node;
		node.next = nodeIndex;
		size++;		
	}

	private Node<T> getNode(int index) {
		return index < size / 2 ? getNodeFromLeft(index) : getNodeFromRight(index);
	}

	private Node<T> getNodeFromRight(int index) {
		Node<T> current = tail;
		for(int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getNodeFromLeft(int index) {
		Node<T> current = head;
		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	private void addHead(T element) {
		Node<T> node = new Node<>(element);
		node.next = head;
		head.prev = node;
		head = node;
		size++;	
	}

	@Override
	public T remove(int index) {
		checkIndex(index, true);
		Node<T> removeble = getNode(index);
		removeNode(removeble);
		return removeble.obj;
	}
	
	private void removeNode (Node<T> node) {
		Node<T> prevNode = node.prev;
		Node<T> nextNode = node.next;
		if (prevNode == null && nextNode== null) {
			node =null;
		} else  if (prevNode == null) {
			nextNode.prev = null;
			head = nextNode;
		} else if (nextNode==null){
			prevNode.next=null;
			tail = prevNode;
		} else {	
		prevNode.next = nextNode;
		nextNode.prev = prevNode;
		}
		size--;
	}

	@Override
	public int indexOf(T pattern) {
		int res = -1;
		int i=0;
		T obj;
		Iterator<T> iter = iterator();
		while (iter.hasNext() && res<0) {
			obj = iter.next();			
			if (isEquals(obj, pattern)) {
				res=i;
			}
			i++;
		}
		return res;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int res = -1;
		int i=size-1;
		Node<T> current = tail;
		while (i>=0 && res<0) {				
			if (isEquals(current.obj, pattern)) {
				res=i;
			}
			current = current.prev;
			i--;
		}
		return res;
	}

	@Override
	public T get(int index) {
		checkIndex(index, false);
		return getNode(index).obj;
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index, false);
		Node<T> node = getNode(index);
		node.obj = element;
	}
}
