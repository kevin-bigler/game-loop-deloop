package com.ktb.deloop.service;

/**
 * Interface for a game loop
 */
@FunctionalInterface
public interface GameLoop {
    /**
     * Determine what to do on a game loop iteration, given delta time since last run
     *
     * @param dt double. Delta Time, ie how long since last run
     */
    void run(final double dt);
}
