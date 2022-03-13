package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

public class ImageLoader {
	private final static String ALL_ASCII = "$@B%8&MW#*oahkbdpqwmZ0OLCJUYXzcvunxrjft/\\(|)1{}][?-_+~i!Il:;,\"^`";
	private BufferedImage img;
	private ContainerRGB[][] colorArr;
	private String path;
	private int w;
	private int h;
	private int maxWidth = 100;
	private float scale = 1f;
	private boolean reverseFlag = false;
	private SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public ImageLoader(String path, int maxWidth, boolean reverseFlag) throws Exception {
		try {
			this.path = path;
			this.maxWidth = maxWidth;
			this.reverseFlag = reverseFlag;

			this.img = ImageIO.read(new File(path));
			countImageSize();
			fillArray();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Load file failed:" + e.getMessage());
		}
	}


	/**
	 * Create image array
	 */
	private void fillArray() {
		colorArr = new ContainerRGB[h][w];
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				//0xff=255 
				int rgb = img.getRGB((int)(j*this.scale), (int)(i*this.scale));
				int r = (rgb>>16) & 0xff;
				int g = (rgb>>8) & 0xff;
				int b = (rgb) & 0xff;
				colorArr[i][j] = new ContainerRGB(r, g, b);
				colorArr[i][j].setReverse(reverseFlag);
				colorArr[i][j].countBrightness();
				colorArr[i][j].createAscii(ALL_ASCII);
			}
		}
	}


	/**
	 * Calculate scaling if image is too big
	 */
	private void countImageSize() {
		if(img.getWidth() > maxWidth || img.getHeight() > maxWidth) {
			BigDecimal max = new BigDecimal((Math.max(img.getWidth(), img.getHeight())));
			scale = max.divide(new BigDecimal(maxWidth), 2, RoundingMode.CEILING).floatValue();
		}
		w = Math.round(img.getWidth() / scale);
		h = Math.round(img.getHeight() / scale);
	}


	/**
	 * Create file by FileWriter
	 * @param path
	 * @param data
	 * @throws IOException
	 */
	public void writeFile(String path, String data) throws IOException {
		FileWriter fw = null;
		try {
			fw = new FileWriter(new File(path));
			fw.write(data);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(fw != null){
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw e;
				}
			}
		}
	}


	/**
	 * Build txt data from arrays
	 * @return
	 */
	public String buildTxt(){
		StringBuffer txt = new StringBuffer();

		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				//重複兩次增加寬度
				txt.append(colorArr[i][j].getAscii() + "" + colorArr[i][j].getAscii());
			}
			txt.append("\n");
		}

		return txt.toString();
	}


	/**
	 * Build html data from arrays
	 * @return
	 */
	public String buildHtml(){
		StringBuffer html = new StringBuffer();

		html.append("<html>");
		html.append("<body style=\"font-family: monospace; white-space: nowrap;\">");
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				html.append("<span style=\"color: rgb(" + colorArr[i][j].getR() + " " + colorArr[i][j].getG() + " " + colorArr[i][j].getB() +"); \">");
				html.append(colorArr[i][j].getAscii() + "" + colorArr[i][j].getAscii());//重複兩次增加寬度
				html.append("</span>");
			}
			html.append("<br>");
		}
		html.append("</body>");
		html.append("</html>");

		return html.toString();
	}


	/**
	 * Get current time in format
	 * @return
	 * @throws Exception
	 */
	private String getNowTime(){
		return DATE_FORMAT.format(new Date());
	}
	
}
