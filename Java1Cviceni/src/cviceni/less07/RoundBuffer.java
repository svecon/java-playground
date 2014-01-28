/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cviceni.less07;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.activity.InvalidActivityException;

/**
 *
 * @author sveco
 */
public class RoundBuffer {

    private static class TestThread extends Thread {

        static Random rand = new Random();

        RoundBuffer rb;
        int num;

        public TestThread(RoundBuffer rb) {
            this.rb = rb;
            this.num = threadNumber++;
        }

        void trySleeping() {
            try {
                if (rand.nextInt(2) == 1) {
                    this.sleep(rand.nextInt(THREAD_MAX_SLEEP));
                }
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static class InsertThread extends TestThread {

        public InsertThread(RoundBuffer rb) {
            super(rb);
        }
        
        @Override
        public void run() {
            while (messageNumber < MESSAGES_COUNT) {

                trySleeping();

                String ln = messageNumber++ + ". message";
                try {
                    rb.put(ln);
                    System.out.println(num + "# added:    " + ln + " [" + rb.count() + "]");
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }

                trySleeping();

            }
            System.out.println(num + "# [InsertThread] exiting");
        }
    }

    private static class SelectThread extends TestThread {

        public SelectThread(RoundBuffer rb) {
            super(rb);
        }
        
        @Override
        public void run() {
            while (messageNumber < MESSAGES_COUNT || rb.count() > 0) {

                trySleeping();

                try {
                    String ln = rb.get();
                    System.out.println(num + "# removed: -" + ln + " [" + rb.count() + "]");
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
                catch (InvalidActivityException ex) {
                    System.out.println(num + "# [SelectThread] invalid activity");
                    break;
                }

                trySleeping();
            }
            System.out.println(num + "# [SelectThread] exiting");
        }
    }

    String[] items;
    static int threadNumber;
    static int messageNumber;
    
    final static int BUFFER_SIZE = 10;
    final static int MESSAGES_COUNT = 20;
    final static int THREADS_COUNT = 6;
    final static int THREAD_MAX_SLEEP = 700;

    int putptr, takeptr, count;

    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    public RoundBuffer(int size) {
        items = new String[size];
    }

    public void put(String msg) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[putptr] = msg;
            if (++putptr == items.length) {
                putptr = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public String get() throws InterruptedException, InvalidActivityException {
        lock.lock();
        try {
            while (count == 0) {
                if (messageNumber >= 19) {
                    notEmpty.signalAll();
                    throw new InvalidActivityException();
                }
                notEmpty.await();
            }
            String ret = items[takeptr];
            if (++takeptr == items.length) {
                takeptr = 0;
            }
            --count;
            notFull.signal();
            return ret;
        } finally {
            lock.unlock();
        }
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        RoundBuffer rb = new RoundBuffer(BUFFER_SIZE);

        TestThread[] threads = new TestThread[THREADS_COUNT];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = i % 2 == 0 ? new InsertThread(rb) : new SelectThread(rb);
            threads[i].start();
        }

    }
}
