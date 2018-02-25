# Notes

## Major Components

`BobRoss` &rarr; in charge of drawing things to the window _(maybe `Artist` interface and BobRoss is one)_

---

`GameEventDispatcher` &rarr; **pub-sub** system. i.e. something says `EventDispatcher.subscribe(eventNameOrType, serviceWhoWantsToBeNotified);`

`GameEventCoordinator` &rarr; part of the **pub-sub** system, but focuses on _using_ the dispatcher by setting up subscriptions. 
In charge of setup. ie it's the one that calls `EventDispatcher.subscribe(...)`

---

`Controller` &rarr; accepts `ControllerInput` data and imposes actions on a subject based on the input.

- Ex: `PlayerController` would make a player character do something based on receiving input
- Ex: `CarController` would make a car do stuff upon receiving controller inputs

controllers should normally transform input into an `Action`, and add to an `ActionQueue` _(aka "Command" and "CommandQueue")_

** Queues can be tapped via a `QTapper` to be recorded. `QLoader` and `QReplay` can be used to replay a tapped (recorded) queue of `Action`s

---

`Director` &rarr; The `Director` is the only one who should be telling actors to do _anything_.  Other components 
may make **suggestions** to the director, but the director gets the final say as to what happens.

It probably makes sense to divide actors up under separate directors, all delegated by a main `LeadDirector` **composite**.

Ex: `EnemyDirector`, `Player1Director`, `Player2Director`, `NpcDirector`, `VehiclesDirector`

---

### Examples &mdash; Pub-Sub

**subscribe**

```java
subscribe(Event.LEVEL_PAUSE, startMenuService);
subscribe(Event.LEVEL_UNPAUSE, startMenuService);
subscribe(Event.CONTROLLER_INPUT, startMenuService);
...
subscribe(Event.LEVEL_LOAD, loadingService);
subscribe(Event.LEVEL_LOAD_COMPLETE, loadingService);
subscribe(Event.LEVEL_LOAD_COMPLETE, levelService); // ?
subscribe(Event.CONTROLLER_INPUT, playerControllerService); // ?
```

**unsubscribe**

```java
unsubscrible(Event.LEVEL_PAUSE, startMenuService);
... etc
```

**events**

```java
dispatch(Event.LEVEL_PAUSE, relevantDataPojo);
dispatch(Event.CONTROLLER_INPUT, controllerInputData);
```

#### Pub-Sub Interface

```java
interface Subscriber {
    void onEvent(Event event, Object data);
}
```

```java
interface Publisher {
    void subscribe(Event event, Subscriber subscriber);
    void unsubscribe(Event event, Subscriber subscriber);
    void dispatch(Event event, Object data);
}
```

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
            adjustVelocity(joystickXComponent);
        }
        if (data contains BUTTON_A) {
            if (BUTTON_A is ButtonState.DOWN) {
                jump();
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