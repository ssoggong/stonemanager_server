package com.ssoggong.stonemanager_server.service;

import com.ssoggong.stonemanager_server.entity.File;
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

}
