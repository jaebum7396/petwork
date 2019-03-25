package com.petwork.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.petwork.model.service.DoctorService;
import com.petwork.model.vo.Doctor;
import com.petwork.model.vo.Member;

import common.encrypt.EcyptPassword;
import common.encrypt.EncryptWrapper;

/**
 * Servlet implementation class DoctorRegistServlet
 */
@WebServlet(name="DoctorRegistServlet" ,urlPatterns="/admin/doctorRegist")
public class DoctorRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorRegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		Member m=(Member)session.getAttribute("loginMember");
		String view="";
		String msg ="잘못된 접근입니다.";
		String loc ="/";
		if(m==null)
		{	
			view="/views/common/msg.jsp";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher(view).forward(request, response);			
		}else if(m!=null&&m.getAdminYN()=='Y')
		{
		
		
			String root=getServletContext().getRealPath("/");
			
			String saveDir=root+"views/upload/doctor";
			//파일에대한 크기제한설정
			int maxSize=1024*1024*10;//10메가 설정
			//멀티파트필터를 겪어야한다??
			MultipartRequest mr=new MultipartRequest(request, saveDir,maxSize,"UTF-8",new DefaultFileRenamePolicy());
			//이렇게까지하면 해당파일이 업로드됨!
			 view="/views/common/msg.jsp";
			 msg="";
			
//			mr.getParameter("doctorRegist-password");
			String doctorId=mr.getParameter("doctorRegist-Id");
			String doctorPwd=EcyptPassword.getSha512(mr.getParameter("doctorRegist-password"));
			String doctorLicense=mr.getParameter("doctorRegist-license");
			String doctorName=mr.getParameter("doctorRegist-name");
			String doctorHospital=mr.getParameter("doctorRegist-hospital");
			String doctorAddress=mr.getParameter("doctorRegist-address");
			String doctorPhone=mr.getParameter("doctorRegist-phone1")+"-"+mr.getParameter("doctorRegist-phone2")+"-"+mr.getParameter("doctorRegist-phone3");
			String doctorImg=mr.getFilesystemName("doctorRegist-img");
			//멀티파트폼일때 암호화 필터 통과그냥됨 
			Doctor d= new Doctor();
			d.setDoctorId(doctorId);
			d.setDoctorPwd(doctorPwd);
			d.setDoctorLicense(doctorLicense);
			d.setDoctorName(doctorName);
			d.setDoctorHospital(doctorHospital);
			d.setDoctorAddress(doctorAddress);
			d.setDoctorPhone(doctorPhone);
			d.setDoctorImg(doctorImg);
			
			int result=new DoctorService().insertDoctor(d);
			
			List list=new ArrayList();
			list.add(d);
		
			
			request.setAttribute("doctor", list);
			if(result>0)
			{
				msg="수의사 등록에 성공하였습니다.";
				loc="/doctor/doctorShow";
				//view="/admin/doctorRegist";	
			}
			else
			{
				msg="수의사 등록에 실패하였습니다.";
				loc="/views/doctor_regist/doctorRegist.jsp";
				
			}
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			RequestDispatcher rd=request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
		else
		{
			view="/views/common/msg.jsp";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher(view).forward(request, response);
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
