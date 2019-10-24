package edu.mum.cs.waa.project.bajargebeyaproject.service;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Picture;

import java.io.File;
import java.util.List;

public interface PictureService {
    File getFilePath(String modifiedFileName, String path);

    List<Picture> findByProductId(Long productId);
}
