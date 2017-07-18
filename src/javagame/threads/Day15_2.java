package javagame.threads;

/**
 * Created by 79001 on 2017/7/17.
 */
public class Day15_2 {
    /**
     * 1、一个江河类River，属性int warning（警戒水位）、int waterLine（当前水位）
     * 方法void flow（）（江河流动），当前水位高出警戒水位10时，抛出异常，在void flow（）中不做处理，如果没有超出输出“宁静的河流”
     */
    class River {
        int warning;
        int waterLine;
    
        public int getWarning() {
            return warning;
        }
    
        public void setWarning(int waring) {
            this.warning = waring;
        }
    
        public int getWaterLine() {
            return waterLine;
        }
    
        public void setWaterLine(int waterLine) {
            this.waterLine = waterLine;
        }
        
        public void flow () throws RuntimeException{
            if (waterLine < warning) {
                System.out.println("宁静的河流");
            } else {
                throw new RuntimeException("");
            }
        }
    }
    /**
     * 2、黄河类继承江河类，重写void flow（），黄河水位高出警戒水位5时，抛出异常，否则输出“黄河在流：黄河之水天上来”
     * 长江类继承江河类，重写void flow（），长江水位高出警戒水位9时，抛出异常，否则输出“长江在流：孤帆远影碧空尽，惟见长江天际流”
     */
    class YellowRiver extends River{
        
        @Override
        public void flow () {
            if (waterLine < warning) {
                System.out.println("黄河在流：黄河之水天上来");
            } else {
                throw new RuntimeException("黄河决堤了");
            }
        }
    }
    
    class Yangtze extends River{
        
        @Override
        public void flow () {
            if (waterLine < warning) {
                System.out.println("长江在流：孤帆远影碧空尽，惟见长江天际流");
            } else {
                throw new RuntimeException("");
            }
        }
    }
    /**
     * 3、政府类Party，void watch(River river)查看各个河流的流动情况，如果有异常产生则显示异常信息，并输出“政府抢险”
     */
    class Party {
        public void watch (River river) {
            try {
                river.flow();
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("政府强险");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
 
    /**
     * 4、测试类Test，代码如下
     *  public static void main(String[] args) {
     *      River river = new YellowRiver();  // 黄河
     *      river.setWarning(100);  //设置黄河警戒水位
     *      river.setWaterLine(110);  //设置黄河当前水位
     *      Party party = new Party();  // 共产党
     *      party.watch(river);  // 共产党检测
     *
     *      river = new LongRiver();  // 长江
     *      river.setWarning(120);  //设置长江警戒水位
     *      river.setWaterLine(106);  //设置长江当前水位
     *      party.watch(river);  // 共产党检测
     *      }
     */
    public static void main(String[] args) {
        Day15_2 day15 = new Day15_2();
            River river = day15.new YellowRiver();  // 黄河
            river.setWarning(100);  //设置黄河警戒水位
            river.setWaterLine(110);  //设置黄河当前水位
            Party party = day15.new Party();  // 共产党
            party.watch(river);  // 共产党检测
        
            river = day15.new Yangtze();  // 长江
            river.setWarning(120);  //设置长江警戒水位
            river.setWaterLine(106);  //设置长江当前水位
            party.watch(river);  // 共产党检测
    }
    
    /**
     * 上机练习（2）
     * 银行系统模拟
     * 学会利用异常处理突发事件
     * 编程实践：模拟银行存取款系统
     * 目标
     * （1）编写程序实现一个银行存取款模拟系统的简单实现。
     * （2）自定义一个异常类，我们对Banker类中的余额进行判断，如果欲取出的金额大于余额则手动抛出该异常。
     * （3）对取款的流程进行设计，每次操作完毕后都返回上一步继续操作。
     * 程序启动后，程序运行结果如图所示。
     */
}
