package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Limelight extends SubsystemBase {
    private NetworkTable table;
    private NetworkTableEntry pipeline;

    public Limelight() {
        table = NetworkTableInstance.getDefault().getTable("limelight-sam");
        pipeline = table.getEntry("pipeline");
    }

    public double getXOffset() {
        return table.getEntry("tx").getDouble(0);
    }

    public double getYOffset() {
        return table.getEntry("ty").getDouble(0);
    }

    public int hasTarget() {
        return (int) table.getEntry("tv").getDouble(0);
    }

    public double getXDistance() {
        double realAngle = 22 + getYOffset();
        return 6.7 / Math.tan(Math.toRadians(realAngle));
    }

    public double getXCrosshairOffset() {
        double xOffset = table.getEntry("tx").getDouble(0);
        if (xOffset > 26) {
            System.out.println("Limelight near max angle");
        } else if (xOffset == 0) {
            System.out.println("Limelight past max angle");
        }
        return xOffset;
    }

    public double getYCrosshairOffset() {
        double yCrosshairOffset = table.getEntry("ty").getDouble(0);
        SmartDashboard.putNumber("Limelight Y Crosshair Offset", yCrosshairOffset);
        return yCrosshairOffset;
    }



    public double getTargetDistance() {
        double targetDistance = (Constants.hubHeight - Constants.limelightHeight)
                / Math.tan(Math.toRadians(Constants.limelightAngle + getYCrosshairOffset()));
        SmartDashboard.putNumber("Limelight Target Distance", targetDistance);
        return targetDistance;
    }

}