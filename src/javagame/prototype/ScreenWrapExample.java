package javagame.prototype;

/**
 * Created by 79001 on 2017/7/23.
 */
public class ScreenWrapExample {
    /**
     * The ScreenWrapExample, as shown in Figure 8.7 and located in the javagames.prototype package,
     * uses the PolygonWrapper class to wrap a square that is moved around the screen.
     * The pos vector holds the position of the square.
     * The poly array holds the square polygon.
     * The renderList holds the copies of the square that are wrapped,
     * as well as the original transformed polygon.
     * The wrapper variable is the PolygonWrapper shown previously.
     */
    public ScreenWrapExample () {
//        appBorderScale = 0.9f;
//        appWidth = 640;
//        appHeight = 640;
//        appMaintainaRation = true;
//        appTitile = "Screen Wrap Example";
//        appBackground = Color.WHITE;
//        appFPSColor = Color.BLACK:
        //233
    }

    /**
     * The initialize() method creates all the objects and sets the mouse to relative movements so 
     * the object can be wrapped around the screen without the mouse leaving the screen.
     */

    /**
     * The processInput() method moves the square by using the mouse position if 
     * the absolute position is used and
     * by adding the relative movement to the current position if 
     * relative movement is being used.
     * This method also toggles relative mouse movement with the spacebar.
     */

    /**
     * The transform() method creates a copy of the object and transforms it by the given matrix.
     */

    /**
     * The updateObjects() method clears the render list, wraps and transforms the square,
     * and then uses the PolygonWrapper to add any copies of the object to the render list.
     */

    /**
     * The render() method draws all the objects in the render list to the screen.
     */

}
