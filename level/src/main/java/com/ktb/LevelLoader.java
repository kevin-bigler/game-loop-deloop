package com.ktb;

public class LevelLoader {
    // TODO: in constructor/init(), subscribe to GameEvent.LEVEL_WILL_LOAD and load levels when that fires
    /*
        TODO: change to use config or something, not a method per level (or at the very least, interface-ify this and do
        different implementations, rather than a string of methods)
    */
    public void loadLevelOne() {
        final LevelFactory levelFactory = new LevelFactory();
        final Level levelOne = levelFactory.getLevelOne();
        levelOne.init();
        levelOne.start();
    }
}
