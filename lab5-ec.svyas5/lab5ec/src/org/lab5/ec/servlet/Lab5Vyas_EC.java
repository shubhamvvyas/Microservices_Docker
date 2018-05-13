package org.lab5.ec.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Servlet implementation class Lab5Vyas
 */
@WebServlet("/lab5ec")
public class Lab5Vyas_EC extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Lab5Vyas_EC() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Properties props = new Properties();
		try {
			InputStream propFile = this.getClass().getClassLoader().getResourceAsStream("lab5Vyas.properties");
			props.load(propFile);
			propFile.close();
		}
		catch (IOException ie) {
			ie.printStackTrace();
		}
		StringBuilder sBuilder = null;
		String targetURL = null;
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet httpGet = null;
		PrintWriter out = response.getWriter();
		HttpResponse serviceResponse = null;
		String year = request.getParameter("year");
		String subject = request.getParameter("subject");
		String grade = request.getParameter("grade");
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<body>");
		out.println("<center>");
		if(request.getParameter("Get_Grade") != null) {
			targetURL = props.getProperty("calcURL");
			sBuilder = new StringBuilder(targetURL);
			sBuilder.append("?year="+year+"&subject="+subject);
			targetURL = sBuilder.toString();
			httpGet = new HttpGet(targetURL);
			httpGet.setHeader("Accept", "application/json");
			serviceResponse = client.execute(httpGet);
			String responseBody = EntityUtils.toString(serviceResponse.getEntity());
			JSONObject jObj = (JSONObject) new JSONTokener(responseBody).nextValue();
			double gradeResponse = jObj.getDouble("grade");
			out.println("<table border=\"1\" ><tr><th>Year</th>");
			out.println("<th>Subject</th>");
			out.println("<th>Grade</th></tr>");
			//out.println("<th>LetterGrade</th>");
			out.println("<tr><td align=\"center\">" + year + "</td>");
			out.println("<td align=\"center\">" + subject + "</td>");
			out.println("<td align=\"center\">" + gradeResponse + "</td></tr></table>");
			out.println("<br/><br/><a href=\"index.html\">Go Back!</a></center>");
			//out.println("Year:"+year+"\tSubject:"+subject+"\tGrade:"+grade);
		}
		else if(request.getParameter("Get_Letter_Grade") != null) {
			targetURL = props.getProperty("mapURL");
			if(grade == null) {
				out.println("Please enter numeric grade to map it to letter grade!<br/><br/><a href=\"index.html\">Go Back!</a></center>");
			}
			else {
				sBuilder = new StringBuilder(targetURL);
				sBuilder.append("?grade="+grade);
				targetURL = sBuilder.toString();
			}
			httpGet = new HttpGet(targetURL);
			httpGet.setHeader("Accept", "application/json");
			serviceResponse = client.execute(httpGet);
			String responseBody = EntityUtils.toString(serviceResponse.getEntity());
			JSONObject jObj = (JSONObject) new JSONTokener(responseBody).nextValue();
			String gradeResponse = jObj.getString("letterGrade");
			out.println("<table border=\"1\" ><tr><th>Grade</th>");
			out.println("<th>Letter Grade</th></tr>");
			//out.println("<th>LetterGrade</th>");
			out.println("<tr><td align=\"center\">" + grade + "</td>");
			out.println("<td align=\"center\">" + gradeResponse + "</td></tr></table>");
			out.println("<br/><br/><a href=\"index.html\">Go Back!</a></center>");
			//out.println("Year:"+year+"\tSubject:"+subject+"\tGrade:"+grade);
		}
		out.println("</body></html>");
	}

}
