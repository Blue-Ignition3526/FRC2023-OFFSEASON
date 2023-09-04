package frc.robot.commands.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

// Librerias importadas por usuario /////////////////////////////////////////////////////

import java.util.function.Supplier;
import frc.robot.subsystems.DriveTrain;

// Setup ////////////////////////////////////////////////////////////////////////////////

public class Drive extends CommandBase {
  
  private final DriveTrain drivetrain;
  private final Supplier<Double> leftSideSpeed, rightSideSpeed;

  public Drive(DriveTrain drivetrain, Supplier<Double> leftSideSpeed, Supplier<Double> rightSideSpeed) {
    this.leftSideSpeed = leftSideSpeed;
    this.rightSideSpeed = rightSideSpeed;
    this.drivetrain = drivetrain;
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute() {
    double leftSpeed = leftSideSpeed.get();
    double rightSpeed = rightSideSpeed.get();

    drivetrain.drive(leftSpeed, rightSpeed);
  }

  @Override
  public void end(boolean interrupted) {

  }

  @Override
  public boolean isFinished() {
    return false;
  }
}