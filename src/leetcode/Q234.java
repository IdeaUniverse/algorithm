package leetcode;

import common.Test;
import common.Utils;

import java.util.Random;

/**
 * 234. 回文链表
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 */
public class Q234 implements Test {

    private boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2 != null) {    // p2前进两步，p1前进一步
            p2 = p2.next;
            if (p2 != null) {
                p2 = p2.next;
                if (p2 != null) {
                    p1 = p1.next;
                } else {
                    break;
                }
            }
        }
        p1 = reverseLinkedLis(p1);
        return contains(p1, head);
    }

    private ListNode reverseLinkedLis(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode first = head;
        while (first.next != null) {
            ListNode second = first.next;
            ListNode third = second.next;
            second.next = head;
            head = second;
            first.next = third;
        }
        return head;
    }

    private boolean contains(ListNode head1, ListNode head2) {
        while (head2 != null) {
            if (head1 == null) {
                return false;
            }
            if (head1.val != head2.val) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return true;
    }

    private ListNode generateLinkedList(int size, boolean palindrome) {
        int halfSize = size / 2;
        boolean even = size % 2 == 0;
        if (size < 1) {
            return null;
        }
        ListNode head = new ListNode(1);
        if(size == 1){
            return head;
        }
        ListNode node = head;
        for (int i = 1; i < halfSize; i++) {
            ListNode newNode = new ListNode(i + 1);
            node.next = newNode;
            node = newNode;
        }
        if(!even){
            node.next = new ListNode(halfSize + 1);
            node = node.next;
        }
        int diff = palindrome ? 0 : Utils.generateRandomInteger(1, halfSize + 1);
        for (int i = halfSize; i > diff; i--) {
            ListNode newNode = new ListNode(i);
            node.next = newNode;
            node = newNode;
        }
        return head;
    }

    /*以下是官方方法*/
    public boolean isPalindrome1(ListNode head) {
        if (head == null) {
            return true;
        }

        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    /*以上是官方方法*/

    @Override
    public boolean test() {
        int size = Utils.generateRandomInteger(1, 20);
        boolean palindrome = size < 3 || new Random().nextBoolean();
        ListNode head = generateLinkedList(size, palindrome);
        ListNode head1 = generateLinkedList(size, palindrome);

        Utils.print("链表", new Q206().traverse(head), "回文", palindrome, "size", size);
        boolean result = isPalindrome(head);
        boolean result1 = isPalindrome1(head1);
        Utils.print("计算结果", result);
        return result == palindrome && result == result1;
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q234(), 100);
    }
}
