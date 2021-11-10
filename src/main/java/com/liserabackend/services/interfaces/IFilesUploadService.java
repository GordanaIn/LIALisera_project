package com.liserabackend.services.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface IFilesUploadService {
    public void init();

    public void save(MultipartFile file);

    public Resource load(String filename);

    public void uploadImage(Long id, MultipartFile file);

    public void deleteAll();

    public Stream<Path> loadAll() ;
}
