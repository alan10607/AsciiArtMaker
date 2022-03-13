package main;

public class ContainerRGB {	
	private int r;
	private int g;
	private int b;
	private int brightness;
	private char ascii;
	private boolean reverse = false;

	public ContainerRGB(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}


	/**
	 * Change RGB to reverse mode
	 * @param reverse
	 */
	public void setReverse(boolean reverse) {
		if(this.reverse != reverse) {
			this.r = 255 - this.r;
			this.g = 255 - this.g;
			this.b = 255 - this.b;
		}
		this.reverse = reverse;
	}


	/**
	 * Average of RGB;
	 */
	public void countBrightness() {
		brightness = (r + g + b) / 3;
	}


	/**
	 * Create this container's ascii according to brightness, reverse, allAscii
	 * @param allAscii
	 */
	public void createAscii(String allAscii) {
		if(reverse) {
			brightness = 255 - brightness;
		}
		ascii = allAscii.charAt(brightness / 4);
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getBrightness() {
		return brightness;
	}

	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}

	public char getAscii() {
		return ascii;
	}

	public void setAscii(char ascii) {
		this.ascii = ascii;
	}

	public boolean isReverse() {
		return reverse;
	}

}
