package coding;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }


        //min heap
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparing(Map.Entry::getValue));

//        PriorityQueue<Map.Entry<Integer, Integer>> pq2 = new PriorityQueue<>((p, q) -> {
//            return p.getValue().compareTo(q.getValue());
//        });

        for (Map.Entry<Integer, Integer> i : map.entrySet()) {
            if (pq.size() >= k) {
                pq.poll();
            }
            pq.offer(i);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq2 = new PriorityQueue<>((p, q) -> {
            return p.getValue().compareTo(q.getValue());
        });

        pq2.addAll(pq);

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pq2.poll().getKey();
        }
        return result;
    }


}