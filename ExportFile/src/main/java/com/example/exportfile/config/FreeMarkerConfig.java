package com.example.exportfile.config;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.Locale;

//@Configuration
public class FreeMarkerConfig {


//    @Bean
//    public freemarker.template.Configuration configuration(){
//
//        freemarker.template.Configuration cfg = new freemarker.template.Configuration();
//        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
//        FileTemplateLoader templateLoader = new FileTemplateLoader(new File());
//        cfg.setTemplateLoader(templateLoader);
//        cfg.setDefaultEncoding("UTF-8");
//        cfg.setLocale(Locale.US);
//        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
//        Template template = cfg.getTemplate(drbTemplateFileName);
//    }

}
