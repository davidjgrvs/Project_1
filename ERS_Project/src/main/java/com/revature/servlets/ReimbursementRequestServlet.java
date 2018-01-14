package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.ERS_Reimbursements;
import com.revature.model.ERS_Users;
import com.revature.service.AppService;

@WebServlet("/requestReimbursement")
public class ReimbursementRequestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("in doGet");
		
		req.getRequestDispatcher("features/rRequest/reimbursementrequest.html").forward(req, resp);
		
		
		//PrintWriter out = resp.getWriter();
		//resp.setContentType("application/json");
		//out.println("In doGet");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		ERS_Users clientUser = (ERS_Users)session.getAttribute("user");
		
		int type = Integer.parseInt((String)req.getParameter("requestType"));
		double amount = Double.parseDouble((String)req.getParameter("amount"));
		String description = (String)req.getParameter("description");
		
		ERS_Reimbursements rRequest = new ERS_Reimbursements();
		
		rRequest.setR_amount(amount);
		rRequest.setR_description(description);
		rRequest.setRt_type(type);
		rRequest.setR_receipt(null);
		rRequest.setU_id_author(clientUser.getU_id());
		
		System.out.println("type:" + type);
		System.out.println("amount: " + amount);
		AppService.appService.submitRequest(rRequest);
		
		
		resp.sendRedirect("app.html");
		
		//PrintWriter out = resp.getWriter();
		//resp.setContentType("application/json");
		//out.println("In doPost");
	}
	
}
