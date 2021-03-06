package com.root;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class QuickSort {
    private static final long THRESHOLD = 32;

    private QuickSort() {
    }

    public static int[] quicksort_fjp(int[] data) {
        RecursiveAction fjt = new SortTask(data);
        ForkJoinPool.commonPool().invoke(fjt);
        return data;
    }

    private static class SortTask extends RecursiveAction {
        private final int[] data;
        private final int lo;
        private final int hi;


        public SortTask(int[] data, int start, int end) {
            this.data = data;
            this.lo = start;
            this.hi = end;
        }

        public SortTask(int[] data) {
            this.data = data;
            this.lo = 0;
            this.hi = data.length - 1;
        }

        @Override
        protected void compute() {
            if (hi - lo <= THRESHOLD) {
                Arrays.sort(data, lo, hi + 1);
            } else {
                int partition = partition(data, lo, hi);
                invokeAll(new SortTask(data, this.lo, partition - 1),
                        new SortTask(data, partition + 1, this.hi));
            }
        }

        int partition(int[] data, int lo, int hi) {
            int i = lo - 1;
            int x = data[hi];
            for (int j = lo; j < hi; j++) {
                if (data[j] < x) {
                    i++;
                    swap(data, i, j);
                }
            }
            i++;
            swap(data, i, hi);
            return i;
        }

        void swap(int[] data, int j, int k) {
            int t = data[j];
            data[j] = data[k];
            data[k] = t;
        }
    }
}
