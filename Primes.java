import java.util.ArrayList;
import java.util.Scanner;

/**
 * <p>
 * Calculates the prime numbers up to an upper bound.
 * </p>
 * <p>
 * This program uses the Sieve of Eratosthenes to determine which numbers are
 * prime. The constructor takes an upper bound and then calculates the primes,
 * also providing some useful methods.
 * </p>
 *
 * @author Karlo Krakan
 * @version 1.0
 */
public class Primes {

    /**
     * <p>
     * An ArrayList<Boolean> where the index represents the number and the boolean
     * element represents whether the number is prime or not.
     * </p>
     */
    private ArrayList<Boolean> primes;

    /**
     * <p>
     * Constructor for the Primes object. n determines the upper bound of prime
     * numbers to find.
     * </p>
     * 
     * @param n the upper bound
     */
    public Primes(int n) {
        primes = new ArrayList<Boolean>(n + 1);
        calculatePrimes(n);
    }

    /**
     * <p>
     * Calculates which numbers are prime up to the upper bound n using the Sieve of
     * Eratosthenes.
     * </p>
     * 
     * @param n the upper bound
     */
    private void calculatePrimes(int n) {
        /* 0 and 1 are not prime, initialize the rest to true */
        for (int i = 0; i <= n; i++) {
            if (i == 0 || i == 1) {
                primes.add(false);
            } else {
                primes.add(true);
            }
        }
        /*
         * Cross out non-primes (the multiples of a prime number), starting with 2.
         */
        for (int i = 2; i <= Math.floor(Math.sqrt(n)); i++) {
            if (primes.get(i)) {
                setNonPrimes(i, n);
            }
        }
    }

    /**
     * <p>
     * Sets the multiples of prime numbers to false (they are not prime).
     * </p>
     * 
     * @param i the prime number
     * @param n the upper bound
     */
    private void setNonPrimes(int i, int n) {
        for (int j = 2; i * j <= n; j++) {
            primes.set(i * j, false);
        }
    }

    /**
     * <p>
     * Prints out all the prime numbers up to the upper bound n.
     * </p>
     */
    public void printPrimes() {
        for (int i = 0; i < primes.size(); i++) {
            if (primes.get(i)) {
                System.out.print(i + " ");
            }
        }
    }

    /**
     * <p>
     * Counts the number of primes up to the upper bound n.
     * </p>
     * 
     * @return the number of primes
     */
    public int countPrimes() {
        int count = 0;
        for (int i = 0; i < primes.size(); i++) {
            if (primes.get(i)) {
                count++;
            }
        }
        return count;
    }

    /**
     * <p>
     * Checks whether a number is prime, where the number to check must be between 0
     * and the upper bound n.
     * </p>
     * 
     * @param num the number to check
     * @return true if num is prime
     */
    public boolean isPrime(int num) {
        checkNum(num);
        return primes.get(num);
    }

    /**
     * <p>
     * Validates that a number is between 0 and the upper bound n. Throws
     * IllegalArgumentException otherwise.
     * </p>
     * 
     * @param num the number to check
     */
    private void checkNum(int num) {
        if (num < 0 || num >= primes.size()) {
            throw new IllegalArgumentException("num must be between " + "0 and " + (primes.size() - 1));
        }
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        int numberOfPrimes;

        System.out.println("This program uses the Sieve of Eratosthenes to " + "determine which numbers are prime.");
        System.out.println("Please enter upper bound:");

        do {
            try {
                numberOfPrimes = Integer.parseInt(inputScanner.nextLine());
                if (numberOfPrimes < 1) {
                    System.out.println("Please enter a number greater " + "than zero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number greater " + "than zero.");
                numberOfPrimes = 0;
            }
        } while (numberOfPrimes < 1);

        Primes primes = new Primes(numberOfPrimes);
        System.out.println("There are " + primes.countPrimes() + " primes:");
        System.out.println("The prime numbers between 0 and " + numberOfPrimes + " are:");
        primes.printPrimes();
        inputScanner.close();
    }

};
