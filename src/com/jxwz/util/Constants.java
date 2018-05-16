package com.jxwz.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Constants {
	public static final int STATUS_ENABLE = 1;
	public static final int STATUS_DISABLE = 0;
	
	public static final int USER_TYPE_ADMIN = 0;
	public static final int USER_TYPE_STUDENT = 1;
	public static final int USER_TYPE_TEACHER = 2;
	
	public static final int USER_VERIFY_STATUS_UNCHECK=0;
	public static final int USER_VERIFY_STATUS_CHECKED=1;
	
	public static String SEND_MAIL_ACCOUNT;
	public static String SEND_MAIL_PASSWORD;
	public static String SEND_USER_NAME;
	public static String EMAIL_CONTENT;
	public static String EMAIL_SUBJECT;
	public static String DEFAULT_PWD;
	public static String KNOWLEDGE_PATH;
	public static String VIDEO_PATH;
	public static String DOC_PATH;
	public static String URL_FREFIX;
	public static String FILE_PATH;
	
	static {
		Properties prop = new Properties();
		InputStream is = Constants.class.getClassLoader().getResourceAsStream("config/config.properties");
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SEND_MAIL_ACCOUNT = prop.getProperty("sendMailAccount");
		SEND_MAIL_PASSWORD = prop.getProperty("sendMailPw");
		SEND_USER_NAME = prop.getProperty("sendUserName");
		EMAIL_CONTENT = prop.getProperty("emailContent");
		EMAIL_SUBJECT = prop.getProperty("emailSubject");
		DEFAULT_PWD = prop.getProperty("defaultPwd");
		KNOWLEDGE_PATH = prop.getProperty("knowledgePath");
		VIDEO_PATH = prop.getProperty("videoPath");
		DOC_PATH = prop.getProperty("docPath");
		URL_FREFIX = prop.getProperty("urlPrefix");
		FILE_PATH=prop.getProperty("filePath");
	}
}
