package pl.dreszer.projekt.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
@PropertySource("classpath:config.properties")
@Service
public class FileServiceImpl {
    @Value("${files.location}")
    private String imagesDir;

    int[][] dimensions = { {100,100}, {200,200}, {300, 300} };

    public void saveFile(MultipartFile multipartFile, int paintingId) throws IOException {
        for (ImageSize i : ImageSize.values() )
        {
            String actualImageDir = imagesDir+"/paintings/"+i.name().toLowerCase()+"/"+paintingId;
            new File(actualImageDir).mkdirs();
            String fileNameAndExtension[] =  multipartFile.getOriginalFilename().split("\\.");
            Path path = Path.of(actualImageDir, "image."+fileNameAndExtension[1]);
            BufferedImage scaled = scaleImage(multipartFile.getBytes(), dimensions[i.getId()][0],dimensions[i.getId()][1]);
            ImageIO.write(scaled, "png", path.toFile());
        }
    }

    public BufferedImage scaleImage(byte[] image, int width, int height) throws IOException {
        InputStream is = new ByteArrayInputStream(image);
        BufferedImage readImage = ImageIO.read(is);
        BufferedImage scaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = scaled.createGraphics();
        graphics2D.drawImage(readImage, 0,0,width,height,null);
        graphics2D.dispose();
        return scaled;
    }

    enum ImageSize
    {
        MIN(0), MED(1), MAX(2);
        private int id;

        ImageSize(int id)
        {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }
}
