/**
 * https://leetcode.com/problems/climbing-stairs/
 */
class Solution {
    /**
     * Counts number of ways to 1 or 2 steps to a step
     * @param n the number of steps
     * @return number of ways to get to that
     */
    public int climbStairs(int n) {
        int f[] = new int[n + 3];
        f[1] = 1;
        f[2] = 2;

        for (int i = 3; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n]
    }
}