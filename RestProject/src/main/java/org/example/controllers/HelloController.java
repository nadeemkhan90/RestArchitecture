package org.example.controllers;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
	
	@RequestMapping(value="/b",method=RequestMethod.GET)
    public  String index(HttpServletResponse response)throws Exception {
		try {
			throw new Exception();}catch(Exception e){
				System.out.println("Inside the throw of hello");	
			}
		response.setHeader("Transfer-Encoding", "chunked");
		
	String s = "hetst";
	InputStream is = new ByteArrayInputStream( s.getBytes() );
    
    IOUtils.copy((InputStream) is, response.getOutputStream());
    response.flushBuffer();
    
        return "Greetings from Spring Boot!";
    }

}
