package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Grabber extends SubsystemBase {
    public CANSparkMax m_grabberMotor; // motor con spark

    public Grabber(){
        m_grabberMotor = new CANSparkMax(1, MotorType.kBrushless); // motor *cambiar id a id de motor*
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void garraSet(double speed){
        m_grabberMotor.set(speed);  // controlar neo
    }
}
