package frc.robot.commands.armCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Brazo;

public class armUp extends CommandBase {

  private final Brazo m_brazo;

  public armUp(Brazo m_brazo) {
    this.m_brazo = m_brazo;
    addRequirements(m_brazo);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_brazo.setMotors(0.4);
  }

  @Override
  public void end(boolean interrupted) {
    m_brazo.setMotors(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}