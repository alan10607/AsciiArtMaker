package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.ImageLoader;

import javax.swing.*;

public class WindowAction implements ActionListener{
	private String type = "";
	private Window window;

	WindowAction(String type, Window ele){
		this.type = type;
		this.window = ele;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("saveAscii".equals(type)) {
			saveAscii(window);
		}else if("browseFile".equals(type)) {
			browseFile(window);
		}
	}


	/**
	 * Save Ascii by ImageLoader
	 * @param window
	 */
	private void saveAscii(Window window){
		ImageLoader imageLoader = null;
		try {
			boolean txtFlag = window.txtCheckBox.isSelected();
			boolean htmlFlag = window.htmlCheckBox.isSelected();
			boolean negativeFlag = window.negativeCheckBox.isSelected();
			String path = window.pathTextField.getText();
			int size = checkSize(window.sizeTextField.getText());

			window.addConsole(getNowTime() + "Loaded:" + path);
			imageLoader = new ImageLoader(path, size, negativeFlag);
			if(txtFlag) {
				String newPath = path.substring(0, path.lastIndexOf(".")) + ".txt";
				String data = imageLoader.buildTxt();
				imageLoader.writeFile(newPath, data);
				window.addConsole(getNowTime() + "Save:" + newPath);
			}
			if(htmlFlag) {
				String newPath = path.substring(0, path.lastIndexOf(".")) + ".html";
				String data = imageLoader.buildHtml();
				imageLoader.writeFile(newPath, data);
				window.addConsole(getNowTime() + "Save:" + newPath);
			}

		}catch (Exception e) {
			window.addConsole(getErrMsg(e));
			e.printStackTrace();
		}
	}


	/**
	 * Browse File
	 * @param window
	 */
	private void browseFile(Window window){
		try {
			//open browse
			JFileChooser chooser = new JFileChooser();
			int flag = chooser.showOpenDialog(window.jFrame);

			if (flag == JFileChooser.APPROVE_OPTION) {
				window.pathTextField.setText(chooser.getSelectedFile().getAbsolutePath());
			}
		}catch (Exception e) {
			window.addConsole(getErrMsg(e));
			e.printStackTrace();
		}
	}



	/**
	 * Format error exception
	 * @return
	 * @throws Exception
	 */
	private String getErrMsg(Exception e){
		return getNowTime() + "Error:" + e.getMessage();
	}


	/**
	 * Get current time in format
	 * @return
	 * @throws Exception
	 */
	private String getNowTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}


	/**
	 * Check input size is integer
	 * @param size
	 * @return
	 * @throws Exception
	 */
	private int checkSize(String size) throws Exception {
		try {
			return Integer.parseInt(size);
		}catch(Exception ex) {
			throw new Exception("Size must be a number");
		}
	}

	
}