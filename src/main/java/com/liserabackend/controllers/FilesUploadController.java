package com.liserabackend.controllers;


import com.liserabackend.dto.MessageDTO;
import com.liserabackend.dto.FileInfoDTO;
import com.liserabackend.services.interfaces.IFilesUploadService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/uploadFiles")
public class FilesUploadController {

    @Autowired
    IFilesUploadService iFilesUploadService;


    @PostMapping(value = "/uploadfile")
    public ResponseEntity<MessageDTO> uploadFile(@RequestParam("file")MultipartFile file){
        String message = "";
        try{
            iFilesUploadService.save(file);

            message = "Uploaded teh file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new MessageDTO(message));
        }catch (Exception e){
            e.printStackTrace();
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageDTO(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfoDTO>> getListFiles(){
        List<FileInfoDTO> fileInfoDTOList = iFilesUploadService.loadAll().map(path ->{
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesUploadController.class, "getFile", path.getFileName().toString()).build().toString();
            return new FileInfoDTO(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfoDTOList);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFileName(@PathVariable String filename) {
        Resource file = iFilesUploadService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
