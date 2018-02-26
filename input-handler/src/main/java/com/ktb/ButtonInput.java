package com.ktb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * Describes a game input of a {@link Button} and its {@link ButtonState state}
 */
@Value
@Builder
@AllArgsConstructor
public class ButtonInput {
    Button button;
    ButtonState buttonState;
}
