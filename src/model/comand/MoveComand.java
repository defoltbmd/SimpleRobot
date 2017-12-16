package model.comand;

import model.AbstractRobot;

public abstract class MoveComand {

    public MoveComand(AbstractRobot robot) {
        this.robot = robot;
    }

    public abstract void execute();
    public MoveComandDelegate delegate;
    protected AbstractRobot robot;
}
