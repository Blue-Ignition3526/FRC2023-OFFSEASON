package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.NavX;

public class AutoBalance extends CommandBase {
  private NavX m_navx = null;
  private DriveTrain m_driveTrain = null;

  public AutoBalance(NavX navx, DriveTrain driveTrain) {
    this.m_navx = navx;
    this.m_driveTrain = driveTrain;

    addRequirements(driveTrain, navx);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double balance =  m_navx.getBalance();

    if (Math.abs(balance) > 3) {
      if (balance < 0) {
        m_driveTrain.arcadeDrive(0.3, 0);
      } else if (balance > 0) {
        m_driveTrain.arcadeDrive(-0.3, 0);
      }
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
