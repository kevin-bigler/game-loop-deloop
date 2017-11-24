package com.ktb.deloop;

import com.ktb.deloop.model.GameLoopSettings;
import com.ktb.deloop.service.GameLoop;
import com.ktb.deloop.service.GameLoopRunner;

import static com.ktb.deloop.constant.Constant.FPS_UPDATE_FREQ;
import static com.ktb.deloop.constant.Constant.MAX_DT;
import static com.ktb.deloop.constant.Constant.MIN_DT;
import static com.ktb.deloop.constant.Constant.SLEEP_TIME;

/**
 * TODO is this class necessary anymore? Repurpose it as the MAIN class, with a main() method? or phase it out?
 */
public class Game {
    private final GameLoopRunner runner;

    public Game() {
        final GameLoopSettings settings = GameLoopSettings.builder()
                .minDt(MIN_DT)
                .maxDt(MAX_DT)
                .sleepTime(SLEEP_TIME)
                .fpsUpdateFreq(FPS_UPDATE_FREQ)
                .build();
        final GameLoop gameLoop = (dt) -> {
            System.out.println("Game Loop Run. dt = " + dt);
        };
        runner = new GameLoopRunner(settings, gameLoop);
    }

    public void start() {
        runner.start();
    }

    public void stop() {
        runner.stop();
    }
}
