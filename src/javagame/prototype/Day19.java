package javagame.prototype;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by 79001 on 2017/7/21.
 */
public class Day19 {
    /**
     * 1、编写一个计时器，每隔一秒钟，在控制台打印出最新时间。
     * 知识点 ： 线程状态
     */
    class First implements Runnable {

        private volatile boolean volatileBoolean;

        public void run () {
            volatileBoolean = true;
            long lastTime = System.currentTimeMillis();
            long delta;
            while (volatileBoolean) {
                Date date = new Date();
                try {
                    Thread.sleep(1000);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } catch (Exception e) {}
                System.out.println(date);
                
                delta = System.currentTimeMillis() - lastTime;
                if (delta > 1000 * 10) {
                    volatileBoolean = false;
                }
            }
        }

    }
    /**
     * 2、一个线程打印数字，一个线程打印字母。(使用两种不同的方式实现)
     * 知识点：创建线程的方式
     */
    class Second implements Runnable, Callable<Boolean> {
        public void method () {
            Thread thread = new Thread(this);
            thread.start();
            
            //打印字母
            ExecutorService executorService = Executors.newCachedThreadPool();
            try {
                Callable<Boolean> callable = new Second();
                Future<Boolean> future = executorService.submit(callable);
                System.out.println("Result:" + future.get());
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    executorService.shutdown();
                    executorService.awaitTermination(10, TimeUnit.SECONDS);
                    System.out.println("Thread pool Shutdown :)");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

        public void run () {
            for (int i = 0; i <= 9; ++i) {
                System.out.println(i);
            }
        }

        public Boolean call () {
            char ch = 'A';
            while (ch <= 'z') {
                for (int i = 0; i < 6; ++i) {
                    System.out.print(ch++ + "\t");
                }
                System.out.println();
            }
            return true;
        }
    }

    /**
     * 3、选择两个城市去旅游,启动两个线程,分别循环十次输出城市A 和 B ,输出一次以后,
     * 让线程休眠100-1000毫秒之间,哪个线程先显示完毕就决定去哪个城市.用Thread和Runnable分别实现
     * new Random().nextInt(901)+100
     * 知识点：创建线程的方式
     */
    class Third implements Runnable, Callable<Boolean> {
        private Random random = new Random();

        public void method () {
            Thread thread = new Thread(this);
            thread.start();
            
            //B
            ExecutorService executorService = Executors.newCachedThreadPool();
            try {
                Callable callable = new Third();
                Future<Boolean> future = executorService.submit(callable);
                System.out.println("Result:" + future.get());
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    executorService.shutdown();
                    executorService.awaitTermination(10, TimeUnit.SECONDS);
                    System.out.println("Thread pool Shutdown :)");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

        public void run () {
            for (int i = 0; i < 10; ++i) {
                try {
                    Thread.sleep(random.nextInt(901) + 100);
                } catch (Exception e) {
                    System.out.println(e);
                }
                System.out.println("A");
            }
        }

        public Boolean call () {
            for (int i = 0; i < 10; ++i) {
                try {
                    Thread.sleep(random.nextInt(901) + 100);
                } catch (Exception e) {
                    System.out.println(e);
                }
                System.out.println("B");
            }
            return true;
        }
    }

    /**
     * 4、小明有面包，小红有牛奶，两个人要吃早饭，但是必须面包和牛奶同时在手才能开始吃
     * 模拟此过程.
     * 知识点：死锁
     */
    class Fourth implements Runnable, Callable<Boolean> {
        private volatile boolean volatileBoolean;
        private Thread thread;

        public void method () {
            thread = new Thread(this);
            thread.start();

            ExecutorService executorService = Executors.newCachedThreadPool();
            try {
                Callable callable = new Fourth();
                Future<Boolean> future = executorService.submit(callable);
                System.out.println("Result:" + future.get());
            } catch (Exception e) {} finally {
                try {
                    executorService.shutdown();
                    executorService.awaitTermination(10, TimeUnit.SECONDS);
                    System.out.println("Thread pool Shutdown :)");
                    //
                    thread.join();
                    //233
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

        public void run () {
            synchronized ("bread") {
                System.out.println("小明等牛奶");
                synchronized ("milk") {
                    System.out.println("早餐小明吃");
                }
            }
        }

        public Boolean call () {
            synchronized ("milk") {
                System.out.println("小红等面包");
                synchronized ("bread") {
                    System.out.println("早餐小红吃");
                }
            }
            return true;
        }
    }

    /**
     * 5、使用多线程模拟：
     * 银行账户中有400元钱,模拟两个用户同时取钱,每人都想取300元,看看执行效果.
     * 知识点：多线程
     */
    
    static class Fifth implements Runnable, Callable {
        public static Integer account = 400;
        
        public void method () {
            Thread thread = new Thread(this);
            thread.start();

            //
            ExecutorService executorService = Executors.newCachedThreadPool();
            try {
                for (int i = 0; i < 10; ++i) {
                    Callable callable = new Fifth();
                    Future<Boolean> future = executorService.submit(callable);
                    System.out.println("Result:" + future.get());
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    executorService.shutdown();
                    executorService.awaitTermination(10, TimeUnit.SECONDS);
                    System.out.println("Thread pool Shutdown :)");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

        public void run () {
            synchronized (account) {
                if (account > 300) {
                    account -= 300;
                }
                System.out.println("第1个人取出，余额：" + account);
            }
        }

        public Boolean call () {
            synchronized (account) {
                if (account > 300) {
                    account -= 300;
                    System.out.println("第2个人取出，余额：" + account);
                    return true;
                }
            }
            return false;
        }
    }
    
    /**
     * 6、有一个框，里边可以放入10个馒头，顾客来买。师傅在做，框满了通知顾客买，框空了通知师傅做。
     * 知识点：生产消费者
     */
    class Sixth implements Runnable, Callable<Boolean> {
        private volatile ArrayList<Bread> arrayList = new ArrayList();
        private volatile boolean volatileBoolean;
        volatile int i = 0;
        private volatile boolean readyBoolean;

        public void run () {
            volatileBoolean = true;
            while (volatileBoolean) {
                synchronized (arrayList) {
                    if (arrayList.size() < 10) {
                        Bread bread = new Bread(i);
                        System.out.println("做馒头 " + i);
                        arrayList.add(bread);
                        if (i == 1000) {
                            readyBoolean = true;
                            arrayList.notifyAll();
                            break;
                        }
                        i++;
                    }  else {
                        readyBoolean = true;
                        arrayList.notifyAll();
                    }
                }
            }
        }

        public Boolean call () throws Exception {
            while (volatileBoolean) {
                synchronized (arrayList) {
                    if (readyBoolean) {
                        for (int j = arrayList.size() - 1; j >= 0; --j) {
                            System.out.println("\t吃馒头 " + arrayList.remove(j));
                        }
                        readyBoolean = false;
                        System.out.println("吃完了等待");
                        //233
                        arrayList.wait();
                    } else {
                        arrayList.notify();
                    }
                }
            }
            return true;
        }
    }
    
    class Bread {
        protected int sequence;
    
        public Bread() {
        }
    
        public Bread(int sequence) {
            this.sequence = sequence;
        }
    
        public int getSequence() {
            return sequence;
        }
    
        public void setSequence(int sequence) {
            this.sequence = sequence;
        }
    
        @Override
        public String toString() {
            return "Bread{" +
                    "sequence=" + sequence +
                    '}';
        }
    }
    
    String wait = "wait";
    Thread thread;
    
   
    //InetAddress
    class Twelfth {
        public void method () {
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                System.out.println(inetAddress);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    /**
     * DatagramSocket
     */
    class Thirth {
        /**
         * 发送
         */
        public void method () {
            try {
                DatagramPacket datagramPacket =  new DatagramPacket("hello".getBytes(), "hello".length(), InetAddress.getByName("10.7.152.102"), 9999);
                DatagramSocket datagramSocket = new DatagramSocket();
                datagramSocket.send(datagramPacket);
                System.out.println("发送成功");
                datagramSocket.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
        //接受
        public void method2 () {
            byte[] bytes = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
            try {
                DatagramSocket datagramSocket = new DatagramSocket(9999);
                System.out.println("等待接收");
                datagramSocket.receive(datagramPacket);
                System.out.println(bytes);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
        /**
         * 发送client
         */
        public void method3 () {
            try {
                synchronized (wait) {
                    wait.wait();
                    Socket socket = new Socket(InetAddress.getLocalHost(), 7777);
                    System.out.println(InetAddress.getLocalHost());
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                    outputStreamWriter.write("hello cross the wall,walk");
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                    socket.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
        /**
         * server
         */
        public void method4 () throws Exception {
            try {
                ServerSocket serverSocket = new ServerSocket(7777);
                synchronized (wait) {
                    wait.notifyAll();
//                    thread.join();
                }
                System.out.println(serverSocket.getLocalSocketAddress());
                Socket socket = serverSocket.accept();
                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                System.out.println(bufferedReader.readLine());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    public static void main (String[] args) {
        Day19 day19 = new Day19();
        
//        Twelfth first = day19.new Twelfth();
//        Thread thread = new Thread(first);
//        thread.start();

//        Thirth second = day19.new Thirth();
//        second.method();

//        Third third = day19.new Third();
//        third.method();
        
        //Fourth fourth = day19.new Fourth();
        //fourth.method();

//        Fifth fifth = new Fifth();
//        fifth.method();
        
        Sixth sixth = day19.new Sixth();
        
        Thread thread = new Thread(sixth);
        thread.start();
    
        //
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            Callable callable = sixth;
            Future<Boolean> future = executorService.submit(callable);
            System.out.println("Result:" + future.get());
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
            try {
                executorService.shutdown();
                executorService.awaitTermination(10, TimeUnit.SECONDS);
                System.out.println("Thread pool Shutdown :)");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
    }
}
