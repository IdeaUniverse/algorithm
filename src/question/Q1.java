package question;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 给定一个数组[1,2,3,4,5,6,7,8,9....,15]，要求遍历数组，
 * 遇到仅能被3整除的数字，打印A；
 * 遇到仅能被5整除的数字，打印B；
 * 遇到可以同时被3和5整除的数字，打印C；
 * 其他打印数字本身；
 * 要求四个线程，每一个线程执行一个打印方法。
 */
public class Q1 {

    private final Lock lock = new ReentrantLock();
    private final Condition condition3 = lock.newCondition();
    private final Condition condition5 = lock.newCondition();
    private final Condition condition15 = lock.newCondition();
    private final Condition conditionN = lock.newCondition();

    public AtomicInteger current = new AtomicInteger(0);

    private void print3A(int[] arr) throws InterruptedException {
        try {
            lock.lock();
            while (true){
                if(current.get() >= arr.length) return;
                if (arr[current.get()] % 3 != 0 || arr[current.get()] % 15 == 0)
                    condition3.await();
                else break;
            }
            System.out.print("A ");
            current.incrementAndGet();
            signal();
        }finally {
            lock.unlock();
        }
    }

    private void print5B(int[] arr) throws InterruptedException {
        try {
            lock.lock();
            while (true){
                if(current.get() >= arr.length) return;
                if (arr[current.get()] % 5 != 0 || arr[current.get()] % 15 == 0)
                    condition5.await();
                else break;
            }
            System.out.print("B ");
            current.incrementAndGet();
            signal();
        }finally {
            lock.unlock();
        }

    }

    private void print15C(int[] arr) throws InterruptedException {
        try {
            lock.lock();
            while (true){
                if(current.get() >= arr.length) return;
                if (arr[current.get()] % 15 != 0)
                    condition15.await();
                else break;
            }
            System.out.print("C ");
            current.incrementAndGet();
            signal();
        }finally {
            lock.unlock();
        }
    }

    private void printN(int[] arr) throws InterruptedException {
        try {
            lock.lock();
            while (true){
                if(current.get() >= arr.length) return;
                if(arr[current.get()] % 3 == 0 || arr[current.get()] % 5 == 0)
                    conditionN.await();
                else break;
            }
            System.out.print(arr[current.getAndIncrement()] + " ");
            signal();
        }finally {
            lock.unlock();
        }
    }

    private void signal(){
        condition3.signal();
        condition5.signal();
        condition15.signal();
        conditionN.signal();
    }

    public static void main(String[] args) {
        Q1 q1 = new Q1();
        int[] arr = new int[15];
        for (int i = 0; i < arr.length; i++) arr[i] = i + 1;

        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.execute(() -> {
            try {
                while (q1.current.get() < arr.length)
                    q1.print3A(arr);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.execute(() -> {
            try {
                while (q1.current.get() < arr.length)
                    q1.print5B(arr);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.execute(() -> {
            try {
                while (q1.current.get() < arr.length)
                    q1.print15C(arr);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.execute(() -> {
            try {
                while (q1.current.get() < arr.length)
                    q1.printN(arr);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.shutdown();
    }
}
