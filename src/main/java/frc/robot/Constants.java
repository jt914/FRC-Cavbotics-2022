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

        /* - - - SWERVE DRIVE CONSTANTS - - - */
    public static final int frontLeftDriveID = 4;
    public static final int frontRightDriveID = 2;
    public static final int backLeftDriveID = 6;
    public static final int backRightDriveID = 8;

    public static final int frontLeftTurnID = 3;
    public static final int frontRightTurnID = 1;
    public static final int backLeftTurnID = 5;
    public static final int backRightTurnID = 7;

    public static final int frontLeftEncoderPort = 1;
    public static final int frontRightEncoderPort = 0;
    public static final int backLeftEncoderPort = 2;
    public static final int backRightEncoderPort = 3;

    public static final double driveEncoderVelocityConversion = (1/5.3333) * (0.106 * Math.PI) * (1/60);
    public static final double turnEncoderPositionConversion = (1/360) * (1/11.6571);

    public static final double drivetrainModuleOffset = 0.2923; /* Assuming the robot is square, the X & Y offset from the center of rotation to each module */
    public static final int numberOfModules = 4;
    public static final double maxVelocityMultiplier = 6; /* Max velocity in m/s */
    public static final double radiansPerSecondMultiplier = 6; /* Max angular rate in radians/second */

    /* - - - OTHER CONSTANTS - - - */
    public static final int swerveControllerPort = 0;
    public static final int alternateControllerPort = 1;

    /* - - - FLYWHEEL CONSTANTS - - - */
    public static final int leftFlywheelID = 4;
    public static final int rightFlywheelID = 5;
    public static final double RPMtoFlywheelTipSpeed = (6 * Math.PI) / 2362; /* Multiply by the RPM to get the tip speed of a 6in flywheel */

    /* - - - HOOD CONSTANTS - - - */
    public static final int hoodMotorID = 11;
    public static final double motorToHoodConversion = 360 * (16/32) * (10/203);
    public static final double distanceToHoodAngle = 1/3; /* Temporary number for testing, real "equation" will use data points and a line of best fit */

    /* - - - INDEXER CONSTANTS - - - */
    public static final int outerIndexerID = 15;
    public static final int innerIndexerID = 12;
    public static final double outerIndexerSpeed = 0; /* Max outer indexer speed from 0 to 1 */
    public static final double innerIndexerSpeed = 0; /* Max inner indexer speed from 0 to 1 */
    
    /* - - - INTAKE CONSTANTS - - - */
    public static final int intakeMotorID = 16;
    public static final int pneumaticsHubID = 0;
    public static final int solenoidOne = 14;
    public static final int solenoidTwo = 15;
    public static final double intakeSpeed = 0.4; /* Max intake speed from 0 to 1 */

    /* - - - CLIMBER CONSTANTS - - - */
    public static final int rightClimberID = 9;
    public static final int leftClimberID = 13;
    public static final double climberSpeed = 0.4; /* Max climber speed from 0 to 1 */

    /* - - - LIMELIGHT CONSTANTS - - - */
    public static final double limelightHeight = 0.5 /*meters*/;
    public static final double hubHeight = 2.64 /*meters*/;
    public static final double limelightAngle = 25 /*degrees*/;
}
