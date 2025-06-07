package coding.recursion;

import java.util.HashMap;

class ClimbStairs {

    public static void main(String[] args) {
        int result = climbStairs(3);
        System.out.println(result);
    }

    public static int climbStairs(int n) {
        HashMap<Integer, Integer> cache = new HashMap<>();
        return climb(n, cache);
    }

    public static int climb(int n, HashMap<Integer, Integer> cache) {
        if (n == 0) {
            return 1;
        }
        if (n <= 0) {
            return 0;
        }
        Integer result1 = cache.get(n - 1);
        Integer result2 = cache.get(n - 2);

        if (result1 == null) {
            result1 = climb(n - 1, cache);
            cache.put(n - 1, result1);
        }

        if (result2 == null) {
            result2 = climb(n - 2, cache);
            cache.put(n - 2, result2);
        }

        return result1 + result2;

    }
}