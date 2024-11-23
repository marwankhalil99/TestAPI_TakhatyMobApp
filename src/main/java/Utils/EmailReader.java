package Utils;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailReader {

    public static String getOTPFromEmail() {
        String otp = null;
        try {
            // Set up email server properties for Yahoo Mail
            Properties properties = new Properties();
            properties.put("mail.store.protocol", "imaps");
            properties.put("mail.imap.host", "imap.mail.yahoo.com");
            properties.put("mail.imap.port", "993");
            properties.put("mail.imap.ssl.enable", "true");

            // Connect to the Yahoo email account
            Session session = Session.getInstance(properties, null);
            Store store = session.getStore();
            store.connect("imap.mail.yahoo.com", "marwanshamsooo@yahoo.com", "ruohoihurobgyvgc");

            // Access the inbox folder
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // Fetch the latest message from inbox
            Message[] messages = inbox.getMessages();
            Message latestMessage = messages[messages.length - 1]; // Get the latest email

            // Get email content as string
            String emailContent = getTextFromMessage(latestMessage);

            if (emailContent != null) {
                // Extract OTP using regex (assuming the OTP is a 6-digit number)
                Pattern pattern = Pattern.compile("\\d{6}");
                Matcher matcher = pattern.matcher(emailContent);
                if (matcher.find()) {
                    otp = matcher.group();
                }
            } else {
                System.out.println("Failed to retrieve email content.");
            }

            inbox.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return otp;
    }

    // Method to extract text from email message (for multipart messages or plain text)
    private static String getTextFromMessage(Message message) throws Exception {
        if (message.isMimeType("text/plain")) {
            // Handle plain text email
            return message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            // Handle multipart (usually means HTML or attachments)
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            return getTextFromMimeMultipart(mimeMultipart);
        } else if (message.isMimeType("text/html")) {
            // If it's HTML, return it as a string (you might need to extract OTP from HTML)
            return message.getContent().toString();
        }
        return null;
    }

    // Method to handle multipart content (e.g., text/plain or text/html)
    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result.append(bodyPart.getContent());
            } else if (bodyPart.isMimeType("text/html")) {
                // If it's HTML content, you may need to parse HTML to extract OTP
                result.append(bodyPart.getContent());
            }
        }
        return result.toString();
    }
}
