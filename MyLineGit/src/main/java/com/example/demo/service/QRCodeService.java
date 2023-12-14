//package com.example.demo.service;
//
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.stereotype.Service;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.client.j2se.MatrixToImageConfig;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.QRCodeWriter;
//import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
//
//@Service
//public class QRCodeService {
//	public byte[] generateQRCodeImage(String text, int width, int height) throws Exception {
//		Map<EncodeHintType, Object> hintMap = new HashMap<>();
//		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
//		hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//
//		QRCodeWriter qrCodeWriter = new QRCodeWriter();
//		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hintMap);
//
//		// BitMatrixをBufferedImageに変換
//		BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, new MatrixToImageConfig());
//
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		javax.imageio.ImageIO.write(qrImage, "png", baos);
//		return baos.toByteArray();
//	}
//}
