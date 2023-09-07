// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class AutoBalance extends CommandBase {
  private final DriveTrain m_driveTrain;
  private final PIDController pid = new PIDController(0.01, 0.0, 0.0055);
  private final SlewRateLimiter xLimiter; // Limiters for acceleration

  public AutoBalance(DriveTrain m_driveTrain) {
    this.m_driveTrain = m_driveTrain;
    this.pid.setSetpoint(0);
    this.xLimiter = new SlewRateLimiter(Constants.Robot.kMaxAccelerationMetersPerSecond);
    addRequirements(m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double pidResult = MathUtil.clamp(pid.calculate(m_driveTrain.getPitchAngle()), -Constants.Robot.kMaxAutoBalanceSpeed, Constants.Robot.kMaxAutoBalanceSpeed);
    double xSpeed = xLimiter.calculate(pidResult) * Constants.Robot.kMaxAngularSpeedRadiansPerSecond;
    System.out.println(xSpeed);
    m_driveTrain.arcadeDrive(new ChassisSpeeds(xSpeed, 0, 0));
  }
  public void end(boolean interrupted) {
    m_driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(m_driveTrain.getPitchAngle()) < 5;
  }
}
