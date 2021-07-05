package com.codegym.project.formatter;

import com.codegym.project.model.Image;
import com.codegym.project.service.image.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

public class ImageFormatter implements Formatter<Image> {
    @Autowired
    private IImageService iImageService;



    @Override
    public Image parse(String text, Locale locale) throws ParseException {
        Optional<Image> imageOptional = iImageService.findById(Long.parseLong(text));
        return imageOptional.orElse(null);
    }

    @Override
    public String print(Image object, Locale locale) {
        return null;
    }
}
