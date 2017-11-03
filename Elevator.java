import java.util.ArrayList;
import java.util.List;

/**
 * The basic elevator class.  This is not a particularly sophisticated
 * simulation as the elevators don't do a lot of error checking.
 *
 * Created by robertstjacquesjr on 7/12/17.
 */
public class Elevator implements ButtonObserver {
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
     * The list of registered observers.  Notified whenever the elevator
     * arrives at a new floor.
     */
    private List<ElevatorObserver> observers;

    /**
     * Constructs a new elevator with the specified number.
     *
     * @param number The elevator's number; should be unique.  Used as an
     *               identifier for an elevator.
     */
    public Elevator(int number) {
        this.number = number;
        currentFloor = 1;
        destinationFloor = currentFloor;

        observers = new ArrayList<>();
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
     * Adds an observer to this elevator to be notified when the elevator
     * arrives at a new floor.
     *
     * @param observer The new observer.
     */
    public void addElevatorObserver(ElevatorObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer that was previously registered to be notified.
     *
     * @param observer The observer to remove.
     */
    public void removeElevatorObserver(ElevatorObserver observer) {
        observers.remove(observer);
    }

    /**
     * Called when a button that this elevator is observing is pressed.
     *
     * @param button The button that was pressed.
     *
     * @param handled A boolean indicating whether or not the button press
     *                was handled by a previous observer.
     *
     * @return A boolean indicating whether or not this button will handle the
     * button press event.
     */
    @Override
    public boolean buttonPressed(Button button, boolean handled) {
        boolean willHandle = false;
        int buttonFloor = button.getFloor();

        switch(direction) {
            case STATIONARY:
                setDestinationFloor(buttonFloor);
                willHandle = true;
                break;
            case DOWN:
                willHandle = buttonFloor < currentFloor;
                break;
            case UP:
                willHandle = buttonFloor > currentFloor;
                break;
        }

        return willHandle;
    }

    /**
     * Notifies observers when the elevator arrives at a floor.
     */
    private void fireFloorChanged() {
        for(ElevatorObserver observer : observers) {
            observer.floorChanged(this);
        }
    }
}
