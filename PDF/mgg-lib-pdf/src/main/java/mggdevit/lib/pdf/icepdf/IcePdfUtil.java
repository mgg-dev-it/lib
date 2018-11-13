package mggdevit.lib.pdf.icepdf;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

public abstract class IcePdfUtil {

	public static ArrayList<BufferedImage> convertPdfToImage2(byte[] b) {
		Document document = new Document();
		ArrayList<BufferedImage> alBIs = new ArrayList<>();
		try {
			document.setByteArray(b, 0, b.length, null);
			float scale = 2.0f;
			float rotation = 0f;

			for (int i = 0; i < document.getNumberOfPages(); i++) {
				try {
					// BufferedImage image = (BufferedImage) document.getPageImage(i,
					// GraphicsRenderingHints.PRINT, Page.BOUNDARY_CROPBOX, rotation, scale);
					BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN,
							Page.BOUNDARY_CROPBOX, rotation, scale);
					alBIs.add(image);
				} catch (InterruptedException e) {
				}
			}

			document.dispose();
		} catch (PDFException e) {
		} catch (PDFSecurityException e) {
		} catch (IOException e) {
		}
		return (alBIs);

	}

}
