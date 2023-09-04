package frc.robot;

import edu.wpi.first.wpilibj.util.Color;

public final class Constants {
  public static final double autonomousSpeed = 0.3;

  public static final int driverControllerPort = 0;
  public static final int operatorControllerPort = 1;

  public static final double balanceLowerThreshold = -3;
  public static final double balanceUpperThreshold = 3;

  public static final double balanceAdjustmentSpeed = 0.15;

  public static final int baymaxBottomLimitSwitch = 6;
  public static final int canifierId = 20;

  public static final double limeLightHeight = 187.5;
  public static final double armLength = 57;

  public static final Color coneColor = new Color(0.37254901960784315, 0.5254901960784314, 0.10588235294117647);
  public static final Color cubeColor = new Color(0.19215686274509805, 0.29411764705882354, 0.5058823529411764);
  
  public static final class Encoders {
    public static final int[] leftEncoder = new int[]{0, 1};
    public static final int[] rightEncoder = new int[]{2, 3};
    public static final int[] armEncoder = new int[]{7, 8};
  }

  public static final class Speeds {
    public static final double grabberIn = -0.65;
    public static final double grabberOut = 0.7;
    public static final double grabberCube = 0.8;

    public static final double armUp = 0.4;
    public static final double armDown = -0.4;
  }

  public final class Motors {

    // Drive Train Motors /////////////////////////////////////////////////////////////////

    public final class DriveTrainMotors {
      public final class Left {
        public static final int left1 = 4;
        public static final int left2 = 3;
      }

      public final class Right {
        public static final int right1 = 2;
        public static final int right2 = 1;
      }
    }

    // Arm Motors /////////////////////////////////////////////////////////////////////

    public final class BaymaxMotors {
      public final class Left {
        public static final int left = 8;
        public static final int right = 7;
      }

      public final class Right {
        public static final int left = 6;
        public static final int right = 5;
      }

      public final class Grabber {
        public static final int grabber = 9;
      }
    }
  }
}