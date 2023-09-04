package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class EndEffector extends SubsystemBase {

    private final CANSparkMax m_grabberMotor = new CANSparkMax(Constants.Motors.EndEffector.kEndEffectorMotorPort, MotorType.kBrushless);

    private final DigitalInput m_pieceSensor = new DigitalInput(Constants.Sensors.kEndEffectorPieceSensor);

    public EndEffector(){
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Piece Sensor", hasPiece());
    }

    /**
     * Sets the speed of the end effector motor
     * @param speed Desired speed
     */
    public void garraSet(double speed) {
        m_grabberMotor.set(speed);
    }

    /**
     * Returns the state of the piece sensor
     * @return True if the sensor is activated
     */
    public boolean hasPiece() {
        return m_pieceSensor.get();
    }
}
