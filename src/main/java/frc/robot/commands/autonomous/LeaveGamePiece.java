// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.commands.armCommands.UpdateArmPosition;
import frc.robot.commands.endEffectorCommands.EndEffectorOut;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.EndEffector;

public class LeaveGamePiece extends CommandBase {
  private final Arm m_arm;
  private final EndEffector m_grabber;
  private UpdateArmPosition updateArmPosition;
  private EndEffectorOut endEffectorOut;

  public LeaveGamePiece(Arm m_arm, EndEffector m_grabber) {
    // set arm and grabber
    this.m_arm = m_arm;
    this.m_grabber = m_grabber;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_arm, m_grabber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    updateArmPosition = new UpdateArmPosition(m_arm, Constants.ArmPositions.kHigh);
  }

  boolean isDone = false; // only do once

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (updateArmPosition.isFinished() && isDone == false) {
      endEffectorOut = new EndEffectorOut(m_grabber);
      isDone = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_grabber.hasPiece();
  }
}
