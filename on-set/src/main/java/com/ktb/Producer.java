package com.ktb;

/**
 * In charge of arranging and disbanding/cleaning up {@link Actor Actors} and {@link Director Directors}, in addition
 * to any auxiliary components (e.g. Controllers)
 */
public interface Producer extends AutoCloseable {
    void init();
    void start();
    void restart();     // this mainly feels useful for the development process

    /**
     * by default, this is an alias for {@link #close()}
     */
    void wrapUp();

    /**
     * {@inheritDoc}
     *
     * <p>by default, this is an alias for {@link #wrapUp()}</p>
     *
     * @throws Exception
     */
    @Override
    default void close() throws Exception {
        wrapUp();
    }
}
