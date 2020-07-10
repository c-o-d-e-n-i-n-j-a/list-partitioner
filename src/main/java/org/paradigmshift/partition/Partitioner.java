package org.paradigmshift.partition;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class Partitioner<T> extends AbstractList<List<T>> {

	private final int partitionSize;
	private final List<T> source;

	Partitioner(final int partitionSize, final List<T> source) {
		this.partitionSize = partitionSize;
		this.source = Objects.requireNonNull(source);
	}

	@Override
	public int size() {
		return (source.size() + partitionSize - 1) / partitionSize;
	}

	@Override
	public boolean isEmpty() {
		return source.isEmpty();
	}

	@Override
	public List<T> get(final int index) throws IndexOutOfBoundsException {
		final int size = size();
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index = " + index + ", size = " + size);
		}

		return source.subList(start(index), end(index));
	}

	private int start(final int index) {
		return index * partitionSize;
	}

	private int end(final int index) {
		return Math.min((index + 1) * partitionSize, source.size());
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		final Partitioner<?> that = (Partitioner<?>) o;
		return partitionSize == that.partitionSize &&
			Objects.equals(source, that.source);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), source, partitionSize);
	}

	@Override
	public String toString() {
		return "Partitioner {size: " + size()
			+ ", partitionSize: " + this.partitionSize
			+ ", partitions: " + this.stream()
				.map(partition -> partition.toString())
				.collect(Collectors.joining(", ", "[", "]")) + "}";
	}
}
