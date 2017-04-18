//import com.rupeng.game.GameCore;
//public class GameClass implements Runnable{
//	public static void main(String[]args){
//		GameCore.start(new GameClass());
//	}
//	public void run(){
//		for(int num=0;num<=5;num++){
//			GameCore.createSprite(num, "mario");
//			GameCore.setSpritePosition(num, 0, num*80);
//			GameCore.setSpriteFlipX(num, true);
//			GameCore.playSpriteAnimate(num,"walk",true);
//		}
//		for(int num=0;num<=5;num++){
//			for(int leftInt=0;leftInt<500;leftInt++){
//				GameCore.setSpritePosition(num, leftInt, num*80);
//				GameCore.pause(3);
//			}
//		}
////		for(;;){
////			GameCore.pause(10);
////		}
//	}
//}
//import java.awt.Dimension;
//import java.awt.Point;
//import java.awt.event.KeyEvent;
//
//import com.rupeng.game.GameCore;
//public class GameClass implements Runnable{
//	public static void main(String[]args){
//		GameCore.start(new GameClass());
//	}
//	public void run(){
//		int girlIndex=0;
//		GameCore.createSprite(girlIndex, "girl");
//		GameCore.setSpritePosition(girlIndex, 0, 0);
//		GameCore.playSpriteAnimate(girlIndex, "walk", true);
//		Dimension dimension=GameCore.getGameSize();
//		int dimensionWidth=dimension.width;
//		int dimensionHeight=dimension.height;
//		Dimension spriteDimension=GameCore.getSpriteSize(girlIndex);
//		int gyroIndex=1;
//		GameCore.createSprite(gyroIndex, "tuoluo");
//		GameCore.setSpritePosition(gyroIndex, 200, 200);
//		GameCore.playSpriteAnimate(gyroIndex, "rotate", true);
//		Dimension gyroDimension=GameCore.getSpriteSize(gyroIndex);
//		int boomIndex=2;
//		GameCore.createSprite(boomIndex, "bomb");
//		GameCore.hideSprite(boomIndex);
//		for(;;){
//			int kc=GameCore.getPressedKeyCode();
//			Point pos=GameCore.getSpritePosition(girlIndex);
//			if(kc==KeyEvent.VK_LEFT||kc==KeyEvent.VK_A){
//				if(pos.x>0){
//					GameCore.setSpriteFlipX(girlIndex, true);
//					GameCore.setSpritePosition(girlIndex, pos.x-1, pos.y);
//				}
//			}else if(kc==KeyEvent.VK_RIGHT||kc==KeyEvent.VK_D){
//				if(pos.x<dimensionWidth-spriteDimension.width){
//					GameCore.setSpriteFlipX(girlIndex,false);
//					GameCore.setSpritePosition(girlIndex, pos.x+1, pos.y);
//				}
//			}else if((kc==KeyEvent.VK_UP||kc==KeyEvent.VK_W)&&pos.y>0){
//				GameCore.setSpritePosition(girlIndex, pos.x, pos.y-1);
//			}else if((kc==KeyEvent.VK_DOWN||kc==KeyEvent.VK_S)&&pos.y<dimensionHeight-spriteDimension.height){
//				GameCore.setSpritePosition(girlIndex, pos.x, pos.y+1);
//			}
//			if(Math.abs(GameCore.getSpriteX(girlIndex)-GameCore.getSpriteX(gyroIndex))<5){
//				GameCore.setSpritePosition(boomIndex, 200, 200);
//				GameCore.playSpriteAnimate(boomIndex,"fire",false);
//				GameCore.hideSprite(gyroIndex);
//				GameCore.showSprite(boomIndex);
//				GameCore.pause(1000);
//				GameCore.hideSprite(boomIndex);
//			}
//			GameCore.pause(8);
//		}
//	}
//}
import com.rupeng.game.GameCore;
public class GameClass implements Runnable{
	public static void main(String[]args){
		GameCore.start(new GameClass());
	}
	public void run(){
		int i=8;
		boolean bool=(i>10)&&((i=i+5)>10);
		System.out.println(bool);
		System.out.println(i);
	}
}