// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ClimbLe;
import edu.wpi.first.wpilibj2.command.Command;


/** An example command that uses an example subsystem. */
public class armsDownL extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ClimbLe m_Climb;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public armsDownL(ClimbLe ClimbLe) {
    m_Climb = ClimbLe;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(ClimbLe);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Climb.clawsDownL();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
     m_Climb.StopL();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
