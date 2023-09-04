package frc.robot.commands.garraCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.EndEffector;

public class EndEffectorOut extends CommandBase {
    private EndEffector garra;

    public EndEffectorOut(EndEffector garra){
        this.garra = garra;
        addRequirements(garra);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        garra.garraSet(Constants.Robot.kEndEffectorSpeedOut);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        garra.garraSet(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
