package com.ktb;

public interface Director {
    <T extends Actor> void queue(Action<? extends T> action, T actor);
    void processQueue();
}
