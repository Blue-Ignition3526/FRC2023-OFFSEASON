package frc.robot.commands.armCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;

public class ArmDown extends CommandBase {
  
  private final Arm m_Brazo;

  public ArmDown(Arm m_Brazo) {
    this.m_Brazo = m_Brazo;
    addRequirements(m_Brazo);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (m_Brazo.getArmAngle() >= 18) {
      m_Brazo.setMotors(Constants.Robot.kArmDownSpeed);
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_Brazo.setMotors(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
