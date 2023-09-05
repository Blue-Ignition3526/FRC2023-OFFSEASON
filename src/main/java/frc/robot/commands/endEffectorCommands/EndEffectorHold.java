package frc.robot.commands.endEffectorCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.EndEffector;

public class EndEffectorHold extends CommandBase {
  private EndEffector endEffector;

  public EndEffectorHold(EndEffector endEffector){
    this.endEffector = endEffector;
    addRequirements(endEffector);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    endEffector.endEffectorSet(Constants.Robot.kEndEffectorSpeedHold);
  }
  
  @Override
  public void end(boolean interrupted) {
    endEffector.endEffectorSet(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
