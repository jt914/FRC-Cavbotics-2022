package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    private CANSparkMax intake;
    private Compressor compressor;
    private DoubleSolenoid mainSolenoid;
    private PneumaticHub hub;

    public Intake() {
        hub = new PneumaticHub(50);
        hub.enableCompressorDigital();
        intake = new CANSparkMax(Constants.intakeId, MotorType.kBrushless);
        intake.enableVoltageCompensation(12);
        mainSolenoid = hub.makeDoubleSolenoid(14, 15);//orig:14,15
        // mainSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH,
        // Constants.mainSolenoidOne, Constants.mainSolenoidTwo);
        // compressor = new Compressor(PneumaticsModuleType.REVPH);
        // compressor.enableDigital();
    }

    public void spinIntake() {
        intake.set(-0.57);
    }

    public void reverseIntake() {
        intake.set(0.40);
    }

    public void extend() {
        mainSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void retract() {
        mainSolenoid.set(DoubleSolenoid.Value.kForward);

    }

    public void stopIntake() {
        intake.setVoltage(0);
    }

    public DoubleSolenoid.Value getValue() {
        return mainSolenoid.get();
    }

}
