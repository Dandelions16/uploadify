package com.spring.uploadify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
public class UploadController {

//    Menentukan path yang akan dituju untuk menaruh file yang diunggah
    private static final String UPLOADED_PATH = "D:\\SEM4\\upload";
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){
        if (file.isEmpty()){
            redirectAttributes.addFlashAttribute("message"," Select File upload");
            return "redirect:status";
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Path.of((UPLOADED_PATH + file.getOriginalFilename()));
            Files.write(path,bytes);

            redirectAttributes.addFlashAttribute(
                    "message", " Success upload file" + file.getOriginalFilename());
        }catch (IOException exception){
            exception.printStackTrace();
        }
        return "redirect:status";
    }
    @GetMapping("/status")
    public String uploadStatus(){
        return "status";
    }
}
