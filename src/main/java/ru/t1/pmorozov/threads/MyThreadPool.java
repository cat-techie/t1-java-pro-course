package ru.t1.pmorozov.threads;

import java.util.ArrayList;
import java.util.LinkedList;

public class MyThreadPool {
    private final LinkedList<Runnable> tasks;
    private final ArrayList<MyThread> threads;
    private boolean shutdownFlag = false;

    public MyThreadPool(int capacity) {
        this.tasks = new LinkedList<>();
        this.threads = new ArrayList<>();

        for (int i = 0; i < capacity; i++) {
            MyThread myThread = new MyThread(tasks);
            threads.add(myThread);
            myThread.start();
        }

    }

    public void execute(Runnable task) {
        synchronized (tasks) {
            if (shutdownFlag) {
                throw new IllegalStateException("Thread pool closed");
            }
            tasks.add(task);
            tasks.notifyAll();
        }
    }

    public void shutdown() {
        shutdownFlag = true;
        for (MyThread thread : threads) {
            thread.interrupt();
        }
    }

    public void awaitTermination() {
        while (true) {
            boolean isComplete = threads.stream()
                    .map(th -> th.getState() == Thread.State.WAITING)
                    .reduce(Boolean::logicalAnd)
                    .orElse(false);
            sleepThread(100);
            if (isComplete && tasks.size() == 0) return;
        }
    }

    public void sleepThread(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
