package pl.dreszer.projekt.services;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.AreaBreakType;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import pl.dreszer.projekt.models.Genre;
import pl.dreszer.projekt.models.Painting;
import pl.dreszer.projekt.repositories.PaintingsRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

@Service
public class PDFService {
	@Value("${pdfs.location}")
	String pdfsLocation;

	@Value("${fonts.location}")
	String fontsLocation;

	@Value("${files.location}")
	private String imagesDir;

	@Autowired
	PaintingsRepository paintingsRepository;

	public String createPdfFromPainting(int paintingId) throws IOException {
		Painting painting = paintingsRepository.getById(paintingId);

		new File(pdfsLocation).mkdirs();
		Path pdfPath = Path.of(pdfsLocation, painting.getName()+".pdf");

		PdfDocument pdf = new PdfDocument(new PdfWriter(pdfPath.toString()));
		Document document = new Document(pdf, PageSize.A5);

		createPage(document, painting, 1);
		document.close();
		return painting.getName();
	}

	public void createPdfFromList() throws IOException
	{
		List<Painting> paintings = paintingsRepository.findAll();

		new File(pdfsLocation).mkdirs();
		Path pdfPath = Path.of(pdfsLocation, "list.pdf");

		PdfDocument pdf = new PdfDocument(new PdfWriter(pdfPath.toString()));
		Document document = new Document(pdf, PageSize.A5);

		int pageNumber = 1;
		for (Painting painting: paintings)
		{
			createPage(document, painting, pageNumber);
			pageNumber++;
		}
		document.close();
	}

	private void createPage(Document document, Painting painting, int pageNumber) throws IOException {
		if (pageNumber > 1)
			document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
		Path imagePath = Path.of(imagesDir+"/paintings/med/"+painting.getPaintingId(), "/image.jpg");

		PdfFont segoeui = PdfFontFactory.createFont(StandardFonts.HELVETICA, "cp1250");
		Table table = new Table(2);
		table.setFont(segoeui);
		document.add(new Paragraph("Data dodania "+painting.getAddDate().toString()).setFontSize(10));
		document.add(new Image(ImageDataFactory.create(imagePath.toString())));
		table.addCell(getCell("Nazwa ")).addCell(getCell(painting.getName()));
		table.addCell(getCell("Autor ")).addCell(getCell(painting.getAuthor()));
		table.addCell(getCell("Data namalowania ")).addCell(getCell(painting.getPaintedDate().toString()));
		table.addCell(getCell("Wartość ")).addCell(getCell(painting.getValue()+""));
		table.addCell(getCell("Wystawiony ")).addCell(getCell(painting.isExhibited()+""));
		table.addCell(getCell("Technika ")).addCell(getCell(painting.getTechnique().getName()+""));
		table.addCell(getCell("Wymiary ")).addCell(getCell(painting.getDimensions()+""));
		String genres = "";
		for(Genre genre: painting.getGenres())
		{
			genres = genres.concat(genre.getName()+" ");
		}
		table.addCell(getCell("Rodzaj ")).addCell(getCell(genres));
		table.addCell(getCell("Muzeum ")).addCell(getCell(painting.getMuseum().getName()+", "+painting.getMuseum().getPlace()));
		table.addCell(getCell("Sztuk ")).addCell(getCell(painting.getExemplars()+""));
		document.add(table);
		Paragraph footer = new Paragraph(Integer.toString(pageNumber));
		document.showTextAligned(footer, 400, 25, pageNumber, TextAlignment.RIGHT, VerticalAlignment.MIDDLE,0);
	}
	public void openPdfFile(@RequestParam String name, HttpServletResponse response) throws IOException {
		response.setContentType("application/pdf");
		FileSystemResource pdfFile = new FileSystemResource("H:/Semestr V/PP/Projekt/pdfs/"+name+".pdf");
		InputStream is = pdfFile.getInputStream();
		IOUtils.copy(is, response.getOutputStream());
		response.setHeader("content-disposition", "inline;filename=" + name+".pdf");
		response.flushBuffer();
	}
	private Cell getCell(String text)
	{
		Cell cell = new Cell().add(new Paragraph(text));
		cell.setPadding(0);
		cell.setTextAlignment(TextAlignment.LEFT);
		cell.setBorder(Border.NO_BORDER);
		return cell;
	}
}
