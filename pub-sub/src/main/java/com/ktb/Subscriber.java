package com.ktb;

/**
 * The "sub" part of the pub-sub interface
 *
 * @param <E> the Event type
 * @param <D> the event Data type
 */
public interface Subscriber<E, D> {
    void onEvent(E event, D data);
}
