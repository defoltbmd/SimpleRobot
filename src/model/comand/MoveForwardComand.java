package model.comand;

import model.AbstractRobot;
import view.MainForm;

public class MoveForwardComand extends MoveComand {
    public MoveForwardComand(AbstractRobot robot) {
        super(robot);
    }

    @Override
    public void execute() {
        this.robot.stepForward();
        MainForm.canvasPanel.setStart(this.robot.getX(), this.robot.getY());
        //todo add check if command was completed
        if (delegate != null) {
            delegate.didCompleteComand();
        }
    }
}
