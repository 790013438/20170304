import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Exercise {
	public static void main(String[]args){
		Runnable taskRunnable=() -> {System.out.println("hello");};
		Executor executor=Executors.newCachedThreadPool();
		executor.execute(taskRunnable);
		int processors=Runtime.getRuntime().availableProcessors();
		System.out.println(processors);
		System.out.println("asdf"+-5%2);
	}
}
