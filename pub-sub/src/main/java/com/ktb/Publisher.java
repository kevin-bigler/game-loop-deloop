package com.ktb;

/**
 * The "pub" part of pub-sub interface
 *
 * @param <E> the Event type
 * @param <D> the event Data type
 */
public interface Publisher<E, D> {
    void subscribe(E event, Subscriber<? extends E, ? extends D> subscriber);
    void unsubscribe(E event, Subscriber<? extends E, ? extends D> subscriber);
    void dispatch(E event, D data);
}
