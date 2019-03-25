package com.petwork.controller;

import java.io.File;
import java.io.IOException;
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

/**
 * Servlet implementation class DoctorMyPageModifyEndServlet
 */
@WebServlet("/doctor/modifyEnd")
public class DoctorMyPageModifyEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorMyPageModifyEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String root=getServletContext().getRealPath("/");
		
		String saveDir=root+"views/upload/doctor";
		
		int maxSize=1024*1024*10;//10메가 설정
		MultipartRequest mr=new MultipartRequest(request, saveDir,maxSize,"UTF-8",new DefaultFileRenamePolicy());
		int doctorNo=Integer.parseInt(mr.getParameter("doctorNo"));
		String doctorLicense=mr.getParameter("doctorModify-license");
		String doctorName=mr.getParameter("doctorModify-name");
		String doctorHospital=mr.getParameter("doctorModify-hospital");
		String doctorAddress=mr.getParameter("doctorModify-address");
		String doctorPhone=mr.getParameter("doctorClick-phone1")+"-"+mr.getParameter("doctorClick-phone2")+"-"+mr.getParameter("doctorClick-phone3");
		String doctorReImg=mr.getFilesystemName("doctorModify-img");
		String doctorOriImg=mr.getParameter("doctorOriName");
		
		Doctor d= new Doctor();
		d.setDoctorLicense(doctorLicense);
		d.setDoctorName(doctorName);
		d.setDoctorNo(doctorNo);
		d.setDoctorHospital(doctorHospital);
		d.setDoctorAddress(doctorAddress);
		d.setDoctorPhone(doctorPhone);
		d.setDoctorImg(doctorReImg);
		if(doctorReImg !=null)
		{
			d.setDoctorImg(doctorReImg);
			File oriFile = new File(saveDir+File.separator+mr.getParameter("doctorOriName"));
			if(oriFile.exists()) oriFile.delete();
		}else
		{
			d.setDoctorImg(doctorOriImg);
		}
	
		int result=new DoctorService().updateClickDoctor(d);
		
		String view="";
		String loc="";
		
		if(result>0)
		{	
			HttpSession session=request.getSession();
			session.setAttribute("loginDoctor", d);
			view="/views/doctor_mypage/doctorMypage.jsp";
			
		}
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
