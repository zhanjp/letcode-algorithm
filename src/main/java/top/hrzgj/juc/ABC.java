package top.hrzgj.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description
 *
 * @author zhan jp
 * @date 2021-05-08 22:02
 */
public class ABC {

    private static class Lock {
        public volatile String value;
    }

    /**
     * 创建线程方式1：继承Thread类，重写run方法
     */
    private static class ThreadA extends Thread {

        private Lock lock;

        public ThreadA(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                synchronized (lock) {
                    if (lock.value == "A") {
                        System.out.println(i + " A");
                        lock.value = "B";
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    // sleep不会释放锁
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 创建线程方式2：实现Runnable接口的run方法
     */
    private static class ThreadB implements Runnable {

        private Lock lock;

        public ThreadB(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                synchronized (lock) {
                    if (lock.value == "B") {
                        System.out.println(i + " B");
                        lock.value = "C";
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class ThreadC implements Callable<Integer> {

        private AtomicInteger count = new AtomicInteger(0);
        private Lock lock;

        public ThreadC(Lock lock) {
            this.lock = lock;
        }

        @Override
        public Integer call() throws Exception {
            for (int i = 0; i < 100; i++) {
                synchronized (lock) {
                    if (lock.value == "C") {
                        System.out.println(i + " C");
                        lock.value = "A";
                        lock.notifyAll();
                        final int i1 = count.incrementAndGet();
                    } else {
                        try {
                            lock.wait(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return count.intValue();
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.out.println(System.currentTimeMillis());
        // native 方法，告诉CPU放弃时间片，从running进入runnable状态
        // CPU不怎么忙，本地测试，1000次yield才放弃了1ms的时间
        for (int i = 0; i < 1000; i++) {
            Thread.yield();
        }
        System.out.println(System.currentTimeMillis());


        Lock lock = new Lock();
        lock.value = "A";

        Thread threadA = new ThreadA(lock);

        Thread threadB = new Thread(new ThreadB(lock));

        // FutureTask实现了runnable接口，所以真正实现线程的方式只有两个
        // 内部通过CAS把当前执行的线程关联起来，所以一个futureTask只能一个线程执行
        FutureTask<Integer> futureTask = new FutureTask<>(new ThreadC(lock));
        Thread threadC = new Thread(futureTask);

        threadA.start();
        threadB.start();
        threadC.start();

        Thread.sleep(1000);
        threadC.interrupt();
        // join 方法是一个synchronized方法，所以会锁住当前threadA对象，在方法内部进行wait，等待threadA执行结束后自动notifyAll()
        threadA.join();
        threadB.join();

        System.out.println(futureTask.get());

        // 这里不会再执行了，futureTask不可复用
        threadC = new Thread(futureTask);
        threadC.start();
        System.out.println(futureTask.get());
    }
}
