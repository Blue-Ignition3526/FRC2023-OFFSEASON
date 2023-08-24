package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
  private final WPI_TalonSRX m_pulleyLeftLeft =  new WPI_TalonSRX(9);
  private final WPI_TalonSRX m_pulleyLeftRight =  new WPI_TalonSRX(8);
  private final WPI_TalonSRX m_pulleyRightLeft =  new WPI_TalonSRX(7);
  private final WPI_TalonSRX m_pulleyRightRight =  new WPI_TalonSRX(6);

  private final MotorControllerGroup m_pulleyLeft = new MotorControllerGroup(m_pulleyLeftLeft, m_pulleyLeftRight);
  private final MotorControllerGroup m_pulleyRight = new MotorControllerGroup(m_pulleyRightLeft, m_pulleyRightRight);
  
  private final MotorControllerGroup m_allMotors = new MotorControllerGroup(m_pulleyLeft, m_pulleyRight);

  private final DutyCycleEncoder m_armEncoder = new DutyCycleEncoder(7); 

  private PIDController pid = new PIDController(0.3, 0, 0);
  private double angle = Constants.ArmConstants.minAngle;

  public Arm() {
    m_pulleyLeft.setInverted(true);

    m_pulleyLeftLeft.setNeutralMode(NeutralMode.Brake);
    m_pulleyLeftRight.setNeutralMode(NeutralMode.Brake);
    m_pulleyRightLeft.setNeutralMode(NeutralMode.Brake);
    m_pulleyRightRight.setNeutralMode(NeutralMode.Brake);

    m_armEncoder.setDistancePerRotation(360);
  }

  @Override
  public void periodic() {
    // Actualizar angulo del brazo en smartdashboard
    SmartDashboard.putNumber("Arm Angle", this.getArmAngle());

    // Siempre actualizar el angulo del brazo al deseado
    this.setMotors(pid.calculate(this.getArmAngle(), this.angle));
  }

  public void setMotors(double speed) {
    m_allMotors.set(speed);
  }

  public double getArmAngle(){
    // Convertir los ticks del encoder al angulo absoluto del brazo
    return m_armEncoder.getAbsolutePosition() * 360;
  }

  public void setArmAngle(double angle) {
    // Actualizar angulo deseado del brazo, con un minimo y un maximo definido en constants
    this.angle = MathUtil.clamp(angle, Constants.ArmConstants.minAngle, Constants.ArmConstants.maxAngle);
  }

  public void changeArmAngle(double delta) {
    this.setArmAngle(this.angle + delta);
  }
}
