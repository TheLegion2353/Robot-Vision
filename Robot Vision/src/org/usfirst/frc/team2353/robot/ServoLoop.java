package org.usfirst.frc.team2353.robot;

public class ServoLoop {

	public double m_pos, m_proportionalGain, m_derivativeGain, m_prevError;
	
	public final int RCS_MIN_POS = 0;
	public final int RCS_MAX_POS = 1;
	public final int RCS_CENTER_POS	= ((RCS_MAX_POS-RCS_MIN_POS)/2);
	
	public ServoLoop(double proportionalGain, double derivativeGain) {
		m_pos = RCS_CENTER_POS;
		m_proportionalGain = proportionalGain;
		m_derivativeGain = derivativeGain;
		m_prevError = (int) 0x80000000L;
	}
	
	public void Update(double error) {
		double velocity;
		if (m_prevError!=0x80000000)
		{	
			velocity = (error*m_proportionalGain + (error - m_prevError)*m_derivativeGain);
			
			m_pos += velocity;
			if (m_pos>RCS_MAX_POS) 
			{
				m_pos = RCS_MAX_POS; 
			}
			else if (m_pos<RCS_MIN_POS) 
			{
				m_pos = RCS_MIN_POS;
			}
		}
		m_prevError = error;
	}
}
