package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Base64;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.google.gson.Gson;

public class BOLSingleton {
	private static BOLSingleton instance;
	
	private static String username = "";
	private static String password = "";
	public static BOLBug bug = null;
	
	private BOLSingleton() {
		
	}
	
	public static BOLSingleton getInstance() throws Exception {
		if(!username.equals("") && !password.equals("") && bug!=null) {
			return BOLSingleton.getInstance(BOLSingleton.username, BOLSingleton.password, BOLSingleton.bug);
		}
		else
		{
			throw new Exception("Username and password cannot be '' or null!");
		}
	}
	
	 public static BOLSingleton getInstance(String username, String password, BOLBug bug) throws Exception
     {
         if(!username.equals("") && !password.equals(""))
         {
             BOLSingleton.username = username;
             BOLSingleton.password = password;
             BOLSingleton.bug = bug;
             return BOLSingleton.Instance();
         }
         else
         {
        	 throw new Exception("Username and password cannot be '' or null!");
         }
         
     }
	 
	 private static BOLSingleton Instance()
     {
         if (instance == null)
         {
             instance = new BOLSingleton();
         }
         return instance;
     }
	 
	 public void Send(Exception ex) {
		 
		 try {
	            bug.setDescription(ex.getMessage());
	            bug.setErrNumber(0);
	            StringWriter sw = new StringWriter();
	            ex.printStackTrace(new PrintWriter(sw));
	            bug.setStackTrace(sw.toString());

	            //Convert to Json
	            Gson gson = new Gson();    
	            String json = gson.toJson(bug);
				/* SSL certificate */
		        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
		            public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
		            public void checkClientTrusted(X509Certificate[] certs, String authType) { }
		            public void checkServerTrusted(X509Certificate[] certs, String authType) { }

		        } };
		        SSLContext sc = SSLContext.getInstance("SSL");
		        sc.init(null, trustAllCerts, new java.security.SecureRandom());
		        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		        // Create all-trusting host name verifier
		        HostnameVerifier allHostsValid = new HostnameVerifier() {
		            public boolean verify(String hostname, SSLSession session) { return true; }

		        };
		        // Install the all-trusting host verifier
		        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		        /* End of SSL certificate */
		        
	            URL url = new URL("https://api.bugsonline.biz/api/send");//your url i.e fetch data from .
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setDoInput(true);
	            conn.setDoOutput(true);
	            conn.setRequestMethod("POST");
	            conn.setRequestProperty("Accept", "application/json");
	            conn.setRequestProperty("Content-Type", "application/json; utf-8");
	            
	            //Authentication
	            String authorization = this.username + ":" + this.password;
	            authorization ="Basic "+new String(Base64.getEncoder().encode(authorization.getBytes()));
	            conn.addRequestProperty("Authorization", authorization);
	            
	            //Pass BOLBug instance
	            OutputStream os = conn.getOutputStream();
	                byte[] input = json.getBytes("utf-8");
	                os.write(input, 0, input.length);
	                os.close();
	            
	            int status = conn.getResponseCode();
	            
	            if (status != HttpURLConnection.HTTP_OK) {
	                throw new RuntimeException("Failed : HTTP Error code : "
	                        + conn.getResponseCode());
	            }
	            InputStreamReader in = new InputStreamReader(conn.getInputStream());
	            BufferedReader br = new BufferedReader(in);
	            String output;
	            while ((output = br.readLine()) != null) {
	                System.out.println(output);
	            }
	            conn.disconnect();

	        } catch (Exception e) {
	            System.out.println("Exception during Send(): " + e);
	        
		}
	 }
	 
	 
}
