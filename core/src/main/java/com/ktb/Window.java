package com.ktb;

/**
 * Game Window on which to draw and receive input
 */
public interface Window extends AutoCloseable {
    /**
     * Opens the Window for drawing and receiving input, ie makes it visible and active
     */
    void open();

    /**
     * Whether the Window is open and active
     * @return
     */
    boolean isOpen();

    /**
     * Performs any per-frame updates (poll events, swap buffer, etc)
     */
    void update();
}
