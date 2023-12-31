// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.armCommands.UpdateArmPosition;
import frc.robot.commands.endEffectorCommands.EndEffectorOutSeconds;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.EndEffector;

public class LeaveGamePiece extends SequentialCommandGroup {
  /** Creates a new LeaveGamePiece. */
  public LeaveGamePiece(Arm m_brazo, EndEffector m_grabber) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new UpdateArmPosition(m_brazo, Constants.ArmPositions.kHigh),
      new WaitCommand(0.1),
      new EndEffectorOutSeconds(m_grabber, 0.25)
    );
  }
}