package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Drive;
import frc.robot.commands.garraCommands.garraHold;
import frc.robot.commands.garraCommands.garraIn;
import frc.robot.commands.garraCommands.garraOut;
import frc.robot.subsystems.Brazo;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Garra;


public class RobotContainer {

  private final DriveTrain m_DriveTrain = new DriveTrain();
  private final Brazo m_brazo = new Brazo();
  private final Garra m_garra = new Garra();

  private final CommandXboxController m_driverController = new CommandXboxController(0);

  private final Trigger m_DC_aButton = m_driverController.a();
  private final Trigger m_DC_bButton = m_driverController.b();


  public RobotContainer() {
    m_DriveTrain.setDefaultCommand(new Drive(m_DriveTrain, () -> m_driverController.getLeftY(), () -> m_driverController.getRightX()));
    configureBindings();
  }

  
  private void configureBindings() {
    m_DC_aButton.onTrue(new frc.robot.commands.armCommands.armUp(m_brazo));
    m_DC_bButton.onTrue(new frc.robot.commands.armCommands.armDown(m_brazo));

    m_driverController.leftTrigger(0.85).toggleOnTrue(new garraIn(m_garra));

    m_driverController.rightTrigger(0.85).onTrue(new garraOut(m_garra, true));
    m_driverController.rightTrigger(0.85).onFalse(new garraOut(m_garra, false));

    m_driverController.y().toggleOnTrue(new garraHold(m_garra));
  }

  
  public Command getAutonomousCommand() {
    return null;
  }
}