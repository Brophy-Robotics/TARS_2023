// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;

/**
  * This class is where the bulk of the robot should be declared. Since Command-based is a
  * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
  * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
  * subsystems, commands, and button mappings) should be declared here.
*/
public class RobotContainer {

    /* Controllers */
    private final GenericHID main = new GenericHID(0);

    /* Subsystems */
    private final Mecanum mecanum = new Mecanum();
    private final Shooter shooter = new Shooter();
    private final Horn horn = new Horn();

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        // Configure the button bindings

        mecanum.setDefaultCommand(new MecanumDrive( // Standard Mecanum Drive on L + R Joysticks
            mecanum, 
            () -> main.getRawAxis(0), 
            () -> -main.getRawAxis(1), 
            () -> main.getRawAxis(4), 
            () -> 1 // always fast for now :)
        ));

        shooter.setDefaultCommand(new ShooterControl( // Left Trigger + Right Trigger
            shooter, 
            () -> main.getRawAxis(2) > 0.5 && main.getRawAxis(3) > 0.5, 
            () -> main.getPOV() == 0, 
            () -> main.getPOV() == 180
        ));

        horn.setDefaultCommand(new HornControl( // A + Left Bumper + Right Bumper
            horn, 
            () -> main.getRawButton(0) && main.getRawButton(4) && main.getRawAxis(5) > 0.5
        ));

        configureButtonBindings();
    }

    /**
       * Use this method to define your button->command mappings. Buttons can be created by
       * instantiating a {@link GenericHID} or one of its subclasses ({@link
       * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
       * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
    */
    private void configureButtonBindings() {} // no commands yet lol

    public Command getAutonomousCommand() {
        return null; // no command lol
    }
}
