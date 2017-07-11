package javagame.threads;

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
                     */
                    Future<Boolean> resultFuture = executorService.submit(new CallableTaskExample());
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
        return null;
    }
}
