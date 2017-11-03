import java.util.List;

/**
 * The main control class that creates all of the elevators and buttons and
 * wires them together.
 *
 * Created by robertstjacquesjr on 7/12/17.
 */
public class ElevatorControl {

    /**
     * The list of elevators.
     */
    private List<Elevator> elevators;

    /**
     * The list of all of the buttons.
     */
    private List<Button> buttons;

    public ElevatorControl(int numberOfFloors, int numberOfElevators) {
        // create elevators
        for(int elevator=0; elevator<numberOfElevators; elevator++) {
            // add elevators
            elevators.add(new Elevator(elevator));
            // add first floor buttons; up only
            buttons.add(new Button(1, Direction.UP));
            // add last floor buttons; down only
            buttons.add(new Button(numberOfFloors, Direction.DOWN));
        }

        // add remaining floor buttons; both up and down
        for(int floor=2; floor<numberOfFloors; floor++) {
            for(int elevator=0; elevator<numberOfElevators; elevator++) {
                buttons.add(new Button(floor, Direction.UP));
                buttons.add(new Button(floor, Direction.DOWN));
            }
        }

        // wire everything together
        for(Elevator elevator : elevators) {
            for(Button button : buttons) {
                button.addButtonObserver(elevator);
                elevator.addElevatorObserver(button);
            }
        }
    }
}
