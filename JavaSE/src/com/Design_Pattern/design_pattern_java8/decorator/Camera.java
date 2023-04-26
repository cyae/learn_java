package com.Design_Pattern.design_pattern_java8.decorator;

import java.util.function.Function;
import java.util.stream.Stream;

@SuppressWarnings("all")
public class Camera {

    private Function<Color, Color> filter;

    public Camera(Function<Color, Color>... filters) {
        setFilters(filters);
    }

    private void setFilters(Function<Color, Color>... filters) {
        // Function<Color, Color> initalFilter = color -> color;
        // BinaryOperator<Function<Color, Color>> accumulate = (oneFilter,
        // anotherFilter) -> oneFilter
        // .andThen(anotherFilter);
        // filter = Stream.of(filters)
        // .reduce(initalFilter, accumulate);

        // 等价于
        filter = Stream.of(filters)
                .reduce(Function.identity(), Function::andThen);
    }

    public Color snap(Color input) {
        return filter.apply(input);
    }
}
