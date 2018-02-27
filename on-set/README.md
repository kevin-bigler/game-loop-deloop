# On-Set: Directors, Actors, Actions, and Producers

- **Directors** &mdash; specify what actors should do and when
- **Actors** &mdash; do stuff
- **Actions** &mdash; describe something to do
- **Producers** &mdash; get everybody together, in charge of set-up and take-down

## Lights, Camera, Action

### Actor

`Actor` &rarr; the star of the show! Not really.  Anyone can be an actor.  Just does what it's told to do via a `Director`,
imposed upon via an `Action`

### Action

`Action &rarr; just says what to do. Ex: Move left, jump, stop, select, wait, explode, die

### Director

`Director` &rarr; The `Director` is the only one who should be telling actors to do _anything_.  Other components
may make **suggestions** to the director, but the director gets the final say as to what happens.

It probably makes sense to divide actors up under separate directors, all delegated by a main `LeadDirector` **composite**.

Ex: `EnemyDirector`, `Player1Director`, `Player2Director`, `NpcDirector`, `VehiclesDirector`

Ultimately controls what actors do or don't do (ie player jump, menu button activate, enemy move left or stop, etc
-- OR prevents movement during a cutscene, grants invincibility by preventing taking damage, prevents jumping if hexed, etc)

### Producer

Gets Directors and Actors all set up together. Also separates/removes them as necessary.  Not hands-on, just gets the ball rolling
(and stops it).

## Interfaces

**Ponder:** May not need to restrict Directors &amp; Actions to specific types of Actors, ie the Generics may be unnecessary.

### Actor

```java
interface Actor {
    // might be abstract class... mainly just properties in the common "interface"
    // -- ie: x, y, xVelocity, yVelocity, xAcceleration, yAcceleration
}
```

### Action

```java
interface Action<T extends Actor> {
    void execute(T actor);
}
```

### Director

#### Option 1

```java
interface Director<T extends Actor> {
    void queue(Action<? extends T> action);
    void processQueue();
}
```

_It's implied that Directors are instantiated with an `Actor` subject_, under most circumstances, in this interface.

#### Option 2

Include `Actor` in the `queue()` method, so that a `Director` can be in charge of more than one `Actor` ***(probably the way to go)***

```java
interface Director {
    <T extends Actor> void queue(Action<? extends T> action, T actor);
    void processQueue();
}
```

### Producer

Can probably be whatever. I don't know, I'm just wingin' it.

## Examples

```java
Actor mario = new Mario();
Director marioDirector = new MarioDirector(mario);
marioDirector.queue(new MarioJumpAction());
...
marioDirector.processQueue();   // performs "jump" on "mario" if the Director OKs it
```

### Actor

```java
class Mario implements Actor {
    private final yVelocity = 0;

    public void modifyYVelocity(final int modifyAmount) {
        this.yVelocity += modifyAmount;
    }
}
```

### Action

```java
class MarioJumpAction implements Action<Mario> {
    @Override
    public void execute(final Mario actor) {
        actor.modifyYVelocity(+1);
    }
}
```

### Director

#### Ex: Mario

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

#### Ex: Generic

```java
class GenericDirector<T> implements Director<T> {
    private final Queue<Action<? extends T>> queue = new LinkedList<>();
    private T actor;

    public GenericDirector(final T actor) {
        this.actor = actor;
    }

    @Override
    public <T extends Actor> void queue(final Action<? extends T> action) {
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

