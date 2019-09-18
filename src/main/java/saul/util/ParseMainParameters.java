package saul.util;

import org.kohsuke.args4j.Option;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ParseMainParameters {
	@Option(name="-address" ,required = false, usage = "IP address")
	private String ipAddress = "localhost";
	
	@Option(name="-port", required = false, usage = "tcp port")
	private int port = 8888;
}
