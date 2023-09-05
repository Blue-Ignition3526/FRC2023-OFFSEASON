package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class MoveDistance extends CommandBase {

  private final DriveTrain m_driveTrain;
  private final double distance;

  /**
   * 
   * @param m_driveTrain The drivetrain subsystem
   * @param distance The distance in cm that the robot will travel
   * @param xSpeed The speed at which the robot is moving (Recommended 0.5)
   */
  public MoveDistance(DriveTrain m_driveTrain, double distance) {
    this.m_driveTrain = m_driveTrain;
    this.distance = distance;
    addRequirements(m_driveTrain);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    m_driveTrain.setMotors(-0.5);
  }

  @Override
  public void end(boolean interrupted) {
    m_driveTrain.stop();
  }

  @Override
  public boolean isFinished() {
    if(m_driveTrain.getEncodersDistance()>distance) {
      return true;
    } else {
      return false;
    }
  }
}
