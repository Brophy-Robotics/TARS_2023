package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Mecanum;

import static frc.robot.Constants.*;

public class MecanumDrive extends CommandBase {
    
    private final Mecanum mecanum;
    private final DoubleSupplier strafe, forward, turn, slow;

    public MecanumDrive(Mecanum mecanum, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn, DoubleSupplier slow) {
        this.mecanum = mecanum;
        addRequirements(mecanum);

        this.strafe = strafe;
        this.forward = forward;
        this.turn = turn;
        this.slow = slow;
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        double strafe_val = normalize_joystick(strafe.getAsDouble());
        double forward_val = normalize_joystick(forward.getAsDouble());
        double turn_val = normalize_joystick(turn.getAsDouble());
        double slow_val = normalize_joystick(slow.getAsDouble());
        mecanum.drive(
            strafe_val * slow_val,
            forward_val * slow_val,
            turn_val * slow_val
        );
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
