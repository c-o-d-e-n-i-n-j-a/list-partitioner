package org.paradigmshift.partition;

import java.util.*;
import java.util.function.BiFunction;

public class UnmodifiablePartitionFunction<T> implements BiFunction<Integer, List<T>, List<List<T>>> {

    private final PartitionFunction<T> partitionFunction = new PartitionFunction<>();

    @Override
    public List<List<T>> apply(final Integer partitionSize, final List<T> source) {
        Objects.requireNonNull(partitionSize);
        Objects.requireNonNull(source);

        return partitionFunction.apply(partitionSize, Collections.unmodifiableList(new ArrayList<>(source)));
    }
}
