<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<form action="lab5" method="post">
			<fieldset>
				<table align="center">
					<tr>
						<td><span>Year</span></td>
						<td><input name="year" type="text"
							title="Enter Year of Study" /></td>
					</tr>
					<tr>
						<td><span>Subject</span></td>
						<td><input name="subject" type="text" title="Enter Subject" /></td>
					</tr>
					<tr>
						<td><input type="submit" name="Get_Grade"
							value="Calculate Grade" /></td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
</body>
</html>