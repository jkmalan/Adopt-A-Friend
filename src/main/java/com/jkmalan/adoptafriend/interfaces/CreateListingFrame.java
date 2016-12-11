package com.jkmalan.adoptafriend.interfaces;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CreateListingFrame extends JFrame 
{
	private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 600;
    private static final int AREA_ROWS = 10;
    private static final int AREA_COLUMNS = 25;
	private static final int FIELD_WIDTH = 20;
	
	private JLabel TitleLabel;
    private JLabel TypeLabel;
    private JLabel AgeLabel;
    private JLabel SexLabel;
    private JLabel RegionLabel;
    private JLabel AttributeLabel;    
    private JLabel DescriptionLabel;
    private JLabel AddPhotoLabel;
    private JLabel Image;
    private JTextField TitleField;
    private JTextField TypeField;
    private JTextField AgeField;
    private JTextField SexField;
    private JTextField RegionField;
    private JTextArea AttributeArea;
    private JTextArea DescriptionArea;
    private JButton SaveButton;
    private JButton CancelButton;
    private JButton OpenFileButton;
    private JPanel jpanel, panel;
    
    public CreateListingFrame()
    {
    	AddPhotoLabel = new JLabel("Add Photo:* ");
    	TitleLabel = new JLabel("Title:* ");
    	TypeLabel = new JLabel("Type of Animal:* ");
    	AgeLabel = new JLabel("Age:* ");
    	SexLabel = new JLabel("Sex:* ");
    	RegionLabel = new JLabel("Region:* ");
    	AttributeLabel = new JLabel("Attributes:* ");   	
    	DescriptionLabel = new JLabel("Description:* ");
    	TitleField = new JTextField(FIELD_WIDTH);
    	TypeField = new JTextField(FIELD_WIDTH);
    	AgeField = new JTextField(FIELD_WIDTH);
    	SexField = new JTextField(FIELD_WIDTH);
    	RegionField = new JTextField(FIELD_WIDTH);
    	AttributeArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
    	AttributeArea.setEditable(true);
    	DescriptionArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
        DescriptionArea.setEditable(true);
        
        jpanel = new JPanel();
        jpanel.setLayout(new BorderLayout());
        Image = new JLabel(" ");
        jpanel.add(Image, BorderLayout.CENTER);
        
       createOpenFileButton(); 
       createSaveButton();
       createCancelButton();
       createProfilePanel();
       
       setSize(FRAME_WIDTH, FRAME_HEIGHT);
       
    }

	private void createOpenFileButton()
	{
		OpenFileButton = new JButton("Open File");
		OpenFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JFileChooser fc = new JFileChooser();
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    String sname = file.getAbsolutePath(); //THIS WAS THE PROBLEM
                    Image = new JLabel("", new ImageIcon(sname), JLabel.CENTER);
                    jpanel.add(Image, BorderLayout.CENTER);
                    jpanel.revalidate(); //ADD THIS AS WELL
                    jpanel.repaint();  //ADD THIS AS WELL
                }
            }
        });
		
	}

	private void createSaveButton()
	{
		SaveButton = new JButton("Save");
		class AddSaveListener implements ActionListener
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	 JFrame frame = new MainPageFrame();
			      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			      frame.setTitle("Main Page");
			      frame.setVisible(true); 
		    }
		}
	ActionListener listener = new AddSaveListener();
    SaveButton.addActionListener(listener);
		
	}

	private void createCancelButton() 
	{
		CancelButton = new JButton("Cancel");
		
		class AddCancelListener implements ActionListener
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	System.exit(0);
		    }
		}
	ActionListener listener = new AddCancelListener();
    CancelButton.addActionListener(listener);
		
	}

	private void createProfilePanel() 
	{
		panel = new JPanel();
		panel.add(AddPhotoLabel);
		panel.add(OpenFileButton);
		panel.add(jpanel);
		panel.add(TitleLabel);
		panel.add(TitleField);
		panel.add(TypeLabel);
		panel.add(TypeField);
		panel.add(AgeLabel);
		panel.add(AgeField);
		panel.add(SexLabel);
		panel.add(SexField);
		panel.add(RegionLabel);
		panel.add(RegionField);
		panel.add(AttributeLabel);
		panel.add(AttributeArea);
		panel.add(DescriptionLabel);
		panel.add(DescriptionArea);
		panel.add(SaveButton);
		panel.add(CancelButton);
		add(panel);
	}

}
