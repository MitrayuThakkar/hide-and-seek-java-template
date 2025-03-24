package org.example;
import java.util.ArrayList;
/**
 * App.java
 *
 * Demonstrates linear and binary searches for a "gold" fuzzy within
 * a list of Fuzzy objects using FuzzyFinder. The main method
 * manually tests the search methods on both sorted and random
 * lists of fuzzies.
 */
public class App {

    public static void main(String[] args) {
        // Create a generator that can produce lists of fuzzies
        FuzzyListGenerator generator = new FuzzyListGenerator();

        // Create lists: one sorted and one random
        ArrayList<Fuzzy> sortedFuzzies = generator.sortedRainbowFuzzies();
        ArrayList<Fuzzy> randomFuzzies = generator.randomizedRainbowFuzzies();

        // Create an instance of our search utility
        FuzzyFinder finder = new FuzzyFinder();

        // Perform searches
        int sortedLinearIndex = finder.linearSearch(sortedFuzzies);
        int sortedBinaryIndex = finder.binarySearch(sortedFuzzies);
        int randomLinearIndex = finder.linearSearch(randomFuzzies);
        int randomBinaryIndex = finder.binarySearch(randomFuzzies);

        // Display the results
        System.out.println("Linear Search on Sorted List: index = " + sortedLinearIndex);
        System.out.println("Binary Search on Sorted List: index = " + sortedBinaryIndex);
        System.out.println("Linear Search on Random List: index = " + randomLinearIndex);
        System.out.println("Binary Search on Random List: index = " + randomBinaryIndex);

        // For clarity, let's see if the index is valid and actually gold
        // (Check only if index != -1)
        if (sortedLinearIndex != -1) {
            System.out.println("Sorted List at " + sortedLinearIndex + " is: "
                    + sortedFuzzies.get(sortedLinearIndex).color);
        }
        if (sortedBinaryIndex != -1) {
            System.out.println("Sorted List at " + sortedBinaryIndex + " is: "
                    + sortedFuzzies.get(sortedBinaryIndex).color);
        }
        if (randomLinearIndex != -1) {
            System.out.println("Random List at " + randomLinearIndex + " is: "
                    + randomFuzzies.get(randomLinearIndex).color);
        }
        if (randomBinaryIndex != -1) {
            System.out.println("Random List at " + randomBinaryIndex + " is: "
                    + randomFuzzies.get(randomBinaryIndex).color);
        }
    }
}

/**
 * A helper class containing linearSearch() and binarySearch()
 * methods to find the index of the fuzzy whose color is "gold".
 */
class FuzzyFinder {

    /**
     * Linear search to find the first fuzzy in the list
     * whose color is "gold". Returns the index if found,
     * otherwise returns -1.
     */
    public int linearSearch(ArrayList<Fuzzy> fuzzies) {
        for (int i = 0; i < fuzzies.size(); i++) {
            if ("gold".equalsIgnoreCase(fuzzies.get(i).color)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Binary search to find the fuzzy whose color is "gold"
     * in a SORTED list. Returns the index if found, otherwise -1.
     * (This may fail or produce incorrect results on a randomized list.)
     */
    public int binarySearch(ArrayList<Fuzzy> fuzzies) {
        int left = 0;
        int right = fuzzies.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            // Compare "gold" to fuzzies.get(mid).color
            int comparison = "gold".compareToIgnoreCase(fuzzies.get(mid).color);

            if (comparison == 0) {
                // Found the "gold" fuzzy
                return mid;
            } else if (comparison < 0) {
                // "gold" is 'less' than the mid color
                right = mid - 1;
            } else {
                // "gold" is 'greater' than the mid color
                left = mid + 1;
            }
        }
        return -1;
    }
}
