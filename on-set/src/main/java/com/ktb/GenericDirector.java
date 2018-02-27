package com.ktb;

import lombok.Value;

import java.util.LinkedList;
import java.util.Queue;

public class GenericDirector implements Director {
    private final Queue<Event<? extends Actor>> queue = new LinkedList<>();

    @Override
    public <T extends Actor> void queue(final Action<? extends T> action, final T actor) {
        queue.add(new Event<>(action, actor));
    }

    @Override
    public void processQueue() {
        while (!queue.isEmpty()) {
            execute(queue.remove());
        }
    }

    private void execute(final Event event) {
        event.getAction().execute(event.getActor());
    }

    @Value
    private class Event<T extends Actor> {
        Action<? extends T> action;
        T actor;
    }
}
