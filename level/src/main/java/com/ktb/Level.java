package com.ktb;

/**
 * Represents a Level in a game, encompassing its lifecycle events (ie loading, unloading, pausing, etc)
 */
public interface Level extends AutoCloseable {
    void init();
    void start();
    void pause();
    void unpause();
    boolean isPaused(); // TODO: not sure this one is necessary
    void end();
}
