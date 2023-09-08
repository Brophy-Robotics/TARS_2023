package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShooterControl extends CommandBase {
    
    private final Shooter shooter;
    private final BooleanSupplier activate, raise, lower;

    public ShooterControl(Shooter shooter, BooleanSupplier activate, BooleanSupplier raise, BooleanSupplier lower) {
        this.shooter = shooter;
        addRequirements(shooter);

        this.activate = activate;
        this.raise = raise;
        this.lower = lower;
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if (activate.getAsBoolean()) {
            shooter.shoot();
        } else {
            shooter.sleep();
        }

        boolean r = raise.getAsBoolean(), l = lower.getAsBoolean();

        if (r && !l) {
            shooter.change_elevation(0.2);
        } else if (l && !r) {
            shooter.change_elevation(-0.2);
        } else {
            shooter.change_elevation(0);
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
