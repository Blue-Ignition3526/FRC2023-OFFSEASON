package frc.robot.commands.endEffectorCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.EndEffector;

public class EndEffectorOutSeconds extends CommandBase {
    private EndEffector endEffector;
    private Timer timer = new Timer();
    private double seconds;

    public EndEffectorOutSeconds(EndEffector endEffector, double seconds) {
        this.seconds = seconds;
        this.endEffector = endEffector;
        addRequirements(endEffector);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        endEffector.endEffectorSet(Constants.Robot.kEndEffectorSpeedOut);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        endEffector.endEffectorSet(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return timer.get() >= this.seconds;
    }
}
