package org.lab5.calc.api;


//import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.lab5.calc.model.CalculateGradeBean;
import org.lab5.calc.service.CalcGradeService;


@Path("/lab5calc")
@Produces(MediaType.APPLICATION_JSON)
public class CalcGradeAPI {
	
	@GET
	public Response calculateGrade(@QueryParam("year") String year, @QueryParam("subject") String subject) {
		CalcGradeService service = CalcGradeService.getService();
		/*
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = null;
		JsonNode year = null;
		JsonNode subject = null;
		*/
		CalculateGradeBean bean = new CalculateGradeBean();
		double grade = 0;
		if(year == null && subject == null) {
			grade = service.calculateGrade(year, subject);
			bean.setYear("Not Specified!");
			bean.setSubject("Not Specified");
		}
		else if(year == null) {
			grade = service.calculateGrade(null, subject);
			bean.setYear("Not Specified");
			bean.setSubject(subject);
		}
		else if(subject == null) {
			grade = service.calculateGrade(year, null);
			bean.setSubject("Not Specified");
			bean.setYear(year);
		}
		else {
			grade = service.calculateGrade(year, subject);
			bean.setSubject(subject);
			bean.setYear(year);
		}
		bean.setGrade(grade);
		return Response.status(Response.Status.OK).entity(bean).build();
	}	
}
