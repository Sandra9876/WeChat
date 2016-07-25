package com.zs.wc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zs.wc.common.CommonField;
import com.zs.wc.util.SignUtil;

@Controller()
@RequestMapping("/wechat")
public class commonController {
	
	private Logger log = Logger.getLogger(commonController.class);  

	@RequestMapping(method = { RequestMethod.GET }) 
	public void common(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			NoSuchAlgorithmException {
		String method = request.getMethod().toUpperCase().trim();
		if (method.equals(CommonField.METHOD_GET)) {
			String signature = request.getParameter("signature");
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			String echostr = request.getParameter("echostr");
			System.out.println("signature :" + signature);
			System.out.println("timestamp:" + timestamp);
			System.out.println("nonce:" + nonce);
			System.out.println("echostr:" + echostr);
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {
				PrintWriter out = response.getWriter();
				out.print(echostr); 
//				out.write(echosssssssstr);
				out.flush(); 
				out.close(); 
				System.out.println(echostr);
			}
		}
	}
}
