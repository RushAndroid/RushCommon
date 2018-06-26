package me.inrush.common.thread.handler;
/**
 * This is {@link Run#onBackground(Action)} and {@link Run#onUiAsync(Action)} (Action)} result class
 * In this you can check done and cancel the asynchronous task
 */

public interface Result {
    boolean isDone();

    void cancel();
}
