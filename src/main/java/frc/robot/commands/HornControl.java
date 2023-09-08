package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Horn;

public class HornControl extends CommandBase {
    
    private final Horn horn;
    private final BooleanSupplier activate;

    public HornControl(Horn horn, BooleanSupplier activate) {
        this.horn = horn;
        addRequirements(horn);

        this.activate = activate;
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if (activate.getAsBoolean()) {
            horn.blare();
        } else {
            horn.sleep();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
