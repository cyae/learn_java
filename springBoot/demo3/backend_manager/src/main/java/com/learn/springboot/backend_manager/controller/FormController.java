package com.learn.springboot.backend_manager.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

/**
 * FormController
 */
@Controller
@Slf4j
public class FormController {

    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username") String username,
                         @RequestPart("avatar") MultipartFile avatar,
                         @RequestPart("photos") MultipartFile[] photos) throws IllegalStateException, IOException {
        
        log.info("上传信息: email={}, username={}, avatar={}, photos={}",
                                   email, username, avatar.getSize(), photos.length);
        if (!avatar.isEmpty()) {
            String originalFilename = avatar.getOriginalFilename();
            avatar.transferTo(new File("ssdf" + originalFilename));
        }
        if (photos != null && photos.length > 0) {
            for (MultipartFile photo : photos) {
                if (!photo.isEmpty()) { 
                    String originalFilename = photo.getOriginalFilename();
                    photo.transferTo(new File("csdcds" + originalFilename));
                }
            }
        }
        return "index";
    }

    @GetMapping("/form_layouts")
    public String formLayout() {
        return "form/form_layouts";
    }

    @GetMapping("/form_advanced_components")
    public String formAdvancedComponent() {
        return "form/form_advanced_components";
    }

    @GetMapping("/form_wizard")
    public String formWizard() {
        return "form/form_wizard";
    }

    @GetMapping("/form_validation")
    public String formValidation() {
        return "form/form_validation";
    }

    @GetMapping("/editors")
    public String formEditor() {
        return "form/editors";
    }

    @GetMapping("/inline_editors")
    public String formInlineEditor() {
        return "form/inline_editors";
    }

    @GetMapping("/pickers")
    public String formPicker() {
        return "form/pickers";
    }

    @GetMapping("/dropzone")
    public String formDropzone() {
        return "form/dropzone";
    }
}