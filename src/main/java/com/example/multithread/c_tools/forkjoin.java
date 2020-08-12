package com.example.multithread.c_tools;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class forkjoin {

    public static void main(String[] args) throws Exception {
        // 创建2000个随机数组成的数组:
        long[] array = new long[2000];
        // 预期结果
        long expectedSum = 0;
        // 赋随机值
        for (int i = 0; i < array.length; i++) {
            array[i] = random();
            expectedSum += array[i];
        }
        System.out.println("Expected sum: " + expectedSum);
        // fork/join:

        ForkJoinTask<Long> task = new SumTask(array, 0, array.length);
        // 开始时间
        long startTime = System.currentTimeMillis();
        // 执行
        Long result = ForkJoinPool.commonPool().invoke(task);
        // 结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
    }

    static Random random = new Random(0);

    static long random() {
        return random.nextInt(10000);
    }
}

class SumTask extends RecursiveTask<Long> {
    // 定义子任务的大小标准
    static final int THRESHOLD = 500;
    long[] array;
    int start;
    int end;

    SumTask(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    /**
     * 拆分任务
     * @return
     */
    @Override
    protected Long compute() {
        // 当前任务大小满足  任务标准时
        if (end - start <= THRESHOLD) {
            // 如果任务足够小,直接计算:
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += this.array[i];
                // 故意放慢计算速度:
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
            }
            return sum;
        }
        // 任务太大,一分为二:
        int middle = (end + start) / 2;

        System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
        // 二分之后创建两个新的任务
        SumTask leftTask = new SumTask(this.array, start, middle);
        SumTask rightTask = new SumTask(this.array, middle, end);
        // 再次执行compute
        invokeAll(leftTask, rightTask);

        Long leftResult = leftTask.join();
        Long rightResult = rightTask.join();
        Long result = leftResult + rightResult;
        System.out.println("result = " + leftResult + " + " + rightResult + " ==> " + result);
        return result;
    }
}
