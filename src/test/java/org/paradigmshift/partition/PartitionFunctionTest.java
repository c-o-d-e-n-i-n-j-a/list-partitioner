package org.paradigmshift.partition;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PartitionFunctionTest {

    @Test
    public void apply_PartitionSize_IsRequired() {
        final List<Integer> source = new ArrayList<>();

        assertThrows(NullPointerException.class, () -> new PartitionFunction<Integer>().apply(null, source));
    }

    @Test
    public void apply_Source_IsRequired() {
        assertThrows(NullPointerException.class, () -> new PartitionFunction<Integer>().apply(5, null));
    }

    @Test
    public void apply_CreatesRandomAccessPartitioner_WhenSourceIsRandomAccess() {
        final List<Integer> source = new ArrayList<>();

        assertEquals(RandomAccessPartitioner.class, new PartitionFunction<Integer>().apply(5, source).getClass());
    }

    @Test
    public void apply_CreatesPartitionerWithoutRandomAccess_WhenSourceIsNotRandomAccess() {
        final List<Integer> source = new LinkedList<>();

        assertNotEquals(RandomAccessPartitioner.class, new PartitionFunction<Integer>().apply(5, source).getClass());
    }
}