package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
  
  // Initialize the motors
  private final WPI_TalonSRX m_pulleyLeftLeft =  new WPI_TalonSRX(Constants.Motors.Arm.kLeftLeftMotor);
    private final WPI_TalonSRX m_pulleyLeftRight =  new WPI_TalonSRX(Constants.Motors.Arm.kLeftRightMotor);
    private final WPI_TalonSRX m_pulleyRightLeft =  new WPI_TalonSRX(Constants.Motors.Arm.kRightLeftMotor);
    private final WPI_TalonSRX m_pulleyRightRight =  new WPI_TalonSRX(Constants.Motors.Arm.kRightRightMotor);

  // Initialize the motor groups
  private final MotorControllerGroup m_pulleyMotors = new MotorControllerGroup(m_pulleyLeftLeft, m_pulleyLeftRight, m_pulleyRightLeft, m_pulleyRightRight);
  
  // Initialize the encoder
  private final DutyCycleEncoder m_armEncoder = new DutyCycleEncoder(Constants.Sensors.kArmEncoder);

  public Arm() {
    // Set all motors to neutral mode (Brake)
    m_pulleyLeftLeft.setNeutralMode(NeutralMode.Brake);
      m_pulleyLeftRight.setNeutralMode(NeutralMode.Brake);
      m_pulleyRightLeft.setNeutralMode(NeutralMode.Brake);
      m_pulleyRightRight.setNeutralMode(NeutralMode.Brake);

    // Invert the right side motors  
    m_pulleyRightLeft.setInverted(true);
      m_pulleyRightRight.setInverted(true);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Arm Position", getArmAngle());
  }

  // Subsystem Functions ////////////////////////////////////////////////////////////////////////////////////////////////

  /**
   * Gets the absolute position of the arm relative to the floor
   * @return The angle in degrees of the arm
   */ 
  public double getArmAngle(){
    return ((m_armEncoder.getAbsolutePosition() * 360)-283)*-1;
  }

  /**
   * Sets the speed of the arm motors
   * @param speed The speed to set the motors to (-1 to 1)
   */
  public void setMotors(double speed){
    m_pulleyMotors.set(speed);
  }
}
