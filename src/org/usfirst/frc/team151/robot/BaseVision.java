package org.usfirst.frc.team151.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.*;
import org.opencv.imgproc.*;

public class BaseVision {

	protected double currentRectWidthPix;
	protected double currentRectHeightPix;

	UsbCamera usbCamera = null;

	//Outputs
	private Mat source = new Mat();
	private Mat hsvThresholdOutput = new Mat();
	private Mat blurOutput = new Mat();
	private ArrayList<MatOfPoint> findContoursOutput = new ArrayList<MatOfPoint>();
	private ArrayList<MatOfPoint> filterContoursOutput = new ArrayList<MatOfPoint>();
	private Point smallestPoint = new Point();
	private Point largestPoint = new Point();
	private Point centerPoint = new Point();

	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}


	public BaseVision(int channel) {
		usbCamera = CameraServer.getInstance().startAutomaticCapture(channel);
	}

	public CvSink getVideoFrame() {
		return CameraServer.getInstance().getVideo();
	}
	/*
	 * A method like this called startVision will be in each class and start the vision 
   for each separate class, and should be distinct for each class
	 */
	public void startVision(Mat source0) {
		//TODO INSERT CODE FOR EACH SEPARATE VISION CLASS
		this.source = source0;
		// Step HSV_Threshold0:
		Mat hsvThresholdInput = source0;
		double[] hsvThresholdHue = {0.0, 46.38225255972697};
		double[] hsvThresholdSaturation = {0.0, 6.9624573378839765};
		double[] hsvThresholdValue = {252.24820143884895, 255.0};
		hsvThreshold(hsvThresholdInput, hsvThresholdHue, hsvThresholdSaturation, hsvThresholdValue, hsvThresholdOutput);

		// Step Blur0:
		Mat blurInput = hsvThresholdOutput;
		BlurType blurType = BlurType.get("Box Blur");
		double blurRadius = 1.8018018018018018;
		blur(blurInput, blurType, blurRadius, blurOutput);

		// Step Find_Contours0:
		Mat findContoursInput = blurOutput;
		boolean findContoursExternalOnly = false;
		findContours(findContoursInput, findContoursExternalOnly, findContoursOutput);

		// Step Filter_Contours0:
		ArrayList<MatOfPoint> filterContoursContours = findContoursOutput;
		double filterContoursMinArea = 250.0;
		double filterContoursMinPerimeter = 0.0;
		double filterContoursMinWidth = 0.0;
		double filterContoursMaxWidth = 1000.0;
		double filterContoursMinHeight = 0.0;
		double filterContoursMaxHeight = 1000.0;
		double[] filterContoursSolidity = {51.2589928057554, 100};
		double filterContoursMaxVertices = 1000000.0;
		double filterContoursMinVertices = 7.0;
		double filterContoursMinRatio = 0.0;
		double filterContoursMaxRatio = 1000.0;
		filterContours(filterContoursContours, filterContoursMinArea, filterContoursMinPerimeter, filterContoursMinWidth, filterContoursMaxWidth, filterContoursMinHeight, filterContoursMaxHeight, filterContoursSolidity, filterContoursMaxVertices, filterContoursMinVertices, filterContoursMinRatio, filterContoursMaxRatio, filterContoursOutput);

		// Step Bounding_Rectangle:
		Mat boundingRectInput = source0;
		ArrayList<MatOfPoint> boundingRectInput2 = filterContoursOutput;
		boundingRect(boundingRectInput, filterContoursOutput);

		//Step center circle:
		Mat drawCenterOfRectInput = source0;
		drawCenterOfRect(drawCenterOfRectInput);

		//			return source0;
	}

	/**
	 * Segment an image based on hue, saturation, and value ranges.
	 *
	 * @param input The image on which to perform the HSL threshold.
	 * @param hue The min and max hue
	 * @param sat The min and max saturation
	 * @param val The min and max value
	 * @param output The image in which to store the output.
	 */
	private void hsvThreshold(Mat input, double[] hue, double[] sat, double[] val, Mat out) {
		Imgproc.cvtColor(input, out, Imgproc.COLOR_BGR2HSV);
		Core.inRange(out, new Scalar(hue[0], sat[0], val[0]),
				new Scalar(hue[1], sat[1], val[1]), out);
	}

	/**
	 * An indication of which type of filter to use for a blur.
	 * Choices are BOX, GAUSSIAN, MEDIAN, and BILATERAL
	 */
	enum BlurType{
		BOX("Box Blur"), GAUSSIAN("Gaussian Blur"), MEDIAN("Median Filter"),
		BILATERAL("Bilateral Filter");

		private final String label;

		BlurType(String label) {
			this.label = label;
		}

		public static BlurType get(String type) {
			if (BILATERAL.label.equals(type)) {
				return BILATERAL;
			}
			else if (GAUSSIAN.label.equals(type)) {
				return GAUSSIAN;
			}
			else if (MEDIAN.label.equals(type)) {
				return MEDIAN;
			}
			else {
				return BOX;
			}
		}

		@Override
		public String toString() {
			return this.label;
		}
	}

	/**
	 * Softens an image using one of several filters.
	 * @param input The image on which to perform the blur.
	 * @param type The blurType to perform.
	 * @param doubleRadius The radius for the blur.
	 * @param output The image in which to store the output.
	 */
	private void blur(Mat input, BlurType type, double doubleRadius, Mat output) {
		int radius = (int)(doubleRadius + 0.5);
		int kernelSize;
		switch(type){
		case BOX:
			kernelSize = 2 * radius + 1;
			Imgproc.blur(input, output, new Size(kernelSize, kernelSize));
			break;
		case GAUSSIAN:
			kernelSize = 6 * radius + 1;
			Imgproc.GaussianBlur(input,output, new Size(kernelSize, kernelSize), radius);
			break;
		case MEDIAN:
			kernelSize = 2 * radius + 1;
			Imgproc.medianBlur(input, output, kernelSize);
			break;
		case BILATERAL:
			Imgproc.bilateralFilter(input, output, -1, radius, radius);
			break;
		}
	}

	/**
	 * Sets the values of pixels in a binary image to their distance to the nearest black pixel.
	 * @param input The image on which to perform the Distance Transform.
	 * @param type The Transform.
	 * @param maskSize the size of the mask.
	 * @param output The image in which to store the output.
	 */
	private void findContours(Mat input, boolean externalOnly, List<MatOfPoint> contours) {
		Mat hierarchy = new Mat();
		contours.clear();
		int mode;
		if (externalOnly) {
			mode = Imgproc.RETR_EXTERNAL;
		}
		else {
			mode = Imgproc.RETR_LIST;
		}
		int method = Imgproc.CHAIN_APPROX_SIMPLE;
		Imgproc.findContours(input, contours, hierarchy, mode, method);
	}

	/**
	 * Filters out contours that do not meet certain criteria.
	 * @param inputContours is the input list of contours
	 * @param output is the the output list of contours
	 * @param minArea is the minimum area of a contour that will be kept
	 * @param minPerimeter is the minimum perimeter of a contour that will be kept
	 * @param minWidth minimum width of a contour
	 * @param maxWidth maximum width
	 * @param minHeight minimum height
	 * @param maxHeight maximimum height
	 * @param Solidity the minimum and maximum solidity of a contour
	 * @param minVertexCount minimum vertex Count of the contours
	 * @param maxVertexCount maximum vertex Count
	 * @param minRatio minimum ratio of width to height
	 * @param maxRatio maximum ratio of width to height
	 */
	private void filterContours(List<MatOfPoint> inputContours, double minArea,
			double minPerimeter, double minWidth, double maxWidth, double minHeight, double
			maxHeight, double[] solidity, double maxVertexCount, double minVertexCount, double
			minRatio, double maxRatio, List<MatOfPoint> output) {
		final MatOfInt hull = new MatOfInt();
		output.clear();
		//operation
		for (int i = 0; i < inputContours.size(); i++) {
			final MatOfPoint contour = inputContours.get(i);
			final Rect bb = Imgproc.boundingRect(contour);
			if (bb.width < minWidth || bb.width > maxWidth) continue;
			if (bb.height < minHeight || bb.height > maxHeight) continue;
			final double area = Imgproc.contourArea(contour);
			if (area < minArea) continue;
			if (Imgproc.arcLength(new MatOfPoint2f(contour.toArray()), true) < minPerimeter) continue;
			Imgproc.convexHull(contour, hull);
			MatOfPoint mopHull = new MatOfPoint();
			mopHull.create((int) hull.size().height, 1, CvType.CV_32SC2);
			for (int j = 0; j < hull.size().height; j++) {
				int index = (int)hull.get(j, 0)[0];
				double[] point = new double[] { contour.get(index, 0)[0], contour.get(index, 0)[1]};
				mopHull.put(j, 0, point);
			}
			final double solid = 100 * area / Imgproc.contourArea(mopHull);
			if (solid < solidity[0] || solid > solidity[1]) continue;
			if (contour.rows() < minVertexCount || contour.rows() > maxVertexCount)	continue;
			final double ratio = bb.width / (double)bb.height;
			if (ratio < minRatio || ratio > maxRatio) continue;
			output.add(contour);
		}
	}

	private void boundingRect(Mat source, List<MatOfPoint> contours) {
		double upperLeftX = 10000;
		double upperLeftY = 10000;
		double lowerRightX = 0;
		double lowerRightY = 0;
		for(MatOfPoint current : contours) {
			Rect rect = Imgproc.boundingRect(current);
			Imgproc.rectangle(source, new Point(rect.x, rect.y), 
					new Point(rect.x+rect.width, rect.y+rect.height), new Scalar(0, 255, 0, 1));
			upperLeftX = (rect.x < upperLeftX) ? rect.x : upperLeftX;
			upperLeftY = (rect.y < upperLeftY) ? rect.y : upperLeftY;
			lowerRightX = (rect.x+rect.width > lowerRightX) ? rect.x+rect.width : lowerRightX;
			lowerRightY = (rect.y+rect.height > lowerRightY) ? rect.y+rect.height : lowerRightY;
		}
		currentRectWidthPix = lowerRightX - upperLeftX;
		currentRectHeightPix = lowerRightY - upperLeftY;
		smallestPoint = new Point(upperLeftX, upperLeftY);
		largestPoint = new Point(lowerRightX, lowerRightY);
		Imgproc.rectangle(source, smallestPoint, largestPoint, new Scalar(255, 0, 0, 1));
	}

	private void drawCenterOfRect(Mat source) {
		double x = smallestPoint.x + (largestPoint.x-smallestPoint.x)/2;
		double y = smallestPoint.y + (largestPoint.y-smallestPoint.y)/2;
		centerPoint = new Point(x, y);
		Imgproc.circle(source, centerPoint, 5, new Scalar(0, 0, 255, 1));
	}

	public double getDistance(double length, double focalLength, BaseVision vis) {
		if(vis instanceof GearVision) {
			return length*focalLength/currentRectHeightPix;
		} else if(vis instanceof BoilerVision) {
			return length*focalLength/currentRectWidthPix;
		}
		return -1;
	}

	public double getDistanceFromCenter() {
		return centerPoint.x - source.cols()/2;
	}

}
