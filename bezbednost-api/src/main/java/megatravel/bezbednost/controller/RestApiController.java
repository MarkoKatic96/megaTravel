package megatravel.bezbednost.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController
{
	@RequestMapping("/api/hi")
	public String hi()
	{
		return "<table>\r\n" + 
				"    <tr>\r\n" + 
				"      <td>Unesi email adresu &nbsp;</td>\r\n" + 
				"      <td><input type=\"email\" id=\"email\" class=\"form-control\" /></td>\r\n" + 
				"    </tr>\r\n" + 
				"    <tr>\r\n" + 
				"      <td>Unesi lozinku</td>\r\n" + 
				"      <td><input type=\"password\" id=\"password\" class=\"form-control\" /></td>\r\n" + 
				"    </tr><br />\r\n" + 
				"    <tr><td></td><td><input type=\"submit\" class=\"btn btn-outline-danger my-2 my-sm-0\" value=\"Prijavi se\"/></td></tr>\r\n" + 
				"  </table>";
	}
}
