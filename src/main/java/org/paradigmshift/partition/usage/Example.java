package org.paradigmshift.partition.usage;

import org.paradigmshift.partition.PartitionFunction;
import org.paradigmshift.partition.UnmodifiablePartitionFunction;

import java.util.*;

public class Example {
    public static void main(final String[] args) {
        System.out.println("-------------------- Strings --------------------");

        final List<String> fruit = Arrays.asList("apple", "orange", "banana", "grape", "lemon", "blueberry", "strawberry", "raspberry", "peach", "mango");

        System.out.println("-- Mutable");
        new PartitionFunction<String>().apply(4, fruit)
            .forEach(System.out::println);

        System.out.println("-- Unmodifiable");
        new UnmodifiablePartitionFunction<String>().apply(4, fruit)
            .forEach(System.out::println);

        System.out.println("-------------------- Integers -------------------");

        final Set<Integer> uniqueNumbers = new HashSet<>();
        uniqueNumbers.add(1);
        uniqueNumbers.add(1);
        uniqueNumbers.add(2);
        uniqueNumbers.add(3);
        uniqueNumbers.add(5);

        System.out.println("-- Mutable");
        new PartitionFunction<Integer>().apply(3, new ArrayList<>(uniqueNumbers))
            .forEach(System.out::println);

        System.out.println("-- Unmodifiable");
        new UnmodifiablePartitionFunction<Integer>().apply(3, new ArrayList<>(uniqueNumbers))
            .forEach(System.out::println);
    }
}
