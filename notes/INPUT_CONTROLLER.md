
`Controller` &rarr; accepts `ControllerInput` data and imposes actions on a subject based on the input.

- Ex: `PlayerController` would make a player character do something based on receiving input
- Ex: `CarController` would make a car do stuff upon receiving controller inputs

controllers should normally transform input into an `Action`, and add to an `ActionQueue` _(aka "Command" and "CommandQueue")_

** Queues can be tapped via a `QTapper` to be recorded. `QLoader` and `QReplay` can be used to replay a tapped (recorded) queue of `Action`s

---

`InputHandler` &rarr; to convert actual system input to `InputEvent` special objects - dispatches. So InputHandler is **pub-sub** also! Its subscribers are
generally Controllers. Make sure the subscribers have an **option to kill propagation** of an InputEvent (like you can do in js with event handlers)


`InputReceiver`, `InputHandler` &rarr; pub-sub taking system input and converting to game input message dispatch

If we do it like Interceptors or Filters (like in spring web mvc), then we can have control as to whether the input
continues propogating down the chain. Like how js event handlers work.

:thinking_face:

But do we really want that capability?

### Examples &mdash; Controllers

Controllers in the sense of like a gamepad/mouse/joystick/etc

**Setup**

```java
Boss bowser = new BowserBoss();
Controller bowserController = new BossController(bowser);

Player mario = new MarioPlayer();
Controller marioController = new MarioController(mario);

Enemy  dryBones = new DryBones();
Controller dryBonesController = new DryBonesController(dryBones);

marioController.acceptInput({BUTTON_A:ButtonState.DOWN, BUTTON_B:ButtonState.UP, JOYSTICK_LEFT:{angle:180, magnitude:80%}});
bowserController.acceptInput({BUTTON_A:ButtonState.DOWN});
dryBonesController.acceptInput({BUTTON_LEFT:ButtonState.DOWN});
```

**Inside the Controller**

```java
class MarioController implements Controller {
    @Override
    void acceptInput(final ControllerInputData data) {
        if (data contains JOYSTICK_LEFT) {
            // break down the data into x & y vertices (ie x = -60%, y = +80%)
            adjustVelocity(joystickXComponent); // this should be changed to queueing an Action
        }
        if (data contains BUTTON_A) {
            if (BUTTON_A is ButtonState.DOWN) {
                jump(); // this should be changed to queueing an Action
            } else {
                // don't jump
            }
        }
    }
}
```

#### Controller interface

```java
interface Controller {
    void acceptInput(ControllerInputData data);
}
```