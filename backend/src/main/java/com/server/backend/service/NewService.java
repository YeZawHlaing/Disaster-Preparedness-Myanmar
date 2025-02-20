package com.server.backend.service;

import com.server.backend.entity.New;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewService {


      New createNew(New news, MultipartFile file);

    List<New> getAllNew();

    void deleteNew(Long id);
    New updateNew(New news, long id);
}
