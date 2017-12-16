package model;

public class DirectionRobot extends AbstractRobot {
    private int x;
    private int y;
    private Direction direction;

    public DirectionRobot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void turnLeft() {
//        System.out.println("Direction LP " + direction);
        direction = direction.prev();
//        System.out.println("Direction L= " + direction);
    }

    @Override
    public void turnRight() {
//        System.out.println("Direction RP " + direction);
        direction = direction.next();
//        System.out.println("Direction R= " + direction);
    }

    @Override
    public void stepForward() {
        switch (direction) {
            case UP:    y--; break;
            case RIGHT: x++; break;
            case DOWN:  y++; break;
            case LEFT:  x--; break;
        }
        //System.out.println("x: " + x + " y: " + y);
    }
}
