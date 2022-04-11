package ua.khpi.oop.alekseenko14;

public class LinkedList<T> {
    private Node head;
    private int size;


    private static class Node <T> {
        private T data;
        Node next;

        // Constructor
        Node ( T data ) {
            this.data = data;
            this.next = null;
        }
    }

    public void push( T data ) {
        // Create a new node with given data
        Node new_node = new Node(data);
        new_node.next = null;

        // If the Linked List is empty,
        // then make the new node as head
        if (this.head == null) {
            this.head = new_node;
        }
        else {
            // Else traverse till the last node
            // and insert the new_node there
            Node last = this.head;
            while (last.next != null) {
                last = last.next;
            }

            // Insert the new_node at last node
            last.next = new_node;
        }

        this.size++;
    }

    public void remove( T data ) {
        if ( this.head == null ) {
            System.err.println("Error: Container is empty! Nothing to delete!");
            return;
        } else {
            Node node = this.head;

            if ( node.data == data ) {
                this.head = node.next;
                this.size--;
                return;
            }

            while  ( node.next != null ) {

                if ( node.next.data == data) {

                    if ( node.next.next == null ) {
                        node.next = null;
                    } else {
                        node.next = node.next.next;
                    }

                    this.size--;
                    break;
                }

                node = node.next;
            }
        }
    }

    public void removeByPos( int number ) {
        if ( this.head == null ) {
            System.err.println("Error: Container is empty! Nothing to delete!");
            return;
        } else {
            Node node = this.head;
            int count = 0;
            number--;

            while  ( node.next != null ) {
                if ( number == 0 ) {
                    this.head = node.next;
                } else if ( count == number ) {
                    if ( node.next.next == null ) {
                        node.next = null;
                        break;
                    } else {
                        node.next = node.next.next;
                    }
                }

                count++;
                node = node.next;
            }
        }

        this.size--;
    }

    public void clear() {
        // Delete chaining with making head as null
        this.head = null;
        this.size = 0;
    }

    public Object[] toArray() {
        Object[] result = new Object[this.size];

        Node currNode = this.head;

        for ( int i = 0; currNode != null; i++ ) {

            result[i] = currNode.data;
            // Go to next node
            currNode = currNode.next;
        }

        return result;
    }

    public void fromArray( Object[] o ) {
        this.clear();

        for (Object value : o) {
            this.push((T) value);
        }
    }

    @Override
    public String toString() {
        Node currNode = this.head;

        StringBuilder result = new StringBuilder();

        while ( currNode != null ) {

            result.append(currNode.data);
            // Go to next node
            currNode = currNode.next;
        }

        return result.toString();
    }

    public boolean contains( T data ) {
        Node currNode = this.head;

        while ( currNode != null ) {

            if ( currNode.data == data ) {
                return true;
            }

            // Go to next node
            currNode = currNode.next;
        }

        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // Method to print the LinkedList.
    public void printList() {
        Node currNode = this.head;

        // Check chain for empty
        if ( currNode == null ) {
            System.out.println("List is empty!");
        } else {
            System.out.println("Cars List:");

            // Traverse through the LinkedList
            while (currNode != null) {
                // Print the data at current node
                System.out.println(currNode.data + " ");

                // Go to next node
                currNode = currNode.next;
            }

            System.out.println();
        }
    }

    public int getSize () {
        return this.size;
    }
//    public void setSize ( int number ) { this.size = number; }

}


