/**
 * Interface for an object that observes elevators for floor changes.
 *
 * Created by robertstjacquesjr on 7/12/17.
 */
public interface ElevatorObserver {
    public void floorChanged(Elevator elevator);
}
