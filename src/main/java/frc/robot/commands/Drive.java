package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Constants.*;

public class Drive extends CommandBase {

  private final DriveTrain m_driveTrain;
  private final Supplier<Double> xSpeed, rotSpeed;
  private final SlewRateLimiter xLimiter, rLimiter;

  public Drive(DriveTrain m_driveTrain, Supplier<Double> xSpeed, Supplier<Double> rotSpeed) {
    this.m_driveTrain = m_driveTrain;
    this.xSpeed = xSpeed;
    this.rotSpeed = rotSpeed;

    //! Numeros de swerve
    this.xLimiter = new SlewRateLimiter(Physical.kTeleopMaxAccelerationUnitsPerSecond); // numeros SWERVE
    this.rLimiter = new SlewRateLimiter(Physical.kTeleopMaxAngularAccelerationUnitsPerSecond); // numeros SWERVE

    addRequirements(m_driveTrain);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    double xSpeed = this.xSpeed.get();
    double rotSpeed = this.rotSpeed.get();

    // deadzone
    xSpeed = Math.abs(xSpeed) > 0.1 ? xSpeed : 0.0;
    rotSpeed = Math.abs(rotSpeed) > 0.1 ? rotSpeed : 0.0;

    xSpeed = xLimiter.calculate(xSpeed) * Physical.kTeleopMaxSpeedMetersPerSecond;
    rotSpeed = rLimiter.calculate(rotSpeed) * Physical.kTeleopMaxAngularSpeedRadiansPerSecond;

    ChassisSpeeds speeds = ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, 0, rotSpeed, m_driveTrain.getYawRotation2d());

    m_driveTrain.arcadeDrive(speeds);
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