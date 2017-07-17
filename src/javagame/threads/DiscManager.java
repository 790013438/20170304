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
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return "\t" + name + '\t' +  state + "\t"+ calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH)+ '\n';
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
                traversal();
                break;
            case 3:
                remove("");
                break;
            case 4:
                lend();
//                System.out.println("功能开发中...");
                break;
            case 5:
                returnDisc();
//                System.out.println("功能开发中...");
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
        for (int i = 0 ; i < 3; ++i) {
            hashMap.put(discs[i].name, discs[i]);
        }
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
            } catch (Exception e) {}
            if (hashMap.containsKey(enterString)) {
                System.out.println("录入失败");
            } else {
                Disc disc = new Disc(enterString, 1);
                hashMap.put(disc.name, disc);
                System.out.println("录入成功");
                System.out.println("\tname" + "\tState" + "\tdate\n");
                System.out.println(hashMap.values());
            }
            System.out.println("输入0返回");
        }
    }

    public Disc search (String name) {
        return hashMap.get(name);
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
        System.out.println("请输入要删除的DVD");
        try {
            string = scanner.next();
        } catch (Exception e) {
            System.out.println(e);
        }
        Disc disc = hashMap.remove(string);
        if (disc == null) {
            System.out.println("想要删除的DVD不存在");
        }
        return disc;
    }
    
    //借出 修改值
    public void lend () {
        System.out.println("输入要借的DVD");
        String enterString = "";
        try {
            enterString = scanner.next();
            Disc disc = hashMap.get(enterString);
            if (disc == null) {
                System.out.println("要借的DVD不存在");
                return;
            }
            System.out.println(disc.state);
            //233
            if (disc.state == 1) {
                disc.state = 0;
                hashMap.put(disc.name, disc);
                System.out.println("借出成功");
            } else {
                System.out.println("该DVD已经借出");
                System.out.println(disc.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void returnDisc () {
        add();
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
