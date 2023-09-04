package frc.robot.subsystems;

// Librerias importadas por usuario /////////////////////////////////////////////////////

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.CANifier.LEDChannel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

// Setup ////////////////////////////////////////////////////////////////////////////////

public class LED extends SubsystemBase {
  public CANifier canifier = null;

  public LEDChannel red = LEDChannel.LEDChannelB;
  public LEDChannel green = LEDChannel.LEDChannelA;
  public LEDChannel blue = LEDChannel.LEDChannelC;

  private double r = 0;
  private double g = 0;
  private double b = 0;

// Robot Init ///////////////////////////////////////////////////////////////////////////

  public LED() {
    try {
      this.canifier = new CANifier(Constants.canifierId);
    } catch (Exception err) {
      System.out.println("Error, Canifier disabled: " + err);
    }
  }

// Robot Periodic ///////////////////////////////////////////////////////////////////////

  @Override
  public void periodic() {
    canifier.setLEDOutput(r, red);
    canifier.setLEDOutput(g, green);
    canifier.setLEDOutput(b, blue);
  }

// Subsystem commands ///////////////////////////////////////////////////////////////////

  public void setRGB(double r, double g, double b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }
}