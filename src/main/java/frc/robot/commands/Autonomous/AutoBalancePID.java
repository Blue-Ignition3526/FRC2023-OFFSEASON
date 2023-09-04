package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.MiniPID;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.NavX;

public class AutoBalancePID extends CommandBase {
  private NavX m_navx = null;
  private DriveTrain m_driveTrain = null;
  
  private MiniPID m_pid = new MiniPID(0.3, 0.45, 0.14);

  public AutoBalancePID(NavX navx, DriveTrain driveTrain) {
    this.m_navx = navx;
    this.m_driveTrain = driveTrain;

    addRequirements(driveTrain, navx);

    m_pid.setSetpoint(0);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double balance =  m_navx.getBalance();
    double output = m_pid.getOutput(balance);
    m_driveTrain.arcadeDrive(output, 0);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
