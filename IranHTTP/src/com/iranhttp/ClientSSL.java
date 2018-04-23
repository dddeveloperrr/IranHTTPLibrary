package com.iranhttp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;


public class ClientSSL {

	private SSLSocket SSLsocket;
	private String serverUrl;
	private int serverPort;

	public  ClientSSL ( String serverUrl, int serverPort) {
		this.serverUrl = serverUrl;
		this.serverPort = serverPort;
	}

	public boolean connect() throws UnknownHostException, IOException {
		 SSLSocketFactory f =(SSLSocketFactory) SSLSocketFactory.getDefault();
		 SSLsocket = (SSLSocket) f.createSocket(serverUrl, serverPort);  // serverUrl like www.zoomit.ir
		 SSLsocket.startHandshake();
		 return true;
	}



	public void sendData(String data) throws IOException {

		BufferedWriter w = new BufferedWriter( new OutputStreamWriter(SSLsocket.getOutputStream()));
        w.write(data);
        w.newLine();
        w.flush();
	}

	public String getData() throws IOException {

		 StringBuilder sb = new StringBuilder();

		 InputStream inputStream = SSLsocket.getInputStream();
		 InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
		 BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String inputLine;
			inputLine = bufferedReader.readLine();

			while ( inputLine != null) {

				sb.append(inputLine);
				inputLine = bufferedReader.readLine();

				 if (inputLine != null) {
					 sb.append(System.lineSeparator());
				 }
			}

			inputStream.close();
			inputStreamReader.close();
			bufferedReader.close();
            return sb.toString();
	}
}
