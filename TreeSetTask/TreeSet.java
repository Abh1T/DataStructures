import java.io.*;
import java.util.ArrayList;


public class TreeSet<E extends Comparable<E>>{
	int size = 0;
	TreeNode<E> root;
	String toStringStr;
	
	public TreeSet() {
		
	}
	public void add(E value) {
		if(size == 0) {
			root = new TreeNode<E>(value);
			size++;
		}else {
			add(value, root);
		}
	}
	public void add(E value, TreeNode<E> root) {
		TreeNode<E> currentNode = root;
		if(currentNode.getValue() == value) {
			return;
		}else if(currentNode.getValue().compareTo(value) > 0) {
			if(currentNode.getLeft() == null) {
				currentNode.setLeft(new TreeNode<E>(value));
				size++;
				return;
			}else {
				add(value, currentNode.getLeft());
			}
		}else {
			if(currentNode.getRight() == null) {
				currentNode.setRight(new TreeNode<E>(value));
				size++;
				return;
			}else {
				add(value, currentNode.getRight());
			}
		}
	}
	
	public TreeNode<E> getRoot(){
		return root;
	}
	
	public int size() {
		return size;
	}
	
	public String preOrder() {
		if(size == 0)
			return "[]";
		else {
			toStringStr = "[";
			preOrder(root);
			toStringStr = toStringStr.substring(0, toStringStr.length()-2);
			toStringStr+="]";
			return toStringStr;
		}
	}
	public void preOrder(TreeNode<E> root) {
		if(root != null) {
			toStringStr += root.getValue()+", ";
			preOrder(root.getLeft());
			preOrder(root.getRight());
		}
	}
	public String inOrder() {
		if(size == 0)
			return "[]";
		else {
			toStringStr = "[";
			inOrder(root);
			toStringStr = toStringStr.substring(0, toStringStr.length()-2);
			toStringStr+="]";
			return toStringStr;
		}
	}
	public void inOrder(TreeNode<E> root) {
		if(root != null) {
			inOrder(root.getLeft());
			toStringStr += root.getValue()+", ";
			inOrder(root.getRight());
		}
	}
	public String postOrder() {
		if(size == 0)
			return "[]";
		else {
			toStringStr = "[";
			postOrder(root);
			toStringStr = toStringStr.substring(0, toStringStr.length()-2);
			toStringStr+="]";
			return toStringStr;
		}
	}
	public void postOrder(TreeNode<E> root) {
		if(root != null) {
			postOrder(root.getLeft());
			postOrder(root.getRight());
			toStringStr += root.getValue()+", ";
		}
	}
	public boolean contains(TreeNode<E> root, E value) 
	{
		if(root == null) {
		return false;
		}
		if(root.getValue() == value) {
			return true;			
		}
		if(root.getValue().compareTo(value) > 0) {
			return contains(root.getLeft(), value);
		}
		return contains(root.getRight(), value);
	}
	
	
	public E minValue(TreeNode<E> root){
		if(root.getLeft() == null) {
			return root.getValue();
		}
		return minValue(root.getLeft());
	}
	
	
	
	
	public void remove(E value) {
		if(root==null) {
			return;
		}
		if(contains(root, value)) {
			if(root.getLeft() == null && root.getRight() == null) {
				root = null;
				size = 0;
				return;
			}else {
				size--;
				root = remove(root, value);
			}
		}
		
	}
	public TreeNode<E> remove(TreeNode<E> root, E value) {
		if(root == null)
			return null;
		if(root.getValue().compareTo(value) > 0) {
			root.setLeft(remove(root.getLeft(), value));
		}else if(root.getValue().compareTo(value) < 0) {
			root.setRight(remove(root.getRight(), value));
		}else {
			if(root.getLeft() == null && root.getRight() == null) {
				root = null;
			}else if(root.getLeft()== null) {
				return root.getRight();
			}else if(root.getRight() == null) {
				return root.getLeft();
			}else {
				E temp = minValue(root.getRight());
				root.setValue(temp);
				root.setRight(remove(root.getRight(), temp));
			}
		}
		return root;
	}
	
	
	
	public void rotateRight() {
		rotateRight(root);
	}
	public void rotateRight(TreeNode<E> root) {
		if(root == null) {
			return;
		}else if(root.getLeft()==null) {
			return;
		}
		TreeNode<E> newRoot = root.getLeft();
		root.setLeft(root.getLeft().getRight());
		newRoot.setRight(root);
		root = newRoot;
		System.out.println("Left: "+newRoot.getLeft());
		System.out.println("Right: "+newRoot.getRight());
		System.out.println("Root: "+root.getValue());
		
	}
	
	
	
	public void rotateLeft() {
		rotateLeft(root);
	}
	public void rotateLeft(TreeNode<E> root) {
		if(root == null) {
			return;
		}else if(root.getRight()==null) {
			return;
		}
		TreeNode<E> newRoot = root.getRight();
		root.setLeft(root.getRight().getLeft());
		newRoot.setLeft(root);
		root = newRoot;
		System.out.println("Left: "+newRoot.getLeft());
		System.out.println("Right: "+newRoot.getRight());
		System.out.println("Root: "+root.getValue());
	}
	
	public void printer() {
		System.out.println("Pre Order: "+preOrder());
		System.out.println("In Order: "+inOrder());
		System.out.println("Post Order: "+postOrder());
	}
	@SuppressWarnings("unchecked")
	public TreeSet<E> copier(String str) {
		String copy = str.substring(1, str.length()-1);
		String[] arr = copy.split(", ");
		TreeSet<E> copier = new TreeSet<E>();
		for(int i = 0; i<arr.length; i++) {
			copier.add((E) arr[i]);
		}
		return copier;
	}
	
	
	//start of treenode class
	public class TreeNode<E extends Comparable<E>> {
		TreeNode<E> left;
		TreeNode<E> right;
		E value;
		
		public TreeNode(E value) {
			this.value = value;
			left = null;
			right = null;
		}
		public TreeNode<E> getRight() {
			return right;
		}
		public TreeNode<E> getLeft() {
			return left;
		}
		public void setRight(TreeNode<E> n){
			right = n;
		}
		public void setLeft(TreeNode<E> n) {
			left = n;
		}
		public E getValue() {
			return value;
		}
		public void setValue(E n) {
			value = n;
		}
		public String toString() {
			return ""+value;
		}
}




	
	public static void main(String [] args) {
		TreeSet app = new TreeSet();
	}
}
