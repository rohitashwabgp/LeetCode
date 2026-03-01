import data.ListNode;

public class LeetCode92 {
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || right - left == 0) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        ListNode curr = prev.next;

        for (int i = 0; i < right - left; i++) {
             ListNode temp = curr.next;
             curr.next = temp.next;
             temp.next = prev.next;
             prev.next = temp;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println(reverseBetween(head, 2, 4));
    }
}
