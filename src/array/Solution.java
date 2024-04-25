package array;

class test {
    public static void main(String[] args) {

        int[] jj = new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4
        };
        totalFruit(jj);
    }

    public static int totalFruit(int[] fruits) {

        // return solu1(fruits);
        return solution2(fruits);

    }

    static int solution2(int[] fruits) {

        int key1 = -1;
        int key2 = -1;
        int max = 0;
        int current_max = 0;
        int freq1 = 0;

        for (int i : fruits) {

            if (i == key1 || i == key2) {
                current_max++;
            } else {
                current_max = freq1 + 1;
                max = Math.max(max, current_max);

            }
            if (i == key1) {
                freq1++;
            } else {
                key2 = key1;
                key1 = i;
                freq1 = 1;
            }
        }
        return Math.max(max, current_max);

    }

    int solu1(int[] fruits) {

        int i = 1;
        int result_max = 0;

        int basket1 = fruits[0];
        int basket1_freq = 1;

        int basket2 = -1;
        int basket2_freq = 0;

        int left = 0;

        while (i < fruits.length) {

            // if aleady picked
            if (fruits[i] == basket1) {
                basket1_freq++;
            } else if (basket2 == -1) {
                basket2 = fruits[i];
                basket2_freq = 1;
            } else if (basket2 == fruits[i]) {
                basket2_freq++;
            }
            // discard basket1
            else {
                result_max = Math.max(result_max, (basket1_freq + basket2_freq));

                while (basket1_freq != 0 && basket2_freq != 0) {
                    if (fruits[left] == basket1)
                        basket1_freq--;
                    else
                        basket2_freq--;
                    left++;
                }
                if (basket1_freq == 0) {
                    basket1 = fruits[i];
                    basket1_freq = 1;
                } else {
                    basket2 = fruits[i];
                    basket2_freq = 1;
                }

            }
            // System.out.println(i + ":" + basket1_freq + ":" + basket2_freq);

            i++;
        }
        result_max = Math.max(result_max, (basket1_freq + basket2_freq));

        return result_max;
    }
}