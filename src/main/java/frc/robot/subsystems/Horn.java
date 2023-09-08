package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Horn extends SubsystemBase {

    private final Solenoid horn; 
    
    public Horn() {
        horn  = new Solenoid(PneumaticsModuleType.CTREPCM, 4); 
    }

    public void sleep() {
        horn.set(false);
    }

    public void blare() {
        horn.set(true);
    }
}
