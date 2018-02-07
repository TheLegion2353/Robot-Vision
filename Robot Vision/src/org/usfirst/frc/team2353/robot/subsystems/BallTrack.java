package org.usfirst.frc.team2353.robot.subsystems;

import org.usfirst.frc.team2353.robot.commands.BallTrackMove;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallTrack extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new BallTrackMove());
    }
}

