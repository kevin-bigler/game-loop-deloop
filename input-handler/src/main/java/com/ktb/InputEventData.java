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
    Collection<ButtonInput> buttons;
}
