package frc.robot.commands.grabber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Grabber;

public class GrabberIn extends CommandBase {
    private Grabber garra;

    public GrabberIn(Grabber garra) {
        this.garra = garra;
        addRequirements(garra);
    }

     // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    garra.garraSet(-0.3); //neo empieza a girar (numero sacado de temporada)
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    garra.garraSet(0); // dejar de jalar
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
