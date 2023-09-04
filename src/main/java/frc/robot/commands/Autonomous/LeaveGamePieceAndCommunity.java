package frc.robot.commands.Autonomous;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Enum.DriveDirection;
import frc.robot.commands.Arm.MoveArmAngle;
import frc.robot.commands.Grabber.SetGrabberOut;
import frc.robot.commands.Grabber.SetGrabberOutSeconds;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Grabber;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class LeaveGamePieceAndCommunity extends SequentialCommandGroup {
  /** Creates a new LeaveGamePieceAndCommunity. */
  public LeaveGamePieceAndCommunity(DriveTrain driveTrain, Arm arm, Grabber grabber) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new MoveArmAngle(arm, 75),
      new MoveArmAngle(arm, -50),
      new LeaveCommunity(driveTrain, DriveDirection.FORWARD)
    );
  }
}