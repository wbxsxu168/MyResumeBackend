package com.sunx.mysprmdbapp1.services;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.sunx.mysprmdbapp1.errors.ApiException;
import com.sunx.mysprmdbapp1.errors.APIErrorType;
import com.sunx.mysprmdbapp1.model.ImageRecords;
import com.sunx.mysprmdbapp1.repository.ImgRecordsRepository;

/**
 * ImageRecords service implementation.
 */
@Service
public class ImgRecordsServiceImp implements ImgRecordsService {

    private static final Logger LOGGER = LogManager.getLogger(ImgRecordsServiceImp.class);

    @Autowired
    private ImgRecordsRepository imgRecordsRepositoryDAO;

    @Override
    public Page<ImageRecords> getAllImageRecords(Pageable pageable) {
        LOGGER.debug("Getting all image records with page size: " + pageable.getPageSize() +
                ", page number: " + pageable.getPageNumber()+ ", sort: " + pageable.getSort().toString());
        return imgRecordsRepositoryDAO.findAll(pageable);
    }

    @Override
    public ImageRecords getImageRecordsById(String id) {
        LOGGER.debug("Getting the ImageRecords by id: " + id);
        return imgRecordsRepositoryDAO.findById(id).orElseThrow(() ->  {
           LOGGER.warn("ImageRecord id: " + id + " can not be found!");
           return new ApiException(APIErrorType.IMGRECORD_NOT_FOUND, id);
       });
    }

    @Override
    public ImageRecords createImageRecords(ImageRecords imgrec) {
        LOGGER.debug("Creating a new ImageRecord with id: " + imgrec.getId());
        return imgRecordsRepositoryDAO.save(imgrec);
    }

    @Override
    public ImageRecords updateImageRecords(ImageRecords imgrec) {
        String imgId = imgrec.getId();
        if (imgRecordsRepositoryDAO.existsById(imgId)) {
            LOGGER.debug("Updating image record with id: " + imgId);
            return imgRecordsRepositoryDAO.save(imgrec);
        } else {
            LOGGER.warn("image record id: " + imgId + " can not been found!");
            throw new ApiException(APIErrorType.IMGRECORD_NOT_FOUND, imgId);
        }
    }

    @Override
    public void deleteImageRecordsById(String id) {
        LOGGER.debug("Deleting ImageRecord with id: " + id);
        imgRecordsRepositoryDAO.delete(getImageRecordsById(id));
    }

}