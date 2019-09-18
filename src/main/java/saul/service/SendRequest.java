package saul.service;

import com.google.gson.Gson;
import lombok.Getter;
import saul.util.MessageType;
import saul.util.Message;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class SendRequest {
	private @Getter Socket socket;
	private PrintStream sender;
	
	private Message message;
	private Gson gson;
	
	public SendRequest(Socket socket) throws IOException{
		this.socket = socket;
		sender = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
		
		message = new Message();
		gson = new Gson();
	}
	
	public  void add(String word, String meanings) {
		message.clear();
		message.setType(MessageType.TYPE_ADD);
		message.setInfo(meanings);
		message.setData(word);
		sender.println(gson.toJson(message));
		sender.flush();
	}
	
	public void search(String word) {
		message.clear();
		message.setType(MessageType.TYPE_SEARCH);
		message.setData(word);
		sender.println(gson.toJson(message));
		sender.flush();
	}
	
	public void remove(String word) {
		message.clear();
		message.setType(MessageType.TYPE_REMOVE);
		message.setData(word);
		sender.println(gson.toJson(message));
		sender.flush();
	}
	
	public void closeStream() {
		if (sender != null) {
			sender.close();
		}
	}

}
