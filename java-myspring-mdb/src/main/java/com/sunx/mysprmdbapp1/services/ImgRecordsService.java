package com.sunx.mysprmdbapp1.services;
import com.sunx.mysprmdbapp1.model.ImageRecords;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * ImageRecordss service interface.
 */
public interface ImgRecordsService {

   Page<ImageRecords> getAllImageRecords(Pageable pageable);

   ImageRecords getImageRecordsById(String id);

   ImageRecords createImageRecords(ImageRecords ImageRecords);

   ImageRecords updateImageRecords(ImageRecords ImageRecords);

   void deleteImageRecordsById(String id);

}