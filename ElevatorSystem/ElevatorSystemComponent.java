package ElevatorSystem;

public abstract class ElevatorSystemComponent {
    public Mediator mediator;

    public ElevatorSystemComponent(Mediator mediator){
        this.mediator = mediator;
    }



}
