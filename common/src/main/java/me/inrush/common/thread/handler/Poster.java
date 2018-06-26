package me.inrush.common.thread.handler;

/**
 * This is common poster to run same work by async or sync
 */
public interface Poster {
    int ASYNC = 0x10101010;
    int SYNC = 0x20202020;

    /**
     * Add a async post to Handler pool
     *
     * @param runnable Runnable
     */
    void async(Task runnable);

    /**
     * Add a async post to Handler pool
     *
     * @param runnable Runnable
     */
    void sync(Task runnable);


    /**
     * To dispose the resource
     */
    void dispose();
}
