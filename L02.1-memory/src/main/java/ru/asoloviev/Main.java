package ru.asoloviev;

import java.lang.management.ManagementFactory;

/**
 * VM options -Xmx512m -Xms512m
 * <p>
 * Runtime runtime = Runtime.getRuntime();
 * long mem = runtime.totalMemory() - runtime.freeMemory();
 * <p>
 * System.gc();
 * <p>
 * jconsole, connect to pid
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());

        int size = 20_000_000;
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Starting the loop");
        while (true) {
            System.gc();
            Thread.sleep(10);
            long mem = runtime.totalMemory() - runtime.freeMemory();
            System.out.println(mem);

            Object[] array = new Object[size];

            long mem2 = runtime.totalMemory() - runtime.freeMemory();
            System.out.println((mem2 - mem) / size);

            System.out.println("New array of size: " + array.length + " created");
            for (int i = 0; i < size; i++) {
                //array[i] = new Object();
                //array[i] = new String(""); // String pool
                //array[i] = new String(new char[0]); // without string pool
                array[i] = new MyClass();
            }
            System.out.println("Created " + size + " objects.");
            Thread.sleep(1000);
        }
    }

    private static class MyClass {
        private int i = 0;
        private long l = 1;
    }
}
