import data.ListNode;

public class LeetCode25 {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) return head;
        int count = 0;
        ListNode dummy = head;
        while (dummy != null && count < k) {
            dummy = dummy.next;
            count++;
        }
        if (count < k) {
            return head;
        }
        ListNode current = head;
        ListNode prev = null;
        count = 0;
        while (current != null && count < k) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }
        head.next = reverseKGroup(current, k);
        return prev;
    }
}
