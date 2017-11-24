package com.ktb.deloop.model;

import lombok.Builder;
import lombok.Data;

/**
 * POJO Model containing the settings for a Game Loop
 */
@Data
@Builder
public class GameLoopSettings {
    private double maxDt;
    private double minDt;
    private double fpsUpdateFreq;
    private long sleepTime;
}
