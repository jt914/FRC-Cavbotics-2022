package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import frc.robot.Constants;

public class Hood extends SubsystemBase {
    private CANSparkMax hood; // left motor from the back
    private RelativeEncoder encoder;
    private SparkMaxPIDController pid;
    private double conversion;

    // The rotations of the motor per one length of the hood multiplied by the
    // native units of the encoder
    private final double hoodLength = 2.5;
    private final double hoodRange = 35;

    public Hood() {
        hood = new CANSparkMax(Constants.hoodId, MotorType.kBrushless);
        encoder = hood.getEncoder();
        pid = hood.getPIDController();
        pid.setOutputRange(-0.17, 0.17);
        pid.setP(0.2);
        conversion = hoodRange / hoodLength;
        encoder.setPosition(0);
    }

    public double getHoodAngle() {
        return encoder.getPosition() * conversion + 10;
    }

    // debug helper
    public double getRawUnits() {
        return encoder.getPosition();
    }

    // sets the hood to a desired angle
    public void setHoodAngle(double angle) {
        pid.setReference((angle - 10) / conversion, com.revrobotics.CANSparkMax.ControlType.kPosition);
    }

    public void adjustAngle(double distanceFromTarget)
    {
        double angle = 2.5 * distanceFromTarget + 10;
        setHoodAngle(angle);
    }

    public void hoodReset() {
        encoder.setPosition(0);
    }

    public void manualMove(double p){
        hood.set(p);
    }
}