package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.InnerIndex;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.OuterIndex;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Hood;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class ShootCommand extends CommandBase {
    private static Shooter shooter;
    private static Limelight limelight;

    public ShootCommand() {
        shooter = RobotContainer.shooter;
        limelight = RobotContainer.limelight;
        addRequirements(shooter, limelight);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("ShootCommand").setBoolean(true);
        //NetworkTableInstance.getDefault().getTable("/limelight-sam").getEntry("ledMode").setDouble(0);
        SmartDashboard.putBoolean("shoot", true);
        if (limelight.getXDistance() <= 6)
            shooter.set(4.5);
            

        else if (limelight.getXDistance() <= 12)
            shooter.set(4.8);

        else
            shooter.set(5);
            

    }

    @Override
    public void end(boolean interrupted) {
        shooter.set(0);
        //NetworkTableInstance.getDefault().getTable("/limelight-sam").getEntry("ledMode").setDouble(1);
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("ShootCommand").setBoolean(false);

    }

    @Override
    public boolean isFinished() {
        return NetworkTableInstance.getDefault().getTable("/datatable").getEntry("Killswitch").getBoolean(false);
    }
}
