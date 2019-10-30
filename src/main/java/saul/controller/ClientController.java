package saul.controller;
import lombok.extern.log4j.Log4j2;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import saul.representation.MainFrame;
import saul.service.ReceiveInfo;
import saul.service.SendRequest;
import saul.util.ParseMainParameters;
import saul.util.UtilDictionary;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


@Log4j2
public class ClientController {
	
	public static void main(String[] args) {
		Socket socket = null;
		ReceiveInfo receiver = null;
		SendRequest sendRequest = null;
		ParseMainParameters args4j = new ParseMainParameters();
		CmdLineParser parser = new CmdLineParser(args4j);
			try {
				parser.parseArgument(args);
				String address = args4j.getIpAddress();
				if (!UtilDictionary.checkIp(address)) {
					log.error("inputs wrong type of ip address.");
					System.exit(0);
				}
				int port = args4j.getPort();
				socket = new Socket(address, port);
				log.info("successfully connect server: " + address + ": " + port );
				sendRequest = new SendRequest(socket);
				MainFrame frame = new MainFrame(sendRequest);
				//receive message from dictionary server
				receiver = new ReceiveInfo(socket, frame.getResultText());
				frame.start();
				receiver.receive();
			} catch (CmdLineException e) {
				log.error("an error in the user input parameters.");
			} catch (UnknownHostException e) {
				log.error("not found the host.");
			} catch (IOException e) {
				log.error("faild in creating socket.");
			}

	}
}
