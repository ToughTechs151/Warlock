package org.usfirst.frc.team151.robot;

import org.opencv.core.Mat;

public class BoilerVision extends BaseVision {

	/**
	 * In inches
	 */
	private static final double HEIGHT_OF_OBJECT = 10.0;
	private static final double FOCAL_LENGTH = 707.567;
//	private static final double FOCAL_LENGTH = 788.6;
	/**
	 * The width of the current bounding rectangle in pixels.
	 */
	
	public BoilerVision(int channel) {
		super(channel);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void startVision (Mat source) {
		super.startVision(source);
	}
	
	
	public double getDistance() {
		return super.getDistance(HEIGHT_OF_OBJECT, FOCAL_LENGTH, this);
	}
	
	public double getDistanceFromCenter() {
		return super.getDistanceFromCenter();
	}

}
