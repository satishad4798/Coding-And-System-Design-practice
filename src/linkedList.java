public class linkedList {

    public static void main(String[] args) {

        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

        convertArrayToLIST(array);

        ListNode test, test2;


    }

    private static void convertArrayToLIST(int[] array) {
        //convert coding.array to linked List.
        ListNode head = new ListNode(array[0]);
        ListNode current = head;
        for (int i = 1; i < array.length; i++) {
            current.next = new ListNode(array[i]);
            current = current.next;
        }

        ListNode iterate = head;
        while (iterate != null) {
            System.out.println(iterate.val);
            iterate = iterate.next;
        }
    }


}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class DoubleListNode {
    int val;
    ListNode prev, next;

    DoubleListNode(int x) {
        val = x;
    }
}