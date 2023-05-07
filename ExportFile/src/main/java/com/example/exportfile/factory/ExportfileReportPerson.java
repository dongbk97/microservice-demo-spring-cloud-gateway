package com.example.exportfile.factory;

import com.example.exportfile.entity.Person;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
@Service
public class ExportfileReportPerson {

    public UploadFileRequest exportPdfFile(Person person) {
        String drbTemplatePath = "../ExportFile/src/main/resources/templates/";
        String drbTemplateFileName = "report.ftl";
        try {
            Configuration cfg = new Configuration();
            cfg.setIncompatibleImprovements(new Version(2, 3, 32));

            FileTemplateLoader templateLoader = new FileTemplateLoader(new File(drbTemplatePath));
            cfg.setTemplateLoader(templateLoader);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setLocale(Locale.US);
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            Template template = cfg.getTemplate(drbTemplateFileName);
            Map<String, Object> input = new HashMap<>();
            input.put("fontFamily", "Roboto-Regular.ttf");
            defaultInput(input, person);
            return outputDrb("export/", drbTemplateFileName, template, input);
        } catch (Exception exception) {
            log.error("Exception occured while processing freeMarker template: {} ", exception.getMessage(), exception);
        }
        return null;
    }

    private UploadFileRequest outputDrb(String drbTemplatePath, String drbTemplateFileName, Template template, Map<String, Object> input) {


        try {
            String outputHtmlPath = "../ExportFile/export/report/";
            File outHtmlPathFolder = new File(outputHtmlPath);
            if (!outHtmlPathFolder.exists()) {
                outHtmlPathFolder.mkdir();
            }
            // save html file
            String fileHtmlName = "fileExport.html";
            String outputHtmlName = outputHtmlPath + fileHtmlName;
            Writer fileWriter = new OutputStreamWriter(new FileOutputStream(outputHtmlName), StandardCharsets.UTF_8);
            template.process(input, fileWriter);
            fileWriter.close();
            // save pdf file
            String filePdfName = outputHtmlPath + "fileExport" + ".pdf";

            File fileUrl = new File(outputHtmlName);
            String uri = fileUrl.toURI().toString();
            log.info("Uri : " + uri);
            OutputStream os = new FileOutputStream(filePdfName);
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withUri(uri);
            builder.toStream(os);
            builder.run();
            os.close();

            InputStream fileInput = new FileInputStream(filePdfName);
            byte[] fileContent = fileInput.readAllBytes();
            UploadFileRequest uploadFileRequest = new UploadFileRequest();
            uploadFileRequest.setFilename("fileExport.pdf");
            uploadFileRequest.setData(fileContent);
            return uploadFileRequest;
        } catch (Exception ex) {
            log.error("Exception occured while processing freeMarker template: {} ", ex.getMessage(), ex);
        }

        return null;
    }

    private void defaultInput(Map<String, Object> input, Person person) {
        input.put("name", person.getName());
        input.put("dayOfBirth", person.getDayOfBirth());
        input.put("address", person.getAddress());
        input.put("nativeLand", person.getNativeLand());
        input.put("workUnit", person.getWorkUnit());
        input.put("job", person.getJob());

    }


}
