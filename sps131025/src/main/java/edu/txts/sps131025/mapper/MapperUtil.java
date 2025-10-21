package edu.txts.sps131025.mapper;

import java.util.List;
import java.util.stream.Collectors;

public class MapperUtil {
	public static <S, T> List<T> mapList(List<S> sourceList, java.util.function.Function<S, T> mapper) {
		return sourceList.stream().map(mapper).collect(Collectors.toList());
	}
}
