package com.ktb.deloop;

public class Game implements Runnable {
    private Thread thread;
    private boolean running = false;
    private static final double MAX_DT = 0.15;

    @Override
    public void run() {
        running = true;
        double lastTime = currentTime();
        double time = 0;
        double dt = 0;

        while (running) {
            time = currentTime();
            dt = lastTime - time;
            lastTime = time;

            if (dt > MAX_DT) {
                dt = MAX_DT;
            }

            getInput();
            update(dt);
            draw();
        }
    }

    private void getInput() {

    }

    private void update(double dt) {
        System.out.println("update called");
    }

    private void draw() {

    }

    private double currentTime() {
        return System.nanoTime() / Double.valueOf(10^9);
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
