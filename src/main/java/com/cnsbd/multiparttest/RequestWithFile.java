package com.cnsbd.multiparttest;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class RequestWithFile {

    @NotBlank(message = "name is required!")
    private String name;

    private MultipartFile file;

    private List<MultipartFile> files = new ArrayList<>();

    /*private List<Wrapper> fileWithTypes = new ArrayList<>();

    @Data
    public static class Wrapper {
        private MultipartFile file;
        private Long type;
    }*/
}
