package com.ktb.deloop.service;

import com.ktb.deloop.model.GameLoopSettings;

/**
 * Wraps a Game Loop, taking care of things like delays, frame time deltas, etc
 */
// TODO separate the thread handling and runnable from this class
public class GameLoopRunner implements Runnable {
    private final GameLoopSettings settings;
    private final GameLoop gameLoop;
    private Thread thread;
    private boolean running;

    public GameLoopRunner(final GameLoopSettings settings, final GameLoop gameLoop) {
        this.settings = settings;
        this.gameLoop = gameLoop;
    }

    @Override
    public void run() {
        running = true;
        double lastTime = currentTime();
        double time = 0;
        double dt = 0;
        double sleepStart = 0;
        double lastFps = currentTime(); // when we record fps, 1x/sec
        double durationSinceLastFps = 0;
        double fps = 0;
        double frames = 0;

        while (running) {
            // delta time (dt) calculation
            time = currentTime();
            dt = time - lastTime;
            lastTime = time;

            // limit the number of times we process per second
            while (dt < settings.getMinDt()) {
                sleepStart = currentTime();
                try {
                    Thread.sleep(settings.getSleepTime());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dt += currentTime() - sleepStart;
            }

            if (dt > settings.getMaxDt()) {
                dt = settings.getMaxDt();
            }

            // fps calculation
            frames++;
            durationSinceLastFps = currentTime() - lastFps;
            if (durationSinceLastFps >= settings.getFpsUpdateFreq()) {
                fps = frames / durationSinceLastFps;
                frames = 0;
                lastFps = currentTime();
                System.out.println("fps: " + fps);
            }

            gameLoop.run(dt);
        }
    }

    public double currentTime() {
        return System.nanoTime() / 1.0E9;
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
