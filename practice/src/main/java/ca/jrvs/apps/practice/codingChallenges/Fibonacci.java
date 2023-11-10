package ca.jrvs.apps.practice.codingChallenges;

/**
 *https://leetcode.com/problems/fibonacci-number/
 */
class Solution {
    /**
     * recursively calculates the fibonacci sequence
     * @param n nth sequence of fibonacci
     * @return the value of that sequence
     */
    public int fib(int n) {
        if (n==0 || n==1) {
            return n;
        }
        return fib(n-1) + fib(n-2);
    }
}