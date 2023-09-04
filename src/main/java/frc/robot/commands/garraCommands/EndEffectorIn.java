package frc.robot.commands.garraCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.EndEffector;

public class EndEffectorIn extends CommandBase {
  private EndEffector garra;

  public EndEffectorIn(EndEffector garra) {
      this.garra = garra;
      addRequirements(garra);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    garra.garraSet(Constants.Robot.kEndEffectorSpeedIn);
  }

  @Override
  public void end(boolean interrupted) {
    if(garra.hasPiece()) {
      garra.garraSet(Constants.Robot.kEndEffectorSpeedHold);
    } else {
      garra.garraSet(0);
    }
  }

  @Override
  public boolean isFinished() {
    return garra.hasPiece();
  }
}
