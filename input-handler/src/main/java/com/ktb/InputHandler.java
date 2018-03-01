package com.ktb;

/**
 * Converts input of some kind into {@link ButtonInput} and/or {@link InputEvent}
 */
public interface InputHandler extends Publisher<InputEvent, InputEventData> {

}
