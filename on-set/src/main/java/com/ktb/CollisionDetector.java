package com.ktb;

@FunctionalInterface
public interface CollisionDetector<A extends Actor, B extends Actor> {
    /**
     * Checks whether the two actors' hitboxes collide with one another
     *
     * @param a
     * @param b
     * @return
     */
    boolean collides(A a, B b);
}
