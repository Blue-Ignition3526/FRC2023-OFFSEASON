package frc.robot.subsystems;

// Librerias importadas por usuario /////////////////////////////////////////////////////

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

// Setup ////////////////////////////////////////////////////////////////////////////////

public class Arm extends SubsystemBase {
  public WPI_TalonSRX pulleyLeftLeft = null;
  public WPI_TalonSRX pulleyLeftRight = null;

  public MotorControllerGroup leftMotors = null;

  public WPI_TalonSRX pulleyRightLeft = null;
  public WPI_TalonSRX pulleyRightRight = null;

  public MotorControllerGroup rightMotors = null;

  public MotorControllerGroup allMotors = null;

  public DigitalInput bottomLimitSwitch = null;

  public Encoder m_Encoder = null;

  // Robot Init ///////////////////////////////////////////////////////////////////////////

  public Arm() {
    // Try getting all motor controllers and handle the errors

    try {
      this.pulleyLeftLeft = new WPI_TalonSRX(Constants.Motors.BaymaxMotors.Left.left);
      this.pulleyLeftRight = new WPI_TalonSRX(Constants.Motors.BaymaxMotors.Left.right);

      this.leftMotors = new MotorControllerGroup(pulleyLeftLeft, pulleyLeftRight);
    } catch (Exception err) {
      System.out.println("Error, Left pulley motors disabled: " + err);
    }

    try {
      this.pulleyRightLeft = new WPI_TalonSRX(Constants.Motors.BaymaxMotors.Right.left);
      this.pulleyRightRight = new WPI_TalonSRX(Constants.Motors.BaymaxMotors.Right.right);
  
      this.rightMotors = new MotorControllerGroup(pulleyRightLeft, pulleyRightRight);
    } catch (Exception err) {
      System.out.println("Error, Right pulley motors disabled: " + err);
    }

    try {
      this.allMotors = new MotorControllerGroup(leftMotors, rightMotors);
    } catch (Exception err) {
      System.out.println("Error, All pulley motors disabled: " + err);
    }

    try {
      this.bottomLimitSwitch = new DigitalInput(Constants.baymaxBottomLimitSwitch);
    } catch (Exception err) {
      System.out.println("Error, Bottom limit switch disabled: " + err);
    }

    try {
      this.m_Encoder = new Encoder(Constants.Encoders.armEncoder[0], Constants.Encoders.armEncoder[1], true, Encoder.EncodingType.k4X);
    } catch (Exception err) {
      System.out.println("Error, Arm encoder disabled: " + err);
    }

    // Configure the motors
    leftMotors.setInverted(true);
    pulleyLeftLeft.setNeutralMode(NeutralMode.Brake);
    pulleyLeftRight.setNeutralMode(NeutralMode.Brake);
    pulleyRightLeft.setNeutralMode(NeutralMode.Brake);
    pulleyRightRight.setNeutralMode(NeutralMode.Brake);
  }

  // Robot Periodic ///////////////////////////////////////////////////////////////////////

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Arm Encoder Raw", m_Encoder.get());
    SmartDashboard.putNumber("Arm Rotation", getEncoderAngle());

    try {
      if (!bottomLimitSwitch.get()) m_Encoder.reset();
    } catch (Exception err) {}
  }

  // Subsystem commands //////////////////////////////////////////////////////////////////////

  public void armSet(double speed) {
    try {
      allMotors.set(speed);
    } catch (Exception err) {}
  }

  public double getEncoder() {
    try {
      return m_Encoder.get();
    } catch (Exception err) {
      return 0;
    }
  }

  public double getEncoderAngle() {
    try {
      return m_Encoder.get() * 360 / 2048;
    } catch (Exception err) {
      return 0;
    }
  }

  public void resetEncoder() {
    m_Encoder.reset();
  }
}
