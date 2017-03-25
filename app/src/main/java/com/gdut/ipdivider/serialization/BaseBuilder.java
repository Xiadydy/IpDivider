package com.gdut.ipdivider.serialization;

public abstract class BaseBuilder<T, E>
{
    public abstract T build(E paramE);

    public abstract void deconstruct(T paramT);
}
