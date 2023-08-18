package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class Drive extends CommandBase {

  private final DriveTrain m_driveTrain;
  private final Supplier<Double> m_forward, m_rotation;

  public Drive(DriveTrain m_driveTrain, Supplier<Double> m_forward, Supplier<Double> m_rotation) {
    this.m_driveTrain = m_driveTrain;
    this.m_forward = m_forward;
    this.m_rotation = m_rotation;
    addRequirements(m_driveTrain);
  }
  

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_driveTrain.arcadeDrive(m_forward.get(), m_rotation.get());
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}