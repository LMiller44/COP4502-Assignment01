// Lester Miller
// COP4520 Parallel and Distributed Processing
// Professor Juan Parra
// Assignment 01
// Spring 2023
// Due January 27, 2023

// Use 8 threads to find all primes between 1 and 10^8.
// Output: execution time, total primes found, sum of all primes, top ten max primes

import java.lang.Math;
import java.util.concurrent.*;

public class Assignment01 {

    public static void main(String[] args) {
        
        long startTime = System.currentTimeMillis();

        // we start the algorithm having already confirmed that 2 is a prime digit
        int count = 1;
        long sum = 2;

        // creates 8 threads
        ExecutorService exService = Executors.newFixedThreadPool(8);

        for (int i = 3; i <= 10000000; i++) {
            boolean isPrime = true;

            // skips all even numbers
            if (i % 2 == 0){
                i++;
            }
                

            for (int j = 2; j <= (Math.sqrt(i)); j++) {
                //System.out.println(i+ " mod " +j+ " = " + i%j);
                if (i % j == 0) {                    
                    //System.out.println(i+ " is not prime.\n");
                    isPrime = false;
                    break;
                }
            }

            if (isPrime == true) {
                //System.out.println(i +" is prime.\n");
                count++;
                sum = sum + i;
            }
        }

        System.out.println("Total number of primes found: " +count);
        System.out.println("Sum of all found primes: " +sum);

        exService.shutdown();

        long endTime = System.currentTimeMillis();
        System.out.println("Total run time: " +(endTime - startTime) +"ms");
    }
}