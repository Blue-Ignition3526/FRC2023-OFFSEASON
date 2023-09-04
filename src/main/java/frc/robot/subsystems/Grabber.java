package frc.robot.subsystems;

// Librerias importadas por usuario /////////////////////////////////////////////////////

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

// Setup ////////////////////////////////////////////////////////////////////////////////

public class Grabber extends SubsystemBase {

  public CANSparkMax grabber;
  
  // Robot Init ///////////////////////////////////////////////////////////////////////////

  public Grabber() {
    try {
      this.grabber = new CANSparkMax(Constants.Motors.BaymaxMotors.Grabber.grabber, MotorType.kBrushless);
    } catch (Exception err) {
      System.out.println("Error, Grabber motor disabled: " + err);
    }
  }

  // Robot Periodic ///////////////////////////////////////////////////////////////////////

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Subsystem commands ///////////////////////////////////////////////////////////////////

  public void grabberSet(double speed) {
    try {
      grabber.set(speed);
    } catch (Exception err) {}
  }
}