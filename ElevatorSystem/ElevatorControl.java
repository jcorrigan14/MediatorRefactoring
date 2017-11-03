package ElevatorSystem;

import java.util.ArrayList;

public class ElevatorControl implements Mediator {
    private ArrayList<ElevatorSystemComponent> elevatorSystemComponents;

    public ElevatorControl() {
        elevatorSystemComponents = new ArrayList<>();
    }

    public void addElevator(ElevatorSystemComponent elevator){
        elevatorSystemComponents.add(elevator);
    }

    public void addButton(ElevatorSystemComponent button){
        elevatorSystemComponents.add(button);
    }

    @Override
    public boolean buttonPressed(Button button, boolean handled) {
        boolean willHandle = false;
        int buttonFloor = button.getFloor();
        for (ElevatorSystemComponent elevator : elevatorSystemComponents) {
            if (elevator instanceof Elevator) {
                switch (((Elevator) elevator).getDirection()) {
                    case STATIONARY:
                        ((Elevator) elevator).setDestinationFloor(buttonFloor);
                        willHandle = true;
                        break;
                    case DOWN:
                        willHandle = buttonFloor < ((Elevator) elevator).getCurrentFloor();
                        break;
                    case UP:
                        willHandle = buttonFloor > ((Elevator) elevator).getCurrentFloor();
                        break;
                }
            }
        }

        return willHandle; //not entirely sure where this is returned to and how it is passed along
    }

    @Override
    public void floorChanged(Elevator elevator) {
        for (ElevatorSystemComponent button : elevatorSystemComponents){
            if (button instanceof Button){
                if (((Button) button).isPressed()){
                    // has the elevator arrived at this floor?
                    if(elevator.getCurrentFloor() == ((Button) button).getFloor()) {
                        Direction elevatorDirection = elevator.getDirection();
                        // is it moving in the right direction? **** I guess this handles the case of stopping early for someone?
                        if(elevatorDirection == ((Button) button).getDirection() ||
                                elevatorDirection == Direction.STATIONARY) {
                            ((Button) button).endPress(); //added this method because it was private varible i needed access to
                        }
                    }
                }
            }
        }
        
    }

}
