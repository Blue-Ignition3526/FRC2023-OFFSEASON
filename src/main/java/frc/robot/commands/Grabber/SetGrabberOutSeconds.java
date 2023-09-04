// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Grabber;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Grabber;

public class SetGrabberOutSeconds extends CommandBase {
  private Grabber grabber;
  private double seconds;
  private Timer timer = new Timer();

  /** Creates a new SetGrabberOutSeconds. */
  public SetGrabberOutSeconds(Grabber grabber, double seconds) {
    this.grabber = grabber;
    this.seconds = seconds;
    addRequirements(grabber);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    grabber.grabberSet(Constants.Speeds.grabberOut);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    grabber.grabberSet(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() >= seconds;
  }
}
