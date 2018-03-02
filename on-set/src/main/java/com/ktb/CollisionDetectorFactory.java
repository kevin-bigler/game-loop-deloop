package com.ktb;

public class CollisionDetectorFactory {
    /**
     * <p>Returns {@link CollisionDetector} relevant to the two {@link Actor Actors} in question</p>
     *
     * <p>Does so by checking the two actors' {@link HitBox} types</p>
     *
     * TODO: should this be HitBox A, HitBox B instead of Actors A & B?  Seems odd to do Actors.
     * May be that Actor & HitBox are too tightly coupled if I feel I need info from both.
     *
     * TODO: maybe just supply an Offset object.  Also, is HitBox independent of location? ie should HitBox only have shape? nah...
     *
     * @param a
     * @param b
     * @param <A>
     * @param <B>
     * @return
     */
    public <A extends Actor, B extends Actor> CollisionDetector<? extends A, ? extends B> getCollisionDetector(A a,
                                                                                                               B b) {

    }
}
