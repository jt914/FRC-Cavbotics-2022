package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.InnerIndex;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.OuterIndex;
import frc.robot.RobotContainer;

public class KickOutBallsCommand extends CommandBase{
    private Intake intake;
    private InnerIndex innerIndex;
    private OuterIndex outerIndex;

  public KickOutBallsCommand() {
    intake = RobotContainer.intake;
    innerIndex = RobotContainer.innerIndex;
    outerIndex = RobotContainer.outerIndex;
    addRequirements(intake, innerIndex, outerIndex);
  }

  @Override
  public void initialize() {
    /*Constants.intakeStatus = true;
    System.out.println("Solenoid current state: " + intake.getValue());
    intake.extend();*/
  }

  @Override
  public void execute() {
    // SmartDashboard.putBoolean("Intake Status", Constants.intakeStatus);

    intake.reverseIntake();
    innerIndex.reverse();
    outerIndex.reverse();
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("KickOutBallsCommand").setBoolean(true);

  }

  @Override
  public void end(boolean interrupted) {
   // Constants.intakeStatus = false;
    intake.stopIntake();
    innerIndex.stop();
    outerIndex.stop();
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("KickOutBallsCommand").setBoolean(false);

  }

  @Override
  public boolean isFinished() {
    return false;
  }
    
}
