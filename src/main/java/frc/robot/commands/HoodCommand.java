package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Hood;

public class HoodCommand extends CommandBase {
    
    private Hood hood;
    private Limelight limelight;

    public HoodCommand() {
        hood = RobotContainer.hood;
        limelight = RobotContainer.limelight;
        addRequirements(hood, limelight);
    }

    @Override
    public void initialize() {
        //NetworkTableInstance.getDefault().getTable("/limelight-sam").getEntry("ledMode").setDouble(0);
    }

    @Override
    public void execute() {
        System.out.println("Hood activated");
        //NetworkTableInstance.getDefault().getTable("/limelight-sam").getEntry("ledMode").setDouble(0);
        double mode = NetworkTableInstance.getDefault().getTable("/datatable").getEntry("shooterMode").getDouble(0);
        if(mode == 0){
            if (limelight.hasTarget() == 1){
                hood.adjustAngle(limelight.getXDistance());
            // } else{
            //     hood.adjustAngle(30);
            }
        }
        if(mode == 1){
            hood.setHoodAngle(30);
        }
        // hood.adjustAngle(limelight.getXDistance());
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("HoodCommand").setBoolean(true);
    }

    @Override
    public void end(boolean interrupted) {
        // if (interrupted)
        // hood.hoodReset();
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("HoodCommand").setBoolean(false);
        //NetworkTableInstance.getDefault().getTable("/limelight-sam").getEntry("ledMode").setDouble(1);

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
