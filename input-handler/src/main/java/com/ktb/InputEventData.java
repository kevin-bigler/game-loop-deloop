package com.ktb;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Collection;

/**
 * Model containing the data that describes input state at any given point in time -- ie, what buttons are pressed vs
 * not pressed
 */
@Value
@AllArgsConstructor
public class InputEventData {
    /*
        TODO: should player1 vs player2 be defined as separate collections? or different buttons? different buttons
        would make controllers less portable so probably collections...
     */
    Collection<ButtonInput> buttons;
}
