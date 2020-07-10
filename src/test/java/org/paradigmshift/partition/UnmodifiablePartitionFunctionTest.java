package org.paradigmshift.partition;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class UnmodifiablePartitionFunctionTest {

    @Test
    public void apply_PartitionSize_IsRequired() {
        final List<Integer> source = new ArrayList<>();

        assertThrows(NullPointerException.class, () -> new UnmodifiablePartitionFunction<Integer>().apply(null, source));
    }

    @Test
    public void apply_Source_IsRequired() {
        assertThrows(NullPointerException.class, () -> new UnmodifiablePartitionFunction<Integer>().apply(5, null));
    }

    @Test
    public void partitionsContainAllFromSource() {
        final List<Integer> source = new GenerateListOfSize().apply(10);

        final List<List<Integer>> integers = new UnmodifiablePartitionFunction<Integer>().apply(5, source);

        assertEquals(source, new ArrayList<>(integers.stream().flatMap(List::stream).collect(Collectors.toList())));
    }

    @Test
    public void modifyingSourceDoesNotChangePartitions() {
        final List<Integer> source = new GenerateListOfSize().apply(10);

        final List<List<Integer>> integers = new UnmodifiablePartitionFunction<Integer>().apply(5, source);

        source.add(11);

        assertNotEquals(source, new ArrayList<>(integers.stream().flatMap(List::stream).collect(Collectors.toList())));
    }

    @Test
    public void add_ThrowsUnsupportedOperationException() {
        final List<Integer> source = new GenerateListOfSize().apply(10);
        final List<List<Integer>> integers = new UnmodifiablePartitionFunction<Integer>().apply(5, source);

        assertThrows(UnsupportedOperationException.class, () -> integers.add(Arrays.asList(1)));
    }

    @Test
    public void addAll_ThrowsUnsupportedOperationException() {
        final List<Integer> source = new GenerateListOfSize().apply(10);
        final List<List<Integer>> integers = new UnmodifiablePartitionFunction<Integer>().apply(5, source);

        assertThrows(UnsupportedOperationException.class, () -> integers.addAll(
            Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2)
            )
        ));
    }

    @Test
    public void removeByObject_ThrowsUnsupportedOperationException() {
        final List<Integer> source = new ArrayList<>();
        source.add(1);

        final List<List<Integer>> integers = new UnmodifiablePartitionFunction<Integer>().apply(5, source);
        final List<Integer> partition = integers.get(0);

        assertThrows(UnsupportedOperationException.class, () -> integers.remove(partition));
    }

    @Test
    public void removeByIndex_ThrowsUnsupportedOperationException() {
        final List<String> source = new ArrayList<>();
        source.add("1");
        final List<List<String>> strings = new UnmodifiablePartitionFunction<String>().apply(5, source);

        assertThrows(UnsupportedOperationException.class, () -> strings.remove(0));
    }

    @Test
    public void removeAll_ThrowsUnsupportedOperationException() {
        final List<Integer> source = new ArrayList<>();
        source.add(1);
        source.add(2);

        final List<List<Integer>> integers = new UnmodifiablePartitionFunction<Integer>().apply(5, source);

        assertThrows(UnsupportedOperationException.class, () -> integers.removeAll(integers));
    }

    @Test
    public void addElementToPartition_ThrowsUnsupportedOperationException() {
        final List<Integer> source = new GenerateListOfSize().apply(10);
        final List<List<Integer>> integers = new UnmodifiablePartitionFunction<Integer>().apply(5, source);

        integers.stream().forEach(partition -> {
            assertThrows(UnsupportedOperationException.class, () -> partition.add(11));
        });
    }

    @Test
    public void addAllElementsToPartition_ThrowsUnsupportedOperationException() {
        final List<Integer> source = new GenerateListOfSize().apply(10);
        final List<List<Integer>> integers = new UnmodifiablePartitionFunction<Integer>().apply(5, source);

        integers.stream().forEach(partition -> {
            assertThrows(UnsupportedOperationException.class, () -> partition.addAll(
                Arrays.asList(11, 12)
            ));
        });
    }

    @Test
    public void removeFromPartitionByObject_ThrowsUnsupportedOperationException() {
        final List<Integer> source = new GenerateListOfSize().apply(10);
        final List<List<Integer>> integers = new UnmodifiablePartitionFunction<Integer>().apply(5, source);

        integers.stream().forEach(partition -> {
            assertThrows(UnsupportedOperationException.class, () -> partition.remove(Integer.valueOf(1)));
        });
    }

    @Test
    public void removeFromPartitionByIndex_ThrowsUnsupportedOperationException() {
        final List<Integer> source = new GenerateListOfSize().apply(10);
        final List<List<Integer>> integers = new UnmodifiablePartitionFunction<Integer>().apply(5, source);

        integers.stream().forEach(partition -> {
            assertThrows(UnsupportedOperationException.class, () -> partition.remove(0));
        });
    }

    @Test
    public void removeAllFromPartition_ThrowsUnsupportedOperationException() {
        final List<Integer> source = new GenerateListOfSize().apply(10);
        final List<List<Integer>> integers = new UnmodifiablePartitionFunction<Integer>().apply(5, source);

        integers.stream().forEach(partition -> {
            assertThrows(UnsupportedOperationException.class, () -> partition.removeAll(partition));
        });
    }
}