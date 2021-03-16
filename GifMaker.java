import java.awt.image.BufferedImage;
import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.awt.Robot;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.MouseInfo;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

import java.util.Timer;
import java.util.TimerTask;

import java.sql.Time;

//import java.awt.Toolkit;

public class GifMaker {
    Thread t;
    Timer timer = new Timer();
    public static int resultNumber=0; //for result capture naming convention
    public static int numOfResults = 5;
    public static int fps = 5;
    public static int length = 1000/fps*numOfResults; //ms

    public static void main(String[] args) throws InterruptedException, AWTException, IOException {

        // System.out.println(Toolkit.getDefaultToolkit().getScreenSize()); // full
        // screen rectangle coords
        Robot robot = new Robot();
        Point mousePos = MouseInfo.getPointerInfo().getLocation();
        Rectangle rect = new Rectangle(0, 0, (int) mousePos.getX(), (int) mousePos.getY()); // rectangle size for //

        long startTime = System.currentTimeMillis();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Your database code here
                BufferedImage capture = robot.createScreenCapture(rect);
                File result = new File("result" + resultNumber + ".jpg");
                try {
                    ImageIO.write(capture, "jpg", result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                resultNumber++;
            }
        };
        timer.scheduleAtFixedRate(task, 1000/fps, 1000/fps);
        Thread.sleep(length);
        timer.cancel();

        ImageOutputStream output = new FileImageOutputStream(new File("finalGif.gif"));
        File filename = new File("result1.jpg");
        BufferedImage ex = ImageIO.read(filename);
        GifSequenceWriter gifWriter = new GifSequenceWriter(output, ex.getType(), 1, false);

        for (int i=0;i<numOfResults;i++) {
            File filename2 = new File("result"+i+".jpg");
            BufferedImage imageToWrite = ImageIO.read(filename2);
            gifWriter.writeToSequence(imageToWrite);
        }
        gifWriter.close();
        output.close();

        long endTime = System.currentTimeMillis();
        System.out.println(startTime - endTime);
    }
}