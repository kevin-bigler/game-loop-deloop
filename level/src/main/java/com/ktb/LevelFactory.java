package com.ktb;

/**
 * It loads da levels
 */
public class LevelFactory {
    // TODO: later on, change factory methods to utilize config in order to build/load level
    public Level getLevelOne() {
        return new LevelOne();
    }
}
