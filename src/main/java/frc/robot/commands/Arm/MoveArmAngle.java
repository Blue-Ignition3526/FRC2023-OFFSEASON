// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;

public class MoveArmAngle extends CommandBase {
  private Arm arm;
  private double angle;
  private Timer m_timer = new Timer();

  /** Creates a new MoveArmAngle. */
  public MoveArmAngle(Arm arm, double angle) {
    this.arm = arm;
    this.angle = angle;
    addRequirements(arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    arm.resetEncoder();
    m_timer.reset();
    m_timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (angle > 0) {
      arm.armSet(Constants.Speeds.armUp);
    } else if (angle < 0) {
      arm.armSet(Constants.Speeds.armDown);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    arm.armSet(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (m_timer.get() >= 6.0) {
      return true;
    } else if ((Math.abs(angle) - Math.abs(arm.getEncoderAngle())) < 0.1) {
      return true;
    }
    return false;
  }
}
