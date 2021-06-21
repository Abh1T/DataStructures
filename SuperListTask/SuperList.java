import java.util.*;

public class SuperList <E>
{
	ListNode<E> root;
	ListNode<E> end;
	int size;
	public SuperList() 
	{
		root = null;
		end = null;
		size = 0;
	}
	public SuperList(E val) 
	{
		this.root = new ListNode<E> (val);
		this.end = this.root;
		size = 1;
	}
	public void add(E value)
	{
		ListNode<E> newNode = new ListNode<E>(value);
		if(root == null) 
		{
			root = newNode;
			end = root;
		}else {
			end.setNext(newNode);
			newNode.setPrevious(end);
			end = newNode;
		}
		size++;
	}
	
	public void add(int index, E value)
	{
		if((index<0)||(index>size())) {
			throw new ArrayIndexOutOfBoundsException();
		}
		if(index==size()) {
			add(value);
		}else if(index==0) {
			ListNode<E> start = new ListNode<E>(value);
			root.setPrevious(start);
			start.setNext(root);
			root = start;
			end = root;
			for(int x = 0; x< size; x++) {
				start = start.getNext();
				end.setNext(start);
				end = end.getNext();
			}
			size++;
		}else {
			ListNode<E> temp = root;
			ListNode<E> toBeAdded = new ListNode<E>(value);
			for(int x = 0; x< index; x++) {
				temp = temp.getNext();
			}
			ListNode<E> prevTemp = temp.getPrevious();
			toBeAdded.setPrevious(prevTemp);
			prevTemp.setNext(toBeAdded);
			
			toBeAdded.setNext(temp);
			temp.setPrevious(toBeAdded);
			size++;
		}
		
		
	}
	
	
	public E remove(int index) {
		if((index<0)||(index>size-1)) {
			throw new IndexOutOfBoundsException();
		}else if(index == 0) {
			return poll();
		}else if(index == size-1) {
			return pop();
		}else {
			size--;
			ListNode<E> temp = root;
			for(int x = 0; x< index; x++) {
			temp = temp.getNext();
		}
		E value = temp.getValue();
		temp.getPrevious().setNext(temp.getNext());
		temp.getNext().setPrevious(temp.getPrevious());
		return value;
			
		}
	}
	
	public void push(E value)  
	{
		add(value);
	}
	public void clear() 
	{
		root = null;
		end = null;
		size = 0;
	}
	public E peekStack() {
		if(size != 0) {
			return end.getValue();
		}else {
			return null;
		}
	}
	
	
	public boolean contains(E value) {
		if(size ==0 ) {
			return false;
		}else {
			boolean isContains = false;
			ListNode<E> temp = root;
			for(int x = 0; x< size; x++) {
				if(value == temp.getValue()) {
					isContains = true;
					break;
				}
				temp = temp.getNext();
			}
			return isContains;
		}
		
	}
	public E poll() {
		if(size == 0) {
			return null;
		}else if(size == 1) {
			size--;
			E values = root.getValue();
			end = null;
			root = null;
			return values;
		}else{
			int x = 0;
			E rootVal = root.getValue();
			root = root.getNext();
			root.setPrevious(null);
			size--;
			return rootVal;
		}
	}
	public E pop() {
		if(size == 0) {
			throw new EmptyStackException();
		}else if(size == 1) {
			ListNode<E> theRealEnd = end;
			end = null;
			root = null;
			size--;
			return theRealEnd.getValue();
		}else{
			int x = 0;
			ListNode<E> theRealEnd = end;
			ListNode<E> beforeEnd = end.getPrevious();
			end = beforeEnd;
			size--;
			return theRealEnd.getValue();
		}
		
	}
	public boolean isEmpty() {
		return size == 0;
	}
	
	public E get(int index) {
		  if (index >= size) {
	            throw new ArrayIndexOutOfBoundsException();
		  }else if(index<0) {
			  throw new ArrayIndexOutOfBoundsException();
		  }else {
			  ListNode<E> temp = root;

	        for (int i = 0; i<index; i++) {
	            temp = temp.getNext();
	        }
	        if(temp.getValue() == null)
				throw new NullPointerException();
	        return temp.getValue();
		 }
	}
	public String toString() {
		String st = "[";
		ListNode<E> tempNode = root;
		for(int x = 0; x< size; x++) {
			st+= tempNode.getValue();
			if(x<size-1) {
				st+= ", ";
				tempNode = tempNode.getNext();
			}
		}
		st+= "]";
		return st;
	}
	public E peekQueue() {
		if (size != 0) 
		return root.getValue();
		else return null;
	}
	public int size() 
	{
		return size;
	}
	
	public class ListNode<E>
	{
		E value;
		ListNode<E> next;
		ListNode<E> previous;
		
		public ListNode(E value)
		{
			this.value = value;
			next = null;
			previous = null;
		}
		public E getValue(){
			return value;
		}
		public ListNode<E> getNext(){
			return next;
		}
		public ListNode<E> getPrevious(){
			return previous;
		}
		public void setNext(ListNode<E> newNode) {
			next = newNode;
		}
		public void setPrevious(ListNode<E> newNode) {
			previous = newNode;
		}
	}
}
