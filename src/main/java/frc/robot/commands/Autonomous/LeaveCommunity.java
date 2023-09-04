// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Enum.DriveDirection;
import frc.robot.subsystems.DriveTrain;

public class LeaveCommunity extends CommandBase {

  private final DriveTrain driveTrain;
  private final Timer m_timer = new Timer();
  private DriveDirection direction;

  public LeaveCommunity(DriveTrain driveTrain, DriveDirection direction) {
    this.direction = direction;
    this.driveTrain = driveTrain;
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveTrain.resetEncoder();
    m_timer.reset();
    m_timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (direction == DriveDirection.BACKWARD) {
      driveTrain.drive(-0.5, -0.46);
    } else {
      driveTrain.drive(0.5, 0.46);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.drive(0, 0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (m_timer.get() >= 6.0) {
      return true;
    } else  if (Math.abs(driveTrain.getEncoderAvg())  > driveTrain.getCmTicks(350)) {
      return true; 
    }
    return false;
  }
}
