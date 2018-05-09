package ru.javawebinar.basejava;

public class TestDeadlock {
    private static final Object LOCK1 = new Object();
    private static final Object LOCK2 = new Object();
    private static volatile boolean isCheck = false;

    private static class Task1 implements Runnable {
        @Override
        public void run() {
            System.out.println("Task1 run");
            synchronized (LOCK1) {
                synchronized (LOCK2) {
                    isCheck = true;
                    try {
                        System.out.println("Task1 wait (isCheck = " + isCheck + ")");
                        while (isCheck) {
                            LOCK2.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Task1 stop");
                }
            }
        }
    }

    private static class Task2 implements Runnable {
        @Override
        public void run() {
            System.out.println("Task2 run");
            synchronized (LOCK1) {
                synchronized (LOCK2) {
                    isCheck = false;
                    LOCK2.notify();
                    System.out.println("Task2 notify (isCheck= " + isCheck + ")");
                    System.out.println("Task2 stop");
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