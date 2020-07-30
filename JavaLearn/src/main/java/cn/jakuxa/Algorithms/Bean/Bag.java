package cn.jakuxa.Algorithms.Bean;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node next;
    }
    private Node first;

    public void add(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>{
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() { }
    }
}
