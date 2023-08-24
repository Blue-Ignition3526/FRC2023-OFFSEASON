package frc.robot.commands.grabber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Grabber;

public class GrabberOut extends CommandBase {
    private Grabber garra;

    public GrabberOut(Grabber garra, boolean active){
        this.garra = garra;

        addRequirements(garra);
    }

    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        garra.garraSet(0.7);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
