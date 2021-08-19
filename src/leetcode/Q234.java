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
        if(head == null){
            return false;
        }
        if(head.next != null && head.next.next == null){// 只有两个节点
            return head.val == head.next.val;
        }
        ListNode first = head;
        while (first.next != null){ // 反转链表的前一半
            ListNode second = first.next;
            ListNode third = second.next;
            second.next = head;
            head = second;
            first.next = third;
            if(third == null || third.val == second.val){
                // 全部反转完都没遇到两个一样的节点，或者遇到两个一样的节点，跳出
                break;
            }
        }
        if(first.next == null){
            return false;
        }
        ListNode head1 = first.next;
        first.next = null;
        while (head != null){ // 用反转的前一半和后一半逐个比较 原本应是 while (head != null || head1 != null){ 但 head1 != null 恒为true
            if(head.val != head1.val){
                return false;
            }
            head = head.next;
            head1 = head1.next;
            if((head == null && head1 != null) ||
                    (head1 == null && head != null)){
                return false;
            }
        }
        return true;
    }

    private ListNode generateLinkedList(int halfSize, boolean palindrome){
        if(halfSize < 1){
            return null;
        }
        ListNode head = new ListNode(1);
        ListNode node = head;
        for (int i = 1; i < halfSize; i++) {
            ListNode newNode = new ListNode(i + 1);
            node.next = newNode;
            node = newNode;
        }
        int diff = palindrome ? 0 : 2;
        for (int i = halfSize; i > diff ; i--) {
            ListNode newNode = new ListNode(i);
            node.next = newNode;
            node = newNode;
        }
        return head;
    }

    @Override
    public boolean test() {
        int halfSize = Utils.generateRandomInteger(1,100);
        boolean palindrome = halfSize == 1 || new Random().nextBoolean();

        ListNode head = generateLinkedList(halfSize, palindrome);

        Utils.print("链表", new Q206().traverse(head), "回文", palindrome, "size", halfSize);
        boolean result = isPalindrome(head);
        Utils.print("计算结果", result);
        return result == palindrome;
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q234(), 1000);
    }
}
