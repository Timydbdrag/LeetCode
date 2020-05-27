package leetcode;

import org.w3c.dom.ls.LSOutput;

import javax.crypto.spec.PSource;
import java.util.LinkedList;
import java.util.List;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(4);
        listNode = insert(listNode,3);
        listNode = insert(listNode,2);
        listNode = insert(listNode,5);
        listNode = insert(listNode,1);
        listNode = insert(listNode,7);



/*        while (listNode!=null){
            System.out.println(listNode.val);
            listNode=listNode.next;
        }*/
        insertionSortList(listNode);
    }

    public static ListNode insertionSortList(ListNode head) {
        //Runtime: 3 ms, faster than 82.74% of Java online submissions for Insertion Sort List.
        //Memory Usage: 39.1 MB, less than 25.64% of Java online submissions for Insertion Sort List.
        if(head==null || head.next==null)return head;
        ListNode temp = head;
        ListNode start = head;
        while (true){
            if(temp.next == null) break;
            if(temp.next.val < temp.val){
                ListNode del = temp.next;
                temp.next = temp.next.next;
                ListNode start2 =start;
                while (true){
                    if(del.val < start.val){
                        del.next = start;
                        start = del;
                        break;
                    } else if(del.val < start2.next.val){
                        del.next = start2.next;
                        start2.next = del;
                        break;
                    } else  start2 = start2.next;
                }

            } else temp = temp.next;

        }
        head = start;

        while (head!=null){
            System.out.println(head.val);
            head = head.next;
        }

        return head;
    }

    public static void reorderList(ListNode head) {

        //2ms
        if(head != null && head.next != null) {
            LinkedList<ListNode> listNodes = new LinkedList<>();
            ListNode temp = head;
            while (temp.next != null) {
                listNodes.add(temp.next);
                temp = temp.next;
            }
            temp = head;
            int i = 0;
            while (listNodes.size() != 0){
                if(i%2 == 0){
                    temp.next = listNodes.pollLast();
                    temp = temp.next;
                    temp.next = null;
                } else  {
                    temp.next = listNodes.pollFirst();
                    temp = temp.next;
                    temp.next = null;
                }
                i++;
            }

/*    //long time 775ms
      ListNode temp = head;
            ListNode start = head;
            while (true){
                temp = temp.next;
                if(temp == null || temp.next == null)break;
                if(temp.next.next == null){
                    ListNode cur = temp.next;
                    temp.next = null;
                    ListNode cur2 = start.next;
                    start.next = cur;
                    cur.next = cur2;
                    temp = cur2;
                    start = cur.next;
                }
            }
        }*/

        }

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
