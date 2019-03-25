package com.petwork.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class SendEmailServlet
 */
@WebServlet("/sendEmail")
public class SendEmailServlet extends HttpServlet {
	
	final String username = "pjun1270";//사용자 아이디
	final String userpassword = "qkrruddjswl135!#%";//사용자 비밀번호
	final String host = "smtp.gmail.com";//사용할 메일
	final int port = 465;//smtp 포트
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendEmailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userMail = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("qna-content");
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); 
        props.put("mail.smtp.port", "25"); 
        props.put("mail.debug", "true"); 
        props.put("mail.smtp.auth", "true"); 
        props.put("mail.smtp.starttls.enable","true"); 
        props.put("mail.smtp.EnableSSL.enable","true");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
        props.setProperty("mail.smtp.socketFactory.fallback", "false");   
        props.setProperty("mail.smtp.port", "465");   
        props.setProperty("mail.smtp.socketFactory.port", "465"); 

        String view = "/views/common/msg.jsp";
        String loc = "/qna/qnaUserList";
        String msg = "";
        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() 
        { 
            protected PasswordAuthentication getPasswordAuthentication() 
            { 
               return new PasswordAuthentication(username, userpassword); 
            }
         });
        
        try {
        	Message message = new MimeMessage(session);
        	
        	message.setFrom(new InternetAddress(userMail));
        	message.setRecipient(Message.RecipientType.TO,new InternetAddress("pjun127@naver.com"));
        	
        	message.setSubject(subject);
        	message.setText(content);
        	Transport.send(message);
        	
        	msg = "이메일 문의가 접수 되었습니다.";
        	
        }catch(Exception e){
        	e.printStackTrace();
        	msg = "이메일 문의 접수에 실패하였습니다. 관리자에게 문의해주세요.";
        }
        
        request.setAttribute("loc", loc);
        request.setAttribute("msg", msg);
        request.getRequestDispatcher(view).forward(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
