package org.example;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * AppTest.java
 *
 * Contains JUnit tests to ensure that our linearSearch and
 * binarySearch methods work as intended on both sorted and random lists.
 */
public class AppTest {


    public void testLinearSearchOnSorted() {
        FuzzyListGenerator generator = new FuzzyListGenerator();
        ArrayList<Fuzzy> sortedFuzzies = generator.sortedRainbowFuzzies();

        FuzzyFinder finder = new FuzzyFinder();
        int index = finder.linearSearch(sortedFuzzies);

        // The index should not be -1, since the list definitely has a gold fuzzy.
        assertTrue(index >= 0, "Linear search on sorted list should find gold fuzzy.");
        // Check that the fuzzy at that index is indeed gold
        assertEquals("gold", sortedFuzzies.get(index).color.toLowerCase());
    }

   public void testBinarySearchOnSorted() {
        FuzzyListGenerator generator = new FuzzyListGenerator();
        ArrayList<Fuzzy> sortedFuzzies = generator.sortedRainbowFuzzies();

        FuzzyFinder finder = new FuzzyFinder();
        int index = finder.binarySearch(sortedFuzzies);

        // The index should not be -1 if the list is truly sorted and includes "gold."
        assertTrue(index >= 0, "Binary search on sorted list should find gold fuzzy.");
        // Check that the fuzzy at that index is indeed gold
        assertEquals("gold", sortedFuzzies.get(index).color.toLowerCase());
    }
    public void testLinearSearchOnRandom() {
        FuzzyListGenerator generator = new FuzzyListGenerator();
        ArrayList<Fuzzy> randomFuzzies = generator.randomizedRainbowFuzzies();

        FuzzyFinder finder = new FuzzyFinder();
        int index = finder.linearSearch(randomFuzzies);

        // The index should not be -1, because there is definitely a gold fuzzy.
        assertTrue(index >= 0, "Linear search on random list should find gold fuzzy.");
        assertEquals("gold", randomFuzzies.get(index).color.toLowerCase());
    }

    public void testBinarySearchOnRandom() {
        FuzzyListGenerator generator = new FuzzyListGenerator();
        ArrayList<Fuzzy> randomFuzzies = generator.randomizedRainbowFuzzies();

        FuzzyFinder finder = new FuzzyFinder();
        int index = finder.binarySearch(randomFuzzies);

        // Because the randomFuzzies list is NOT sorted, binary search may fail.
        // We'll just check: if we do find an index, see if it's actually gold.
        if (index >= 0) {
            // Sometimes it might get lucky, but usually it's not correct.
            // We'll see if the fuzzy found is gold.
            assertEquals("gold", randomFuzzies.get(index).color.toLowerCase(),
                    "Binary search on a random list *might* incorrectly find a non-gold fuzzy.");
        } else {
            // It's likely -1, or it might happen to find it by chance.
            // We'll just pass the test acknowledging it "didn't find gold" in an unsorted list.
            System.out.println("Binary search did not find gold in an unsorted list (expected).");
        }
    }
}
