package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    private final Solenoid shoot, load; 
    
    private final TalonSRX shooter; // no clue if we need this

    public Shooter() {
        shoot = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
        load  = new Solenoid(PneumaticsModuleType.CTREPCM, 2);
    
        shooter = new TalonSRX(0);
    }

    public void sleep() {
        shoot.set(false);
        load.set(true);
    }

    public void shoot() {
        shoot.set(true);
        load.set(false);
    }

    public void change_elevation(double value) {
        shooter.set(ControlMode.PercentOutput, value);
    }
}
