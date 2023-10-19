package ca.jrvs.practice.sum_of_array;

//could use b-tree?
public class sum_of_array {

    static int score;
    static int sum;
    static int length;
    static int[] arr;

    public static void main(String[] args) {
        play(10);
    }

    public static void play(int len) {
        length = len;
        arr = new int[len];
        for (int i = 1; i <= len;i++){
            arr[i-1]=i;
        }
        sum = len*(len+1)/2;

        //while(score>10)
        //{
            System.out.println(round(11));
            System.out.println(round(10));;
        //}
    }

    public static int round(int n) {
        if (find(n)) {
            int temp = arr[0];
            arr[0]=arr[length-1];
            arr[length-1] = temp;
            return sum;
        }
        fastSum(n);
        arr[length-1] = n;
        return sum;
    }

    public static boolean find(int n) {
        for(int i = 0; i < length; i++) {
            if (i==n){
                return true;
            }
        }
        return false;
    }

    public static int fastSum(int n) {
        sum = sum-arr[length-1];
        sum = sum+n;

        return sum;
    }

    public static int slowSum() {
        sum = 0;
        for(int i = 0; i < length; i++){
            sum = sum+arr[sum];
        }
        return sum;
    }



}