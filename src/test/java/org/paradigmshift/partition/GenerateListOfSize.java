package org.paradigmshift.partition;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GenerateListOfSize implements Function<Integer, List<Integer>> {
	@Override
	public List<Integer> apply(final Integer size) {
		Objects.requireNonNull(size);
		return IntStream.range(0, size)
			.mapToObj(i -> Integer.valueOf(i))
			.collect(Collectors.toList());
	}
}
