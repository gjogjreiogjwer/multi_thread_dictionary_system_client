package saul.representation;

import saul.service.SendRequest;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyButtonListener implements ActionListener {
	private TextField textField;
	private SendRequest sendRequest;
	private TextArea resultText;
	 
	public MyButtonListener(TextField textField, SendRequest sendRequest, TextArea resultText) {
		super();
		this.textField = textField;
		this.sendRequest = sendRequest;
		this.resultText = resultText;
	}
 
	public void actionPerformed(ActionEvent e) {
		String command = textField.getText();
		String[] commands = command.split(",");
		if (commands[0] == null || commands[0].equals("")) {
			resultText.setForeground(Color.red);
			resultText.setText("invalid word, word should not be null or white space");
		}else if (e.getActionCommand().equals("add")) {
			if (commands.length < 2) {
				resultText.setForeground(Color.red);
				resultText.setText("please enter correct command, for example: \njava,code language");
			}else {
				sendRequest.add(commands[0].trim(), command.substring(commands[0].length() + 1).trim());
			}			
		}else if (e.getActionCommand().equals("search")) {
			
			sendRequest.search(command);
			
		}else if (e.getActionCommand().equals("remove")) {
			
			sendRequest.remove(command);
			
		}else if (e.getActionCommand().equals("clear")) {
			textField.setText("");
		}	
	}
	

}
