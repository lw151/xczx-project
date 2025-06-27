package com.xuecheng.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class threadTest  {
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            tasks.add(() -> {
                return "任务" + taskId + "由" + Thread.currentThread().getName() + "执行完成";
            });
        }

// 提交所有任务并获取Future列表
        List<Future<String>> futures = executor.invokeAll(tasks);
// 处理结果
        for (Future<String> future : futures) {
            System.out.println(future.get());
        }
    }

}
