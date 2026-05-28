public class Robot {
     private GridPosition gridPosition;
     private Orientation orientation;
    public Robot(GridPosition gridPosition,Orientation orientation) {
        this.gridPosition=gridPosition;
        this.orientation=orientation;
    }

    public GridPosition getGridPosition() {
        return gridPosition;
    }

    public Orientation getOrientation() {
        return orientation;
    }
    
    public void turnRight(){
        switch (this.orientation) {
            case NORTH:
                this.orientation=Orientation.EAST;
                break;
            case EAST:
                this.orientation=Orientation.SOUTH;
                break;
            case SOUTH:
                this.orientation=Orientation.WEST;
                break;
            case WEST:
                this.orientation=Orientation.NORTH;
                break;     
            default:
                throw new AssertionError();
        }
    }
    public void turnLeft(){
        switch (this.orientation) {
            case NORTH:
                this.orientation=Orientation.WEST;
                break;
            case EAST:
                this.orientation=Orientation.NORTH;
                break;
            case SOUTH:
                this.orientation=Orientation.EAST;
                break;
            case WEST:
                this.orientation=Orientation.SOUTH;
                break;     
            default:
                throw new AssertionError();
        }
    }
    public void advance(){        
        int x=this.gridPosition.x;
        int y=this.gridPosition.y;        
        switch (this.orientation) {
            case NORTH:
                y +=1;
                break;
            case EAST:
                x +=1;
                break;
            case SOUTH:
                 y -=1;
                break;
            case WEST:
                x -=1;
                break;     
            default:
                throw new AssertionError();
        }
       this.gridPosition=new GridPosition(x, y);
        
    }
    public void simulate(String direction){
        String[] comandos=direction.split("");
        for (String comando : comandos) {
            if (comando.equals("L")) {
                turnLeft();
            }else if (comando.equals("R")) {
                turnRight();
            }else if(comando.equals("A")){
               advance();
            }   
        }
    }
}
