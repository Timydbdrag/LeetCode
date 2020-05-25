package leetcode;

import java.util.List;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode = insert(listNode,1);
        listNode = insert(listNode,2);
        listNode = insert(listNode,3);
        listNode = insert(listNode,3);


/*        while (listNode!=null){
            System.out.println(listNode.val);
            listNode=listNode.next;
        }*/
        deleteDuplicates1(listNode);
    }

    public static ListNode deleteDuplicates1(ListNode head) {
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted List.
        //Memory Usage: 39.3 MB, less than 7.14% of Java online submissions for Remove Duplicates from Sorted List.
        if(head==null || head.next==null) return head;
        ListNode temp = head;
        while (temp.next!=null){
            if (temp.val == temp.next.val)temp.next=temp.next.next;
            else temp = temp.next;
        }
        while (head!=null){
            System.out.println(head.val);
            head = head.next;
        }
        return head;
    }

    public static ListNode deleteDuplicates(ListNode head) {

        //TASK 82
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted List II.
        //Memory Usage: 39.4 MB, less than 6.98% of Java online submissions for Remove Duplicates from Sorted List II.
        if(head==null || head.next==null) return head;
        ListNode temp = head;
        ListNode prev = new ListNode(0);
        ListNode start = prev;
        ListNode cur;
        while (temp.next != null){
            if(temp.val==temp.next.val){
                cur = temp;
                while (cur.val == temp.val){
                    if(cur.next!=null){
                        cur = cur.next;
                        System.out.println("del " + temp.val);
                    } else {
                        if(cur.val == temp.val) cur = null;
                        break;
                    };
                }
                if(cur!=null && cur.next!=null){
                    temp.val = cur.val;
                    temp.next = cur.next;
                } else {
                    prev.next = cur;
                    prev = prev.next;
                    break;
                }
                continue;
            } else {
                prev.next = temp;
                temp = temp.next;
                prev = prev.next;
                if(temp.next!=null) prev.next = null;
                System.out.println("temp >>>> " + temp.val);
            }
        }
        head = start.next;
        while (head!=null){
            System.out.println(head.val);
            head = head.next;
        }
        return head;
    }


    public static ListNode rotateRight(ListNode head, int k) {
        if(head==null || head.next==null || k==0)return head;
        int n = 1;
        int i = 0;

        while (true){
            ListNode temp = head;
            while (temp!=null && temp.next!=null){
                System.out.println("i > " + i);
                if(i==0)n++;
                if(temp.next.next == null){
                    if(k%n==0){
                        i=k-1;
                        break;
                    }
                    else if(i==0) {
                        k=k%n;
                        System.out.println("i" + k);
                    }
                    ListNode newNode = new ListNode(temp.next.val);
                    newNode.next = head;
                    head = newNode;
                    temp.next = null;
                }
                if(temp != null)temp = temp.next;
            }
            i++;
            if(i==k)break;
        }




        System.out.println(">>>   "+n);
        while (head!=null){
            System.out.println(head.val);
            head = head.next;
        }

        return head;
    }

    public static ListNode insert(ListNode list, int data)
    {
        ListNode new_node = new ListNode(data);
        new_node.next = null;
        if (list == null) {
            list = new_node;
        }
        else {
            ListNode last = list;
            while (last.next != null) {
                last = last.next;
            }
            last.next = new_node;

        }
        return list;
    }


}
