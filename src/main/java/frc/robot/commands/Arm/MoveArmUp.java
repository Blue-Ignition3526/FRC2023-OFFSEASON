package frc.robot.commands.Arm;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;


public class MoveArmUp extends CommandBase {
  
  private final Arm arm;
  private boolean m_isActive = false;
  
  // Setup ////////////////////////////////////////////////////////////////////////////////
  public MoveArmUp(Arm arm, boolean m_isActive) {
    this.m_isActive = m_isActive;
    this.arm = arm;
    addRequirements(arm);
  }

  // Init /////////////////////////////////////////////////////////////////////////////////

  @Override
  public void initialize() {}

  // Periodic /////////////////////////////////////////////////////////////////////////////

  @Override
  public void execute() {
    if (m_isActive) {
      arm.armSet(Constants.Speeds.armUp);
    } else {
      arm.armSet(0);
    }
  }

  ///////////////////////////////////////////////////////////////////////////////////////// 

  @Override
  public void end(boolean interrupted) {
    arm.armSet(0);
  }

  @Override
  public boolean isFinished() {
    return !m_isActive;
  }
}
