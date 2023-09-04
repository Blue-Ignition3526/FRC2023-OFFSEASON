package frc.robot.commands.LED;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LED;

public class HandleLEDInput extends CommandBase {

  private final LED ledSubsystem;
  private Supplier<Double> leftJoystick, rightJoystick;

  public HandleLEDInput(LED ledSubsystem, Supplier<Double> leftJoystick, Supplier<Double> rightJoystick) {
    this.ledSubsystem = ledSubsystem;
    this.leftJoystick = leftJoystick;
    this.rightJoystick = rightJoystick;
    addRequirements(ledSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    ledSubsystem.setRGB((50/255)*Math.abs((leftJoystick.get()+rightJoystick.get())/2), 0, (255/255)*Math.abs((leftJoystick.get()+rightJoystick.get())/2));
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
