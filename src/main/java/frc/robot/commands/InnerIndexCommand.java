package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.InnerIndex;

public class InnerIndexCommand extends CommandBase {

    private InnerIndex index;

    public InnerIndexCommand() {
        index = RobotContainer.innerIndex;
        addRequirements(index);
    }

    @Override
    public void initialize() {
        index.spin();
    }

    @Override
    public void execute() {
        index.spin();
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("InnerIndexCommand").setBoolean(true);

    }

    @Override
    public void end(boolean interrupted) {
        index.stop();
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("InnerIndexCommand").setBoolean(false);

    }

    @Override
    public boolean isFinished() {
        return NetworkTableInstance.getDefault().getTable("/datatable").getEntry("Killswitch").getBoolean(false);
    }
}
