package com.example.logging;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

final class BufferedResponseWrapper extends  HttpServletResponseWrapper {

	HttpServletResponse original;
	TeeServletOutputStream tee;
	ByteArrayOutputStream bos;
	volatile boolean shouldCopyStream = false; //always copy stream, only when you dont watn to read anything for logging we dont need to copy
	
	public BufferedResponseWrapper(HttpServletResponseWrapper response) {
		super(response);
		original = response;
	}

	public String getContent() {
		return bos.toString();
	}

	public ServletOutputStream getOutputStream() throws IOException {
		if(!shouldCopyStream){
			return original.getOutputStream();
		}
		if (tee == null) {
			bos = new ByteArrayOutputStream();
			tee = new TeeServletOutputStream(original.getOutputStream(), bos);
		}
		return tee;

	}

	@Override
	public void setHeader(String name, String value) {
		super.setHeader(name, value);
		if (name.equalsIgnoreCase("Transfer-Encoding") && value.equalsIgnoreCase("chunked")) {
			shouldCopyStream = false;
		}
	}
}