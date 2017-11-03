package ElevatorSystem;

public abstract class ElevatorComponent {
    public Mediator mediator;

    public ElevatorComponent(Mediator mediator){
        this.mediator = mediator;
    }



}
