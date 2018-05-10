package ru.javawebinar.basejava;

public class TestDeadlock {
    private static final Object LOCK1 = new Object();
    private static volatile boolean isCheck = false;

    private static class Task1 implements Runnable {
        @Override
        public void run() {
            System.out.println("Task1 run");
            synchronized (LOCK1) {
                System.out.println("Task1 locked LOCK1");
                isCheck = true;
                    try {
                        System.out.println("Task1 wait (isCheck = " + isCheck + ")");
                        while (isCheck) {
                            LOCK1.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                LOCK1.notify();
                System.out.println("Task1 notify (isCheck= " + isCheck + ")");
            }
        }
    }

    private static class Task2 implements Runnable {
        public void run() {
            System.out.println("Task2 run");
            synchronized (LOCK1) {
                System.out.println("Task2 locked LOCK1");
                isCheck = false;
                try {
                    System.out.println("Task2 wait (isCheck = " + isCheck + ")");
                    while (!isCheck) {
                        LOCK1.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LOCK1.notify();
                System.out.println("Task2 notify (isCheck= " + isCheck + ")");
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

