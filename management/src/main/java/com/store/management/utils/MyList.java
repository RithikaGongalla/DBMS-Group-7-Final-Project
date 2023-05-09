package com.store.management.utils;
public class MyList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public MyList(Node<E> head, Node<E> tail, int size) {
  		super();
  		this.head = head;
  		this.tail = tail;
  		this.size = size;
  	}
      
    
    public MyList() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void add(E element) {
        Node<E> newNode = new Node<>(element);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

  
    

	public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.element;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head = head.next;
        } else {
            Node<E> prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            prev.next = prev.next.next;
            if (prev.next == null) {
                tail = prev;
            }
        }
        size--;
    }

    public int size() {
        return size;
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }
}
