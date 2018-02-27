# Pub-Sub Interface

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