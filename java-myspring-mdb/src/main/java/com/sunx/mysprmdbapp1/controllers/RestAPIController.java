package com.sunx.mysprmdbapp1.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.sunx.mysprmdbapp1.model.ImageRecords;
import com.sunx.mysprmdbapp1.services.ImgRecordsService;
import com.sunx.mysprmdbapp1.services.SeqIDGenService;
import static com.sunx.mysprmdbapp1.utils.ApiConstCfg.IMGRECORDS_PAGE_DEFAULT;
import static com.sunx.mysprmdbapp1.utils.ApiConstCfg.IMGRECORDS_SIZE_DEFAULT;

/**
 * RESTAPI controller for ImageRecords.
 */
@RestController
@Tag(name = "Rest API CRUD with MongoDB Demo", description = "REST APIs with MongoDB")
@RequestMapping("/api/imgrec") // from /api/imgrec to /api/v1/imgrec
public class RestAPIController {

	private static final Logger LOGGER = LogManager.getLogger(RestAPIController.class);
	@Autowired
	private SeqIDGenService seqGenService;
	@Autowired
	private ImgRecordsService imgrecordsService;

	@Operation(summary = "Create a new ImageRecord", tags = { "image records", "post" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = ImageRecords.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public ImageRecords createImageRecords(@Valid @RequestBody ImageRecords imgrec) {
		LOGGER.info("Entering into restapi endpoint to create a new ImageRecord ");
		imgrec.setId(seqGenService.generateSequence(ImageRecords.SEQUENCE_NAME));
		return imgrecordsService.createImageRecords(imgrec);
	}

	@Operation(summary = "Get all ImageRecords", tags = { "image records", "get", "filter" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = ImageRecords.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "There are no image records", content = {
					@Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public Page<ImageRecords> getAllImageRecords(
			@RequestParam(value = "page", required = false, defaultValue = IMGRECORDS_PAGE_DEFAULT) int page,
			@RequestParam(value = "size", required = false, defaultValue = IMGRECORDS_SIZE_DEFAULT) int size) {
		LOGGER.info("Entering into restapi endpoint to get all ImageRecords");
		PageRequest pageRequest = PageRequest.of(page, size);
		return imgrecordsService.getAllImageRecords(pageRequest);
	}

	@Operation(summary = "Retrieve an image record by Id", description = "Get an Image Record by specifying its id. The response is an Image record with its id, title, description and dates.", tags = {
			"image records", "get" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = ImageRecords.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping("/{imgrecId}")
	@ResponseStatus(HttpStatus.OK)
	public ImageRecords getImageRecordsById(@PathVariable("imgrecId") String imgId) {
		LOGGER.info("Entering into restapi endpoint to get an ImageRecord by id");
		return imgrecordsService.getImageRecordsById(imgId);
	}

	@Operation(summary = "Update an existing Image Records by Id", tags = { "image records", "put" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = ImageRecords.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
	@PutMapping("/{imgId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ImageRecords updateImageRecords(@Valid @RequestBody ImageRecords imgrecords,
			@PathVariable("imgId") String imageId) {
		LOGGER.info("Entering into restapi endpoint to update ImageRecords");
		imgrecords.setId(imageId);
		return imgrecordsService.updateImageRecords(imgrecords);
	}

	@Operation(summary = "Delete an ImageRecord by Id", tags = { "image records", "delete" })
	@ApiResponses({ @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@DeleteMapping("/{imgId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteImageRecords(@PathVariable("imgId") String imagerecId) {
		LOGGER.info("Entering into restapi endpoint to delete the selected ImageRecord! ");
		imgrecordsService.deleteImageRecordsById(imagerecId);
	}

}