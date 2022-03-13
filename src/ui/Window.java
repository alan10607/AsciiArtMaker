package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Window {
	public final static int WINDOW_WIDTH = 500;
	public final static int WINDOW_HEIGHT = 500;
	public final static Font SENS_BOLD_FONT_STYLE = new Font(Font.SANS_SERIF, Font.BOLD, 14);
	public final static Font SENS_NORMAL_FONT_STYLE = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
	
	//main page
	public JFrame jFrame = new JFrame("Mememaker");
    public JPanel panel = new JPanel();

    //button
	public JButton exportBtn = new JButton("Export");
	public JButton browseBtn = new JButton("Browse");

    //text
    public JLabel pathHint = new JLabel("Jpg file path: ");
    public JLabel exportAsHint = new JLabel("Export as: ");
    public JLabel sizeHint = new JLabel("Size: ");
    public JTextField pathTextField = new JTextField("C:/");
    public JTextField sizeTextField = new JTextField("100");
    public JTextArea consoleTextArea = new JTextArea("Console:\n");
    
    //CheckBox
    public JCheckBox txtCheckBox = new JCheckBox(".txt");
    public JCheckBox htmlCheckBox = new JCheckBox(".html(with color)");
    public JCheckBox negativeCheckBox = new JCheckBox("negative image");

    //panel
    public JPanel pathPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
    public JPanel toolPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
    public JPanel btnPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    public JScrollPane consolePane = new JScrollPane(consoleTextArea);
    

	public Window() {
		//pathPane
		pathHint.setFont(SENS_BOLD_FONT_STYLE);
		pathTextField.setPreferredSize(new Dimension(WINDOW_WIDTH, 25));
		pathPane.add(pathHint);
		pathPane.add(pathTextField);
		
		
		//toolPane
		exportAsHint.setFont(SENS_BOLD_FONT_STYLE);
		txtCheckBox.setFont(SENS_BOLD_FONT_STYLE);
		txtCheckBox.setSelected(true);
		htmlCheckBox.setFont(SENS_BOLD_FONT_STYLE);
		sizeHint.setFont(SENS_BOLD_FONT_STYLE);
		negativeCheckBox.setFont(SENS_BOLD_FONT_STYLE);
		sizeTextField.setPreferredSize(new Dimension(50, 25));
		toolPane.add(exportAsHint);
		toolPane.add(txtCheckBox);
		toolPane.add(htmlCheckBox);
		toolPane.add(new JLabel("      "));
		toolPane.add(sizeHint);
		toolPane.add(sizeTextField);
		toolPane.add(new JLabel("      "));
		toolPane.add(negativeCheckBox);
		
		
		//btnPane
		exportBtn.addActionListener(new WindowAction("saveAscii", this));
		exportBtn.setBackground(new Color(235, 235, 235));
		exportBtn.setFont(SENS_NORMAL_FONT_STYLE);
		btnPane.add(putInBtnPanel(exportBtn));
		browseBtn.addActionListener(new WindowAction("browseFile", this));
		browseBtn.setBackground(new Color(235, 235, 235));
		browseBtn.setFont(SENS_NORMAL_FONT_STYLE);
		btnPane.add(putInBtnPanel(browseBtn));
		btnPane.setPreferredSize(new Dimension(WINDOW_WIDTH, 35));

		
		//consolePane
		consoleTextArea.setFont(SENS_NORMAL_FONT_STYLE);
		consoleTextArea.setLineWrap(true);//auto line break
		consoleTextArea.setEditable(false);
		consoleTextArea.setBackground(new Color(235, 235, 235));
		consolePane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		consolePane.setPreferredSize(new Dimension(WINDOW_WIDTH, 120));


		//add to main panel
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(pathPane);
		panel.add(toolPane);
		panel.add(btnPane);
		panel.add(consolePane);

        showFrame();
	}


	/**
	 * Show console
	 * @param console
	 */
	public void addConsole(String console) {
		consoleTextArea.setText(consoleTextArea.getText() + console + "\n");
	}


	/**
	 * Panel for button
	 * @param <E>
	 * @param ele
	 * @return
	 */
	private <E extends Component> JPanel putInBtnPanel(E ele) {
		JPanel jPanel = new JPanel(new GridLayout(1, 0));
		jPanel.add(ele);
		jPanel.setPreferredSize(new Dimension(87, 25));
        return jPanel;
	}


	/**
	 * Open main page
	 */
	private void showFrame() {
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//shutdown process if close
		jFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		jFrame.setLocationRelativeTo(null);//middle view
		jFrame.setVisible(true);//set visible
		jFrame.setContentPane(panel);
		jFrame.pack();//reduced to the size of the internal object
	}

}
