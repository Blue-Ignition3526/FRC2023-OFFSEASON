package frc.robot.commands.endEffectorCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.EndEffector;

public class EndEffectorIn extends CommandBase {
  private EndEffector endEffector;

  public EndEffectorIn(EndEffector endEffector) {
      this.endEffector = endEffector;
      addRequirements(endEffector);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    endEffector.endEffectorSet(Constants.Robot.kEndEffectorSpeedIn);
  }

  @Override
  public void end(boolean interrupted) {
    if(endEffector.hasPiece()) {
      endEffector.endEffectorSet(Constants.Robot.kEndEffectorSpeedHold);
    } else {
      endEffector.endEffectorSet(0);
    }
  }

  @Override
  public boolean isFinished() {
    return endEffector.hasPiece();
  }
}
