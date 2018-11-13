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

	/**
	 * Converts a PDF document (given as a byte array) to images. The image size in
	 * pixels will be as many as the PDF printout would be at 72 DPI resolution.
	 * (For example an A4 page 595 pixels wide and 842 pixels high.)
	 * 
	 * @param b byte array representing the PDF document
	 * @return ArrayList of BufferedImages
	 * 
	 * @see <a href=
	 *      "http://www.icesoft.org/wiki/display/PDF/Converting+PDF+Page+Renderings">http://www.icesoft.org/wiki/display/PDF/Converting+PDF+Page+Renderings</a>
	 */
	public static ArrayList<BufferedImage> convertPdfToImage(byte[] b) {
		return (convertPdfToImage(b, 1f));
	}

	/**
	 * Converts a PDF document (given as a byte array) to images. The image size in
	 * pixels will be as many as the PDF printout would be at 72 DPI resolution.
	 * (For example an A4 page 595 pixels wide and 842 pixels high.)
	 * 
	 * @param b     byte array representing the PDF document
	 * @param fZoom zoom ratio - how big should be the image
	 * @return ArrayList of BufferedImages
	 * 
	 * @see <a href=
	 *      "http://www.icesoft.org/wiki/display/PDF/Converting+PDF+Page+Renderings">http://www.icesoft.org/wiki/display/PDF/Converting+PDF+Page+Renderings</a>
	 * 
	 */
	public static ArrayList<BufferedImage> convertPdfToImage(byte[] b, float fZoom) {
		if (fZoom < 0.001f) {
			fZoom = 1f;
		}
		Document document = new Document();
		ArrayList<BufferedImage> alBIs = new ArrayList<>();
		try {
			document.setByteArray(b, 0, b.length, null);
			// float scale = 2.0f;
			float rotation = 0f;

			for (int i = 0; i < document.getNumberOfPages(); i++) {
				try {
					// BufferedImage image = (BufferedImage) document.getPageImage(i,
					// GraphicsRenderingHints.PRINT, Page.BOUNDARY_CROPBOX, rotation, scale);
					BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN,
							Page.BOUNDARY_CROPBOX, rotation, fZoom);
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
