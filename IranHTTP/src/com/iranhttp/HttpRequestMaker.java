package com.iranhttp;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/*
	this class has methods that prepare an http request which a client
	should send to server to get a source. this source can be an php page,
	an html page, a image file, a video file, a javascript or css file.

	an http request is provided below as an example:

	GET /help.php HTTP/1.0
	Host: www.zoomit.ir
	User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0
	Accept: text/css;q=0.1
	Accept-Language: en-US,en;q=0.5
	Accept-Encoding: gzip, deflate, br
	Referer: https://www.zoomit.ir/help.php
	Connection: keep-alive
 */


public class HttpRequestMaker {



//-------------------------------------------------------------------------------------------------------------------


	    private String method;
	    private String httpVersion;
	    private String postContent;
	    private URL sourceUrl;
	    private HashMap<String, String> httpRequestHeaders = new HashMap<String, String>();


//-------------------------------------------------------------------------------------------------------------------


	    public void set(String method, String httpVersion, String sourceUrl) throws Exception {

	    	if ( method.toUpperCase().equals("GET") == true || method.toUpperCase().equals("POST") == true || method.toUpperCase().equals("HEAD") == true  ) {
	    		this.method = method;
	    	} else {
	    		throw new Exception("The method is invalid. Enter GET Or POST Or HEAD");
			}

	    	if ( httpVersion.equals("HTTP/1.0") == true || httpVersion.equals("HTTP/1.1") == true) {
		    	this.httpVersion = httpVersion;
	    	} else {
	    		throw new Exception("The httpVersion is invalid. Enter HTTP/1.0 Or HTTP/1.1");
			}

	    	try {

	        	URL url = new URL(sourceUrl);
	        	this.sourceUrl = url;

	    	} catch (Exception e) {

	    		throw new Exception("The url is not in standard form. It should be like http://www.example.com");
			}
	    }


//-------------------------------------------------------------------------------------------------------------------


	    public void addHearder( String headerName, String HeaderValue) {
	    	httpRequestHeaders.put(headerName, HeaderValue);
	    }


//-------------------------------------------------------------------------------------------------------------------


	    public void addPostContent(String postContent) {
	    	this.postContent = postContent;
		}


//-------------------------------------------------------------------------------------------------------------------


	    public String prepareHttpRequest () throws Exception {

	    	StringBuilder sb = new StringBuilder();

			if ( method.toUpperCase().equals("GET") == true ) {

				if (sourceUrl.getFile().isEmpty() == true ) {
					sb.append(method.toUpperCase() + " " + "/"  + " " + httpVersion + System.lineSeparator());
				} else {
					sb.append(method.toUpperCase() + " " + sourceUrl.getFile() + " " + httpVersion + System.lineSeparator());
				}

				//the Host: www.example.com should be always there
				httpRequestHeaders.put("Host", sourceUrl.getHost());

              Iterator it = httpRequestHeaders.entrySet().iterator();
              while (it.hasNext()) {
                  Map.Entry pair = (Map.Entry)it.next();
                  sb.append(pair.getKey() + ": " + pair.getValue() + System.lineSeparator());
              }
              sb.append(System.lineSeparator());



			} else if ( method.toUpperCase().equals("POST") == true ) {

				if (sourceUrl.getFile().isEmpty() == true ) {
					sb.append(method.toUpperCase() + " " + "/"  + " " + httpVersion + System.lineSeparator());
				} else {
					sb.append(method.toUpperCase() + " " + sourceUrl.getFile() + " " + httpVersion + System.lineSeparator());
				}


				//the Host: www.example.com should be always there
				httpRequestHeaders.put("Host", sourceUrl.getHost());

				//the Content-Lengh: 999 should be always available in POST request
				if (postContent != null) {

					httpRequestHeaders.put("Content-Lengh", String.valueOf(postContent.length()));

				} else {

 					throw new Exception("If you use POST method, you should pass String paraeter to HttpRequest.addPostContent() method as post content");

				}


				 Iterator it = httpRequestHeaders.entrySet().iterator();
	              while (it.hasNext()) {
	                  Map.Entry pair = (Map.Entry)it.next();
	                  sb.append(pair.getKey() + ": " + pair.getValue() + System.lineSeparator());
	              }
	              sb.append(postContent);
	              sb.append(System.lineSeparator());



			} else if (method.toUpperCase().equals("HEAD") == true) {

				if (sourceUrl.getFile().isEmpty() == true ) {
					sb.append(method.toUpperCase() + " " + "/"  + " " + httpVersion + System.lineSeparator());
				} else {
					sb.append(method.toUpperCase() + " " + sourceUrl.getFile() + " " + httpVersion + System.lineSeparator());
				}

	              Iterator it = httpRequestHeaders.entrySet().iterator();
	              while (it.hasNext()) {
	                  Map.Entry pair = (Map.Entry)it.next();
	                  sb.append(pair.getKey() + ": " + pair.getValue() + System.lineSeparator());
	              }
	              sb.append(System.lineSeparator());

			}


	    return sb.toString();
	 }


//-------------------------------------------------------------------------------------------------------------------
}
