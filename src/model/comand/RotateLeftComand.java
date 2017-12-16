package model.comand;

import model.AbstractRobot;
import view.MainForm;

public class RotateLeftComand extends MoveComand{
    public RotateLeftComand(AbstractRobot robot) {
        super(robot);
    }

    @Override
    public void execute() {
        this.robot.turnLeft();
        MainForm.canvasPanel.setDirection(this.robot.getDirection());
        if (delegate != null) {
            delegate.didCompleteComand();
        }
    }
}
