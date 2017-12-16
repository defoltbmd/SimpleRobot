package model;

public class RobotBuilder {

    private int x;
    private int y;
    private AbstractRobot.Direction direction;

    public RobotBuilder() {
        //direction  = AbstractRobot.Direction.UP;
    }

    public RobotBuilder(DirectionRobot robot) {
        if (robot != null) {
            x = robot.getX();
            y = robot.getY();
            direction = robot.getDirection();
        }
    }

    public RobotBuilder setX(int value) {
        x = value;
        return this;
    }

    public RobotBuilder setY(int value) {
        y = value;
        return this;
    }

    public RobotBuilder setDirection(AbstractRobot.Direction value) {
        direction = value;
        return this;
    }

    public AbstractRobot build() {
        return new DirectionRobot(x, y, direction);
    }
}
