package com.root;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Arrays;

import static java.lang.Integer.MAX_VALUE;

class QuickSortTest {

    private final EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom();
    private final int[] unsorted = random.ints().limit(MAX_VALUE / 20).toArray();
    private final int[] sorted = Arrays.stream(unsorted).sorted().toArray();

    @RepeatedTest(2)
    void quicksort_fjp_successful() {
        int[] actual = QuickSort.quicksort_fjp(unsorted);
        Assertions.assertThat(actual).isEqualTo(sorted);
    }

    @RepeatedTest(2)
    void traditionalSort_successful() {
        Arrays.sort(unsorted);
        Assertions.assertThat(unsorted).isEqualTo(sorted);
    }
}
