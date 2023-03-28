package com.pigliu.mongodb.service;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author liufuqiang
 */
public class ForkJoinPoolService {

    private static ForkJoinPool forkJoinPool;

    private static class CustomForkTask extends RecursiveTask<Integer> {
        final int i;
        private CustomForkTask(int i) {
            this.i = i;
        }

        @Override
        protected Integer compute() {
           if (i <= 1) {
               return i;
           }
           CustomForkTask left = new CustomForkTask(i - 1);
           CustomForkTask right = new CustomForkTask(i - 2);
           left.fork();
           return right.compute() + left.join();
        }
    }



    public static void main(String[] args) {
//        CustomForkTask customForkTask = new CustomForkTask(10);
//        forkJoinPool = ForkJoinPool.commonPool();
//        Integer invoke = forkJoinPool.invoke(customForkTask);
//        System.out.println(invoke);
        System.out.println( 1 << 13);
    }
}
