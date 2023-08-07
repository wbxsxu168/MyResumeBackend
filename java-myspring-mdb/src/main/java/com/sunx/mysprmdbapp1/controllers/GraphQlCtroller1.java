package com.sunx.mysprmdbapp1.controllers;

import com.sunx.mysprmdbapp1.model.ImageRecords;
import com.sunx.mysprmdbapp1.services.ImgRecordsService;
import com.sunx.mysprmdbapp1.services.SeqIDGenService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

/**
 * GraphQL controller for ImageRecords.
 */
@Controller 
public class GraphQlCtroller1 {

    private static final Logger LOGGER = LogManager.getLogger(GraphQlCtroller1.class);

    @Autowired
    private SeqIDGenService seqGenService;
    
    @Autowired
    private ImgRecordsService imgrecordsService;
     
    @QueryMapping
    public Page<ImageRecords> getAllImageRecords(@Argument int page, @Argument int size) {
        LOGGER.info("Entering into graphql endpoint to get all ImageRecords");
        PageRequest pageRequest = PageRequest.of(page, size);
        return imgrecordsService.getAllImageRecords(pageRequest);
    }
  
    @QueryMapping
    public ImageRecords getImageRecordsById(@Argument String imgId) {
    	System.out.println("Entering into graphql endpoint to get an ImageRecord by id!!");
        LOGGER.info("Entering into graphql endpoint to get an ImageRecord by id");
        return imgrecordsService.getImageRecordsById(imgId);
    }

    @MutationMapping
    public ImageRecords createImageRecords(@Argument ImageRecords imgrec) {
        LOGGER.info("Entering into restapi endpoint to create a new ImageRecord ");
        imgrec.setId(seqGenService.generateSequence(ImageRecords.SEQUENCE_NAME));
        return imgrecordsService.createImageRecords(imgrec);
    } 
  
    @MutationMapping
    public ImageRecords updateImageRecords(@Argument  ImageRecords imgrecords,  @Argument  String imageId) {
        LOGGER.info("Entering into graphql endpoint to update ImageRecords");
        imgrecords.setId(imageId);
        return imgrecordsService.updateImageRecords(imgrecords);
    }
    
    @MutationMapping
    public void deleteImageRecords(@Argument  String imagerecId) {
        LOGGER.info("Entering into graphql endpoint to delete the selected ImageRecord! ");
        imgrecordsService.deleteImageRecordsById(imagerecId);
    }

}
