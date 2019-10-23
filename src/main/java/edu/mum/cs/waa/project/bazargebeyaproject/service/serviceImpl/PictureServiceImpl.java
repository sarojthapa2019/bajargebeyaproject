package edu.mum.cs.waa.project.bazargebeyaproject.service.serviceImpl;

import edu.mum.cs.waa.project.bazargebeyaproject.Repository.PictureRepo;
import edu.mum.cs.waa.project.bazargebeyaproject.domain.Picture;
import edu.mum.cs.waa.project.bazargebeyaproject.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    PictureRepo pictureRepo;

    @Autowired
    ServletContext servletContext;

    @Override
    public File getFilePath(String modifiedFileName, String path) {
        String realPath = servletContext.getRealPath("/"+path+"/");

        boolean exists = new File(servletContext.getRealPath("/"+path+"/")).exists();

        if(!exists){
            new File(servletContext.getRealPath("/"+path+"/")).mkdir();
        }

        String modifiedFilePath = servletContext.getRealPath("/"+path+"/"+ File.separator+modifiedFileName);

        File file = new File(modifiedFilePath);
        return file;
    }

    @Override
    public List<Picture> findByProductId(Long productId) {
        return pictureRepo.findByProductId(productId);
    }
}
