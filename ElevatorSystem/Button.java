package ElevatorSystem;

/**
 * A class that represents an elevator button.
 *
 * Created by robertstjacquesjr on 7/12/17.
 */
public class Button extends ElevatorSystemComponent {

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
     * Makes a new button on the specified floor that calls an elevator to
     * move in the specified direction.
     *
     * @param floor The floor on which the button is installed.
     * @param direction The direction that the button calls an elevator to
     *                  move in.
     */
    public Button(ElevatorControl mediator, int floor, Direction direction) {
        super(mediator);
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

    public void endPress(){
        pressed = false;
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
     * Notifies each of the currently registered observers that the button
     * has been pressed and whether or not another (previous) observer has
     * handled the button press.
     */
    //might not need this because no external force can change the state fo the elevator. its just
    // //on a floor . maybe direction is its state?
     //or we remove this
    private void fireButtonPressed() {
        boolean handled = false;
        mediator.buttonPressed(this, handled);
    }
}
