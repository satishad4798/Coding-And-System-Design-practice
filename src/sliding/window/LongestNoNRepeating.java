package sliding.window;

import java.util.HashMap;

public class LongestNoNRepeating {

    public static void main(String[] args) {
        String s = "pwwkew";


        int longest = 0;

        HashMap<Character, Integer> hashmap = new HashMap();

        int left = 0;
        for (int i = 0; i < s.length(); i++) {

            char sss = s.charAt(i);
            // duplicate when key found in hashmap and also key location should be after
            // left pointer (otherwise its already removed )
            if (hashmap.containsKey(sss) && hashmap.get(sss) >= left) {
                longest = Math.max(longest, i - left);
                left = hashmap.get(sss) + 1;
                hashmap.put(sss, i);

            } else {
                hashmap.put(sss, i);
            }

        }
        int answer = Math.max(s.length() - left, longest);
    }


}
