package coding.array;

import java.util.HashMap;
import java.util.Map;

class long_sub {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("aab"));
    }

    public static int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> cache = new HashMap<>();
        int max = 0;
        int current = 0;
        int left = 0;
        int right = 0;
        if (s.length() < 2) {
            return s.length();
        }
        cache.put(s.charAt(0), 0);
        for (int i = 1; i < s.length(); i++) {
            Character current_char = s.charAt(i);
            if (cache.containsKey(current_char) && cache.get(current_char) >= left) {

                left = cache.get(current_char);
                System.out.println(left);
                left++;
                right++;
                max = Math.max(right - left, max);
                cache.put(current_char, i);

            } else {
                cache.put(current_char, i);
                right++;
                current++;
                max = Math.max(right - left + 1, max);
            }
            System.out.println("left:" + left + " right:" + right);
            // System.out.println(i+":"+max);
        }

        return Math.max(right - left, max);
    }
}