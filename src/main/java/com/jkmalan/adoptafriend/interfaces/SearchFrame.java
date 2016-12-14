package com.jkmalan.adoptafriend.interfaces;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SearchFrame extends JFrame {
    private JLabel KeywordLabel;
    private JLabel ResultLabel;
    private JTextField KeywordField;
    private JButton SearchButton;
    private JButton CancelButton;
    private JPanel SearchPanel;
    private JPanel ResultPanel;
    private ActionListener listener;

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;
    private static final int FIELD_WIDTH = 25;

    public SearchFrame() {
        ResultLabel = new JLabel("Results: ");
        createTextArea();
        createSearchButton();
        createSearchPanel();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void createSearchPanel() {

        SearchPanel = new JPanel();
        SearchPanel.add(KeywordLabel);
        SearchPanel.add(KeywordField);
        SearchPanel.add(SearchButton);
        SearchPanel.add(ResultLabel);
        add(SearchPanel);


    }

    private void createSearchButton() {
        SearchButton = new JButton("Search");
        class AddSearchListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                ResultLabel.setText("Results: ");
                JFrame frame = new SelectedResultFrame();
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setTitle("Selected Pet Lisitng");
                frame.setVisible(true);
            }
        }

        ActionListener listener = new AddSearchListener();
        SearchButton.addActionListener(listener);
    }

    private void createTextArea() {
        KeywordLabel = new JLabel("Enter Keyword ");
        KeywordField = new JTextField(FIELD_WIDTH);
    }


}
