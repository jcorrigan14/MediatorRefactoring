package ElevatorSystem;

/**
 * The basic elevator class.  This is not a particularly sophisticated
 * simulation as the elevators don't do a lot of error checking.
 *
 * Created by robertstjacquesjr on 7/12/17.
 */
public class Elevator extends ElevatorSystemComponent {
    /**
     * The elevator number.
     */
    private int number;

    /**
     * The elevator's current floor.
     */
    private int currentFloor;

    /**
     * The elevator's destination floor.
     */
    private int destinationFloor;

    /**
     * The elevator's current direction of movement.
     */
    private Direction direction;

    /**
     * Constructs a new elevator with the specified number.
     *
     * @param number The elevator's number; should be unique.  Used as an
     *               identifier for an elevator.
     */

    public Elevator(ElevatorControl mediator, int number) {
        super(mediator);
        this.number = number;
        currentFloor = 1;
        destinationFloor = currentFloor;
    }

    /**
     * Returns the elevator's unique ID number.
     *
     * @return The elevator's unique ID number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the elevator's current direction of movement.
     *
     * @return The elevator's current direction.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Returns the elevator's current destination floor; the floor to which the
     * elevator is moving.
     *
     * @return The elevator's current destination floor.
     */
    public int getDestinationFloor() {
        return destinationFloor;
    }

    /**
     * Returns the elevator's current floor.
     *
     * @return The elevator's current floor.
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * Sets the elevator's current destination floor to the specified value.
     * This method does not verify that the elevator is not currently in
     * motion in the opposite direction!
     *
     * @param destinationFloor The floor that the elevator should move towards.
     */
    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
        if(destinationFloor > currentFloor) {
            direction = Direction.UP;
        }
        else if(destinationFloor < currentFloor) {
            direction = Direction.DOWN;
        }
        else {
            direction = Direction.STATIONARY;
        }
    }

    /**
     * Moves the elevator one floor in its current direction.  A stationary
     * elevator does not move.  If the elevator does move, observers are
     * notified.
     */

    public void move() {
        switch(direction) {
            case UP:
                currentFloor = currentFloor + 1;
                fireFloorChanged();
                break;
            case DOWN:
                currentFloor = currentFloor - 1;
                fireFloorChanged();
                break;
        }

        if(currentFloor == destinationFloor) {
            direction = Direction.STATIONARY;
        }
    }

    /**
     * Notifies observers when the elevator arrives at a floor.
     */
    private void fireFloorChanged() {
        mediator.floorChanged(this);
    }
}
