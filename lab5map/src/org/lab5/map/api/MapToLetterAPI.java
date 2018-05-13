package org.lab5.map.api;




//import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.lab5.map.model.LetterGrade;
import org.lab5.map.service.MapToLetterService;


@Path("/lab5map")
@Produces(MediaType.APPLICATION_JSON)
public class MapToLetterAPI {
	@GET
	public Response mapToLetterGrade(@QueryParam("grade") String grade) {
		
		LetterGrade letterGrade = new LetterGrade();
		MapToLetterService service = new MapToLetterService();
		String s = null;
		if(grade != null) {
			try {
				s = service.mapToLetterGrade(Double.parseDouble(grade));
			}
			catch(NumberFormatException nfe) {
				return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Please enter numeric grade!\"}").build();
			}
			letterGrade.setLetterGrade(s);
			return Response.status(Response.Status.OK).entity(letterGrade).build();
		}
		return Response.status(Response.Status.OK).entity("{\"message\": \"Grade cannot be null!\"}").build();
	}
}
