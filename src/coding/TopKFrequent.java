package coding;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
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

        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i++) {
            result[i] = pq.poll().getKey();
        }
        return result;
    }

}