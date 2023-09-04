// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NavX extends SubsystemBase {
  public AHRS m_accelerometer = null;

  /** Creates a new NavX. */
  public NavX() {
    try {
      this.m_accelerometer = new AHRS(Port.kMXP);
    } catch (Exception err) {
      System.out.println("Error, NavX disabled: " + err);
    }

    m_accelerometer.reset();
  }

  public final double getBalance() {
    return m_accelerometer.getRoll();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Pitch", m_accelerometer.getPitch());
    SmartDashboard.putNumber("Yaw", m_accelerometer.getYaw());
    SmartDashboard.putNumber("Roll / Balance", getBalance());
  }
}
