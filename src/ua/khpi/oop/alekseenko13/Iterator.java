package ua.khpi.oop.alekseenko13;

import java.util.Objects;
import java.util.function.Consumer;

public interface Iterator<E> {
    public boolean hasNext();
    public E next();
    public void remove();
    public void reset();

    default void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (hasNext())
            action.accept(next());
    }
}
