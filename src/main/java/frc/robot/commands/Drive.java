package frc.robot.commands;

import java.util.function.Supplier;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class Drive extends CommandBase {

  private final DriveTrain m_driveTrain;
  private final Supplier<Double> xSpeed, rotSpeed; // X and rotation speeds
  private final SlewRateLimiter xLimiter, rotLimiter; // Limiters for acceleration

  public Drive(DriveTrain m_driveTrain, Supplier<Double> xSpeed, Supplier<Double> rotSpeed) {
    this.m_driveTrain = m_driveTrain;
    this.xSpeed = xSpeed;
    this.rotSpeed = rotSpeed;
    this.xLimiter = new SlewRateLimiter(Constants.Robot.kMaxAccelerationMetersPerSecond);
    this.rotLimiter = new SlewRateLimiter(Constants.Robot.kMaxAngularSpeedRadiansPerSecond);

    addRequirements(m_driveTrain);
  }
  

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    // Get the speeds from the controller
    double xSpeed = this.xSpeed.get();
      double rotSpeed = this.rotSpeed.get();

    // Apply deadzone
    xSpeed = Math.abs(xSpeed) > 0.1 ? xSpeed : 0.0;
      rotSpeed = Math.abs(rotSpeed) > 0.1 ? rotSpeed : 0.0;

    // Limit the speeds
    xSpeed = xLimiter.calculate(xSpeed) * Constants.Robot.kMaxAngularSpeedRadiansPerSecond;
      rotSpeed = rotLimiter.calculate(rotSpeed) * Constants.Robot.kMaxAngularSpeedRadiansPerSecond;

    m_driveTrain.arcadeDrive(new ChassisSpeeds(xSpeed, 0, rotSpeed));
  }

  @Override
  public void end(boolean interrupted) {
    m_driveTrain.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}