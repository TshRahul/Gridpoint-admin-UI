package com.gridpoint.adminui.automation.user;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

import com.gridpoint.automation.constants.PathConstants;
import com.sun.mail.imap.IMAPFolder;

public class EmailReader {
	
	public HashMap<String, String> execute(String userCreateDate) throws IOException {
		final String user = PathConstants.USERNAME;
		final String password = PathConstants.PASSWORD;
		IMAPFolder inbox = null;
		Store store = null;
		try {
			Properties props = getProperties();

			store = getStore(props, user, password);
			inbox = (IMAPFolder) store.getFolder("inbox");

			if (!inbox.isOpen())
				inbox.open(Folder.READ_WRITE);
			SearchTerm searchTerm = searchFilter(userCreateDate);

			Message[] messages = inbox.search(searchTerm);

			System.out.println("No of Unread Messages : " + inbox.getUnreadMessageCount());

			HashMap<String, String> userNamePasswordMap = new HashMap<String, String>();

			if (messages.length == 0) {
				System.out.println("No Message for downloading");
			} else {
				for (int i = 0; i < messages.length; i++) {
					if (isValidMessage(messages[i])) {
						String message = messages[i].getContent().toString();
						String fetchedUsername = message
								.substring(message.indexOf("Username:") + 9, message.indexOf("Password:"))
								.replaceAll("\\<.*?>", "").trim();
						String fetchedPassword = message
								.substring(message.indexOf("Password:") + 9, message.indexOf("URL:"))
								.replaceAll("\\<.*?>", "").trim();
						userNamePasswordMap.put(fetchedUsername, fetchedPassword);
					}
					inbox.setFlags(new Message[] { messages[i] }, new Flags(Flags.Flag.SEEN), true);
				}
				for (Map.Entry<String, String> userNamePassword : userNamePasswordMap.entrySet()) {
					System.out.println(userNamePassword.getKey() + "\n" + userNamePassword.getValue());
				}
			}
			return userNamePasswordMap;
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (AuthenticationFailedException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inbox != null && inbox.isOpen()) {
					inbox.close(false);
				}
				if (store != null) {
					store.close();
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private Store getStore(Properties props, String user, String password)
			throws NoSuchProviderException, MessagingException {
		Session session = Session.getDefaultInstance(props);
		Store store = session.getStore("imaps");
		store.connect("mail.gridpoint.com", user, password);
		return store;
	}

	private Properties getProperties() {
		Properties props = System.getProperties();
		props.put("mail.imap.host", "mail.gridpoint.com");
		props.put("mail.imap.auth", true);
		// props.put("mail.imap.port",143);
		props.put("mail.imap.starttls.enable", true);
		props.put("mail.transport.protocol", "imaps");
		return props;
	}

	private boolean isValidMessage(final Message message) {
		try {
			if (isOriginatedMail(message.getHeader("In-Reply-To"))) {
				if (message.getSubject().equals("Welcome")) { // changed
					return true;
				} else {
					return false;
				}
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * Will verify the message is originated mail else it is forwarded or reply
	 * mail. Return true if it is originating mail
	 */
	private boolean isOriginatedMail(String[] header) {
		if (header == null) {
			return true;
		}
		return false;
	}

	private SearchTerm searchFilter(final String userCreateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
		Date dt = null;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(formatter.parse(userCreateDate));
			cal.add(Calendar.DATE, -1);
			dt = formatter.parse(cal.getTime().toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Flags seen = new Flags(Flags.Flag.SEEN);
		FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
		SearchTerm newerThan = new ReceivedDateTerm(ComparisonTerm.GT, dt);
		return new AndTerm(unseenFlagTerm, newerThan);
	}
}
