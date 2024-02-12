package trees;
import java.util.ArrayList;
import java.util.NoSuchElementException;
public class GenericTree<T extends Comparable<T>> {
    private Node root;
    private int size;
    public boolean add(T element){
        // what if the tree is empty?
        if(root==null){
            root= new Node(element);
            size++;
            return true;
        }else{
            // what if there is a tree?
            return add(root,element);
        }
    }
    public T findParent(T element){
        if(root == null||root.data.equals(element)){
            return null;
        }

        return findParent(root,null,element);
    }
    private T findParent(Node current,Node parent, T element){
        if(current == null){
            return null;
        }
        if(current.data.equals(element)){
            return parent.data;
        }
        int comparison = element.compareTo(current.data);
        if(comparison<0){
            return findParent(current.left,current,element);
        }else {
            return findParent(current.right,current,element);
        }
    }
    private boolean add(Node current, T element){
        int comparison = element.compareTo(current.data);

        if(comparison==0){   // base case
            return false;
        } else if(comparison<0) {     // recursion case
            if (current.left != null) {
                return add(current.left, element); // recursion call
            } else {                         // base case
                current.left = new Node(element);
                size++;
                return true;
            }
        } else{
                if(current.right!=null){
                    return add(current.right, element);
                }
                else{
                    current.right = new Node(element);
                    size++;
                    return true;
                }
        }
    }

    public boolean contains(T element){
        if(root==null){
            return false;
        }else{
            return contains(root,element);
        }
    }

    private boolean contains(Node current, T element){
        if(current==null){
            throw new NoSuchElementException("Element is not found in tree.");
        }
        int comparison = element.compareTo(current.data);
        if(comparison==0){
            return true;
        }else if(comparison<0){
            return contains(current.left,element);
        }else{
            return contains(current.right,element);
        }
    }

    public String toString(){
        if(root==null){
            return "<root is null>";
        }
        return root.toString();
    }

    public ArrayList<T> inOrder(){
        ArrayList<T> list = new ArrayList<>();
        inOrder(this.root,list);
        return list;
    }

    private void inOrder(Node current, ArrayList<T> list){
        if(current==null){
            return;
        }else{
            inOrder(current.left,list);
            list.add(current.data);
            inOrder(current.right,list);
        }
    }

    public int findHeight(){
        return findHeight(this.root);
    }

    private int findHeight(Node current){
        if(current==null){
            return 0;
        }else{
            return 1 + Math.max(findHeight(current.left),findHeight(current.right));
        }
    }

    public int size(){
        return size(this.root);
    }

    private int size(Node current){
        if(current==null){
            return 0;
        }else{
            return 1+size(current.left)+size(current.right);
        }
    }

    public T findMinimum(){
        if(this.root == null){
            return null;
        }else{
            return findMinimum(this.root);
        }
    }

    private T findMinimum(Node current){
        if(current.left==null){
            return current.data;
        }else{
            return findMinimum(current.left);
        }
    }

    public T findMaximum(){
        if(this.root == null){
            return null;
        }else{
            return findMaximum(this.root);
        }
    }

    private T findMaximum(Node current){
        if(current.right==null){
            return current.data;
        }else{
            return findMaximum(current.right);
        }
    }

    public boolean equals(GenericTree<T> other){
        return equals(other.root,this.root);
    }

    private boolean equals(Node other, Node current){
        if(current==null & other ==null){
            return true;
        }else if(current!=null && other !=null){
            return other.data.equals(current.data) && equals(other.left,current.left) && equals(other.right,current.right);
        }else{
            return false;
        }
    }

    public int countInternalNodes(){
        return countInternalNodes(this.root);
    }
    private int countInternalNodes(Node current){
        if(current==null){
            return 0;
        }else if(current.left != null && current.right != null){
            return 1+countInternalNodes(current.left) + countInternalNodes(current.right);
        }else{
            return countInternalNodes(current.left)+countInternalNodes(current.right);
        }
    }
    // Edit notes learn how to implement remove(), it's advance due to learning how to balance the tree after removing it
    private class Node {
        private T data;
        private Node left;
        private Node right;

        public Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public Node(T data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
        public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder builder)
        {
            if(right!=null)
            {
                right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, builder);
            }

            builder.append(prefix).append(isTail ? "└── " : "┌── ").append(data).append("\n");

            if(left!=null)
            {
                left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, builder);
            }

            return builder;
        }

        @Override
        public String toString()
        {
            return toString(new StringBuilder(), true, new StringBuilder()).toString();
        }
    }

}
