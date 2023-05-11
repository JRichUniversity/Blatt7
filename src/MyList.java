import java.util.Iterator;

public class MyList <T> implements Iterable<T> {
    int size;
    private Node<T> head;

    MyList(){
        head = null;
        size = 0;
    }

    private static class Node <T>{
        T content;
        Node<T> successor;
        Node<T> predecessor;

        Node(T content){
            this.content = content;
        }

        void add(Node<T> nextNode) {
            if(this.successor == null){
                this.successor = nextNode;
                this.successor.predecessor = this;
            } else {
                successor.add(nextNode);
            }
        }

        public Node<T> getIndex(int index){
            return getIndex(index, 0);
        }

        private Node<T> getIndex(int index, int current){
            if(index == current){
                return this;
            } else if (successor == null) {
                throw new IndexOutOfBoundsException("Index is out of the lists boundary.");
            } else {
                return successor.getIndex(index,++current);
            }
        }
    }

    /**
     * Adds a new node element to the list
     * @param content Content of new node
     */
    public void add(T content){
        Node<T> nextNode = new Node<>(content);
        if (head == null){
           head = nextNode;
        } else {
            head.add(nextNode);
        }
        ++size;
    }

    /**
     * Returns node at index
     *
     * @param index index of the element
     * @return Node at index
     */
    public Node<T> get(int index){
        try{
            return head.getIndex(index);
        }
        catch(IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int size(){
        return size;
    }

    private class ListIterator implements Iterator<T> {
        private Node<T> currentNode = head;
        private Node<T> previousNode;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            T data = currentNode.content;
            previousNode = currentNode;
            currentNode = currentNode.successor;
            return data;
        }

        @Override
        public void remove(){
            if(previousNode == head){
                head = currentNode;
            } else {
                previousNode.predecessor.successor = currentNode;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }
}
