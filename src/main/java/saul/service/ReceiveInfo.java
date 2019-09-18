package saul.service;

import com.google.gson.Gson;
import lombok.Setter;
import saul.util.MessageType;
import saul.util.Status;
import saul.util.Message;

import java.awt.Color;
import java.awt.TextArea;
import java.io.IOException;
import java.net.Socket;import java.util.Scanner;

public class ReceiveInfo {
	private Scanner receiver;
	private @Setter boolean flag;
	private TextArea resultText;
	public ReceiveInfo(Socket socket, TextArea resultText) throws IOException {
		super();
		receiver = new Scanner(socket.getInputStream());
		this.resultText = resultText;
		flag = true;
	}
	
	public void receive() {
		Gson gson = new Gson();
		Status status = Status.DEFAULT;
		Message message = null;
		String receiveString = null;
		while(flag) {
			receiveString = receiver.nextLine();
			message = gson.fromJson(receiveString, Message.class);
			status = message.getStatus();
			//successful request
			if (status == Status.SUCCESS) {
				resultText.setForeground(Color.black);
				resultText.setText(message.getData());
			//failed request
			}else if(status == Status.FAIL && (message.getType()!= MessageType.TYPE_ADD)){
				resultText.setForeground(Color.orange);
				resultText.setText(message.getInfo());
			// error
			}else if( status == Status.ERROR){
				resultText.setForeground(Color.red);
				resultText.setText(message.getInfo());
			}
		}
	}
}
