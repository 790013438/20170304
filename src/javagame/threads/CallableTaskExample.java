package javagame.threads;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by 79001 on 2017/7/10.
 * It has been said that threads can solve any problem except too many threads.
 * Although I would prefer to never need them, because they are so complex,
 * there are some situtions where thread are the best solution.
 * Two really cool features that make Callable objects different from Runnable objects 
 * are that Callable objects can return a result,
 * and they can capture and rethrow exceptions inside other threads.
 */
public class CallableTaskExample implements Callable<Boolean> {

    /**
     * The CallableTaskExample, located in the javagames.threads package, is a simple program 
     * to demonstrate using the ExecutorService to execute Callable tasks.
     * It also demonstrates using the Future objects to get the results and exceptions from the tasks.
     * The CallableTaskExample implements the Callable<Boolean> interface.
     */
    public static void main (String[] args) {
        /**
         * There exists an ExecutorService interface that can process Callable tasks and return Future Objects.
         * This is possible by using a future object.
         * The main() method uses the Executors class to create a new cached thread pool:
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 50; ++i) {
                try {
                    /**
                     * The cached thread pool creates a new thread for each task,
                     * but keeps those threads around for a little while before destroying them.
                     * If other task are submitted, the cached threads are reused.
                     * Notice how the get() method from the Future object will not return 
                     * until the Callable task if finished; it rethrows any exceptions thrown by the Callable task.
                     * The ExecutorService contains a submit method, 
                     * which executes a Callable task and returns a Future object.
                     */
                    Future<Boolean> resultFuture = executorService.submit(new CallableTaskExample());
                    /**
                     * The Future object returned from the executor has a get() method that will block 
                     * until the Callable task has finished.
                     */
                    Boolean successBoolean = resultFuture.get();
                    System.out.println("Result:" + successBoolean);
                } catch (ExecutionException ex) {
                    Throwable throwable = ex.getCause();
                    System.out.println("Error:" + throwable.getMessage());
                } catch (InterruptedException e) {
                    System.out.println("Awesome! Thread was canceled");
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println("qwerty");
                }
            }
        } finally {
            try {
                /**
                 * The thread pool is shut down with the shutdown() method.
                 * It uses the awaitTermination() method to block until all the threads in the thread pool have finished.
                 */
                executorService.shutdown();
                executorService.awaitTermination(10, TimeUnit.SECONDS);
                System.out.println("Threadpool Shutdown :)");
            } catch (InterruptedException e) {
                //at this point,just give up..
                e.printStackTrace();
                System.exit(-1);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    /**
     * Notice that the call() method returns a Boolean result.
     * The task sleeps for a few milliseconds, returns true or false, 
     * and even throws an exception when the random slepp time is zero.
     */
    @Override
    public Boolean call() throws Exception {

        //simulate some stupid long task and maybe fail...
        Random random = new Random();
        int seconds = random.nextInt(6);
        if (seconds == 0) {
            //pretend there was an error
            throw new RuntimeException("I love the new thread stuff!!!:");
        }
        try {
            Thread.sleep(seconds * 100);
        } catch (InterruptedException e) {
        } catch (Exception e) {
            System.out.println(e);
        }
        //even = true, odd = false;
        return (seconds & 1) == 0;
    }
}
