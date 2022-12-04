package org.example;

import java.util.Random;
import java.util.Scanner;

class Node<T> {
    private T item;
    private int height;
    Node left;
    Node right;

    Node(T d) {
        this.item = d;
        this.height = 1;
    }

    public void height_setter(int height){
        this.height = height;
    }

    public int height_getter(){
        return this.height;
    }

    public void item_setter(T item){
        this.item = item;
    }

    public T item_getter(){
        return this.item;
    }

}


class tree_10<T> {
    Node root;

    int arr[];
    int i;


    int [] list(int no){
        arr = new int [no];
        i = 0;
        preorder(root);

        return arr;
    }

    private void preorder(Node node) {
        if (node != null) {
            arr[i] = (int)node.item_getter();
            i++;
            preorder(node.left);
            preorder(node.right);
        }
    }


    int height(Node N) {
        if (N != null)
            return N.height_getter();
        else{
            return 0;
        }
    }

    int max(int a, int b) {
        if (a > b){
            return a;
        }
        return b;
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height_setter(max(height(y.left), height(y.right)) + 1);
        x.height_setter(max(height(x.left), height(x.right)) + 1);
        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height_setter(max(height(x.left), height(x.right)) + 1);
        y.height_setter(max(height(y.left), height(y.right)) + 1);
        return y;
    }

    // Get balance factor of a node
    int getBalanceFactor(Node N) {
        if (N != null)
            return height(N.left) - height(N.right);
        else{
            return 0;
        }
    }

    // Insert a node
    Node insertNode(Node node, T i) {

        // Find the position and insert the node
        if (node == null)
            return (new Node(i));
        if ((int)i > (int)node.item_getter())
            node.right = insertNode(node.right, i);
        else if ((int)i < (int)node.item_getter())
            node.left = insertNode(node.left, i);
        else
            return node;

        node.height_setter(1 + max(height(node.left), height(node.right)));
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor < -1) {
            if ((int)i < (int)node.right.item_getter()) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            } else if ((int)i > (int)node.right.item_getter()) {
                return leftRotate(node);
            }
        }
        if (balanceFactor > 1) {
            if ((int)i > (int)node.left.item_getter()) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            } else if ((int)i < (int)node.left.item_getter()) {
                return rightRotate(node);
            }
        }
        return node;
    }

}

class tree_10_2<T>{
    Node root;

    int height(Node N) {
        if (N != null)
            return N.height_getter();
        else{
            return 0;
        }
    }

    int max(int a, int b) {
        if (a > b){
            return a;
        }
        return b;
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height_setter(max(height(y.left), height(y.right)) + 1);
        x.height_setter(max(height(x.left), height(x.right)) + 1);
        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height_setter(max(height(x.left), height(x.right)) + 1);
        y.height_setter(max(height(y.left), height(y.right)) + 1);
        return y;
    }


    // Get balance factor of a node
    int getBalanceFactor(Node N) {
        if (N != null)
            return height(N.left) - height(N.right);
        else{
            return 0;
        }
    }

    // Insert a node
    Node insertNode(Node node, T i) {

        // Find the position and insert the node
        if (node == null)
            return (new Node(i));
        if ((int)i > (int)node.item_getter())
            node.right = insertNode(node.right, i);
        else if ((int)i < (int)node.item_getter())
            node.left = insertNode(node.left, i);
        else
            return node;

        node.height_setter(1 + max(height(node.left), height(node.right)));
        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor < -1) {
            if ((int)i < (int)node.right.item_getter()) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            } else if ((int)i > (int)node.right.item_getter()) {
                return leftRotate(node);
            }
        }
        if (balanceFactor > 1) {
            if ((int)i > (int)node.left.item_getter()) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            } else if ((int)i < (int)node.left.item_getter()) {
                return rightRotate(node);
            }
        }
        return node;
    }


}

class threads2 implements Runnable{
    private  int arr[];
    private  int n;

    private int start;
    private int end;

    threads2(int arr[],int n , int start , int end){
        this.arr = arr;
        this.n = n;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        tree_10_2 tree1 = new tree_10_2();
        for (int i = start ; i < end ; i ++){
            //System.out.println(arr[i]);
            tree1.root = tree1.insertNode(tree1.root, arr[i]);
        }
    }
}

class ifexist implements Runnable{
    private int arr[];
    private int start;
    private int end;

    private int target;

    ifexist(int arr[],int start, int end,int target){
        this.arr = arr;
        this.end = end;
        this.start = start;
        this.target = target;
    }

