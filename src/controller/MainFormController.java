package controller;

import model.AbstractRobot;
import model.DirectionRobot;
import model.RobotBuilder;
import view.MainForm;
import view.MainFormDelegate;

import java.awt.*;

import static java.lang.Math.abs;

public class MainFormController implements MainFormDelegate, iComandInvokerDelegate {

    private RobotBuilder builder;
    private int endX;
    private int endY;
    private ComandInvoker comandInvoker;
    private DirectionRobot robot;

    public MainFormController() {
        endX = -1;
        endY = -1;
    }

    @Override
    public void didClickOnCanvas(int x, int y, MainForm.EditingMode mode) {
        switch (mode) {
            case START_POINT: {
                System.out.println("Start " + x + "," + y);
                if (builder == null) {
                    builder = new RobotBuilder(robot).setDirection(AbstractRobot.Direction.UP);
                }
                MainForm.canvasPanel.setStart(x, y);
                MainForm.canvasPanel.setDirection(builder.build().getDirection());
                builder = builder
                        .setX(x)
                        .setY(y);
            }
            break;
            case END_POINT: {
                MainForm.canvasPanel.setEnd(x, y);
                System.out.println("End " + x + "," + y);
                endX = x;
                endY = y;
            }
        }
    }

    @Override
    public void didPickDirection(AbstractRobot.Direction direction) {
        if (builder == null) {
            builder = new RobotBuilder(robot);
        }
        MainForm.canvasPanel.setDirection(direction);

        builder.setDirection(direction);
    }

    @Override
    public void didStart() {
        //build a path
        comandInvoker = new ComandInvoker();
        comandInvoker.delegate = this;
        if (builder != null && endX > 0 && endY > 0) {
            comandInvoker.fillPath((DirectionRobot) builder.build(), endX, endY);
            comandInvoker.invoke();
        }
//        System.out.println("New x: " + robot.getX() + " y:" + robot.getY());
    }

    @Override
    public void didCompletedComand() {
        //todo redraw robot
    }

    @Override
    public void didFinishedQueue(Point lastPoint) {
        //todo show something
        builder = builder
                .setX(lastPoint.x)
                .setY(lastPoint.y);
    }
}
