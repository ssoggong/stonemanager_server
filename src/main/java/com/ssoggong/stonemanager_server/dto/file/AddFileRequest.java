package com.ssoggong.stonemanager_server.dto.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddFileRequest {
    private String fileName;
    private String fileUri;
}
