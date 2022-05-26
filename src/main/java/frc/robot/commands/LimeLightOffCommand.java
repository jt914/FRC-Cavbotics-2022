package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class LimeLightOffCommand extends CommandBase {
    private static Limelight limelight;
    private static Shooter shooter;

    public LimeLightOffCommand() {
        limelight = RobotContainer.limelight;
        addRequirements(limelight);

        this.shooter = RobotContainer.shooter;
    }

    @Override
    public void execute() {
        NetworkTableInstance.getDefault().getTable("/limelight-sam").getEntry("ledMode").setDouble(1);
        // shooter.set(0);
    }

    @Override
    public void end(boolean interrupted) {
        //NetworkTableInstance.getDefault().getTable("/limelight-sam").getEntry("ledMode").setDouble(1);
       
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
