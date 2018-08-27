package com.fashionmii.home.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@WebServlet("/2DBarCode")
public class TwoDBarCodeServlet extends HttpServlet {
	private static final long serialVersionUID = -206725495650187650L;
	String fileFomat = ".gif";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = this.getServletContext().getRealPath("/") + "2DBarCode/";
		File dir = new File(path);
		if (!dir.exists() && !dir.isDirectory()) {
			dir.mkdir();
		}

		File file = null;
		String url = request.getParameter("url");
		String fileName = null;
		if (StringUtils.isNotBlank(url)) {
			fileName = path + DigestUtils.md5Hex(url);
			file = new File(fileName + fileFomat);
			if(!file.exists()){
				encode(url, 200, 200, fileName + fileFomat);
			}
		} else {
			return;
		}

		response.setContentType("image/jpeg");

		file = new File(fileName + fileFomat);
		// 创建文件输入流
		FileInputStream is = new FileInputStream(file);
		// 响应输出流
		ServletOutputStream out = response.getOutputStream();
		// 创建缓冲区
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = is.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
		is.close();
		out.flush();
		out.close();
	}

	public static void encode(String contents, int width, int height, String imgPath) {
		Map<EncodeHintType, Object> hints = new Hashtable<>();
		// 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		// 指定编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);

			MatrixToImageWriter.writeToStream(bitMatrix, "gif", new FileOutputStream(imgPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
