import data.ListNode;

public class LeetCode19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int k = 0;
        ListNode next = head;
        int size = 1;
        ListNode dummy = head;
        while (dummy != null) {
            dummy = dummy.next;
            size++;
        }
        while (k < (size - n) && next != null) {
            next = next.next;
            k++;
        }
        if (next == null) return head;

        if (next.next != null) {
            next.next = next.next.next;
        }
        return head;
    }

    public ListNode removeNthFromEndOp(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = dummy;

        // Move fast n+1 steps
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Remove
        slow.next = slow.next.next;

        return dummy.next;
    }
}
