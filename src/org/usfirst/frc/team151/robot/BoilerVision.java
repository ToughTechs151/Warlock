package org.usfirst.frc.team151.robot;

import org.opencv.core.Mat;

public class BoilerVision extends BaseVision {

	/**
	 * The actual, real-world height of the boiler in inches
	 */
	private static final double HEIGHT_OF_OBJECT = 10.0;
	/**
	 * The focal length of the camera
	 */
	private static final double FOCAL_LENGTH = 707.567;
//	private static final double FOCAL_LENGTH = 788.6;
	
	public BoilerVision(int channel) {
		super(channel);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void startVision() {
		super.startVision();
	}
	
	@Override
	public void stopVision() {
		super.stopVision();
	}
	
	public double getDistance() {
		return super.getBoilerDistance(HEIGHT_OF_OBJECT, FOCAL_LENGTH);
	}
	
	@Override
	public double getDistanceFromCenter() {
		return super.getDistanceFromCenter();
	}

}
