// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class ArmConstants {
    public static final double minAngle = 25;
    public static final double maxAngle = 145;
    public static final double rate = 1;
  }

  public static class Physical {

    //! TODO SACADO DE SWERVE (perdon victor)
    // TODO: cambiar valores, remover no usados
    public static final double kMaxSpeedMetersPerSecond = 5.0; // Maxima Velocidad en Metros por Segundo
    public static final double kMaxAngularSpeedRadiansPerSecond = 2 * 2 * Math.PI; // Maxima Velocidad Angular en Radianes por Segundo

    public static final double kMaxAccelerationUnitsPerSecond = 3; // Maxima Aceleracion
    public static final double kMaxAngularAccelerationUnitsPerSecond = Math.PI / 4; // Maxima Aceleracion Angular

    public static final double kTeleopMaxSpeedMetersPerSecond = 3; // Maxima Velocidad en Metros por Segundo
    public static final double kTeleopMaxAngularSpeedRadiansPerSecond = 3; // Maxima Velocidad Angular en Radianes por Segundo

    public static final double kTeleopMaxAccelerationUnitsPerSecond = 3; // Maxima Aceleracion
    public static final double kTeleopMaxAngularAccelerationUnitsPerSecond = 3; // Maxima Aceleracion Angular

  }
}
