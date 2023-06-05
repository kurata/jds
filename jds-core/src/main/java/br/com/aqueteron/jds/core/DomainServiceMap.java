package br.com.aqueteron.jds.core;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

@Data
public class DomainServiceMap<T extends Enum<T>> {

    private final Class<T> enumClass;

    private final String resource;

    private final ToIntFunction<T> getIdFunction;

    private final Function<T, String> getKeyFunction;

    public List<T> getDomainValues() {
        return Arrays.asList(this.enumClass.getEnumConstants());
    }

    public Integer getId(final Enum<? extends Enum<?>> value) {
        return this.getIdFunction.applyAsInt((T) value);
    }

    public String getKey(final Enum<? extends Enum<?>> value) {
        return this.getKeyFunction.apply((T) value);
    }

}
