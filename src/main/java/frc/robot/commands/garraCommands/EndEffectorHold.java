package frc.robot.commands.garraCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.EndEffector;

public class EndEffectorHold extends CommandBase {
  private EndEffector garra;

  public EndEffectorHold(EndEffector garra){
    this.garra = garra;
    addRequirements(garra);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    garra.garraSet(Constants.Robot.kEndEffectorSpeedHold);
  }
  
  @Override
  public void end(boolean interrupted) {
    garra.garraSet(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
