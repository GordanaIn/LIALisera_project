package com.liserabackend.exceptions;

import com.liserabackend.dto.MessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class FilesUploadExceptionAdvice  extends ResponseEntityExceptionHandler {

        @ExceptionHandler(MaxUploadSizeExceededException.class)
        public ResponseEntity<MessageDTO> handleMaxSizeException(MaxUploadSizeExceededException exc) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageDTO("File too large!"));
        }
}
