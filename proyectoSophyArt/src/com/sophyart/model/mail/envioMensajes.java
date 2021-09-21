package com.sophyart.model.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class envioMensajes {

	public static void enviarMail(String para,String asunto,String contenido) throws Exception {
		Properties properties= new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String miCuenta="xxxxxxxx@gmail.com";
		String contra="xxxxxxxx";
		
		Session session=Session.getInstance(properties,new Authenticator() {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(miCuenta, contra);	
		}
		});
		Message mensaje= nuevoMensaje(session,miCuenta,para,asunto,contenido);
		Transport.send(mensaje);
	}

	private static Message nuevoMensaje(Session s,String cuenta,String para,String asunto, String contenido) {
		Message mens= new MimeMessage(s);
		try {
			mens.setFrom(new InternetAddress(cuenta));
			mens.setRecipient(Message.RecipientType.TO, new InternetAddress(para));
			mens.setSubject(asunto);
			mens.setText(contenido);
			return mens;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
