package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Drive;
import frc.robot.commands.MoveDistance;
import frc.robot.commands.armCommands.ArmDown;
import frc.robot.commands.armCommands.ArmUp;
import frc.robot.commands.armCommands.UpdateArmPosition;
import frc.robot.commands.autonomous.AutoBalance;
import frc.robot.commands.autonomous.LeaveGamePiece;
import frc.robot.commands.autonomous.LeaveGamePieceAndLeaveCommunity;
import frc.robot.commands.endEffectorCommands.EndEffectorHold;
import frc.robot.commands.endEffectorCommands.EndEffectorIn;
import frc.robot.commands.endEffectorCommands.EndEffectorOut;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.EndEffector;
import frc.robot.Constants.Controllers;


public class RobotContainer {

  private final DriveTrain m_DriveTrain = new DriveTrain();
  private final Arm m_brazo = new Arm();
  private final EndEffector m_garra = new EndEffector();

  private final CommandXboxController m_driverController = new CommandXboxController(Controllers.kDriverControllerPort);

  // Driver Controller
  private final Trigger m_DC_aButton = m_driverController.a();
    private final Trigger m_DC_bButton = m_driverController.b();
    private final Trigger m_DC_xButton = m_driverController.x();
    private final Trigger m_DC_yButton = m_driverController.y();

    private final Trigger m_DC_povUp = m_driverController.povUp();
    private final Trigger m_DC_povDown = m_driverController.povDown();
    private final Trigger m_DC_povRight = m_driverController.povRight();
    private final Trigger m_DC_povLeft = m_driverController.povLeft();

    private final Trigger m_DC_rightTrigger = m_driverController.rightTrigger();
    private final Trigger m_DC_leftTrigger = m_driverController.leftTrigger();


  
  public RobotContainer() {
    m_DriveTrain.setDefaultCommand(new Drive(m_DriveTrain, () -> m_driverController.getLeftY(), () -> m_driverController.getRightX()));
    configureBindings();
  }

  
  private void configureBindings() {
    // Driver Controller
    m_DC_povUp.whileTrue(new ArmUp(m_brazo));
    m_DC_povDown.whileTrue(new ArmDown(m_brazo));

    m_DC_aButton.onTrue(new UpdateArmPosition(m_brazo, Constants.ArmPositions.kGround));
    m_DC_bButton.onTrue(new UpdateArmPosition(m_brazo, Constants.ArmPositions.kLow));
    m_DC_xButton.onTrue(new UpdateArmPosition(m_brazo, Constants.ArmPositions.kMiddle));
    m_DC_yButton.onTrue(new UpdateArmPosition(m_brazo, Constants.ArmPositions.kHigh));

    m_DC_rightTrigger.whileTrue(new EndEffectorOut(m_garra));
    m_DC_leftTrigger.toggleOnTrue(new EndEffectorIn(m_garra));
    m_DC_povRight.toggleOnTrue(new EndEffectorHold(m_garra));

    m_DC_povLeft.whileTrue(new RepeatCommand(new AutoBalance(m_DriveTrain)));
  }


  
  public Command getAutonomousCommand() {
    //return new MoveDistance(m_DriveTrain, 400);
    return new LeaveGamePieceAndLeaveCommunity(m_brazo, m_garra, m_DriveTrain);
  }
}