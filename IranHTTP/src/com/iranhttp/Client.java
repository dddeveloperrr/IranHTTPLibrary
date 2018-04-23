package com.iranhttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;



public class Client {


	private Socket socket;
	private String serverUrl;
	private int serverPort;
	private OutputStream outputStream;
	private InputStream inputStream;
	private long byteSent;
	private long byteRecieved;

	public  Client ( String serverUrl, int serverPort) {

		this.serverUrl = serverUrl;
		this.serverPort = serverPort;

	}

	public boolean connect() throws UnknownHostException, IOException {

		    socket = new Socket(serverUrl, serverPort);
			return true;
	}



	public void sendData(String data) throws IOException {

			 outputStream = socket.getOutputStream();
			 PrintWriter out = new PrintWriter(outputStream, false);
			 byteSent = data.getBytes("UTF-8").length;
			 out.write(data);
			 out.flush();
	}

	public String getData() throws IOException {

		    StringBuilder sb = new StringBuilder();
			inputStream = socket.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader in = new BufferedReader(inputStreamReader);
			String inputLine;
			inputLine = in.readLine();

			while ( inputLine != null) {

				sb.append(inputLine);
				inputLine = in.readLine();

				 if (inputLine != null) {
					 sb.append(System.lineSeparator());
				 }
			}
			in.close();
            byteRecieved = sb.toString().getBytes().length;
            return sb.toString();
	}

	public long getByteRecieved() {
		return byteRecieved;
	}

	public long getByteSent() {
		return byteSent;
	}



	public boolean socketIsBound() {
		return socket.isBound();
	}

	public boolean socketIsClosed() {
		return socket.isClosed();
	}

	public boolean socketIsConnected() {
		return socket.isConnected();
	}
}
