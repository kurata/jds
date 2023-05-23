package br.com.aqueteron.jds.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

@Builder
@AllArgsConstructor
@Data
public class DomainServiceMap<T extends Enum<T>> {

    private Class<T> enumClass;

    private String resource;

    private ToIntFunction<T> getIdFunction;

    private Function<T, String> getKeyFunction;

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
