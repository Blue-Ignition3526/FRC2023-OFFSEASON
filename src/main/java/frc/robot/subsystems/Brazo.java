package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Brazo extends SubsystemBase {
  
  private final WPI_TalonSRX m_pulleyLeftLeft =  new WPI_TalonSRX(8);
  private final WPI_TalonSRX m_pulleyLeftRight =  new WPI_TalonSRX(7);
  private final WPI_TalonSRX m_pulleyRightLeft =  new WPI_TalonSRX(6);
  private final WPI_TalonSRX m_pulleyRightRight =  new WPI_TalonSRX(5);

  private final MotorControllerGroup m_pulleyLeft = new MotorControllerGroup(m_pulleyLeftLeft, m_pulleyLeftRight);
  private final MotorControllerGroup m_pulleyRight = new MotorControllerGroup(m_pulleyRightLeft, m_pulleyRightRight);
  
  private final MotorControllerGroup m_allMotors = new MotorControllerGroup(m_pulleyLeft, m_pulleyRight);

  private final Encoder m_armEncoder = new Encoder(7, 8);


  public Brazo() {
    m_pulleyLeft.setInverted(true);

    m_pulleyLeftLeft.setNeutralMode(NeutralMode.Brake);
    m_pulleyLeftRight.setNeutralMode(NeutralMode.Brake);
    m_pulleyRightLeft.setNeutralMode(NeutralMode.Brake);
    m_pulleyRightRight.setNeutralMode(NeutralMode.Brake);

  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Encoder Ticks", m_armEncoder.get());
  }

  public void setMotors(double speed){
    m_allMotors.set(speed);
  }

  public double getArmAngle(){
    return m_armEncoder.get() * 360 / 2048;
  }

  public void setArmAngle(double angle, double tolerance){

    while((getArmAngle() > angle - tolerance) && (getArmAngle() < angle + tolerance)){ // revisar si es angulo con tolerancia
      if(getArmAngle() > angle){ // decidir lado
        setMotors(-0.5); // cambiar numero
      }else{
        setMotors(0.5); // cambiar numero
      }
    }
    setMotors(0);
  }
}
