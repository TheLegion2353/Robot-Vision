package org.usfirst.frc.team2353.robot.commands;

import org.usfirst.frc.team2353.robot.ArduinoRead;
import org.usfirst.frc.team2353.robot.OI;
import org.usfirst.frc.team2353.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class getArduino extends Command {
	
	boolean isPressed = false;
	ArduinoRead arduino;
	
    public getArduino() {
    	requires(Robot.arduino);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	arduino = new ArduinoRead();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (OI.xboxController.getRawButton(OI.AButtonNum) == true) {
			if (isPressed == false) {
				isPressed = true;

				int yVal = arduino.getPixyYSerial();
				System.out.println("Y: " + yVal);
			}
		}
    	else if (OI.xboxController.getRawButton(OI.BButtonNum) == true) {
			if (isPressed == false) {
				isPressed = true;
				
				int xVal = arduino.getPixyXSerial();
				System.out.println("X-Value: " + xVal);
			}
		}
		else {
			isPressed = false;
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
