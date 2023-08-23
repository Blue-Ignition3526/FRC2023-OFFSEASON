package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;

// Librerias a Importar

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

public class DriveTrain extends SubsystemBase {
  // Declarar los Motores
  private final WPI_TalonSRX m_leftMotor1 = new WPI_TalonSRX(4);
  private final WPI_TalonSRX m_leftMotor2 = new WPI_TalonSRX(3);
  private final WPI_TalonSRX m_rightMotor1 = new WPI_TalonSRX(2);
  private final WPI_TalonSRX m_rightMotor2 = new WPI_TalonSRX(1);

  // Declarar los Grupos de Motores
  private final MotorControllerGroup m_leftMotors = new MotorControllerGroup(m_leftMotor1, m_leftMotor2);
  private final MotorControllerGroup m_rightMotors = new MotorControllerGroup(m_rightMotor1, m_rightMotor2);

  // Declarar el DriveTrain Diferencial
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  // Declarar los Sensores
  private final Encoder m_leftEncoder = new Encoder(0, 1);
  private final Encoder m_rightEncoder = new Encoder(2, 3);
  private final AHRS m_navx = new AHRS(Port.kMXP);

  private final DifferentialDriveKinematics m_ddKinematics = new DifferentialDriveKinematics(Units.inchesToMeters(26));
  private final DifferentialDriveOdometry m_ddOdometry = new DifferentialDriveOdometry(getYawRotation2d(), m_leftEncoder.getDistance(), m_rightEncoder.getDistance());

  public DriveTrain() {
    // Invertir los Motores del Lado Izquierdo
    m_leftMotors.setInverted(true);

    // Configurar los modos neutrales de los Motores
    m_leftMotor1.setNeutralMode(NeutralMode.Brake);
    m_leftMotor2.setNeutralMode(NeutralMode.Brake);
    m_rightMotor1.setNeutralMode(NeutralMode.Brake);
    m_rightMotor2.setNeutralMode(NeutralMode.Brake);

    // Invertir el encoder del lado izquierdo
    m_leftEncoder.setReverseDirection(true);

    // Resetear NavX
    m_navx.reset();
  }

  @Override
  public void periodic() {
    // Codigo que repite
    SmartDashboard.putNumber("Left Encoder", m_leftEncoder.get());
    SmartDashboard.putNumber("Right Encoder", m_rightEncoder.get());
    SmartDashboard.putNumber("Right Distance", getEncoderDistance());

    m_ddOdometry.update(getYawRotation2d(), m_leftEncoder.getDistance(), m_rightEncoder.getDistance());
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds(ChassisSpeeds speeds){
    return m_ddKinematics.toWheelSpeeds(speeds);
  }

  public void arcadeDrive(ChassisSpeeds speeds) {
    DifferentialDriveWheelSpeeds wheelSpeeds = getWheelSpeeds(speeds);
    m_leftMotors.set(wheelSpeeds.leftMetersPerSecond);
    m_rightMotors.set(wheelSpeeds.rightMetersPerSecond);
  }

  public double getEncoderDistance() {
    return m_rightEncoder.getDistance() / 360 * 50;
  }

  public final double getYaw(){
    return m_navx.getYaw();
  }
  public final Rotation2d getYawRotation2d(){
    return Rotation2d.fromDegrees(getYaw());
  }
}