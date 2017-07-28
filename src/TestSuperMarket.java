/**
 * Created by 79001 on 2017/7/28.
 */
public class testSuperMarket {
    public static void main (String[] args) {
        SuperMarket superMarket = SuperMarket.singleSuperMarket();
        Thread thread = new Thread(superMarket);
        thread.start();
    }
}
