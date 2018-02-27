package com.ktb;

public interface Action<T extends Actor> {
    void execute(T actor);
}
