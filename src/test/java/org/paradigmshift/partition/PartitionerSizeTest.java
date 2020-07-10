package org.paradigmshift.partition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PartitionerSizeTest {
	
	@Test
	public void testSize_Returns0_WhenSourceSizeIs0() {
		final List<String> source = Collections.emptyList();
		final Partitioner<String> partitioner = new Partitioner<>(50, source);

		assertTrue(partitioner.size() == 0, "size() must be 0 when source is empty.");
	}
	
	@Test
	public void testSize_Returns1_WhenSourceSizeIs10AndPartitionSizeIs10() {
		final int partitionSize = 10;
		final List<Integer> source = new GenerateListOfSize().apply(10);

		final Partitioner<Integer> partitioner = new Partitioner<>(partitionSize, source);
		assertTrue(partitioner.size() == 1, "size() must be 1 when source contains 10 elements and partition size is 10.");
	}
	
	@Test
	public void testSize_Returns2_WhenSourceSizeIs11AndPartitionSizeIs10() {
		final int partitionSize = 10;
		final List<Integer> source = new GenerateListOfSize().apply(11);

		final Partitioner<Integer> partitioner = new Partitioner<>(partitionSize, source);
		assertTrue(partitioner.size() == 2, "size() must be 2 when source contains 11 elements and partition size is 10.");
	}
	
	@Test
	public void testSize_Returns2_WhenSourceSizeIs30AndPartitionSizeIs10() {
		final int partitionSize = 10;
		final List<Integer> source = new GenerateListOfSize().apply(30);

		final Partitioner<Integer> partitioner = new Partitioner<>(partitionSize, source);
		assertTrue(partitioner.size() == 3, "size() must be 3 when source contains 30 elements and partition size is 10.");
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 100, 1000})
	public void testSize_ReturnsSourceSizeDividedByPartitionSizePlus1ForAnyRemainderElements(final int partitionSize) {
		IntStream.range(0, 1000)
			.boxed()
			.map(new GenerateListOfSize())
			.forEach(source -> {
				final Partitioner<Integer> partitioner = new Partitioner<>(partitionSize, source);
	
				final int baseNumberOfPartitions = (source.size() / partitionSize);
				final boolean hasRemainder = (source.size() % partitionSize > 0);
				final int expectedNumberOfPartitions = baseNumberOfPartitions + (hasRemainder ? 1 : 0);
	
				assertEquals(expectedNumberOfPartitions, partitioner.size(), 
					"size() must match the expected number of partitions. Expected: " + expectedNumberOfPartitions + ", Actual: " + partitioner.size());
			});
	}
}
