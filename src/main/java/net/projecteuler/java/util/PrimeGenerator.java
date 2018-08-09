package net.projecteuler.java.util;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class PrimeGenerator {

	private BitSet primes = null;

	private int limit;

	public PrimeGenerator(int limit) {
		this.limit = limit;
		this.primes = new BitSet(limit / 2 + 1);

		primes.set(0, primes.size(), true);

		primes.set(0, false);
		primes.set(1, true);
		int rootMP = (int) Math.floor(Math.sqrt(limit));
		int halfMax = limit / 2;

		// get next prime
		for (int i = 3; i <= rootMP;) {
			// use prime to mark off all mutiples as not prime
			for (int j = ((i * 3) / 2); j <= halfMax; j += i) {
				primes.set(j, false);
			}

			// just want to move up index's while not prime
			// rolled out like this to prevent needing division to get index
			i += 2;
			int k = i / 2;
			while (primes.get(k) == false) {
				k++;
			}
			i = (k * 2) + 1;
		}
	}

	public int getLimit() {
    	return limit;
    }

	/**
	 * Get the given prime from the prime sequence by its index.
	 * @param index
	 * @return
	 */
	public int getPrimeByIndex(int index) {
		int i = 2;
		for (int counter = 0; counter < index && i <= limit; i++) {
			if (isPrime(i)) {
				++counter;
			}
		}
		return i - 1;
	}

	/**
	 * Gets the prime index.
	 * @param number
	 * @return -1 if the number is not prime.
	 */
	public int getIndex(int number) {
		if (!isPrime(number)) {
			return -1;
		}

		int index = 0;
		for (int i = 0; i <= number && i <= limit; i++) {
			if (isPrime(i)) {
				++index;
			}
		}

		return index; 
	}

	public List<Integer> getPrimeList() {
		// for how this works see http://primes.utm.edu/howmany.shtml
		int estimateNumPrimes = (int) (1.02 * (limit / (Math.log(limit)-1)));

		ArrayList<Integer> primeList = new ArrayList<Integer>(estimateNumPrimes);
		primeList.add(2);
		for(int i = 0; i <= limit / 2; i++) {
			if(primes.get(i)) {
				primeList.add((i * 2) + 1);
			}
		}

		return primeList;
	}

	public List<Long> getSemiPrimeList() {
		List<Long> semiPrimeList = new ArrayList<Long>();
		for(int i = 0; i <= limit / 2; i++) {
			if(primes.get(i)) {
				for(int j = i; j <= limit / 2; j++) {
					if (primes.get(j)) {
						semiPrimeList.add((long) (((i * 2) + 1) * ((i * 2) + 1)));
					}
				}
			}
		}

		return semiPrimeList;
	}

	public boolean isPrime(int number) {
		if (number == 2) {
			return true;
		}
		if (number % 2 == 0) {
			return false;
		}
		return primes.get((number - 1) / 2);
	}

	public static void main(String[] args) {
		long t = System.currentTimeMillis(); 
		PrimeGenerator sieve = new PrimeGenerator(1000000000);
		System.out.println(System.currentTimeMillis() - t);
		int counter = 0;
		for (int i = 0; i <= sieve.getLimit(); i++) {
			if (sieve.isPrime(i)) {
				//System.out.println(i);
				++counter;
			}
		}
		System.out.println(counter);
	}
}