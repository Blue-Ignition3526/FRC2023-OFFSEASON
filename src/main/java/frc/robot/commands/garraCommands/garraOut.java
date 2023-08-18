package frc.robot.commands.garraCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Garra;

public class garraOut extends CommandBase {
    private Garra garra;

    public garraOut(Garra garra, boolean active){
        this.garra = garra;

        addRequirements(garra);
    }

    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        garra.garraSet(1);
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
