import java.util.Random;
import java.util.Scanner;

class oddEvenwop{
    private Double arr[];
    oddEvenwop(Double arr[] ){
        this.arr = arr;
    }

    void sort(){
        long start = System.nanoTime();
        int n = arr.length;
        boolean sorted = false;
        // initally to sorted nahi hogya

        if (n == 0 || n == 1){
            long end = System.nanoTime();
            long execution = end - start;
            System.out.println("Execution time (not-threading): " + execution + "nanoseconds");
            return;
        }

        while (!sorted){
            sorted = true;
            Double temp = 0.0;
            for (int i = 1; i <= n-2 ;){
                if (arr[i] > arr[i+1]) {
                    temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    sorted = false;
                }
                i = i + 2;
            }

            for (int i = 0; i <= n-2 ;){
                if (arr[i] > arr[i+1]) {
                    temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    sorted = false;
                }
                i = i + 2;
            }
        }
        long end = System.nanoTime();
        long execution = end - start;
        System.out.println("Execution time (not-threading): " + execution + "nanoseconds");
        return;
    }
}

class Sorting implements  Runnable {
    private  Double[] arr;
    private int start;
    private int end;

    public Sorting(Double arr[] , int start, int end ){
        this.arr = arr;
        this.end =  end;
        this.start = start;
    }

    @Override
    public void run() {
        int n = this.end;
        boolean sorted = false;
        // initally to sorted nahi hogya

        if (n == 0 || n == 1){
            return;
        }

        while (!sorted){
            sorted = true;
            Double temp = 0.0;
            for (int i = this.start + 1; i <= n-2 ;){
                if (this.arr[i] > this.arr[i+1]) {
                    temp = this.arr[i];
                    this.arr[i] = this.arr[i+1];
                    this.arr[i+1] = temp;
                    sorted = false;
                }
                i = i + 2;
            }

            for (int i = this.start; i <= n-2 ;){
                if (this.arr[i] > this.arr[i+1]) {
                    temp = this.arr[i];
                    this.arr[i] = this.arr[i+1];
                    this.arr[i+1] = temp;
                    sorted = false;
                }
                i = i + 2;
            }
        }
        return;
    }
}


class oddEvenwp{
    private Double arr[];
    oddEvenwp(Double arr[]){
        this.arr = arr;
    }

    void sort(){
        int n = arr.length;
        Sorting s1 = new Sorting(arr,0,n/2);
        Sorting s2 = new Sorting(arr,n/2,n);
        Thread t1 = new Thread(s1,"s1");
        Thread t2 = new Thread(s2,"s2");
        long start = System.nanoTime();
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        }
        catch(InterruptedException e){
            System.out.println(e);
        }

        // [ 6.0 26.0 53.0 71.0 72.0 72.0 81.0 83.0 // 3.0 20.0 30.0 35.0 40.0 52.0 79.0 89.0 90.0 ]
//        for (int z = 0; z < arr.length ; z++){
//            System.out.print(arr[z] + " ");
//        }
//        System.out.println();

        int i = 0 , j = arr.length/2;
        int k = 0;
        double ans[] = new double[arr.length];
        while (i != arr.length / 2 && j != arr.length){
            if (arr[i] > arr[j]){
                ans[k] = arr[j];
                j++;
            }
            else{
                ans[k] = arr[i];
                i++;
            }
            k++;
        }
        try{
            while ( i != arr.length/2 && k != arr.length){
                ans[k] = arr[j];
                j++;
                k++;
            }
        }catch(Exception e){

        }
        while ( j != arr.length && k != arr.length){
            ans[k] = arr[j];
            j++;
            k++;
        }

//        for (int z = 0; z < arr.length ; z++){
//            System.out.print(ans[z] + " ");
//        }
//        System.out.println();
        long end = System.nanoTime();
        long time = end - start;
        System.out.println( "Execution time (threading): " + time + " nanoseconds.");
    }

}

public class Q1 {
    public static void main(String [] args){
        Random nos = new Random();
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.close();
        Double arr[] = new Double[n];
        Double arr1[] = new Double[n];

        for (int i = 0 ; i < n ; i++){
            int b = nos.nextInt(100,10000);
            Double a = (double)b/(double)1000;
            arr[i] = a;
            arr1[i] = a;
        }
        oddEvenwop s1 = new oddEvenwop(arr);
        s1.sort();

        oddEvenwp s2 = new oddEvenwp(arr1);
        s2.sort();

    }
}
