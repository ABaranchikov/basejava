package ru.javawebinar.basejava;

import static java.lang.Thread.sleep;

public class TestDeadlock {
    private static final Object LOCK1 = new Object();
    private static final Object LOCK2 = new Object();

    private static class Task1 implements Runnable {
        @Override
        public void run() {
            synchronized (LOCK1) {
                System.out.println(Thread.currentThread().getName() + " lock LOCK1");
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " try get LOCK2");
                synchronized (LOCK2) {
                    System.out.println(Thread.currentThread().getName() + " lock LOCK2");
                }
            }
        }
    }

    private static class Task2 implements Runnable {
        public void run() {
            synchronized (LOCK2) {
                System.out.println(Thread.currentThread().getName() + " lock LOCK2");
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " try get LOCK1");
                synchronized (LOCK1) {
                    System.out.println(Thread.currentThread().getName() + " lock LOCK1");
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Task1());
        Thread thread2 = new Thread(new Task2());
        thread1.start();
        thread2.start();
    }
}

