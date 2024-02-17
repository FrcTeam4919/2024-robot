// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj.Joystick;
//import commands
import frc.robot.commands.armsDownL;
import frc.robot.commands.armsDownR;
import frc.robot.commands.armsUpL;
import frc.robot.commands.armsUpR;
import frc.robot.commands.lowIn;
import frc.robot.commands.lowOut;

import frc.robot.commands.shootSlow;

//import subsystems
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Pickup;
import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }
  // subsystems
    private final Climb m_Climb = new Climb();
    private final Pickup m_Pickup = new Pickup();
    private final Shooter m_Shooter = new Shooter();

  // Joysticks
    private final Joystick buttonBoard = new Joystick(1);
    private final Joystick StickOfHope = new Joystick(0);

  //Commands
    private final armsDownL m_ArmsDownL = new armsDownL(m_Climb);
    private final armsDownR m_ArmsDownR = new armsDownR(m_Climb);
    private final armsUpL m_ArmsUpL = new armsUpL(m_Climb);
    private final armsUpR m_ArmsUpR = new armsUpR(m_Climb);
    private final shootSlow m_ShootSlow = new shootSlow(m_Shooter);
    
    
    
    private final lowIn m_LowIn = new lowIn(m_Pickup);
    private final lowOut m_LowOut = new lowOut(m_Pickup);
   
   


    //Button setup
    private JoystickButton armsDownL = new JoystickButton(buttonBoard, 7);
    private JoystickButton armsDownR = new JoystickButton(buttonBoard, 15);
    private JoystickButton armsUpL = new JoystickButton(buttonBoard, 8);
    private JoystickButton armsUpR = new JoystickButton(buttonBoard, 6);
    private JoystickButton shootSlow = new JoystickButton(buttonBoard, 5);
    private JoystickButton ShooterPistonOn = new JoystickButton(buttonBoard, 4);
    
    private JoystickButton lowIn = new JoystickButton(buttonBoard, 2);
    private JoystickButton lowOut = new JoystickButton(buttonBoard, 3);
    private JoystickButton PickupPistonOn = new JoystickButton(buttonBoard, 1);
   

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    armsDownL.whileTrue(m_ArmsDownL);
    armsDownR.whileTrue(m_ArmsDownR);
    armsUpL.whileTrue(m_ArmsUpL);
    armsUpR.whileTrue(m_ArmsUpR);
    shootSlow.whileTrue(m_ShootSlow);
    
    ShooterPistonOn.toggleOnTrue(new StartEndCommand( m_Shooter::dump , m_Shooter::undump, m_Shooter));
    lowIn.whileTrue(m_LowIn);
    lowOut.whileTrue(m_LowOut);
    PickupPistonOn.toggleOnTrue(new StartEndCommand(m_Pickup::drop, m_Pickup::undrop, m_Pickup));
    

    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
