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
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Servlet implementation class CalcForm
 */
@WebServlet("/lab5calcec")
public class CalcForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalcForm() {
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
		// TODO Auto-generated method stub
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
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<body>");
		out.println("<center>");
		targetURL = props.getProperty("subjectURL");
		sBuilder = new StringBuilder(targetURL);
		targetURL = sBuilder.toString();
		httpGet = new HttpGet(targetURL);
		httpGet.setHeader("Accept", "application/json");
		serviceResponse = client.execute(httpGet);
		String responseBody = EntityUtils.toString(serviceResponse.getEntity());
		JSONObject jObj = (JSONObject) new JSONTokener(responseBody).nextValue();
		JSONArray jArr = jObj.getJSONArray("subjectList");
		String[] subjects = new String[jArr.length()];
		for (int i = 0; i < jArr.length(); i++) {
		    JSONObject tempObj = (JSONObject) jArr.get(i);
		    subjects[i] = tempObj.getString("subject");
		}
		out.println("<form action=\"lab5ec\" method=\"post\">\n" + 
				"			<fieldset>\n" + 
				"				<table align=\"center\">\n" + 
				"					<tr>\n" + 
				"						<td><span>Year</span></td>\n" + 
				"						<td><input name=\"year\" type=\"text\"\n" + 
				"							title=\"Enter Year of Study\" /></td>\n" + 
				"					</tr>\n" + 
				"					<tr>\n" + 
				"						<td><span>Subject</span></td>");
		out.println("<td><select name=\"subject\">");
		for(String s : subjects) {
			out.println("<option value=\""+s+"\">"+s+"</option>");
		}
		out.println("</select></td></tr>");
		out.println("<tr>\n" + 
				"						<td><input type=\"submit\" name=\"Get_Grade\"\n" + 
				"							value=\"Calculate Grade\" /></td>\n" + 
				"					</tr>\n" + 
				"				</table>\n" + 
				"			</fieldset>\n" + 
				"		</form>");
	}

}
