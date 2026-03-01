import java.util.HashMap;
import java.util.Map;

public class LeetCode146 {
    static class Node {
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

        Node prev;
        Node next;
        int key;
        int val;
    }

    Node head;
    Node tail;
    int capacity;
    Map<Integer, Node> store = new HashMap<>();

    public LeetCode146(int capacity) {
        this.capacity = capacity;
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        this.head.next = tail;
        this.tail.prev = head;
    }

    public int get(int key) {
        if (!store.containsKey(key)) {
            return -1;
        }
        Node valToReturn = store.get(key);
        removeNode(valToReturn);
        addToTop(valToReturn);
        return valToReturn.val;
    }

    private void removeNode(Node valToReturn) {
        valToReturn.prev.next = valToReturn.next;
        valToReturn.next.prev = valToReturn.prev;
    }

    private void addToTop(Node valToReturn) {
        valToReturn.prev = head;
        valToReturn.next = head.next;
        head.next.prev = valToReturn;
        head.next = valToReturn;
    }

    public void put(int key, int value) {
        if (store.containsKey(key)) {
            Node valToUpdate = store.get(key);
            removeNode(valToUpdate);
            valToUpdate.val = value;
            addToTop(valToUpdate);
            store.put(key, valToUpdate);
            return;
        }
        if (store.size() >= capacity) {
            Node valToRemove = tail.prev;
            store.remove(valToRemove.key);
            removeNode(valToRemove);
        }

        Node nextVal = new Node(key, value);
        store.put(key, nextVal);
        addToTop(nextVal);
    }
}
