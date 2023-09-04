// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DriveTrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class MoveDistance extends CommandBase {

  private DriveTrain driveTrain;
  private double distanceCM;
  private double ticks;

  public MoveDistance(DriveTrain driveTrain, double distanceCM) {
    this.driveTrain = driveTrain;
    this.distanceCM = distanceCM;
    this.ticks = driveTrain.getCmTicks(distanceCM);
    addRequirements(driveTrain);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    System.out.println("qpd");
    while (driveTrain.getEncoderAvg() < ticks) {
      driveTrain.arcadeDrive(Constants.autonomousSpeed, 0);
      System.out.println("jala");
    }

    driveTrain.arcadeDrive(0, 0);
  }

  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
