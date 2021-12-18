package pl.dreszer.projekt.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import pl.dreszer.projekt.services.FileServiceImpl;

import java.io.IOException;

public class FileController {
    @PostMapping("uploadFile.html")
    public String processForm(MultipartFile multipartFile)
    {
        if (!multipartFile.isEmpty())
        {
            ;
        }
        else
        {
            FileServiceImpl fileService = new FileServiceImpl();
            try {
                fileService.saveFile(multipartFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "index";
    }
}
