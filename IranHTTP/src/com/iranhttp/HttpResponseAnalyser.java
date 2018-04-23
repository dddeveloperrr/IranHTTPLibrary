package com.iranhttp;


/*
	this class recieves an httpresponse String (consisted of response headers
	and body like below) and has methods that analyses the server response to
	for example, extract the headers and body and status code


	an example of HttpResponse that a server may send:

	HTTP/1.1 301 Moved Permanently
	Content-Type: text/html; charset=UTF-8
	Location: https://www.zoomit.ir/help.php
	Date: Thu, 15 Feb 2018 10:06:46 GMT
	Connection: close
	Content-Length: 153

	<head><title>Document Moved</title></head>
	<body>
	<h1>Object Moved</h1>This document may be found <a HREF="https://www.zoomit.ir/help.php">here</a>
	</body>

 */


public class HttpResponseAnalyser {

	// this variable holds the server response which consists of response headers and body
	String serverResponse = null;


//-------------------------------------------------------------------------------------------------------------------

	  //Constructor
      public HttpResponseAnalyser (String serverResponse) {
    	  this.serverResponse = serverResponse;
      }


//-------------------------------------------------------------------------------------------------------------------


      public String getResponseHeaders() {

    	  String receivedHeaders;
    	  receivedHeaders = serverResponse.substring(0, serverResponse.indexOf("\r\n\r\n"));

    	  return receivedHeaders;
	}


//-------------------------------------------------------------------------------------------------------------------


      public String getResponseBody() {

    	  String receivedBody;
    	  receivedBody = serverResponse.replaceFirst(getResponseHeaders(), "");

    	  return receivedBody;
	}


//-------------------------------------------------------------------------------------------------------------------


      public String getStatusCode() {

    	  String responseHeaders = getResponseHeaders();
    	  String statusCode = responseHeaders.substring(9, responseHeaders.indexOf(System.lineSeparator()));
    	  return statusCode;
	}


//-------------------------------------------------------------------------------------------------------------------
}
