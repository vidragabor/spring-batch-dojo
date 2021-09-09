package hu.vidragabor.springbatchdojo.ftp.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Service
public class FtpService {
	
	@Value("${ftp.host}")
	private String host;
	@Value("${ftp.port}")
	private int port;
	@Value("${ftp.username}")
	private String username;
	@Value("${ftp.password}")
	private String password;
	@Value("${ftp.timeout}")
	private long timeout;
	
	private final FTPClient ftpClient = createFTPClientInstance();
	
	@Bean
	public FTPClient createFTPClientInstance() {
		return new FTPClient();
	}
	
	@PostConstruct
	public void openConnection() {
		try {
			ftpClient.connect(host, port);
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				log.error("Exception occurred while connecting to FTP server! Reply code: {}", ftpClient.getReplyCode());
				ftpClient.disconnect();
			}
			
			boolean isLoggedIn = ftpClient.login(username, password);
			if (!isLoggedIn) {
				log.error("Exception occurred while log in to FTP server! Reply code: {}", ftpClient.getReplyCode());
				ftpClient.disconnect();
			}
			ftpClient.setControlKeepAliveTimeout(timeout);
			ftpClient.enterLocalPassiveMode();
		} catch (IOException e) {
			log.error("Exception occurred in connection creation", e);
		}
	}
	
	@PreDestroy
	public void closeConnection() {
		try {
			if (ftpClient.isConnected() && isConnected()) {
				ftpClient.logout();
				ftpClient.disconnect();
			}
		} catch (IOException e) {
			log.error("Exception occurred in connection closing", e);
		}
	}
	
	public boolean isConnected() {
		boolean connected = false;
		try {
			connected = ftpClient.sendNoOp();
		} catch (IOException e) {
			log.warn("FTP server prematurely closes the connection as a result of the client being idle!", e);
		} catch (Exception e) {
			log.error("Exception code replied from the server!", e);
		}
		return connected;
	}
	
	public boolean uploadFile(File file, String remoteFileName) {
		log.info("Uploading {} to {}", file.getAbsolutePath(), remoteFileName);
		try (InputStream inputStream = new FileInputStream(file)) {
			return ftpClient.storeFile(remoteFileName, inputStream);
		} catch (IOException e) {
			log.error("Error while uploading file to ftp.", e);
			return false;
		}
	}
	
}
