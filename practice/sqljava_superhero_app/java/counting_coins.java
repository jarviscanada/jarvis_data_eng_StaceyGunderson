package ca.jrvs.practice.counting_coins;

import java.util.Arrays;

public class counting_coins{

    static int[] coins = {22,7,1};

    public static void main(String[] args) {

        System.out.println(Arrays.toString(count(0)));
        System.out.println(Arrays.toString(count(1)));
        System.out.println(Arrays.toString(count(7)));
        System.out.println(Arrays.toString(count(23)));
        System.out.println(Arrays.toString(count(30)));
        System.out.println(Arrays.toString(count(38)));


    }

    public static int[] count(int change){
        int index = 0;
        int number_of_coins = 0;
        int[] types_of_coins = {0,0,0};
        while (change > 0) {
            if (change >= coins[index]){
                change = change-coins[index];
                number_of_coins++;
                types_of_coins[index] = types_of_coins[index] + 1;
            }
            else {
                index ++;
            }
        }

        return types_of_coins;
    }
}
