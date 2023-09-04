// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Enum.TurnDirection;
import frc.robot.subsystems.DriveTrain;

public class TurnSeconds extends CommandBase {
  private DriveTrain driveTrain;
  private double seconds;
  private Timer timer;
  private TurnDirection turnDirection;

  /** Creates a new MoveSeconds. */
  public TurnSeconds(DriveTrain driveTrain, double seconds, TurnDirection turnDirection) {
    this.driveTrain = driveTrain;
    this.seconds = seconds;
    this.turnDirection = turnDirection;
    this.timer = new Timer();
    addRequirements(driveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (turnDirection == TurnDirection.LEFT) {
      driveTrain.arcadeDrive(0, -Constants.autonomousSpeed);
    } else {
      driveTrain.arcadeDrive(0, Constants.autonomousSpeed);
    }
    driveTrain.arcadeDrive(0, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() >= seconds;
  }
}
