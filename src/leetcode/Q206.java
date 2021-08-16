package leetcode;

import common.Test;
import common.Utils;

/**
 * 206. 反转链表
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class Q206 implements Test {

    /**
     * 迭代
     * @param head
     * @return
     */
    private ListNode reverseList1(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode second = head.next;
        head.next = null;
        while (second != null){
            ListNode third = second.next;
            second.next = head;
            head = second;
            second = third;
        }
        return head;
    }

    /**
     * 递归
     * @param head
     * @return
     */
    private ListNode reverseList2(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode newHead = head;
        ListNode second = head.next;
        if(second != null){
            newHead = reverseList2(second);
            second.next = head;
            head.next = null;
        }
        return newHead;
    }

    private String traverse(ListNode head){
        StringBuilder s = new StringBuilder();
        while (head != null){
            s.append(head.val);
            if(head.next != null){
                s.append(" -> ");
            }
            head = head.next;
        }
        return s.toString();
    }

    private ListNode generateLinkedList(int size){
        ListNode head = null;
        ListNode current = null;
        for (int i = 1; i <= size; i++) {
            ListNode node = new ListNode(i);
            if(current == null){
                current = node;
                head = node;
            }else {
                current.next = node;
                current = node;
            }
        }
        return head;
    }

    private boolean validate(ListNode head, int size){
        if(head == null && size == 0) {
            return true;
        }
        ListNode current = head;
        for (int i = size; i > 0; i--) {
            if(current.val != i){
                return false;
            }
            current = current.next;
        }
        return true;
    }

    @Override
    public boolean test() {
        int size = Utils.generateRandomInteger(1, 20);
        ListNode head1 = generateLinkedList(size);
        ListNode head2 = generateLinkedList(size);
        Utils.print("原链表", traverse(head1));
        ListNode reversedHead1 = reverseList1(head1);
        Utils.print("迭代法反转", traverse(reversedHead1));
        ListNode reversedHead2 = reverseList2(head2);
        Utils.print("递归法反转", traverse(reversedHead2));
        return validate(reversedHead1, size) && validate(reversedHead2, size);
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q206(), 100);
    }
}


class ListNode {
  int val;
  ListNode next;
  ListNode() {}
  ListNode(int val) { this.val = val; }
  ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
