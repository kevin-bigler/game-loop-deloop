package com.ktb;

/**
 * Types of input events: BEGIN, UPDATE, STOP essentially. AKA controller connected, input read, controller disconnected
 */
public enum InputEvent {
    /**
     * Input state has been captured and publishable as an updated state (not necessarily meaning <em>different</em> --
     * just <em>current</em>
     */
    INPUT_UPDATE,
    /**
     * For when connection has been made to an input device, and input states will now be monitored
     */
    INPUT_ACTIVATED,
    /**
     * For when connection is ended with an input device, and input will no longer be monitored
     */
    INPUT_DEACTIVATED;
}
