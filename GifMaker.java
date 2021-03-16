/*
Harshul Singhal
Started 3/15/21
Designed to make a gif out of specified window similar to function
of snipping tool native to Windows
To Do:
Add keyboard shortcut
Add timer for after keyboard shortcut (event listener) triggered
possibly make a GUI for specified window
*/
import java.awt.*;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.PointerInfo;

import java.io.File;
import java.io.*;
import javax.imageio.ImageIO;


//import java.awt.Toolkit;
/*System.out.println("test");*/

public class GifMaker {
    public static void main(String[] args) throws IOException{
        try {
            int resultNumber=0; //for result capture naming convention
            //System.out.println(Toolkit.getDefaultToolkit().getScreenSize()); // full screen rectangle coords
            Robot robot = new Robot();
            Point mousePos = MouseInfo.getPointerInfo().getLocation();
            Rectangle rect = new Rectangle(0, 0, (int)mousePos.getX(), (int)mousePos.getY()); // rectangle size for capture

            BufferedImage capture = robot.createScreenCapture(rect);
            File result = new File("result"+resultNumber+".jpg");
            ImageIO.write(capture, "jpg", result);

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}