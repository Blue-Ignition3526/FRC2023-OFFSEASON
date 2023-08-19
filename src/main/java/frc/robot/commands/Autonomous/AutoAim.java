package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.garraCommands.garraOut;
import frc.robot.subsystems.Brazo;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Garra;
import frc.robot.subsystems.LimeLight;

public class AutoAim extends CommandBase {
    private LimeLight m_limeLight;
    private DriveTrain m_DriveTrain;
    private Brazo m_Brazo;
    private Garra m_garra;
    private garraOut m_out = new garraOut(m_garra, false);

    public AutoAim(LimeLight m_limeLight, DriveTrain m_DriveTrain, Brazo m_Brazo, Garra m_garra) {
        this.m_limeLight = m_limeLight;
        this.m_DriveTrain = m_DriveTrain;
        this.m_Brazo = m_Brazo;
        this.m_garra = m_garra;

        addRequirements(m_limeLight, m_DriveTrain, m_Brazo, m_garra);
    }

    @Override
    public void initialize() {
        m_out = new garraOut(m_garra, false);
    }

    @Override
    public void execute() {
        boolean targetVisible = m_limeLight.getValidTarget();
        double targetX = m_limeLight.getTargetX();
        double targetY = m_limeLight.getTargetY();

        if (targetVisible == true) {
            if (Math.abs(targetX) < 0.5) {
                m_Brazo.setArmAngle(targetY * 20 + 5, 1);
                if (m_Brazo.getArmAngle() > targetY * 20 - 6 && m_Brazo.getArmAngle() < targetY * 20 + 6) {
                    m_out = new garraOut(m_garra, true);
                }
            } else if (targetX > 5) {
                m_DriveTrain.arcadeDrive(0, 0.3);
            } else {
                m_DriveTrain.arcadeDrive(0, -0.3);
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_out.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
