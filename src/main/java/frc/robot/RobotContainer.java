// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.simulation.XboxControllerSim;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;
import frc.robot.commands.*;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  public static Intake intake;
  public static Shooter shooter;
  public static InnerIndex innerIndex;
  public static OuterIndex outerIndex;
  public static Climber climber;
  public static Hood hood;
  public static Limelight limelight;
  public static SwerveDrive swerveDrive;
  public static XboxController controller;
  public static XboxController swerveController;
  public static SwerveCommand swerveCommand;
  public static int status;
  // The robot's subsystems and commands are defined here...


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    status = 0;
    controller = new XboxController(0);
    swerveController = new XboxController(1);
    intake = new Intake();
    shooter = new Shooter();
    innerIndex = new InnerIndex();
    outerIndex = new OuterIndex();
    climber = new Climber();
    hood = new Hood();
    limelight = new Limelight();
    swerveDrive = new SwerveDrive(0.2923);
    intake.retract();
    hood.hoodReset();
    
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
// // Y button shoot
//new JoystickButton(controller, XboxController.Button.kY.value).toggleWhenPressed(new ShootSequenceCommand());
// X button intake
//new JoystickButton(controller, XboxController.Button.kX.value).toggleWhenPressed(new InnerIndexCommand());
// //Right stick hood set
new JoystickButton(controller, XboxController.Button.kB.value).whenPressed(new AutoAimCommand());
//b button intake
new JoystickButton(swerveController, XboxController.Button.kB.value).toggleWhenPressed(new IntakeCommand());
 //Back button kick out ball
new JoystickButton(swerveController, XboxController.Button.kY.value).whileHeld(new KickOutBallsCommand());
// // A button outer intake 
//new JoystickButton(controller, XboxController.Button.kA.value).toggleWhenPressed(new OuterIndexCommand());
//new JoystickButton(controller, XboxController.Button.kRightkRightStick).toggleWhenPressed(new OuterIndexCommand());
// //Left bumper Extend Climber
new JoystickButton(controller, XboxController.Button.kRightBumper.value).whenHeld(new ExtendClimberCommand());
new JoystickButton(controller, XboxController.Button.kLeftBumper.value).whenHeld(new RetractClimberCommand());
new JoystickButton(controller, XboxController.Button.kA.value).toggleWhenPressed(new LimeLightToggleCommand());
new JoystickButton(controller, XboxController.Button.kX.value).toggleWhenPressed(new LimeLightOffCommand());


//left bumper decline hood
// new JoystickButton(controller, 3).whenActive(new DeclineHoodCommand());
// //right bumper raise hood
// new JoystickButton (controller, 2).whenActive(new RaiseHoodCommand());

//start button, start swerve
new JoystickButton(swerveController, XboxController.Button.kStart.value).toggleWhenPressed(new SwerveCommand());


  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    double routine = NetworkTableInstance.getDefault().getTable("/datatable").getEntry("routine").getDouble(1);

    if(routine == 0){
      return new Auto1(limelight, hood, innerIndex, intake, outerIndex, shooter, swerveDrive);
    }
    if(routine == 1){ 
      return new Auto2(limelight, hood, innerIndex, intake, outerIndex, shooter, swerveDrive);
    }
    System.out.println("Do nothing");
      return new DoNothingCommand();
  }
}
