package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Constants.*;

public class Drive extends CommandBase {

  private final DriveTrain m_driveTrain;
  private final Supplier<Double> xSpeed, ySpeed, rotSpeed;
  private final SlewRateLimiter xLimiter, yLimiter, rLimiter;

  public Drive(DriveTrain m_driveTrain, Supplier<Double> xSpeed, Supplier<Double> ySpeed, Supplier<Double> rotSpeed) {
    this.m_driveTrain = m_driveTrain;
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
    this.rotSpeed = rotSpeed;
    // TODO: camiar numeros
    this.xLimiter = new SlewRateLimiter(Physical.kTeleopMaxAccelerationUnitsPerSecond); // numeros SWERVE
    this.yLimiter = new SlewRateLimiter(Physical.kTeleopMaxAccelerationUnitsPerSecond); // numeros SWERVE
    this.rLimiter = new SlewRateLimiter(Physical.kTeleopMaxAngularAccelerationUnitsPerSecond); // numeros SWERVE

    addRequirements(m_driveTrain);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double xSpeed = this.xSpeed.get();
    double ySpeed = this.ySpeed.get();
    double rotSpeed = this.rotSpeed.get();

    // deadzone
    xSpeed = Math.abs(xSpeed) > 0.1 ? xSpeed : 0.0;
    ySpeed = Math.abs(ySpeed) > 0.1 ? ySpeed : 0.0;
    rotSpeed = Math.abs(rotSpeed) > 0.1 ? rotSpeed : 0.0;

    xSpeed = xLimiter.calculate(xSpeed) * Physical.kTeleopMaxSpeedMetersPerSecond;
    ySpeed = yLimiter.calculate(ySpeed) * Physical.kTeleopMaxSpeedMetersPerSecond;
    rotSpeed = rLimiter.calculate(rotSpeed) * Physical.kTeleopMaxAngularSpeedRadiansPerSecond;

    ChassisSpeeds speeds;
    speeds = ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rotSpeed, m_driveTrain.getYawRotation2d());

    m_driveTrain.arcadeDrive(speeds);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}