package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.InnerIndex;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.OuterIndex;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Hood;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ShootSequenceCommand extends CommandBase {
    private static InnerIndex innerIndex;
    private static OuterIndex outerIndex;
    private static Shooter shooter;
    private static Hood hood;
    private static Limelight limelight;

    public ShootSequenceCommand() {
        innerIndex = RobotContainer.innerIndex;
        outerIndex = RobotContainer.outerIndex;
        shooter = RobotContainer.shooter;
        hood = RobotContainer.hood;
        limelight = RobotContainer.limelight;
        addRequirements(innerIndex, outerIndex, shooter, limelight);

    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        
        innerIndex.spin();
        outerIndex.spin();
        // NetworkTableInstance.getDefault().getTable("/limelight-sam").getEntry("ledMode").setDouble(0);
        // if(limelight.getXDistance() <= 13)
        // {
        //     System.out.println("Short Distance");
        //     shooter.set(3.7);
        //     if(shooter.getRPM() > 1580) {
        //         outerIndex.spin();
        //         innerIndex.spin();
        //     }
        // }
        // else if (limelight.getXDistance() <= 18)
        // {
        //     System.out.println("Medium Distance");
        //     shooter.set(4.1);
        //     if(shooter.getRPM() > 1900) {
        //         outerIndex.spin();
        //         innerIndex.spin();
        //     }
        // }
        // else {
        //     System.out.println("Long Distaqnce");
        //     shooter.set(5.2);
        //     if (shooter.getRPM() > 2200){
        //         outerIndex.spin();
        //         innerIndex.spin();
        //     }
        // }
        // // outerIndex.spin();
        // // innerIndex.spin();
    }

    @Override
    public void end(boolean interrupted) {
        innerIndex.stop();
        outerIndex.stop();
        shooter.set(0);
        //NetworkTableInstance.getDefault().getTable("/limelight-sam").getEntry("ledMode").setDouble(1);
    }

    @Override
    public boolean isFinished() {
        return NetworkTableInstance.getDefault().getTable("/datatable").getEntry("Killswitch").getBoolean(false);
    }

}
