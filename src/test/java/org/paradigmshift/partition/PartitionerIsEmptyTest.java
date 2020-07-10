package org.paradigmshift.partition;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PartitionerIsEmptyTest {

	@Test
	public void isEmpty_ReturnsTrue_WhenSourceIsEmpty() {
		final List<String> source = Collections.emptyList();
		final Partitioner<String> partitioner = new Partitioner<>(50, source);

		assertTrue(partitioner.isEmpty(), "isEmpty() must be true when source is empty.");
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 100})
	public void isEmpty_ReturnsFalse_WhenSourceIsNotEmpty(final int sourceSize) {
		final List<Integer> source = new GenerateListOfSize().apply(sourceSize);
		final Partitioner<Integer> partitioner = new Partitioner<>(50, source);

		assertFalse(partitioner.isEmpty(), "isEmpty() must be false when source is not empty.");
	}
}
