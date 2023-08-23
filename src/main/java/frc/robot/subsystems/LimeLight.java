package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimeLight extends SubsystemBase {
    public NetworkTable m_limeLightTable = NetworkTableInstance.getDefault().getTable("limelight");

    public LimeLight() {}

    private NetworkTableEntry tv = m_limeLightTable.getEntry("tv");  // Whether the limelight has any valid targets (0 or 1)
    private NetworkTableEntry tx = m_limeLightTable.getEntry("tx");  // Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
    private NetworkTableEntry ty = m_limeLightTable.getEntry("ty");  // Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
    private NetworkTableEntry ta = m_limeLightTable.getEntry("ta");  // Target Area (0% of image to 100% of image)
    private NetworkTableEntry ts = m_limeLightTable.getEntry("ts");  // Target Skew

    private NetworkTableEntry tl = m_limeLightTable.getEntry("tl"); // The pipeline’s latency contribution (ms). Add to “cl” to get total latency.
    private NetworkTableEntry cl = m_limeLightTable.getEntry("cl"); // Capture pipeline latency (ms). Time between the end of the exposure of the middle row of the sensor to the beginning of the tracking pipeline.
    private NetworkTableEntry tshort = m_limeLightTable.getEntry("tshort"); // Sidelength of shortest side of the fitted bounding box (pixels)
    private NetworkTableEntry tlong = m_limeLightTable.getEntry("tlong"); // Sidelength of longest side of the fitted bounding box (pixels)
    private NetworkTableEntry thor = m_limeLightTable.getEntry("thor"); // Horizontal sidelength of the rough bounding box (0 - 320 pixels)
    private NetworkTableEntry tvert = m_limeLightTable.getEntry("tvert"); // Vertical sidelength of the rough bounding box (0 - 320 pixels)
    private NetworkTableEntry getpipe = m_limeLightTable.getEntry("getpipe"); // True active pipeline index of the camera (0 .. 9)
    private NetworkTableEntry json = m_limeLightTable.getEntry("json"); // Full JSON dump of targeting results
    private NetworkTableEntry tclass = m_limeLightTable.getEntry("tclass"); // Class ID of primary neural detector result or neural classifier result
    private NetworkTableEntry tc = m_limeLightTable.getEntry("tc"); // Get the average HSV color underneath the crosshair region as a NumberArray
    /**
     * Whether the limelight has any valid targets (0 or 1)
     * @return 0 if there is no target, 1 if there is
     */
    public boolean getValidTarget() {
        return tv.getInteger(0) > 0;
    }

    /**
     * Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
     * @return Target X offset from crosshair in degrees if there is a valid target
     */
    public double getTargetX() {
        return tx.getDouble(0);
    }

    /**
     * Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
     * @return Target Y offset from crosshair in degrees if there is a valid target
     */
    public double getTargetY() {
        return ty.getDouble(0);
    } 

    /**
     * Target Area (0% of image to 100% of image)
     * @return Target area percentage
     */
    public double getTargetArea() {
        return ta.getDouble(0);
    } 

    /**
     * Target skew
     * @return Target skew degrees
     */
    public double getTargetSkew() {
        return ts.getDouble(0);
    } 

    /**
     * The pipeline’s latency contribution (ms). Add to “cl” to get total latency.
     * @return Pipeline latency
     */
    public double getPipelineLatency() {
        return tl.getDouble(0);
    }

    /**
     * Capture pipeline latency (ms). Time between the end of the exposure of the middle row of the sensor to the beginning of the tracking pipeline.
     * @return Capture latency
     */
    public double getCaptureLatency() {
        return cl.getDouble(0);
    } 

    /**
     * Total latency
     * @return pipeline latency + capture latency
     */
    public double getLatency() {
        return getPipelineLatency() + getCaptureLatency();
    } 

    /**
     * Sidelength of shortest side of the fitted bounding box (pixels)
     * @return Short side length
     */
    public double getBoundingBoxShortSide() {
        return tshort.getDouble(0);
    }
 
    /**
     * Sidelength of longest side of the fitted bounding box (pixels)
     * @return Long side length
     */
    public double getBoundingBoxLongSide() {
        return tlong.getDouble(0);
    } 
    
    /**
     * Horizontal sidelength of the rough bounding box (0 - 320 pixels)
     * @return Bounding box width
     */
    public double getBoundingBoxWidth() {
        return thor.getDouble(0);
    } 
    
    /**
     * Vertical sidelength of the rough bounding box (0 - 320 pixels)
     * @return Bounding box height
     */
    public double getBoundingBoxHeight() {
        return tvert.getDouble(0);
    } 

    /**
     * True active pipeline index of the camera (0 .. 9)
     * @return Pipeline index
     */
    public long getPipelineIndex() {
        return getpipe.getInteger(0);
    } 

    /**
     * Full JSON dump of targeting results
     * @return JSON data
     */
    public String getJson() {
        return json.getString("{}");
    } 
    
    /**
     * Class ID of primary neural detector result or neural classifier result
     * @return Class ID
     */
    public String getNeuralDetectorClassId() {
        return tclass.getString("");
    } 

    /**
     * Get the average HSV color underneath the crosshair region as a NumberArray
     * @return HSV color array
     */
    public Number[] getCenterColorHSV() {
        return tc.getNumberArray(null);
    }
    
    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Valid Target", getValidTarget());
        SmartDashboard.putNumber("Target Offset X", getTargetX());
        SmartDashboard.putNumber("Target Offset Y", getTargetY());
        SmartDashboard.putNumber("Target Area", getTargetArea());
        SmartDashboard.putNumber("Limelight Latency", getLatency());
    }
}
