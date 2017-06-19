package cn.knife037.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * launcher for SFS(Simple File Server)。
 * I'm so lazy to write a doc for this class....
 * @author knife037
 *
 */
public class Launcher {
	
	private static final int THREAD_COUNT = 100;
	
	private static final int PORT = 8080;
	
	private Logger logger = Logger.getLogger(Launcher.class);
	
	private ServerSocket server;
	
	private Executor executor = null;
	
	public Launcher() {
		BasicConfigurator.configure();
		
		executor =  Executors.newFixedThreadPool(THREAD_COUNT);
		
		try {
			server = new ServerSocket(PORT);
		} catch (IOException e) {
			logger.error(PORT + "端口被占用");
			return ;
		}
		
		Socket client = null;
		try {
			while((client = server.accept()) != null) {
				logger.info("收到来自" + client.getRemoteSocketAddress() + "的请求");
				executor.execute(new ClientHandler(client));
			}
		} catch (IOException e) {
			logger.error(e.getStackTrace());
		}
		
	}
	
	
	public class ClientHandler implements Runnable {
		
		private static final int BUFFERED_SIZE = 1024 * 1024 * 1;
		
		private static final String PARENT_PATH = "file/";
		
		private Pattern headerPtn = Pattern.compile("(.+?):(.+)");
		
		private Pattern urlPtn = Pattern.compile("GET /(.+?) HTTP");
		
		private Socket client;
		
		private String url = "";
		
		private HashMap<String, String> header;
		
		public ClientHandler(Socket client) {
			this.client = client;
			header = new HashMap<String, String>();
		}
		
		public void renderHeader() {
			BufferedReader br;
			try {
				br = new BufferedReader(new InputStreamReader(client.getInputStream(), "utf-8"));
				String headLine = br.readLine();
				readerUrl(headLine);
				String line = null;
				Matcher mc = null;
				while((line = br.readLine()).length() != 0) {
					mc = headerPtn.matcher(line);
					mc.find();
					header.put(mc.group(1), mc.group(2));
				}
			} catch (IOException e) {
				logger.error(e.getStackTrace());
			}
			
		}
		
		public void readerUrl(String headLine) {
				Matcher mc = urlPtn.matcher(headLine);
				if(mc.find()) {
					url = mc.group(1);
				} else {
					// TODO 输出404
				}
		}
		
		@Override 
		public void run() {
			logger.debug(client.getRemoteSocketAddress() + "开始处理请求");
			renderHeader();
			File file = new File(PARENT_PATH + url);
			if(file.exists()) {
				if(file.isDirectory()) {
					
					response();
					
				} else {
					try {
						OutputStream output = client.getOutputStream();
						BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(output, "utf-8"));
						bw.write("HTTP/1.1 200 OK\r\n");
						bw.write("Content-Type: application/octet-stream;charset=utf-8\r\n");
						bw.write("Accept-Length: " + file.length() + "\r\n\r\n");
						bw.flush();
						
						FileInputStream input = new FileInputStream(file);
						byte[] bytes = new byte[BUFFERED_SIZE]; 
						int b = 0;
						while((b = input.read(bytes)) != -1) {
							output.write(bytes, 0, b);
						}
						input.close();
						
					} catch (Exception e){
						logger.error(e.getStackTrace());
					}
				}
			} else {
				try {
					OutputStream output = client.getOutputStream();
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(output, "utf-8"));
					bw.write("HTTP/1.1 200 OK\r\n");
					bw.write("Content-Type: text/html;charset=utf-8\r\n");
					bw.write("Vary: Accept-Encoding\r\n\r\n");
					
					bw.write("<html><head><title>404</title></head><body>文件（目录）不存在</body></html>");
					bw.flush();
				} catch(Exception e) {
					logger.error(e.getStackTrace());
				}
				logger.debug(PARENT_PATH + url + "文件不存在");
			}
			
			try {
				client.close();
			} catch (IOException e) {
				logger.error(e.getStackTrace());
			}
			logger.debug(client.getRemoteSocketAddress() + "处理完毕");
		}
		
		public void response() {
			try {
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), "utf-8"));
				String responseContent = getResponseContent();
				bw.write("HTTP/1.1 200 OK\r\n");
				bw.write("Content-Type: text/html;charset=utf-8\r\n");
				bw.write("Vary: Accept-Encoding\r\n\r\n");
				bw.write(responseContent);
				bw.flush();
				
			} catch (IOException e) {
				logger.error(e.getStackTrace());
			}
		}
		
		public String getResponseContent() {
			StringBuilder ans = new StringBuilder("<html><head><title>" + url + "</title></head><body>");
			File file = new File(PARENT_PATH + url);
			String[] files = file.list();
			for(String f : files) {
				if(url.equals("")) {
					ans.append("<a href=\"/" + f +"\">" + f + "</a><br/>\r\n");
				} else {
					ans.append("<a href=\"/" + url + "/" + f +"\">" + f + "</a><br/>\r\n");
				}
			}
			ans.append("</body></html>");
			return ans.toString();
		}
	}
	
	
	public static void main(String[] args) {
		new Launcher();
	}
}
