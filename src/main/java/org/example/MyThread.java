package org.example;

public class MyThread extends Thread {

    private int min;
    private int max;

    public static int count=0;

    public MyThread(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static final int divisor = Constants.DIVISOR;

    @Override
    public void run() {
        for (int i = min; i <= max; i++) {
            if (i % divisor == 0) {
                synchronized (MyThread.class) {count++;}
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

