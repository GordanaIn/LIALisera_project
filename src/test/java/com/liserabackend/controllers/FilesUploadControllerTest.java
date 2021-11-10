package com.liserabackend.controllers;/*
package com.example.lialiserabackend.filesUploads;


import com.example.lialiserabackend.backendForFrontend.controllers.logged_in.FilesUploadController;
import com.example.lialiserabackend.backendForFrontend.dto.responses.MessageDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integrationtest")
public class FilesUploadControllerTest {

    @Autowired
    FilesUploadController filesUploadController;



    @Test
    @Transactional
    void test_uploadFile (){
        //Give

        MultipartFile file = new MockMultipartFile("fileThatDoesNotExists.txt",
                "fileThatDoesNotExists.txt",
                "text/plain",
                "This is a dummy file content".getBytes(StandardCharsets.UTF_8));

        //When
        ResponseEntity<MessageDTO> responseMessageResponseEntity = filesUploadController.uploadFile(file);

        //Then
        assertEquals(200, responseMessageResponseEntity.getStatusCodeValue());
    }



}
*/
