package com.ktb.deloop;

public class Game implements Runnable {
    private Thread thread;
    private boolean running = false;
    private static final double MAX_DT = 3.0 / 60.0;
    private static final double MIN_DT = 1.0 / 60.0;
    private static final double FPS_UPDATE_FREQ = 1.0;
    private static final long SLEEP_TIME = 1;

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
            while (dt < MIN_DT) {
                sleepStart = currentTime();
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dt += currentTime() - sleepStart;
            }

            if (dt > MAX_DT) {
                dt = MAX_DT;
            }

            // fps calculation
            frames++;
            durationSinceLastFps = currentTime() - lastFps;
            if (durationSinceLastFps >= FPS_UPDATE_FREQ) {
                fps = frames / durationSinceLastFps;
                frames = 0;
                lastFps = currentTime();
                System.out.println("fps: " + fps);
            }

            getInput();
            update(dt);
            draw();
        }
    }

    private void getInput() {

    }

    private void update(final double dt) {
//        System.out.println("update called, dt = " + dt);
    }

    private void draw() {

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
