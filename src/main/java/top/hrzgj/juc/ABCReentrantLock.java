package top.hrzgj.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 3个线程打印ABC，
 * ReentrantLock实现
 *   3个condition
 *
 * @author zhan jp
 * @date 2021-05-09 16:14
 */
public class ABCReentrantLock {

    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final Condition CONDITIONA = LOCK.newCondition();
    private static final Condition CONDITIONB = LOCK.newCondition();
    private static final Condition CONDITIONC = LOCK.newCondition();

    private static class ThreadA extends Thread {

        @Override
        public void run() {
            while (true) {
                LOCK.lock();
                try {
                    CONDITIONA.await();
                    System.out.println("A");
                    CONDITIONB.signal();
                } catch (InterruptedException e) {
                    System.out.println("ThreadA interrupted.");
                    return;
                } finally {
                    LOCK.unlock();
                }

            }
        }
    }

    private static class ThreadB extends Thread {

        @Override
        public void run() {
            while (true) {
                LOCK.lock();
                try {
                    CONDITIONB.await();
                    System.out.println("B");
                    CONDITIONC.signal();
                } catch (InterruptedException e) {
                    System.out.println("ThreadB interrupted.");
                    return;
                } finally {
                    LOCK.unlock();
                }

            }
        }
    }

    private static class ThreadC extends Thread {

        @Override
        public void run() {
            while (true) {
                LOCK.lock();
                try {
                    CONDITIONC.await();
                    System.out.println("C");
                    CONDITIONA.signal();
                } catch (InterruptedException e) {
                    System.out.println("ThreadC interrupted.");
                    return;
                } finally {
                    LOCK.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ThreadA threadA = new ThreadA();
        threadA.start();
        final ThreadB threadB = new ThreadB();
        threadB.start();
        final ThreadC threadC = new ThreadC();
        threadC.start();

        LOCK.lock();
        try {
            CONDITIONA.signal();
        } finally {
            LOCK.unlock();
        }
        Thread.sleep(3000);
        threadA.interrupt();
        threadB.interrupt();
        threadC.interrupt();
    }
}
