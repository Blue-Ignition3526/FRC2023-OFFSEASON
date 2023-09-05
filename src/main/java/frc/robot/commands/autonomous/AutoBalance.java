// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.Drive;
import frc.robot.subsystems.DriveTrain;

public class AutoBalance extends CommandBase {
  private final DriveTrain m_driveTrain;
  private double direction;

  public AutoBalance(DriveTrain m_driveTrain) {
    this.m_driveTrain = m_driveTrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_driveTrain.getPitchAngle() > 0){
      m_driveTrain.arcadeDrive(new ChassisSpeeds(0.4, 0, 0));
    }else if (m_driveTrain.getPitchAngle() < 0){
      m_driveTrain.arcadeDrive(new ChassisSpeeds(-0.4, 0, 0));
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
