package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.InnerIndex;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.OuterIndex;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SwerveDrive;
import frc.robot.subsystems.Limelight;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // Hood
    public static final int hoodId = 11;
    // Flywheel
    public static final int flyWheelIdOne = 4;
    public static final int flyWheelIdTwo = 5;

    // Index IDs
    public static final int innerIndexId = 12;
    public static final int outerIndexId = 15;
    
    // Intake
    public static final int intakeId = 16;

    // Solenoid
    public static final int mainSolenoidOne = 14;
    public static final int mainSolenoidTwo = 15;

    // Climber
    public static final int leftArmID = 13;
    public static final int rightArmID = 9;

    // Shooter
    public static final int shooterIdOne = 14;
    public static final int shooterIdTwo = 10;
    public static final double bigShooterVoltage = 5;
    public static final double smallShooterVoltage = 5;
    public static final double bigRPM = 2000;
    public static final double smallRPM = 2300;


    // Swerve modules
    public static final int frontRightTurn = 1;
    public static final int frontRightDrive = 2;
    public static final int frontLeftTurn = 3;
    public static final int frontLeftDrive = 4;
    public static final int backLeftTurn = 5;
    public static final int backLeftDrive = 6;
    public static final int backRightTurn = 7;
    public static final int backRightDrive = 8;

    public static final int frontRightEncoder = 0;
    public static final int frontLeftEncoder = 1;
    public static final int backLeftEncoder = 2;
    public static final int backRightEncoder = 3;

    public static final double desiredRPM = 0.5;

    // Smartdashboard states
    public static boolean intakeStatus = false;
    public static boolean outerIndexStatus = false;
    public static boolean innerIndexStatus = false;
    public static boolean shooterStarted = false;
    public static boolean shooterDelay = false;
    public static double startTime = 0;

    
}
