package homework;

import java.util.Arrays;

public class ValueCalculator {
    private final static int lenght = 1000000;
    private static final int[] array = new int[lenght];

    public static void main(String[] args) {
        doCalc();
    }

    public static void doCalc() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            array[i] = 36;
        }
        System.out.println(Arrays.toString(array));

        int[] array1 = new int[lenght / 2];
        int[] array2 = new int[lenght / 2];

        System.arraycopy(array, 0, array1, 0, array1.length);
        System.arraycopy(array, array1.length, array2, 0, array2.length);
        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));


        Thread t1 = new Thread(() -> {
            for (int i = 0; i < lenght / 2; i++) {
                array1[i] = (int) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < lenght / 2; i++) {
                array2[i] = (int) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(array1, 0, array, 0, array1.length);
        System.arraycopy(array2, 0, array, array1.length, array2.length);
        System.out.println(Arrays.toString(array));
        System.out.print("Execution time: ");
        System.out.println((double) (start / 1000) % 60);

    }
}
