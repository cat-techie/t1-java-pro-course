package ru.t1.pmorozov;

import ru.t1.pmorozov.threads.MyThreadPool;

public class Main {

    static int THREAD_COUNT = 4;
    static int NUMBER_OF_TASKS = 9;

    public static void main(String[] args) {
        System.out.printf("Create MyThreadPool with %s capacity%n", THREAD_COUNT);
        MyThreadPool myThreadPool = new MyThreadPool(THREAD_COUNT);

        for (int i = 0; i < NUMBER_OF_TASKS; i++) {
            int finalI = i;
            myThreadPool.execute(() -> {
                System.out.printf("Start task: %s | Thread: %s%n", finalI, Thread.currentThread().getName());
                myThreadPool.sleepThread(900);
                System.out.printf("Stop task: %s | Thread: %s%n", finalI, Thread.currentThread().getName());
            });
        }

        System.out.println("Await termination");
        myThreadPool.awaitTermination();
        System.out.println("Shutdown threads");
        myThreadPool.shutdown();
    }
}