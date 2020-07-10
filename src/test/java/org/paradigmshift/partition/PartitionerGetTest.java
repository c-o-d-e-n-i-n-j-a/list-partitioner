package org.paradigmshift.partition;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class PartitionerGetTest {

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 100, 1000})
	public void get_ReturnsLists_WithAllPartitionsEqualToSpecifiedSizeWithLastContainingRemainder(final int partitionSize) {
		IntStream.range(1, 1000)
			.boxed()
			.map(new GenerateListOfSize())
			.map(source -> new Partitioner<>(partitionSize, source))
			.forEach(partitioner -> {
				for (int i = 0; i < partitioner.size() - 1; i++) {
					assertEquals(partitionSize, partitioner.get(i).size(), "Partition sizes must be equal to the specified partitionSize if it is not the last partition.");
				}
				assertTrue(partitionSize >= (partitioner.get(partitioner.size() - 1).size()), "Last partition size must be less than or equal to the specified partitionSize.");
			});
	}
	
	@Test
	public void get_ThrowsIndexOutOfBoundsException_WhenGivenAnIndexLessThan0() {
		final int partitionSize = 5;
		final List<Integer> source = new GenerateListOfSize().apply(5);

		final Partitioner<Integer> partitioner = new Partitioner<>(partitionSize, source);
		
		assertThrows(IndexOutOfBoundsException.class, () -> partitioner.get(-1), "get() must throw an IndexOutOfBoundsException when the index is less than 0.");
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 100, 1000})
	public void get_ThrowsIndexOutOfBoundsException_WhenEqualToPartitionSize(final int partitionSize) {
		IntStream.range(1, 1000)
			.boxed()
			.map(new GenerateListOfSize())
			.map(source -> new Partitioner<>(partitionSize, source))
			.forEach(partitioner ->
				assertThrows(IndexOutOfBoundsException.class, () -> partitioner.get(partitioner.size()), "get() must throw an IndexOutOfBoundsException when the index is equal to Partitioner.size().")
			);
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 100, 1000})
	public void get_ThrowsIndexOutOfBoundsException_WhenGreaterThanPartitionSize(final int partitionSize) {
		IntStream.range(1, 1000)
			.boxed()
			.map(new GenerateListOfSize())
			.map(source -> new Partitioner<>(partitionSize, source))
			.forEach(partitioner ->
				assertThrows(IndexOutOfBoundsException.class, () -> partitioner.get(partitioner.size() + 1), "Partitioner.get() must throw an IndexOutOfBoundsException when the index is greater than Partitioner.size().")
			);
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 100, 1000})
	public void get_ReturnsLists_WithAllElementsFromOriginalSource(final int partitionSize) {
		IntStream.range(1, 1000)
			.boxed()
			.map(new GenerateListOfSize())
			.forEach(source -> {
				final Partitioner<Integer> partitioner = new Partitioner<>(partitionSize, source);

				final List<Integer> aggregatedElements = new ArrayList<>();
				for (int i = 0; i < partitioner.size(); i++) {
					aggregatedElements.addAll(partitioner.get(i));
				}

				assertEquals(source.size(), aggregatedElements.size(), "The count of all elements in all partitions must equal the source size.");
				source.forEach(element -> {
					assertTrue(aggregatedElements.contains(element), "Each element in all partitions must be contained within the source.");
				});
			});
	}
}
