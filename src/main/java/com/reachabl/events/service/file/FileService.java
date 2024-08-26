package com.reachabl.events.service.file;

import org.springframework.data.util.Pair;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String store(MultipartFile file, FileContext context);

    Pair<String, byte[]> load(String path);
}
