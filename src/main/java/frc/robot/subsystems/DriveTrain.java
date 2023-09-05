package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import frc.robot.Constants;


public class DriveTrain extends SubsystemBase {

  // Initialize the Motors
  private final WPI_TalonSRX m_leftMotor1 = new WPI_TalonSRX(Constants.Motors.DriveTrain.kLeftMotor1Port);
    private final WPI_TalonSRX m_leftMotor2 = new WPI_TalonSRX(Constants.Motors.DriveTrain.kLeftMotor2Port);
    private final WPI_TalonSRX m_rightMotor1 = new WPI_TalonSRX(Constants.Motors.DriveTrain.kRightMotor1Port);
    private final WPI_TalonSRX m_rightMotor2 = new WPI_TalonSRX(Constants.Motors.DriveTrain.kRightMotor2Port);

  // Create motor groups
  private final MotorControllerGroup m_leftMotors = new MotorControllerGroup(m_leftMotor1, m_leftMotor2);
    private final MotorControllerGroup m_rightMotors = new MotorControllerGroup(m_rightMotor1, m_rightMotor2);

  // Initialize the Encoders
  private final Encoder m_leftEncoder = new Encoder(Constants.Sensors.kLeftEncoderPorts[0], Constants.Sensors.kLeftEncoderPorts[1]);
    private final Encoder m_rightEncoder = new Encoder(Constants.Sensors.kRightEncoderPorts[0], Constants.Sensors.kRightEncoderPorts[1]);

  // Initialize the Gyro
  //private final AHRS m_gyro = new AHRS(I2C.Port.kMXP);

  // Kinematics and Odometry Instances
  private final DifferentialDriveKinematics m_kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(Constants.Robot.kWidthInches));
    //private final DifferentialDriveOdometry m_odometry = new DifferentialDriveOdometry(getYawRotation2d(), m_leftEncoder.getDistance(), m_rightEncoder.getDistance());

  public DriveTrain() {
    // Reset NavX
    m_gyro.reset();

    // Invert the left side motors
    m_leftMotors.setInverted(true);

    // Set all motors to neutral mode (Brake)
    m_leftMotor1.setNeutralMode(NeutralMode.Brake);
    m_leftMotor2.setNeutralMode(NeutralMode.Brake);
    m_rightMotor1.setNeutralMode(NeutralMode.Brake);
    m_rightMotor2.setNeutralMode(NeutralMode.Brake);

    // Invert the left side encoder
    m_rightEncoder.setReverseDirection(true);

    // Configure the distance per pulse for the encoders (Measured in Centimeters, 1 Tick = 0.13888889 Centimeters)
    m_rightEncoder.setDistancePerPulse(Constants.Robot.kTicksToMeters);
    m_leftEncoder.setDistancePerPulse(Constants.Robot.kTicksToMeters); 
  }

  @Override
  public void periodic() {
    // Smart Dashboard Data
<<<<<<< HEAD
    SmartDashboard.putNumber("Left Encoder", m_leftEncoder.get());
    SmartDashboard.putNumber("Right Encoder", m_rightEncoder.get());
    //SmartDashboard.putString("Position 2d", m_odometry.toString());
    //SmartDashboard.putNumber("Angle", getYawAngle());
=======
    SmartDashboard.putNumber("Left Encoder", m_leftEncoder.getDistance());
    SmartDashboard.putNumber("Right Encoder", m_rightEncoder.getDistance());
    SmartDashboard.putString("Odometer", m_odometry.toString());
    SmartDashboard.putNumber("Pitch", getPitchAngle());
    SmartDashboard.putNumber("Yaw", getYawAngle());
    SmartDashboard.putNumber("Roll", getRollAngle());
>>>>>>> fd251089f3325af3c8e187399f39a79ff26fdb75

    //Update the odometry in the periodic block

    //m_odometry.update(getYawRotation2d(), m_leftEncoder.getDistance(), m_rightEncoder.getDistance());
  }

  // Subsystem Functions ////////////////////////////////////////////////////////////////////////////////////////////////

  /**
  * Converts a ChassisSpeeds (dx and dtheta components) to left and right
  * wheel velocities.
  * @param speeds The desired ChassisSpeeds.
  * @return The left and right wheel velocities to use.
  */
  public DifferentialDriveWheelSpeeds getWheelSpeeds(ChassisSpeeds speeds) {
    return m_kinematics.toWheelSpeeds(speeds);
  }

  /**
  * Controls the left and right sides of the drive directly with ChassisSpeeds.
  * Drives in an "Arcade Drive" style.
  * @param speeds The desired ChassisSpeeds.
  */
  public void arcadeDrive(ChassisSpeeds speeds) {
    DifferentialDriveWheelSpeeds wheelSpeeds = getWheelSpeeds(speeds);
    m_leftMotors.set(wheelSpeeds.leftMetersPerSecond);
    m_rightMotors.set(wheelSpeeds.rightMetersPerSecond);
  }

  public void setMotors(double speed ) {
    m_leftMotors.set(speed);
    m_rightMotors.set(speed);
  }

  /**
  * Stops the drivetrain from moving. 
  * Sets the left and right motor speeds to 0.
  */ 
  public void stop() {
    m_leftMotors.set(0);
    m_rightMotors.set(0);
  }

  /**
   * Returns the left side encoder value
   * @return The left side encoder measured distance
   */
  public double getLeftEncoder() {
    return m_leftEncoder.getDistance();
  }

  /**
   * Returns the right side encoder value
   * @return The right side encoder measured distance
   */
  public double getRightEncoder() {
    return m_rightEncoder.getDistance();
  }

  /**
   * Returns the average of both drivetrain encoders' value
   * @return The mean of the distance measured by the drivetrain's encoders
   */
  public double getEncodersDistance() {
    return (m_leftEncoder.getDistance()+m_rightEncoder.getDistance())/2;
  }

  /**
  * Converts the angle of the robot to a Rotation2d.
  * @return The Yaw angle of the robot as a Rotation2d.
  */ 
  /* public final Rotation2d getYawRotation2d() {
    return Rotation2d.fromDegrees(getYawAngle());
<<<<<<< HEAD
  }  */
=======
  }

  /**
  * Obtains the Pitch angle of the robot obtained from the NavX.
  * (Pitch is the angle the robot is inclining to, since the gryoscope is mounted facing up, it returns the Roll angle)
  * @return The Roll angle of the robot in degrees.
  */
  public final double getPitchAngle() {
    return m_gyro.getPitch();
  }
>>>>>>> fd251089f3325af3c8e187399f39a79ff26fdb75

  /**
  * Obtains the Yaw angle of the robot obtained from the NavX.
  * (Yaw is the angle the robot is facing, since the gryoscope is mounted facing up, it returns the Roll angle)
  * @return The Roll angle of the robot in degrees.
  */
<<<<<<< HEAD
  /* public final double getYawAngle() {
    return m_gyro.getRoll();
  }  */
=======
  public final double getYawAngle() {
    // a ver que hace
    return m_gyro.getYaw();
  }
  
  /**
  * Obtains the Yaw angle of the robot obtained from the NavX.
  * (Yaw is the angle the robot is facing, since the gryoscope is mounted facing up, it returns the Roll angle)
  * @return The Roll angle of the robot in degrees.
  */
  public final double getRollAngle() {
    // a ver que hace
    return m_gyro.getRoll();
  }
>>>>>>> fd251089f3325af3c8e187399f39a79ff26fdb75
}