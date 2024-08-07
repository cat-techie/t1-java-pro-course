package ru.t1.pmorozov.threads;

import java.util.LinkedList;

public class MyThread extends Thread {
    private final LinkedList<Runnable> tasks;

    public MyThread(LinkedList<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while (true) {
            Runnable task;
            synchronized (tasks) {
                while (this.tasks.isEmpty()) {
                    try {
                        this.tasks.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                task = this.tasks.removeFirst();
            }
            try {
                task.run();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }
}