    @Override
    public void run() {
        for (int i = this.start; i < this.end ; i++){
            if (arr[i] == target){
                return;
            }
        }
    }
}


public class Q2{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.close();
        Random nos = new Random();
        int arr[] = new int[n];
        for (int i = 0 ; i < n; i++){
            int b = nos.nextInt(-1000000,1000000);
            arr[i] = b;
        }

        long start = System.nanoTime();
        tree_10 tree = new tree_10();
        for (int i = 0 ; i < n ; i ++){
            tree.root = tree.insertNode(tree.root,arr[i]);
        }
        long end = System.nanoTime();
        long time = end - start;
        System.out.println("Without threading : "+ time +" Nanoseconds");


        threads2 p1 = new threads2(arr,n,0,n/2);
        threads2 p2 = new threads2(arr,n,n/2,n);
        Thread t1 = new Thread(p1,"t1");
        Thread t2 = new Thread(p2,"t2");
        t1.start();
        t2.start();
        long start1 = System.nanoTime();
        try{
            t1.join();
            t2.join();
        }
        catch(InterruptedException e){
            System.out.println(e);
        }

        long end1 = System.nanoTime();
        long time1 = end1 - start1;
        System.out.println("With threading 2 : "+ time1 +" Nanoseconds");

        threads2 q1 = new threads2(arr,n,0,n/4);
        threads2 q2 = new threads2(arr,n,n/4,n/2);
        threads2 q3 = new threads2(arr,n,n/2,3*n/4);
        threads2 q4 = new threads2(arr,n,3*n/4,n);
        Thread t11 = new Thread(q1,"t1");
        Thread t22 = new Thread(q2,"t2");
        Thread t33 = new Thread(q3,"t2");
        Thread t44 = new Thread(q3,"t2");
        t11.start();
        t22.start();
        t33.start();
        t44.start();

        long start2 = System.nanoTime();
        try{
            t11.join();
            t22.join();
            t33.join();
            t44.join();
        }
        catch(InterruptedException e){
            System.out.println(e);
        }
        long end2 = System.nanoTime();
        long time2 = end2 - start2;
        System.out.println("With threading 4 : "+ time2 +" Nanoseconds");

        System.out.println("height of tree: " + tree.root.height_getter());

        int preorder[] = new int[n];
        preorder = tree.list(n);

        int target = nos.nextInt(0,arr.length-1);
        System.out.println("Index of target in inital array is : " + target + " and element is " + arr[target]);

        long start3 = System.nanoTime();
        for (int aa = 0 ; aa < n ; aa++){
            if (preorder[aa] == arr[target]){
                break;
            }
        }
        long end3 = System.nanoTime();
        long time3 = end3 - start3;
        System.out.println("For Searching an element without threading : "+ time3 +" Nanoseconds");


        ifexist test1 = new ifexist(preorder,0,n/2,arr[target]);
        ifexist test2 = new ifexist(preorder,n/2,n,arr[target]);
        Thread aat1 = new Thread(test1,"t1");
        Thread aat2 = new Thread(test2,"t1");
        long start4 = System.nanoTime();
        aat1.start();
        aat2.start();
        try{
            aat1.join();
            aat2.join();
        }
        catch (InterruptedException e){
            System.out.println(e);
        }
        long end4 = System.nanoTime();
        long time4 = end4 - start4 ;
        System.out.println("For Searching an element with 2 threading : "+ time4 +" Nanoseconds");

        ifexist test3 = new ifexist(preorder,0,n/4,arr[target]);
        ifexist test4 = new ifexist(preorder,n/4,n/2,arr[target]);
        ifexist test5 = new ifexist(preorder,n/2,3*n/4,arr[target]);
        ifexist test6 = new ifexist(preorder,3*n/4,n,arr[target]);
        Thread aat3 = new Thread(test3,"t1");
        Thread aat4 = new Thread(test4,"t1");
        Thread aat5 = new Thread(test5,"t1");
        Thread aat6 = new Thread(test6,"t1");
        long start5 = System.nanoTime();
        aat3.start();
        aat4.start();
        aat5.start();
        aat6.start();
        try{
            aat3.join();
            aat4.join();
            aat5.join();
            aat6.join();
        }
        catch (InterruptedException e){
            System.out.println(e);
        }
        long end5 = System.nanoTime();
        long time5 = end5 - start5 ;
        System.out.println("For Searching an element with 4 threading : "+ time5 +" Nanoseconds");
    }
}

