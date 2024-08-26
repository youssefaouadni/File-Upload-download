package com.reachabl.events.service.file.local;


import com.reachabl.events.service.file.FileContext;
import com.reachabl.events.service.file.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class DefaultFileService implements FileService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String uploadFolder;

    public DefaultFileService(@Value("${reachabl.files.uploadFolder}") String uploadFolder) {
        this.uploadFolder = uploadFolder;
    }

    @Override
    public String store(MultipartFile file, FileContext context) {
        File parentFolder = new File(uploadFolder);
        if (parentFolder.mkdirs()) logger.info("Directory {} is not found and created", parentFolder.getAbsolutePath());
        File contextFolder = new File(parentFolder, context.getName());
        if (contextFolder.mkdirs())
            logger.info("Directory {} is not found and created", contextFolder.getAbsolutePath());
        String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String newFileName = "%s.%s".formatted(UUID.randomUUID(), fileExtension);

        File newFile = new File(contextFolder, newFileName);
        try {
            Files.copy(file.getInputStream(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "%s/%s".formatted(context.getName(), newFileName);
    }

    @Override
    public Pair<String, byte[]> load(String path) {
        File file = new File(uploadFolder, path);
        try {
            String mime = URLConnection.guessContentTypeFromName(file.getName());
            return Pair.of(mime, Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
