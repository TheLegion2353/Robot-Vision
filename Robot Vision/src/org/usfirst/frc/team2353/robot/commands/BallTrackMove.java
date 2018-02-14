package org.usfirst.frc.team2353.robot.commands;

import org.usfirst.frc.team2353.robot.ArduinoRead;
import org.usfirst.frc.team2353.robot.PDControl;
import org.usfirst.frc.team2353.robot.Robot;
import org.usfirst.frc.team2353.robot.subsystems.BallTrack;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BallTrackMove extends Command {

	Timer timer;
	ArduinoRead arduino;
	PDControl panControl, tiltControl;

	final int maxPixyRightX = 319, maxPixyLeftX = 0;
	final int maxPixyUpY = 200, maxPixyDownY = 0;

	public BallTrackMove() {
		requires(Robot.ballTrack);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer = new Timer();
		arduino = new ArduinoRead();
		panControl = new PDControl(-0.1, -0.1);
		tiltControl = new PDControl(-0.1, -0.1);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double pixyX = arduino.getPixyXSerial();
		//double pixyY = arduino.getPixyYSerial();
		
		if (pixyX > 0 /*|| pixyY > 0*/) {
			double xError = 160 - pixyX;
			
			if(Math.abs(xError) < 30) {
				xError = 0;
			}
			
			xError = (double) (xError + maxPixyLeftX) / maxPixyRightX;
			
			panControl.Update(xError);
			
			if(panControl.currentPos != 0 && panControl.currentPos != 1) {
				BallTrack.LeftRight.set(panControl.currentPos);
			}
			
			/*
			double yError = 100 - pixyY;
			
			if(Math.abs(yError) < 25) {
				yError = 0;
			}
			
			yError = (double) (yError + maxPixyDownY) / maxPixyUpY;
			
			tiltControl.Update(yError);
			
			if(tiltControl.currentPos != 0 && tiltControl.currentPos != 1) {
				BallTrack.UpDown.set(tiltControl.currentPos);
			}
			*/
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
