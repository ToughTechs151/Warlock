package org.usfirst.frc.team151.robot;

import org.opencv.core.Mat;

public class GearVision extends BaseVision {

	/**
	 * The actual, real-world width of the gear tape in inches
	 */
	//TODO fix this value
	private static final double WIDTH_OF_OBJECT = 10.0;
	/**
	 * The focal length of the camera
	 */
	private static final double FOCAL_LENGTH = 707.567;
//	private static final double FOCAL_LENGTH = 788.6;
	
	public GearVision(int channel) {
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
		return super.getDistance(WIDTH_OF_OBJECT, FOCAL_LENGTH, this);
	}
	
	@Override
	public double getDistanceFromCenter() {
		return super.getDistanceFromCenter();
	}

}
