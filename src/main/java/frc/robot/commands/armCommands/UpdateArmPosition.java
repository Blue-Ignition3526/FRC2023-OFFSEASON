package frc.robot.commands.armCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;

public class UpdateArmPosition extends CommandBase {

  private final Arm m_Brazo;
  private final double angle;
  private double direction;
  private double difference;
  private boolean isUp;

  public UpdateArmPosition(Arm m_Brazo, double angle) {
    this.m_Brazo = m_Brazo;
    this.angle = angle;
    this.difference = angle - m_Brazo.getArmAngle();
    this.direction = (difference > 0) ? Constants.Robot.kArmUpSpeed : Constants.Robot.kArmDownSpeed;
    addRequirements(m_Brazo);
  }

  @Override
  public void initialize() {
    this.isUp = (difference > 0.0);
    if (isUp) {
      this.direction = Constants.Robot.kArmUpSpeed;
    } else if (!isUp) {
      this.direction = Constants.Robot.kArmDownSpeed;
    }
  }

  @Override
  public void execute() {
    m_Brazo.setMotors(direction);
    difference = angle - m_Brazo.getArmAngle();
    SmartDashboard.putBoolean("IsUp", isUp);
    SmartDashboard.putNumber("Direction", direction);
    SmartDashboard.putNumber("Difference", difference);
  }

  @Override
  public void end(boolean interrupted) {
    m_Brazo.setMotors(0);
  }

  @Override
  public boolean isFinished() {
    return Math.abs(difference) < Constants.Robot.kArmTolerance;
  }
}
