package model;

public abstract class AbstractRobot {

    public enum Direction {
        UP {
            @Override
            public Direction prev() {
                return LEFT; // see below for options for this line
            }
        },
        RIGHT,
        DOWN,
        LEFT {
            @Override
            public Direction next() {

                return UP; // see below for options for this line
            }
        };

        public Direction next() {
            // No bounds checking required here, because the last instance overrides
            return values()[ordinal() + 1];
        }

        public Direction prev() {
            return values()[ordinal() - 1];
        }
    }

    public abstract Direction getDirection();

    public abstract int getX(); // текущая координата X

    public abstract int getY(); // текущая координата Y

    public abstract void turnLeft(); // повернуться на 90 градусов против часовой стрелки

    public abstract void turnRight(); // повернуться на 90 градусов по часовой стрелке

    public abstract void stepForward(); // шаг в направлении взгляда за один шаг робот изменяет одну свою координату на единицу
}
