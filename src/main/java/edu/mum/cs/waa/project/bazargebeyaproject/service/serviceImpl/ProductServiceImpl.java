package edu.mum.cs.waa.project.bazargebeyaproject.service.serviceImpl;

import edu.mum.cs.waa.project.bazargebeyaproject.Repository.PictureRepo;
import edu.mum.cs.waa.project.bazargebeyaproject.Repository.ProductOrderRepo;
import edu.mum.cs.waa.project.bazargebeyaproject.Repository.ProductRepo;
import edu.mum.cs.waa.project.bazargebeyaproject.domain.Picture;
import edu.mum.cs.waa.project.bazargebeyaproject.domain.Product;
import edu.mum.cs.waa.project.bazargebeyaproject.domain.ProductOrder;
import edu.mum.cs.waa.project.bazargebeyaproject.service.PictureService;
import edu.mum.cs.waa.project.bazargebeyaproject.service.ProductService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    PictureRepo pictureRepo;

    @Autowired
    PictureService pictureService;

    @Autowired
    ProductOrderRepo productOrderRepo;

    @Override
    public Product save(Product product){

        Product savedProduct =  productRepo.save(product);

        if(savedProduct!=null && product.getPictures()!=null && product.getPictures().size() > 0){
            for(MultipartFile file : product.getPictures()){
                String currentFileName = file.getOriginalFilename();
                String fileName = FilenameUtils.getBaseName(currentFileName) + "_" + System.currentTimeMillis()+"."+FilenameUtils.getExtension(currentFileName);
                File storeFile = pictureService.getFilePath(fileName, "images");

                if(storeFile!=null){
                    try {
                        FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }

                Picture pictures = new Picture();

                pictures.setFileExtension(FilenameUtils.getExtension(fileName));
                pictures.setFileName(fileName);
                pictures.setProduct(savedProduct);

                pictureRepo.save(pictures);
            }
        }

        return savedProduct;
    }

    @Override
    public ProductOrder saveOrder(ProductOrder po){
        return productOrderRepo.save(po);
    }

    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public Product findById(Long id) {
        return productRepo.findById(id).get();
    }

}
