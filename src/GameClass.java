import com.rupeng.game.GameCore;

public class GameClass implements Runnable{
    
    private int x = 3;
    private int y;
    
	/**
	 * @return
	 */
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public static void main(String[]args){
        final int x = 0;
//        x = 3;
        System.out.println(x);
        int y = 0;
        System.out.println(y);
//		GameCore.start(new GameClass());
	}
	@Override
    public void run(){
		int i=8;
		boolean bool=(i>10)&&((i=i+5)>10);
		System.out.println(bool);
		System.out.println(i);
	}
}