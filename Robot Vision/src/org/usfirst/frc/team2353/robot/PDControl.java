package org.usfirst.frc.team2353.robot;

public class PDControl {
	
	public double currentPos, proportionalGain, derivativeGain, previousError;
	
	public final int minPos = 0;
	public final int maxPos = 1;
	public final int centerPos	= ((minPos-maxPos)/2);
	
	public PDControl(double proportionalGain, double derivativeGain) {
		currentPos = centerPos;
		this.proportionalGain = proportionalGain;
		this.derivativeGain = derivativeGain;
		previousError = (int) 0x80000000L;
	}
	
	public void Update(double error) {
		double velocity;
		if (previousError!=0x80000000)
		{	
			velocity = (error*proportionalGain + (error - previousError)*derivativeGain);
			
			currentPos += velocity;
			if (currentPos>maxPos) 
			{
				currentPos = maxPos; 
			}
			else if (currentPos<minPos) 
			{
				currentPos = minPos;
			}
		}
		previousError = error;
	}
}
