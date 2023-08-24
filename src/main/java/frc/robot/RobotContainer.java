package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Drive;
import frc.robot.commands.arm.ChangeArmAngle;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.grabber.GrabberHold;
import frc.robot.commands.grabber.GrabberIn;
import frc.robot.commands.grabber.GrabberOut;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Grabber;


public class RobotContainer {

  private final DriveTrain m_DriveTrain = new DriveTrain();
  private final Arm m_arm = new Arm();
  private final Grabber m_garra = new Grabber();

  private final CommandXboxController m_driverController = new CommandXboxController(0);

  private final Trigger m_DC_aButton = m_driverController.a();
  private final Trigger m_DC_bButton = m_driverController.b();

  public RobotContainer() {
    m_DriveTrain.setDefaultCommand(new Drive(m_DriveTrain, () -> m_driverController.getLeftY(), () -> m_driverController.getRightX()));
    configureBindings();
  }

  
  private void configureBindings() {
    //m_DC_aButton.onTrue(new frc.robot.commands.armCommands.armUp(m_arm));
    //m_DC_bButton.onTrue(new frc.robot.commands.armCommands.armDown(m_arm));

    // TODO: extract command to another file
    m_DC_aButton.whileTrue(new ChangeArmAngle(m_arm, 5));
    m_DC_bButton.whileTrue(new ChangeArmAngle(m_arm, -5));

    m_driverController.leftTrigger(0.85).toggleOnTrue(new GrabberIn(m_garra));

    m_driverController.rightTrigger(0.85).onTrue(new GrabberOut(m_garra, true));
    m_driverController.rightTrigger(0.85).onFalse(new GrabberOut(m_garra, false));

    m_driverController.y().toggleOnTrue(new GrabberHold(m_garra));
  }

  
  public Command getAutonomousCommand() {
    return null;
  }
}