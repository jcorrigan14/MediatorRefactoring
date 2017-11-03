import java.util.List;

/**
 * A class that represents an elevator button.
 *
 * Created by robertstjacquesjr on 7/12/17.
 */
public class Button implements ElevatorObserver {

    /**
     * The floor on which this button is installed.
     */
    private int floor;

    /**
     * The direction for this button.
     */
    private Direction direction;

    /**
     * Indicates whether or not the button is currently pressed.
     */
    private boolean pressed;

    /**
     * The registered button observers.
     */
    private List<ButtonObserver> observers;

    /**
     * Makes a new button on the specified floor that calls an elevator to
     * move in the specified direction.
     *
     * @param floor The floor on which the button is installed.
     * @param direction The direction that the button calls an elevator to
     *                  move in.
     */
    public Button(int floor, Direction direction) {
        this.floor = floor;
        this.direction = direction;
        pressed = false;
    }

    /**
     * Returns the floor on which this button is installed.
     *
     * @return The floor on which this button is installed.
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Returns the direction that the button calls an elevator to move in.
     *
     * @return The direction that this button calls an elevator to move in.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Returns true if the button has been pressed and false otherwise.
     *
     * @return A boolean indicating whether or not the button has been pressed.
     */
    public boolean isPressed() {
        return pressed;
    }

    /**
     * Presses the button (sets {@link #pressed} to true).
     */
    public void press() {
        if(!pressed) {
            pressed = true;
            fireButtonPressed();
        }
    }

    /**
     * Adds an observer to this button that will be notified when the button
     * is pressed.
     *
     * @param observer The observer that should be notified when the button is
     *                 pressed.
     */
    public void addButtonObserver(ButtonObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes the observer from the list of registered observers.
     *
     * @param observer The observer to remove.
     */
    public void removeButtonObserver(ButtonObserver observer) {
        observers.remove(observer);
    }


    @Override
    public void floorChanged(Elevator elevator) {
        // has the elevator arrived at this floor?
        if(elevator.getCurrentFloor() == floor) {
            Direction elevatorDirection = elevator.getDirection();
            // is it moving in the right direction?
            if(elevatorDirection == direction ||
                    elevatorDirection == Direction.STATIONARY) {
                pressed = false;
            }
        }
    }

    /**
     * Notifies each of the currently registered observers that the button
     * has been pressed and whether or not another (previous) observer has
     * handled the button press.
     */
    private void fireButtonPressed() {
        boolean handled = false;
        for(ButtonObserver observer : observers) {
            handled = observer.buttonPressed(this, handled);
        }
    }
}
