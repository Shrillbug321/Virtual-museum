package pl.dreszer.projekt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import pl.dreszer.projekt.services.FileServiceImpl;

import java.io.IOException;
@Controller
public class FileController {
    @Autowired
    private FileServiceImpl fileService;

    @RequestMapping("uploadFile.html")
    public String file()
    {
        return "uploadFile";
    }
    @PostMapping("uploadFile.html")
    public String processForm(@Param("multipartFile") MultipartFile multipartFile)
    {
        if (!multipartFile.isEmpty())
        {
            try {
                fileService.saveFile(multipartFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {

        }
        return "index";
    }
}
