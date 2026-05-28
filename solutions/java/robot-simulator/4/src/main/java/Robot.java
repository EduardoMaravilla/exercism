public class Robot {
    private GridPosition gridPosition;
    private Orientation orientation;

    public Robot(GridPosition gridPosition, Orientation orientation) {
        this.gridPosition = gridPosition;
        this.orientation = orientation;
    }

    public GridPosition getGridPosition() {
        return gridPosition;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void turnRight() {
        switch (this.orientation) {
            case NORTH -> this.orientation = Orientation.EAST;
            case EAST -> this.orientation = Orientation.SOUTH;
            case SOUTH -> this.orientation = Orientation.WEST;
            case WEST -> this.orientation = Orientation.NORTH;
            default -> throw new AssertionError();
        }
    }

    public void turnLeft() {
        switch (this.orientation) {
            case NORTH -> this.orientation = Orientation.WEST;
            case EAST -> this.orientation = Orientation.NORTH;
            case SOUTH -> this.orientation = Orientation.EAST;
            case WEST -> this.orientation = Orientation.SOUTH;
            default -> throw new AssertionError();
        }
    }

    public void advance() {
        int x = this.gridPosition.x;
        int y = this.gridPosition.y;
        switch (this.orientation) {
            case NORTH -> y += 1;
            case EAST -> x += 1;
            case SOUTH -> y -= 1;
            case WEST -> x -= 1;
            default -> throw new AssertionError();
        }
        this.gridPosition = new GridPosition(x, y);
    }

    public void simulate(String direction) {
        String[] comandos = direction.split("");
        for (String comando : comandos) {
            switch (comando) {
                case "L" -> turnLeft();
                case "R" -> turnRight();
                case "A" -> advance();
            }
        }
    }
}