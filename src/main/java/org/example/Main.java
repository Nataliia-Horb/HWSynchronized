package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Задача 1: найти все целые числа в диапазоне от Integer.MINVALUE до Integer.MAXVALUE,
 * которые делятся на заданное целое число без остатка
 */
public class Main {
    public static void main(String[] args) {
        // В параметре указываем количество потоков, которые должны обработать задачу
        // Задача 1:
//         runStream(2);
        runStream(6);
    }

    public static void runStream(int n) {
        long start = System.currentTimeMillis();
        List<MyThread> listAllThreads = createAllThreads(n);
        listAllThreads.stream().forEach(Thread::start);
        listAllThreads.stream().forEach(e -> {
            try {
                e.join();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        System.out.println("Total number of items: "+MyThread.count);
        System.out.println("Program Execution Time with " + listAllThreads.size() + " Threads: "
                + (System.currentTimeMillis() - start) + "\n");
    }


    public static List<MyThread> createAllThreads(int n) {
        int min = Constants.MIN_VALUE;
        int step = Constants.MAX_VALUE / n - Constants.MIN_VALUE / n;
        int max = min + step;

        List<MyThread> allThreads = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            allThreads.add(new MyThread(min, max));
            min = max + 1;
            max += step;
        }
        return allThreads;
    }
}