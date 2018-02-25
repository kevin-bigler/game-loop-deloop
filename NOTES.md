# Notes

## Major Components

`BobRoss` &rarr; in charge of drawing things to the window _(maybe `Artist` interface and BobRoss is one impl)_

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

#### Director

Ultimately controls what actors do or don't do (ie player jump, menu button activate, enemy move left or stop, etc
-- OR prevents movement during a cutscene, grants invincibility by preventing taking damage, prevents jumping if hexed, etc)

```java
interface Director<T extends Actor> {
    void queue(Action<? extends T> action);
    void processQueue();
}
```

_It's implied that Directors are instantiated with an `Actor` subject_ under most circumstances.

```java
interface Action<T extends Actor> {
    void execute(T actor);
}
```

#### Example &mdash; Director

```java
Actor mario = new Mario();
Director marioDirector = new MarioDirector(mario);
marioDirector.queue(new MarioJumpAction());
...
marioDirector.processQueue();   // performs "jump" on "mario" if the Director OKs it
```

```java
class MarioDirector implements Director<Mario> {
    private final Queue<Action<? extends Mario>> queue = new LinkedList<>();
    private Mario actor;

    public MarioDirector(final Mario actor) {
        this.actor = actor;
    }

    @Override
    public void queue(final Action<Mario> action) {
        queue.add(action);
    }

    @Override
    public void processQueue() {
        while (!queue.isEmpty()) {
            // check if it's a jump action. if it is and mario is jumping, DON'T execute it -- just skip it
            queue.remove().execute(actor);
        }
    }
}
```

```java
class GenericDirector<T> implements Director<T> {
    private final Queue<Action<? extends T>> queue = new LinkedList<>();
    private T actor;

    public GenericDirector(final T actor) {
        this.actor = actor;
    }

    @Override
    public void queue(final Action<? extends T> action) {
        queue.add(action);
    }

    @Override
    public void processQueue() {
        while (!queue.isEmpty()) {
            queue.remove().execute(actor);
        }
    }
}
```

```java
class MarioJumpAction implements Action<Mario> {
    @Override
    public void execute(final Mario actor) {
        actor.modifyYVelocity(+1);
    }
}
```

**Ponder:** May not need to restrict Directors &amp; Actions to specific types of Actors, ie the Generics may be unnecessary.

```java
class Mario implements Actor {
    private final yVelocity = 0;

    public void modifyYVelocity(final int modifyAmount) {
        this.yVelocity += modifyAmount;
    }
}
```