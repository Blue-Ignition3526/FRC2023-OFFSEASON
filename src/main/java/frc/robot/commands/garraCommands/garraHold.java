package frc.robot.commands.garraCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Garra;

public class garraHold extends CommandBase {
    private Garra garra;
    private boolean active;

    public garraHold(Garra garra){
        this.garra = garra;

        addRequirements(garra);
    }

    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    if (active) {
        garra.garraSet(0.2);
    } else {
        garra.garraSet(0);
    }
  }

  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
