package frc.robot;


public final class Constants {
  public static class Controllers {
    public static final int kDriverControllerPort = 0;
  }

  /** 
   * The port numbers of the motors on the robot.
   */
  public static class Motors {
    public static class DriveTrain {
      public static final int kLeftMotor1Port = 2;
      public static final int kLeftMotor2Port = 3;
      public static final int kRightMotor1Port = 4;
      public static final int kRightMotor2Port = 5;
    }

    public static class Arm {
      public static final int kRightRightMotor = 6;
      public static final int kRightLeftMotor = 7;
      public static final int kLeftRightMotor = 8;
      public static final int kLeftLeftMotor = 9;
    }

    public static class EndEffector {
      public static final int kEndEffectorMotorPort = 10;
    }
  }
  public static class Sensors {
    public static final int[] kLeftEncoderPorts = {0, 1};
    public static final int[] kRightEncoderPorts = {2, 3};

    public static final int kArmEncoder = 4;

    public static final int kEndEffectorPieceSensor = 5;
  }

  public static class Robot {
    public static final double kMaxAccelerationMetersPerSecond = 2.5;
    public static final double kMaxAngularSpeedRadiansPerSecond = 2.5;
    public static final double kWidthInches = 27;
    public static final double kTicksToMeters = 0.13888889;
    public static final double kArmUpSpeed = 0.4;
    public static final double kArmDownSpeed = -0.4;
    public static final double kArmTolerance = 1;
    public static final double kEndEffectorSpeedIn = -0.3;
    public static final double kEndEffectorSpeedOut = 0.5;
    public static final double kEndEffectorSpeedHold = -0.05;
  }

  public static class ArmPositions {
    public static final double kGround = 33;
    public static final double kLow = 50;
    public static final double kMiddle = 90;
    public static final double kHigh = 115;
  }
}
