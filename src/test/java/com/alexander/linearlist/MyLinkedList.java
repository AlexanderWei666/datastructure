package com.alexander.linearlist;

public class MyLinkedList<T> {
    //链表头部
    private Node<T> first;
    //链表尾部
    private Node<T> rear;
    //链表长度
    private int size;

    //结点定义
    private static class Node<T> {
        private Node<T> next;
        private Node<T> prev;
        private T data;

        Node(T data) {
            this.data = data;
        }
    }

    /**
     * 从头部进行插入
     * 步骤：
     * 1.新结点的next链指向当前头结点；头结点的prev指向新结点
     * 2.将first指向新节点
     * 时间复杂度：O(1)
     *
     * @param data
     */
    public void insertFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if(isEmpty()){
            first = newNode;
            rear = newNode;
            size++;
            return;
        }
        //连接结点
        first.prev = newNode;
        newNode.next = first;
        //移动头指针
        first = newNode;
        size++;
    }

    /***
     * 从头部进行删除操作
     * 步骤：
     * 1.将头结点的next链置空
     * 2.将first引用指向第二个结点
     * 3.将first引用的prev置空
     * 时间复杂度为：O(1)
     * @return
     */
    public boolean deleteFirst() throws Exception {
        if(isEmpty()){
            throw new Exception("删除头结点：链表为空");
        }
        //保存下一个结点
        Node<T> secondNode = first.next;
        //断开一个引用
        first.next = null;
        //first引用指向下一个
        first = secondNode;
        if(first == null){
            rear = null;
        }else{
            //断开prev
            first.prev = null;
        }
        size--;
        return true;
    }

    /**
     * 取出第i个结点
     * 步骤：从头结点进行遍历，取第i个结点
     * 时间复杂度：O(n)，此操作对于利用数组实现的顺序存储结构，仅需常数阶O(1)即可完成。
     *
     * @param index
     * @return
     */
    public T get(int index) throws Exception {
        if (!checkIndex(index)) {
            throw new Exception("index不合法！");
        }
        Node<T> curr = first;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.data;
    }

    /**
     * 遍历线性表
     * 时间复杂度：O(n)
     */
    public void displayList() {
        Node<T> currNode = first;
        while (currNode != null) {
            System.out.print(currNode.data + " ");
            currNode = currNode.next;
        }
        System.out.println();
    }

    /**
     * 链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * index是否合法
     *
     * @param index
     * @return
     */
    private boolean checkIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * 链表大小
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 双端链表，从尾部进行插入
     * 步骤：将当前尾结点的next链指向新节点即可
     * 时间复杂度：O(1)
     * @param data
     */
    public void insertLast(T data){
        Node<T> newNode = new Node<>(data);
        if(isEmpty()){
            first = newNode;
            rear = newNode;
            size++;
            return;
        }
        //先连接引用
        newNode.prev = rear;
        rear.next = newNode;
        //移动rear引用至尾部
        rear = newNode;
        size++;
    }

    /**
     * 删除尾结点
     * 主要步骤：1.将rear指向倒数第二个结点 2.处理相关结点的引用链
     * 时间复杂度：O(1)
     * @return
     */
    public void deleteLast() throws Exception {
        if(isEmpty()){
            throw new Exception("删除尾结点：链表为空");
        }
        //保存上一个结点的引用
        Node<T> secondLast = rear.prev;
        //断开prev引用
        rear.prev = null;
        //移动rear引用
        rear = secondLast;
        if(rear == null){
            first = null;
        }else{
            //断开next引用
            rear.next = null;
        }
        size--;
    }

    /*test*/
    public static void main(String[] args) {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        //删除空表
        try {
            myLinkedList.deleteLast();
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            myLinkedList.deleteFirst();
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //从头部插入
        myLinkedList.insertFirst("D");
        myLinkedList.insertFirst("C");
        myLinkedList.insertFirst("B");
        myLinkedList.insertFirst("A");
        //从尾部插入
        myLinkedList.insertLast("E");
        //遍历线性表中元素
        myLinkedList.displayList();
        //获取第二个元素
        try {
            System.out.println(myLinkedList.get(1));
        } catch (Exception e){
            e.printStackTrace();
        }
        //删除头结点
        try {
            myLinkedList.deleteFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }
        myLinkedList.displayList();
        //删除尾结点
        try {
            myLinkedList.deleteLast();
        } catch (Exception e) {
            e.printStackTrace();
        }
        myLinkedList.displayList();
    }
}