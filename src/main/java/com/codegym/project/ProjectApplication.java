package com.codegym.project;

import com.codegym.project.formatter.ImageFormatter;
import com.codegym.project.service.fileUpload.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

@SpringBootApplication
public class ProjectApplication{
    @Resource
    FilesStorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

//    Khởi tạo và xóa storage
//    @Override
//    public void run(String... args) throws Exception {
//        storageService.deleteAll();
//        storageService.init();
//    }

    @Configuration
    static class MyConfig extends WebMvcConfigurerAdapter {
        @Override
        public void addFormatters(FormatterRegistry registry) {
            registry.addFormatter(new ImageFormatter());
        }
    }
}
