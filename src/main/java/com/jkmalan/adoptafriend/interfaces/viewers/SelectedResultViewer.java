package com.jkmalan.adoptafriend.interfaces.viewers;

import com.jkmalan.adoptafriend.interfaces.SelectedResultFrame;

import javax.swing.JFrame;

public class SelectedResultViewer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new SelectedResultFrame();
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setTitle("Selected Pet Information");
	      frame.setVisible(true);  
	}

}
