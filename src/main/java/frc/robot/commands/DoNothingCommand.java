package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DoNothingCommand extends CommandBase {
    
    @Override
    public void execute(){
        
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("DoNothingCommand").setBoolean(true);

    }

    @Override
    public void end(boolean interrupted){
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("DoNothingCommand").setBoolean(false);

    }
    
}
