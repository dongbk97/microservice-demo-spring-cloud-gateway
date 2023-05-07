package com.example.exportfile.factory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UploadFileRequest {

    private String filename;
    private byte[] data;
}
