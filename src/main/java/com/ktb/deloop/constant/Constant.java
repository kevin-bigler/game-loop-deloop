package com.ktb.deloop.constant;

/**
 * Constants for our application
 */
public final class Constant {
    // Config
    // TODO extract these config values to an application.yaml
    public static final double MAX_DT = 3.0 / 60.0;
    public static final double MIN_DT = 1.0 / 60.0;
    public static final double FPS_UPDATE_FREQ = 1.0;
    public static final long SLEEP_TIME = 1;

    private Constant() {
        throw new UnsupportedOperationException();
    }
}
