package model.comand;

import model.AbstractRobot;
import view.MainForm;

public class RotateRightComand extends MoveComand {

    public RotateRightComand(AbstractRobot robot) {
        super(robot);
    }

    @Override
    public void execute() {
        this.robot.turnRight();
        MainForm.canvasPanel.setDirection(this.robot.getDirection());
        if (delegate != null) {
            delegate.didCompleteComand();
        }
    }
}
