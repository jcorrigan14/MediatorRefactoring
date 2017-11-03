package ElevatorSystem;

public interface Mediator {
    public boolean buttonPressed(Button button, boolean handled);
    public void floorChanged(Elevator elevator);

}
