/**
 * Interface implemented by objects that observe a button and potentially
 * react to button presses.
 *
 * Created by robertstjacquesjr on 7/12/17.
 */
public interface ButtonObserver {

    /**
     * Notifies the observer when a button is pressed.
     *
     * @param button The button that was pressed.
     *
     * @param handled A boolean indicating whether or not the button press
     *                was handled by a previous observer.
     *
     * @return A boolean indicating whether or not the observer handled the
     * button press event.
     */
    public boolean buttonPressed(Button button, boolean handled);
}
