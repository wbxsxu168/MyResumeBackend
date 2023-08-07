package com.sunx.mysprmdbapp1.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunx.mysprmdbapp1.model.ImageRecords;
import com.sunx.mysprmdbapp1.services.ImgRecordsServiceImp;
import com.sunx.mysprmdbapp1.services.SeqIDGenService;
import org.junit.Before;
import org.junit.Test;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests to campaigns controller.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(RestAPIController.class)
public class restapiCtrltest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ImgRecordsServiceImp imgrecsService;

    @MockBean
    private SeqIDGenService sequenceGeneratorService;

    private Page<ImageRecords> imgrecPage;

    private static final String ENDPOINT_IMGRECORDS = "/api/imgrec";

    @Before
    public void setupBeforeEachTest() {
        List<ImageRecords> imgList = new ArrayList<>();
        ImageRecords i1 = new ImageRecords("1", "account 1", "image1 title","Description: Diabetes stage II retinal image", "Diabetes");
        imgList.add(i1);
        imgrecPage = new PageImpl<>(imgList, PageRequest.of(0, 2), imgList.size());
    }

    @Test
    public void shouldReturnImgRecordsPage_StatusOK() throws Exception {
        when(imgrecsService.getAllImageRecords(any())).thenReturn(imgrecPage);

        MvcResult mvcResult = mockMvc.perform(get(ENDPOINT_IMGRECORDS))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value("1"))
                .andReturn();

        assertThat(objectMapper.writeValueAsString(imgrecPage))
                .isEqualToIgnoringWhitespace(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void shouldCreateNewImgRec_StatusCREATED() throws Exception {
    	ImageRecords i = imgrecPage.getContent().get(0);
        when(sequenceGeneratorService.generateSequence(any())).thenReturn(i.getId());
        when(imgrecsService.createImageRecords(any())).thenReturn(i);

        MvcResult mvcResult = mockMvc.perform(post(ENDPOINT_IMGRECORDS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(i)))
                .andExpect(status().isCreated())
                .andReturn();

        assertThat(objectMapper.writeValueAsString(i))
                .isEqualToIgnoringWhitespace(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void shouldNotCreateNewImgRec_StatusBADREQUEST() throws Exception {
    	ImageRecords i = imgrecPage.getContent().get(0);
        i.setName(null);

        MvcResult mvcResult = mockMvc.perform(post(ENDPOINT_IMGRECORDS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(i)))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString()
                .contains("Name is mandatory"));
    }

    // TODO create other unit tests if needed!
}