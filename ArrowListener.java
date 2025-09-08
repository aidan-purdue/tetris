import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
/**
 * ArrowListener establishes the name of the methods that
 * respond to the four arrow keys.
 * 
 * @author  Dave Feinberg
 * @author  Richard Page
 * @author  Susan King     Added documentation
 * @author  Aidan Wang     Added cPressed, xPressed, and zPressed
 * @version May 13, 2015
 * @version March 7, 2023
 */
public interface ArrowListener
{
    /**
     * Responses to the up arrow.
     */
    void upPressed();

    /**
     * Responses to the down arrow.
     */
    void downPressed();

    /**
     * Responses to the left arrow.
     */
    void leftPressed();

    /**
     * Responses to the right arrow.
     */
    void rightPressed();
    
    /**
     * Responses to the space.
     */
    void spacePressed();
    
    /**
     * Responses to the c key.
     */
    void cPressed();

    /**
     * Responses to the x key.
     */
    void xPressed();
    
    /**
     * Responses to the z key.
     */
    void zPressed();
}
