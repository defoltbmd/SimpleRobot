package controller;

import model.AbstractRobot;
import model.DirectionRobot;
import model.comand.*;

import java.util.ArrayList;

import static java.lang.Math.abs;

//we won't separate use queue class for simplicity
public class ComandInvoker extends Thread implements MoveComandDelegate {
    private ArrayList<MoveComand> comands;
    public iComandInvokerDelegate delegate;

    public ComandInvoker() {
        comands = new ArrayList<>();
    }

    @Override
    public void run() {
        super.run();
        if (comands != null && comands.size() > 0) {
            MoveComand comand = comands.get(0);
            comand.execute();
            comands.remove(0);
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.run();
        } else {
            if (delegate != null) {
                delegate.didFinishedQueue();
            }
        }

    }

    public void invoke() {
        this.start();
        /*
        for (MoveComand comand : comands) {
            comand.execute();
        }
        if (delegate != null) {
            delegate.didFinishedQueue();
        }*/
    }

    public void addComand(MoveComand comand) {
        comands.add(comand);
//        if (comand instanceof RotateLeftComand || comand instanceof RotateRightComand) {
//            System.out.println("Add Comand " + comand);
//        }
        comand.delegate = this;
    }

    @Override
    public void didCompleteComand() {
        if (delegate != null) {
            delegate.didCompletedComand();
        }
    }

    public void fillPath(DirectionRobot robot, int endX, int endY) {

        int width = robot.getX() - endX;
        int height = robot.getY() - endY;
        //todo refactor logic
        //select direction
        AbstractRobot.Direction abstractDirection = robot.getDirection();
        System.out.println("first!");
        if (width < 0) {
            //start on the left side
            switch (abstractDirection) {
                case UP: {
                    this.addComand(new RotateRightComand(robot));
                }
                break;
                case LEFT: {
                    this.addComand(new RotateRightComand(robot));
                    this.addComand(new RotateRightComand(robot));
                }
                break;
                case DOWN: {
                    this.addComand(new RotateLeftComand(robot));
                }
                break;
            }
            abstractDirection = AbstractRobot.Direction.RIGHT;
        }
        if (width > 0) {
            //start on the right side
            switch (abstractDirection) {
                case UP: {
                    this.addComand(new RotateLeftComand(robot));
                }
                break;
                case DOWN: {
                    this.addComand(new RotateRightComand(robot));
                }
                break;
                case RIGHT: {
                    this.addComand(new RotateLeftComand(robot));
                    this.addComand(new RotateLeftComand(robot));
                }
                break;
            }
            abstractDirection = AbstractRobot.Direction.LEFT;
        }
        //move X
        System.out.println(width);
        for (int i = 0; i < abs(width); i++) {
            this.addComand(new MoveForwardComand(robot));
        }
        //select direction again
        if (height < 0) {
            //if start above end
            switch (abstractDirection) {
                case LEFT: {
                    this.addComand(new RotateLeftComand(robot));
                }
                break;
                case RIGHT: {
                    this.addComand(new RotateRightComand(robot));
                }
                break;
            }
//            abstractDirection = AbstractRobot.Direction.DOWN;
        }
        if (height > 0) {
            //if start below end
            switch (abstractDirection) {
                case LEFT: {
                    this.addComand(new RotateRightComand(robot));
                }
                break;
                case RIGHT: {
                    this.addComand(new RotateLeftComand(robot)); //done
                }
                break;
            }
//            abstractDirection = AbstractRobot.Direction.UP;
        }
        //move Y
        System.out.println(height);
        for (int i = 0; i < abs(height); i++) {
            this.addComand(new MoveForwardComand(robot));
        }
    }
}
