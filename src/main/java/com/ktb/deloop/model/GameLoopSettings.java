package com.ktb.deloop.model;

import lombok.Builder;
import lombok.Data;

/**
 * POJO Model containing the settings for a Game Loop
 */
@Data
@Builder
public class GameLoopSettings {
    // TODO change settings to be in terms of min FPS, max FPS (then these values extrapolated from that)
    private double maxDt;
    private double minDt;
    private double fpsUpdateFreq;
    private long sleepTime;
}
