package edu.mum.cs.waa.project.bazargebeyaproject.service;

import edu.mum.cs.waa.project.bazargebeyaproject.domain.Picture;

import java.io.File;
import java.util.List;

public interface PictureService {
    File getFilePath(String modifiedFileName, String path);

    List<Picture> findByProductId(Long productId);
}
