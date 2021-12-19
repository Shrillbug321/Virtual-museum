package pl.dreszer.projekt.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
@PropertySource("classpath:config.properties")
@Service
public class FileServiceImpl {
    @Value("${files.location}")
    private String photoDir;

    public void saveFile(MultipartFile multipartFile) throws IOException {
        new File(photoDir).mkdirs();
        Path path = Path.of(photoDir, multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(path.toFile());
        fos.write(multipartFile.getBytes());
        fos.close();
    }
}
