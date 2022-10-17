package com.tw.javabasic;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenericTest {
    @SuppressWarnings("unused")
    @Test
    void should_auto_resolve_generic_method() {
        final String[] words = {"Hello", "Good", "Morning"};

        // TODO: please call getMiddle method for string
        // <--start
        final String middle = getMiddle(words);
        // --end-->

        assertEquals("Good", middle);
    }

    @Test
    void should_specify_a_type_restriction_on_typed_parameters() {
        final int minimumInteger = min(new Integer[]{1, 2, 3});
        final double minimumReal = min(new Double[]{1.2, 2.2, -1d});

        assertEquals(1, minimumInteger);
        assertEquals(-1d, minimumReal, 1.0E-05);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_not_know_generic_type_parameters_at_runtime() {
        final KeyValuePair<String, Integer> pair = new KeyValuePair<>("name", 2);
        final KeyValuePair<Integer, String> pairWithDifferentTypeParameter = new KeyValuePair<>(2, "name");

        // TODO: please modify the following code to pass the test
        // <--start
        final boolean expected = true;
        // --end-->

        assertEquals(expected, pair.getClass().equals(pairWithDifferentTypeParameter.getClass()));
    }

    @SuppressWarnings({"UnnecessaryLocalVariable", "unchecked", "unused"})
    @Test
    void should_be_careful_of_raw_type_generic() {
        final Pair<Manager> managerPair = new Pair<>();
        final Pair rawPair = managerPair;
        rawPair.setFirst(new Employee());

        boolean willThrow = false;
        try {
            Manager first = managerPair.getFirst();
        } catch (ClassCastException error) {
            willThrow = true;
        }

        // TODO: please modify the following code to pass the test
        // <--start
        final boolean expected = false;
        // --end-->

        assertEquals(expected, willThrow);
    }

    @SuppressWarnings("unused")
    private static <T> T getMiddle(T[] args) {
        return args[args.length / 2];
    }

    // TODO: please implement the following code to pass the test. It should be generic after all.
    // The method should only accept `Number` and the number should implement `Comparable<T>`
    // <--start
    @SuppressWarnings("unused")
    private static <T extends Number & Comparable<T>> T min(T[] values) {
        T minObj = values[0];
        for (int i = 1; i < values.length; i++) {
            if (minObj.compareTo(values[i]) > 0) minObj = values[i];
        }
        return minObj;
    }
    // --end-->
}
