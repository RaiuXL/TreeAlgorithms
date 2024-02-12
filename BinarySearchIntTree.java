package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BinarySearchIntTree {
    private Node root;
    private int size;

    // Printing
    public void printLevel(int level) {
        if (level < 1) {
            throw new IllegalArgumentException("Level must be greater than or equal to 1");
        }
        printLevel(root, level);
    }
    private void printLevel(Node node, int level) {
        if (node == null || level < 1) {
            return;
        }

        if (level == 1) {
            System.out.println(node.data);
        } else{
            printLevel(node.left, level - 1);
            printLevel(node.right, level - 1);
        }
    }
    public void printLeaves() {
        if (root == null) {
            System.out.println("No leaves in the tree.");
        } else {
            printLeaves(root);
        }
    }
    private void printLeaves(Node current) {
        if (current != null) {
            printLeaves(current.right);
            printLeaves(current.left);
            if (current.left == null && current.right == null) {
                System.out.print(current.data + " ");
            }
        }
    }

    public String toString(){
        if(root==null){
            return "<root is null>";
        }
        return root.toString();
    }


    // Tree traversal methods
    public ArrayList<Integer> inOrderTraversal(){
        // edit note: we are passing in the memory location of the arraylist
        //            so we only create a new object once.
        ArrayList<Integer> inOrderList = new ArrayList<>();
        inOrderTraversal(root,inOrderList);
        return inOrderList;
    }
    private void inOrderTraversal(Node current, ArrayList<Integer> list){
        /* edit note: we go all the way to the left until we hit null then returns to the previous node
         * */
        if(current!=null){

            inOrderTraversal(current.left,list);
            list.add(current.data);
            inOrderTraversal(current.right,list);
        }

    }
    // Wed Nov.15 GRC
    public ArrayList<Integer> preOrderTraversal(){
        ArrayList<Integer> preOrderList = new ArrayList<>();
        preOrderTraversal(root, preOrderList);
        return preOrderList;
    }
    private void preOrderTraversal(Node current, ArrayList<Integer> list){
        if(current!=null){
            list.add(current.data);
            preOrderTraversal(current.left,list);
            preOrderTraversal(current.right,list);
        }
    }
    // Wed Nov.15 GRC
    public ArrayList<Integer> postOrderTraversal(){
        ArrayList<Integer> postOrderList = new ArrayList<>();
        postOrderTraversal(root,postOrderList);
        return postOrderList;
    }
    private void postOrderTraversal(Node current, ArrayList<Integer> list){
        if(current!=null){
            postOrderTraversal(current.left,list);
            postOrderTraversal(current.right,list);
            list.add(current.data);
        }
    }
    public void breadthFirstSearch() {
        if (root == null) {
            return;
        }
        //ArrayList<Integer> traversal = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.remove();
            //traversal.add(current.data);
            System.out.print(current.data + " ");
            if (current.left != null) {
                queue.add(current.left);
                //traversal.add(current.left.data);
            }
            if (current.right != null) {
                queue.add(current.right);
                //traversal.add(current.right.data);
            }
        }
        // could make it return traversal ArrayList 'return traversal'
        // edit note: I wanted to print the contents of traversal
    }


    // Search and retrieval methods
    public boolean add(int element){
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
    private boolean add(Node current, int element){
        if(element==current.data){   // base case
            return false;
        }else if(element<current.data){     // recursion case
            if(current.left!=null){
                return add(current.left,element); // recursion call
            }else{                         // base case
                current.left = new Node(element);
                size++;
                return true;
            }
        }else{ // if element > current.data
            if (current.right != null) {
                return add(current.right, element);
            } else {
                current.right = new Node(element);
                size++;
                return true;
            }
        }
    }
    public boolean contains(int element){
        if(root==null){
            return false;
        }else{
            return contains(root,element);
        }
    }
    private boolean contains(Node current, int element){
        if(current==null){
            throw new NoSuchElementException("Element is not found in tree.");
        }

        if(element==current.data){
            return true;
        }else if(element<current.data){
            return contains(current.left,element);
        }else{
            return contains(current.right,element);
        }
    }
    public boolean isLeaf(int element) {
        if (root == null) {
            return false;
        } else {
            return isLeaf(root, element);
        }
    }
    private boolean isLeaf(Node current, int element) {
        if (current == null) {
            return false; // Element not found in the tree
        }

        if (element == current.data && current.left == null && current.right == null) {
            return true; // Element found and is a leaf
        } else if (element < current.data) {
            return isLeaf(current.left, element);
        } else {
            return isLeaf(current.right, element);
        }
    }
    public ArrayList<Integer> range(int low, int high) {
        ArrayList<Integer> result = new ArrayList<>();
        range(root, low, high, result);
        return result;
    }
    private void range(Node current, int low, int high, ArrayList<Integer> list){
        if(current==null){
            return;
        }
        if(current.data>=low && current.data <= high){
            list.add(current.data);
        }
        if(current.data>low){
            range(current.left, low, high, list);
        }
        if(current.data<high){
            range(current.right, low, high, list);
        }
    }
    public int findMin() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMin(root);
    }
    private int findMin(Node node) {
        if (node.left == null) {
            return node.data;
        }
        return findMin(node.left);
    }
    public int findMax() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMax(root);
    }
    private int findMax(Node node) {
        if (node.right == null) {
            return node.data;
        }
        return findMax(node.right);
    }


    // Tree statistics methods
    public int countEmpty(){
        return countEmpty(root);
    }
    private int countEmpty(Node current){
        // base case
        if(current==null){
            return 1;
        }else{
            return countEmpty(current.left) + countEmpty(current.right);
        }
    }
    public int depthSum(){
        int depth = 1;
        return depthSum(root,depth);
    }
    private int depthSum(Node current,int depth){
        if(current==null){
            return 0;
        }else{
            return current.data * depth + depthSum(current.left,depth+1) + depthSum(current.right,depth+1);
        }
    }
    public boolean listOfLeafNodes(ArrayList<Integer> list) {
        if (root == null) {
            return false;
        } else {
            return listOfLeafNodes(root, list);
        }
    }
    private boolean listOfLeafNodes(Node current, ArrayList<Integer> list) {
        if (current == null) {
            return false;
        }
        if (current.left == null && current.right == null) {
            list.add(current.data);
            return true;
        }
        boolean leftResult = listOfLeafNodes(current.left, list);
        boolean rightResult = listOfLeafNodes(current.right, list);

        return leftResult || rightResult;
    }


    // Node Class
    private class Node{
        private int data;
        private Node left;
        private Node right;

        public Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
        public Node(int data, Node left, Node right) {
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
