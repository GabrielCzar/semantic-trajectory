package com.gabrielczar.semantic.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.*;

@Service
public class StorageService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final Path rootLocation;

    @Autowired
    public StorageService(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") String root) {
        this.rootLocation = init(root);
    }

    public Path init(String root) {
        if (!Files.exists(Paths.get(root), LinkOption.NOFOLLOW_LINKS)) {
            try {
                Files.createDirectories(Paths.get(root));
            } catch (IOException e) {
                LOGGER.error("Error to create directory => ", e);
            }
        }
        return Paths.get(root);
    }

    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                LOGGER.error("Could not read file: " + filename);
                throw new RuntimeException("Could not read file: " + filename);
            }
        }
        catch (MalformedURLException e) {
            LOGGER.error("Could not read file: " + filename, e);
            throw new RuntimeException("Could not read file: " + filename, e);
        }
    }

    public String store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                LOGGER.error("Failed to store empty file " + filename);
                throw new RuntimeException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                LOGGER.error("Cannot store file with relative path outside current directory " + filename);
                throw new RuntimeException("Cannot store file with relative path outside current directory " + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);

                return filename;
            }
        }
        catch (IOException e) {
            LOGGER.error("Failed to store file " + filename, e);
            throw new RuntimeException("Failed to store file " + filename, e);
        }
    }
}
