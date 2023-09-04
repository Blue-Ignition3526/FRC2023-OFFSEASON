package frc.robot.commands.Arm;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;
import frc.robot.Constants;


public class MoveArmDown extends CommandBase {
  
  private final Arm arm;
  private boolean m_isActive = false;
  
  // Setup ////////////////////////////////////////////////////////////////////////////////
  public MoveArmDown(Arm arm, boolean m_isActive) {
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
      arm.armSet(Constants.Speeds.armDown);
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
