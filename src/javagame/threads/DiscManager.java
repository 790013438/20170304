package javagame.threads;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 新增DVD
 * 查看DVD
 * Created by 79001 on 2017/7/15.
 */
public class DiscManager implements Runnable {
    
    private Map<String, Disc> hashMap = new HashMap<>();
    private volatile boolean runningVolatileBoolean = false;
    private Scanner scanner = new Scanner(System.in);
    private Thread thread;

    public class Disc {
        
        public String name;
        public int state;
        private Date date;
    
        public Disc() {
        }
    
        public Disc(String name, int state) {
            this(name, state, new Date());
        }
    
        public Disc(String name, int state, Date date) {
            this.name = name;
            this.state = state;
            this.date = date;
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
        
            Disc disc = (Disc) o;
        
            if (state != disc.state) return false;
            if (name != null ? !name.equals(disc.name) : disc.name != null) return false;
            return date != null ? date.equals(disc.date) : disc.date == null;
        }
    
        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + state;
            result = 31 * result + (date != null ? date.hashCode() : 0);
            return result;
        }
    
        @Override
        public String toString() {
            return "\t" + name + '\t' +  state + "\t"+ date + '\n';
        }
    }
    
    public DiscManager() {
        initialize();
    }
    
    public DiscManager(Map<String, Disc> hashMap) {
        this.hashMap = hashMap;
    }
    
    public void startMenu () {
        print();
        System.out.println("请选择:");
        int integer = -1;
        integer = scanner.nextInt();
        switch (integer) {
            case 1:
                add();
                break;
            case 2:
                System.out.println("qwerty");
                break;
            case 3:
                System.out.println("qwerty");
                break;
            case 4:
                System.out.println("qwerty");
                break;
            case 5:
                System.out.println("qwerty");
                break;
            case 6:
                close();
                break;
            case 0:
                returnMain();
            default:
                System.out.println("");
        }
    }

    public void returnMain () {
        print();
    }

    public void print () {
        System.out.println("欢迎试用迷你DVD管理器");
        System.out.println("-------------------------------------------");
        System.out.println("1. 新增DVD");
        System.out.println("2. 查看DVD");
        System.out.println("3. 删除DVD");
        System.out.println("4. 借出DVD");
        System.out.println("5. 归还DVD");
        System.out.println("6. 退  出");
    }
    
    public void initialize () {
        Disc[] discs = new Disc[3];
        Calendar calendar = Calendar.getInstance();
        calendar.set(2013, 7, 1);
        discs[0] = new Disc("罗马假日", 0, calendar.getTime());
        discs[1] = new Disc("风声鹤唳", 1);
        discs[2] = new Disc("浪漫满屋", 1);
    }
    
    public void close () {
        runningVolatileBoolean = false;
        scanner.close();
    }
    
    public void start() {
        thread = new Thread(this);
        thread.start();
    }
    
    @Override
    public void run () {
        runningVolatileBoolean = true;
        while(runningVolatileBoolean) {
            startMenu();
            sleep(10);
        }
    }
    
    public void sleep (long sleep) {
        try {
            Thread.sleep(sleep);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void add () {
        String enterString = "";
        while (!"0".equals(enterString)) {
            System.out.println("录入DVD名称：");
            try {
                enterString = scanner.next();
            } catch (Exception e) {
            }
            Disc disc = new Disc(enterString, 1);
            hashMap.put(disc.name, disc);
            System.out.println("录入成功");
            System.out.println("\tname" + "\tState" + "\tdate\n");
            System.out.println(hashMap.values());
            System.out.println("输入0返回");
        }
    }

    public Disc search (String name) {
        return new Disc();
    }

    public void search () {
        traversal();
    }

    //遍历
    public void traversal () {
        System.out.println("\tname" + "\tState" + "\tdate\n");
        System.out.println(hashMap.values());
    }

    //移除
    public Disc remove (String string) {
        return hashMap.remove(string);
    }
    
    //借出 修改值
    public void lend () {
    }

    public void returnDis () {
    }
    
    public static void main (String[] args) {
        DiscManager discManager = new DiscManager();
        Thread thread = new Thread(new Runnable () {
            public void run () {
                discManager.start();
            }
        } );
        thread.start();
    }

}
