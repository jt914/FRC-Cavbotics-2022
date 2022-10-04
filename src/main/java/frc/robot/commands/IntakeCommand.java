package frc.robot.commands;

import frc.robot.subsystems.Intake;
import frc.robot.subsystems.OuterIndex;

import com.fasterxml.jackson.databind.util.ClassUtil.Ctor;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class IntakeCommand extends CommandBase {
  private Intake intake;
  private OuterIndex outer;

  public IntakeCommand() {
    intake = RobotContainer.intake;
    outer = RobotContainer.outerIndex;
  }

  @Override
  public void initialize() {
    /*Constants.intakeStatus = true;
    System.out.println("Solenoid current state: " + intake.getValue());
    intake.extend();*/
    intake.extend();
  }

  @Override
  public void execute() {
    // SmartDashboard.putBoolean("Intake Status", Constants.intakeStatus);
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("IntakeCommand").setBoolean(true);


    // intake.spinIntake();
    // outer.spin();
  }

  @Override
  public void end(boolean interrupted) {
   // Constants.intakeStatus = false;
    intake.stopIntake();
    outer.stop();
    intake.retract();
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("IntakeCommand").setBoolean(false);

  }

  @Override
  public boolean isFinished() {
    return NetworkTableInstance.getDefault().getTable("/datatable").getEntry("Killswitch").getBoolean(false);
  }
}
