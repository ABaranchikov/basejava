package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.Executors.newCachedThreadPool;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10_000;
    private static int counter;
    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }
        };
        thread0.start();
        new Thread(() -> System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState())).start();
        System.out.println(thread0.getState());

        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newCachedThreadPool();
        //List<Thread> list = new ArrayList<>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            executorService.submit(()->{
          //  Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                     counter();
                   // inc();
                }
                latch.countDown();
            });

          //  thread.start();
            //list.add(thread);
        }
   /*     list.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
*/
        try {
            latch.await();
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter);
    }

    private static synchronized void counter() {
        counter++;
    }

    private static void inc(){
        double x = Math.sin(10);
        synchronized (LOCK) {
            counter++;
        }
    }

}
