package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Climber;

public class RetractClimberCommand extends CommandBase{

    private Climber climber;

    public RetractClimberCommand(){
        climber = RobotContainer.climber;
    }

    @Override
    public void execute(){
        climber.retract();
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("RetractClimberCode").setBoolean(true);

    }

    @Override
    public void end(boolean interrupted){
        climber.stop();
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("RetractClimberCode").setBoolean(false);

    }

    @Override
    public boolean isFinished(){
        return false;
    }
    
}
