package view;

import model.AbstractRobot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.toRadians;

public class CanvasPanel extends JPanel {
    BufferedImage targetImage;
    BufferedImage robotsUpImage;
    BufferedImage robotsLeftImage;
    BufferedImage robotsDownImage;
    BufferedImage robotsRightImage;

    private int startX;
    private int startY;
    private AbstractRobot.Direction direction;
    private int endX;
    private int endY;

    public CanvasPanel() {
        super();
        this.setBackground(Color.BLACK);
        direction = AbstractRobot.Direction.UP;
        try {
            targetImage = ImageIO.read(new File("res\\target.png"));
            robotsUpImage = ImageIO.read(new File("res\\arow.png"));
            robotsLeftImage = rotate(robotsUpImage, -90);
            robotsDownImage = rotate(robotsUpImage, -180);
            robotsRightImage = rotate(robotsUpImage, -270);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        super.paint(g); // clears drawing area
        if (targetImage != null) {
            g.drawImage(targetImage, endX, endY, this);
        }
        switch (direction) {
            case UP:
                g.drawImage(robotsUpImage, startX, startY, this);
                break;
            case LEFT:
                g.drawImage(robotsLeftImage, startX, startY, this);
                break;
            case DOWN:
                g.drawImage(robotsDownImage, startX, startY, this);
                break;
            case RIGHT:
                g.drawImage(robotsRightImage, startX, startY, this);
                break;
        }
    }

    public void setStart(int x, int y) {
        startX = x;
        startY = y;
        repaint();
    }

    public void setEnd(int x, int y) {
        endX = x;
        endY = y;
        repaint();
    }

    public void setDirection(AbstractRobot.Direction direction) {
        this.direction = direction;
        repaint();
    }

    private static BufferedImage rotate(BufferedImage imgOld, int deg) {                                                 //parameter same as method above
        BufferedImage imgNew = new BufferedImage(imgOld.getWidth(), imgOld.getHeight(), imgOld.getType());              //create new buffered image
        Graphics2D g = (Graphics2D) imgNew.getGraphics();                                                               //create new graphics
        g.rotate(toRadians(deg), imgOld.getWidth() / 2, imgOld.getHeight() / 2);                                    //configure rotation
        g.drawImage(imgOld, 0, 0, null);                                                                                //draw rotated image
        return imgNew;                                                                                                  //return rotated image
    }
}
