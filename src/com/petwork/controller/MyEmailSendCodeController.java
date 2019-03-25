package com.petwork.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petwork.model.service.MemberService;
import com.petwork.model.vo.Member;


/**
 * Servlet implementation class MyPasswordResetController
 */
@WebServlet("/myemailsendcodecontroller")
public class MyEmailSendCodeController extends HttpServlet 
{
    final String username="jaebum6937";
    final String password="jaja7396";
    String toMail=null;
    String findId=null;
    int random6;

    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyEmailSendCodeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		findId=request.getParameter("inputId");
		Member checkM=null;
		Member checkedM=null;
		MemberService ms=null;
		ms=new MemberService();
		checkM = new Member();
		String msg=null;
		
		checkM.setId(findId);		
		
		checkedM=ms.memberService(checkM);			
		
		if(checkedM==null||checkedM.getMemberYN()=='Y')
		{
			/*System.out.println(returnData);
			System.out.println("로그인 실패");*/
			
			msg="은/는 존재하지 않는 아이디입니다.";
			
			request.setAttribute("checkedM", checkedM);
			request.setAttribute("msg", msg);
			request.setAttribute("random6", random6);
	        request.setAttribute("findId", findId);
	        
			RequestDispatcher rd=request.getRequestDispatcher("/views/member/emailSendPop.jsp");				
			rd.forward(request, response);
		}
		else
		{		
	        Properties props = new Properties(); 
	        /*props.put("mail.smtp.user",username); 
	        props.put("mail.smtp.password", password);*/
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
	        
	        toMail=checkedM.getEmail();
	        
	        Session session = Session.getInstance(props, 
	        
			new javax.mail.Authenticator() 
	        { 
		        protected PasswordAuthentication getPasswordAuthentication() 
		        { 
		        	return new PasswordAuthentication(username, password); 
		        }
	        });
	        
	        try
	        {
	            Message message = new MimeMessage(session); 
	            message.setFrom(new InternetAddress("jaebum6937@gmail.com"));//
	            message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(toMail));// 보낼 이메일 주소 입력 
	            message.setSubject("Petwork 비밀번호 재설정 Email 인증입니다.");
	            
	            random6 = (int)(Math.floor(Math.random() * 1000000)+100000);
	            
	            if(random6>1000000){
	               random6 = random6 - 100000;
	            }
	            
	            message.setText("비밀번호 재설정 인증 코드는 ["+random6+"] 입니다.");//내용 
	            
	            //message.setContent("내용","text/html; charset=utf-8");//글내용을 html타입 charset설정
	           
	            Transport.send(message);           
	        } 
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	       /* 
	        System.out.println("메일찍기"+toMail);*/
	        
	        msg=toMail+"로 인증코드를 전송하였습니다.";	
	       request.setAttribute("checkedM", checkedM);
	        request.setAttribute("msg", msg);
	        request.setAttribute("random6", random6);
	        request.setAttribute("findId", findId);
	        request.getRequestDispatcher("/views/member/emailSendPop.jsp").forward(request, response);
		}
    }  


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
