// In src/main/java/frc/robot/RobotContainer.java
package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.DriveSubsystem;

/**
 * This class is where the bulk of the robot should be declared.
 */
public class RobotContainer {
    
    // The robot's subsystems
    private final DriveSubsystem m_robotDrive = new DriveSubsystem();

    // The driver's controller
    private final XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        // Configure the button bindings
        configureBindings();
        
        // Set the default drive command to run continuously
        m_robotDrive.setDefaultCommand(
            // A RunCommand calls the given function every tick while the command is scheduled
            new RunCommand(
                () -> m_robotDrive.driveMecanum(
                    // Y (Forward/Backward): Left stick Y, *inverted* (as per your note: Y is reversed)
                    -m_driverController.getLeftY(),
                    // X (Strafe): Left stick X
                    m_driverController.getLeftX(),
                    // RX (Rotation): Right stick X
                    m_driverController.getRightX()),
                m_robotDrive)
        );
    }

    /**
     * Use this method to define your button->command mappings.
     */
    private void configureBindings() {
        // Define any other button/joystick to command mappings here.
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // Return your autonomous command here
        return null;
    }
}