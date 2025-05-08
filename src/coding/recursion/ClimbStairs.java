package coding.recursion;

class ClimbStairs {

    public static vvoid main(String[] args) {
        int result = climbStairs(3);
        System.out.println(result);
    }


    public static int climbStairs(int n) {

        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }

        return climbStairs(n - 1) + climbStairs(n - 2);

    }
}