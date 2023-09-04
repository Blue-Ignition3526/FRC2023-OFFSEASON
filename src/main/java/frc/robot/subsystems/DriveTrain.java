package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

// Librerias importadas por usuario /////////////////////////////////////////////////////

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.NeutralMode;

// Setup ////////////////////////////////////////////////////////////////////////////////

public class DriveTrain extends SubsystemBase {
  
  private final WPI_TalonSRX m_leftMotor1 = new WPI_TalonSRX(Constants.Motors.DriveTrainMotors.Left.left1);
  private final WPI_TalonSRX m_leftMotor2 = new WPI_TalonSRX(Constants.Motors.DriveTrainMotors.Left.left2);

  private final WPI_TalonSRX m_rightMotor1 = new WPI_TalonSRX(Constants.Motors.DriveTrainMotors.Right.right1);
  private final WPI_TalonSRX m_rightMotor2 = new WPI_TalonSRX(Constants.Motors.DriveTrainMotors.Right.right2);

  public final MotorControllerGroup m_leftMotors = new MotorControllerGroup(m_leftMotor1, m_leftMotor2);
  public final MotorControllerGroup m_rightMotors = new MotorControllerGroup(m_rightMotor1, m_rightMotor2);

  public final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  public final Encoder m_leftEncoder = new Encoder(Constants.Encoders.leftEncoder[0], Constants.Encoders.leftEncoder[1], true, Encoder.EncodingType.k4X);
  public final Encoder m_rightEncoder = new Encoder(Constants.Encoders.rightEncoder[0], Constants.Encoders.rightEncoder[1], false, Encoder.EncodingType.k4X);

  public DriveTrain() {
    m_leftMotors.setInverted(true);

    m_leftMotor1.setNeutralMode(NeutralMode.Brake);
    m_leftMotor2.setNeutralMode(NeutralMode.Brake);
    m_rightMotor1.setNeutralMode(NeutralMode.Brake);
    m_rightMotor2.setNeutralMode(NeutralMode.Brake);

    m_leftEncoder.setReverseDirection(true);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Encoder Count Left", m_leftEncoder.get());
    SmartDashboard.putNumber("Encoder Rate Left", m_leftEncoder.getRate());
    SmartDashboard.putBoolean("Is stopped Left", m_leftEncoder.getStopped());
    SmartDashboard.putNumber("Encoder Count Right", m_rightEncoder.get());
    SmartDashboard.putNumber("Encoder Rate Right", m_rightEncoder.getRate());
    SmartDashboard.putBoolean("Is stopped Right", m_rightEncoder.getStopped());
  }

  public void drive(double leftSideSpeed, double rightSideSpeed) {
    m_drive.tankDrive(leftSideSpeed, rightSideSpeed);
  }

  public void arcadeDrive(double x, double z) {
    m_drive.arcadeDrive(x, z);
  }

  public void resetEncoder() {
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }

  public double getEncoderAvg() {
    return (m_leftEncoder.get() + m_rightEncoder.get()) * 0.5;
  }

  public double getCmTicks(double cm) {
    return cm / 45 * 360;
  }

  public double getEncoderDistance() {
    return getEncoderAvg() / 360 * 50;
  }
}