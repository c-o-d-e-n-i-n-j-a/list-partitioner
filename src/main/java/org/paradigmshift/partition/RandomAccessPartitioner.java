package org.paradigmshift.partition;

import java.util.List;
import java.util.RandomAccess;

class RandomAccessPartitioner<T> extends Partitioner<T> implements RandomAccess {
    RandomAccessPartitioner(final int partitionSize, final List<T> source) {
        super(partitionSize, source);
    }
}
