package com.example.exportfile.controller;

import com.example.exportfile.entity.Person;
import com.example.exportfile.factory.ExportfileReportPerson;
import com.example.exportfile.factory.UploadFileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/export")
public class ExportController {


    @Autowired
    private ExportfileReportPerson exportfileReportPerson;


    @GetMapping("/getFilePdf")
    public ResponseEntity<?> getFilePdf(){
        Person person= new Person(1L, "Nguyễn Văn Đồng", LocalDate.of(1997, 2, 22)
                ,"Quan Hoa, Cầu Giấy, Hà Nội","Mỹ Sơn, Đ Lương, Nghệ An","Msb","Dev");
        UploadFileRequest uploadFileRequest=exportfileReportPerson.exportPdfFile(person);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "student.pdf" + "\"")
                .body(uploadFileRequest.getData());
    }


}
