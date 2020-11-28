package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.dto.file.AddFileRequest;
import com.ssoggong.stonemanager_server.entity.File;
import com.ssoggong.stonemanager_server.entity.Task;
import com.ssoggong.stonemanager_server.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    @Transactional
    public void saveFile(File file) { fileRepository.save(file);}

    @Transactional
    public void createFile(AddFileRequest addFileRequest, Task task){
        File file = File.builder()
                .task(task)
                .name(addFileRequest.getFileName())
                .uri(addFileRequest.getFileUri())
                .build();
        saveFile(file);
    }

    @Transactional
    public void deleteFile(Long fileId){
        fileRepository.deleteById(fileId);
    }
}
