package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Climber;

public class ExtendClimberCommand extends CommandBase{

    private Climber climber;

    public ExtendClimberCommand(){
        climber = RobotContainer.climber;
    }

    @Override
    public void execute(){
        climber.extend();
        
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("ExtendClimberCommand").setBoolean(true);
     
    }

    @Override
    public void end(boolean interrupted){
        climber.stop();
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("ExtendClimberCommand").setBoolean(false);

    }

    @Override
    public boolean isFinished(){
        return false;
    }
    
}
