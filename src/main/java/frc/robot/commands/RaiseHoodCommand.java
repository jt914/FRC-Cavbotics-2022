package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Hood;

public class RaiseHoodCommand extends CommandBase {

    private Hood hood;

    public RaiseHoodCommand() {
        hood = RobotContainer.hood;
        addRequirements(hood);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        hood.manualMove(0.12);
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("RaiseHoodCommand").setBoolean(true);

    }

    @Override
    public void end(boolean interrupted) {
        hood.manualMove(0);
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("RaiseHoodCommand").setBoolean(false);

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}