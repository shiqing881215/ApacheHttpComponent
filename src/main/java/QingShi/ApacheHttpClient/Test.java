package QingShi.ApacheHttpClient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * This is refers the Apache Http Client Quick Start
 * http://hc.apache.org/httpcomponents-client-ga/quickstart.html
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// HTTP GET METHOD
        try {
            HttpGet httpGet = new HttpGet("https://www.google.com/#newwindow=1&q=hello");
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            // The underlying HTTP connection is still held by the response object
            // to allow the response content to be streamed directly from the network socket.
            // In order to ensure correct deallocation of system resources
            // the user MUST either fully consume the response content  or abort request
            // execution by calling CloseableHttpResponse#close().

            try {
                System.out.println(response1.getStatusLine());
                HttpEntity entity1 = response1.getEntity();
                // Method 1: Using EntityUtil to get the response body
                System.out.println(EntityUtils.toString(entity1, "UTF-8"));
                // Method 2 : Using the BasicResponseHandler to get the response body
                String responseString = new BasicResponseHandler().handleResponse(response1);
                System.out.println(responseString);
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity1);
            } finally {
                response1.close();
            }

//            HttpPost httpPost = new HttpPost("http://targethost/login");
//            List <NameValuePair> nvps = new ArrayList <NameValuePair>();
//            nvps.add(new BasicNameValuePair("username", "vip"));
//            nvps.add(new BasicNameValuePair("password", "secret"));
//            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
//            CloseableHttpResponse response2 = httpclient.execute(httpPost);
//
//            try {
//                System.out.println(response2.getStatusLine());
//                HttpEntity entity2 = response2.getEntity();
//                // do something useful with the response body
//                // and ensure it is fully consumed
//                EntityUtils.consume(entity2);
//            } finally {
//                response2.close();
//            }
        } finally {
            httpclient.close();
        }
	}
}
