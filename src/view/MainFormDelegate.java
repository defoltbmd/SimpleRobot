package view;

import model.AbstractRobot;

public interface MainFormDelegate {

    void didClickOnCanvas(int x, int y, MainForm.EditingMode mode);

    void didStart();

    void didClear();

    void didPickDirection(AbstractRobot.Direction direction);
}
