package top.hrzgj.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解释
 *   Thread#Interrupt()
 *   Thread#isInterrupted()
 *   Thread#interrupted()
 * 这三个方法的作用
 *
 * @author zhan jp
 * @date 2021-05-08 23:51
 */
public class InterruptThread {

    private static class ThreadA extends Thread {

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public void run() {
            while (true) {
                System.out.println(count.incrementAndGet());

                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("isInterrupted: true, " + Thread.currentThread().isInterrupted() );
                    // 系统调用中断线程，这里可以进行响应
                    if (count.intValue() > 10) {
                        return;
                    }
                }

                // interrupted()方法会清除中断的信息，一个中断，第二次调用的时候就会是false
                if (Thread.interrupted()) {
                    System.out.println("interrupted: true, " + Thread.interrupted() );
                }

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ThreadA threadA = new ThreadA();
        threadA.start();
        for (int i = 0; i < 100; i++) {
            // 线程sleep也会被打断，抛出异常
            threadA.interrupt();
            Thread.sleep(100);
        }

    }
}
