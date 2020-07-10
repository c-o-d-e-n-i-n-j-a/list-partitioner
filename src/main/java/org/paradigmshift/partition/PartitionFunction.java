package org.paradigmshift.partition;

import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.function.BiFunction;

public class PartitionFunction<T> implements BiFunction<Integer, List<T>, List<List<T>>> {

    @Override
    public List<List<T>> apply(final Integer partitionSize, final List<T> source) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(partitionSize);

        if (source instanceof RandomAccess) {
            return new RandomAccessPartitioner<>(partitionSize, source);
        }
        return new Partitioner<T>(partitionSize, source);
    }
}
