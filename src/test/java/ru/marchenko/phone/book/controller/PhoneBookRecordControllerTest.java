package ru.marchenko.phone.book.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.marchenko.phone.book.model.PhoneBookRecord;
import ru.marchenko.phone.book.service.PhoneBookRecordService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Created by Vladislav Marchenko on 02.04.2021
 */
@WebMvcTest(PhoneBookRecordController.class)
class PhoneBookRecordControllerTest {

    private final List<PhoneBookRecord> PHONE_BOOK_RECORDS = Arrays.asList(
            new PhoneBookRecord(1L, "title1", "num1"),
            new PhoneBookRecord(2L, "title2", "num2"),
            new PhoneBookRecord(1L, "title3", "num3")
    );
    @Autowired
    private MockMvc mvc;
    @MockBean
    private PhoneBookRecordService phoneBookRecordService;

    @Test
    void getAllUsersWhenStatusIsOk() throws Exception {
        doReturn(PHONE_BOOK_RECORDS).when(phoneBookRecordService).getAll();

        mvc.perform(MockMvcRequestBuilders
                .get("/api/records")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is(PHONE_BOOK_RECORDS.get(0).getTitle())));

    }

    @Test
    void getAllUsersWhenStatusIsNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/records")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getUserByIdWhenStatusIsOk() throws Exception {
        doReturn(PHONE_BOOK_RECORDS.get(0)).when(phoneBookRecordService).getById(1L);

        mvc.perform(MockMvcRequestBuilders
                .get("/api/records/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(PHONE_BOOK_RECORDS.get(0).getTitle())));
    }

    @Test
    void getUserByIdWhenStatusIsNotFound() throws Exception {
        doReturn(null).when(phoneBookRecordService).getById(1L);

        mvc.perform(MockMvcRequestBuilders
                .get("/api/records/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void saveUserWhenStatusIsCreated() throws Exception {
        doReturn(PHONE_BOOK_RECORDS.get(0)).when(phoneBookRecordService).save(PHONE_BOOK_RECORDS.get(0));

        mvc.perform(MockMvcRequestBuilders
                .post("/api/records")
                .content(new ObjectMapper().writeValueAsString(PHONE_BOOK_RECORDS.get(0)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is(PHONE_BOOK_RECORDS.get(0).getTitle())));
    }

    @Test
    void saveUserWhenStatusIsBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/records")
                .content(new ObjectMapper().writeValueAsString(null))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void saveUserWhenStatusIsInternalServerError() throws Exception {
        doReturn(null).when(phoneBookRecordService).save(PHONE_BOOK_RECORDS.get(0));

        mvc.perform(MockMvcRequestBuilders
                .post("/api/records")
                .content(new ObjectMapper().writeValueAsString(PHONE_BOOK_RECORDS.get(0)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    void updateUserWhenStatusIsBadRequest() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .put("/api/records")
                .content(new ObjectMapper().writeValueAsString(null))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateUserIsInternalServerError() throws Exception {
        doReturn(null).when(phoneBookRecordService).update(any());
        doReturn(PHONE_BOOK_RECORDS.get(0)).when(phoneBookRecordService).getById(any());

        mvc.perform(MockMvcRequestBuilders
                .put("/api/records")
                .content(new ObjectMapper().writeValueAsString(PHONE_BOOK_RECORDS.get(0)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    void updateUserWhenStatusIsNotFound() throws Exception {
        doReturn(null).when(phoneBookRecordService).getById(any());

        mvc.perform(MockMvcRequestBuilders
                .put("/api/records")
                .content(new ObjectMapper().writeValueAsString(PHONE_BOOK_RECORDS.get(0)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    void updateUserWhenStatusIsOk() throws Exception {
        doReturn(PHONE_BOOK_RECORDS.get(0)).when(phoneBookRecordService).update(any());
        doReturn(PHONE_BOOK_RECORDS.get(0)).when(phoneBookRecordService).getById(any());

        mvc.perform(MockMvcRequestBuilders
                .put("/api/records")
                .content(new ObjectMapper().writeValueAsString(PHONE_BOOK_RECORDS.get(0)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(PHONE_BOOK_RECORDS.get(0).getTitle())));
    }

    @Test
    void deleteUserByIdWhenStatusIsNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/api/records/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteUserByIdWhenStatusIsOk() throws Exception {
        doReturn(PHONE_BOOK_RECORDS.get(0)).when(phoneBookRecordService).getById(any());
        mvc.perform(MockMvcRequestBuilders
                .delete("/api/records/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void getAllRecordsByOwnerIdWhenStatusIsOk() throws Exception {
        doReturn(PHONE_BOOK_RECORDS).when(phoneBookRecordService).getAllRecordsByOwnerId(any());

        mvc.perform(MockMvcRequestBuilders
                .get("/api/records/ownerId")
                .param("ownerId", String.valueOf(1L))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAllRecordsByOwnerIdWhenStatusIsNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/records/ownerId")
                .param("ownerId", String.valueOf(1L))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllRecordsByOwnerIdWhenStatusIsBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/records/ownerId")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAllRecordsByOwnerIdAndTitleWhenStatusIsOk() throws Exception {
        doReturn(PHONE_BOOK_RECORDS).when(phoneBookRecordService).getAllRecordsByOwnerIdAndTitle(any(), any());

        mvc.perform(MockMvcRequestBuilders
                .get("/api/records/ownerIdAndTitle")
                .param("ownerId", String.valueOf(1L))
                .param("title", "title1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAllRecordsByOwnerIdAndTitleWhenStatusIsNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/records/ownerIdAndTitle")
                .param("ownerId", String.valueOf(1L))
                .param("title", "title1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllRecordsByOwnerIdAndTitleWhenStatusIsBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/records/ownerIdAndTitle")
                .param("title", "title1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAllRecordsByOwnerIdAndPhoneNumberWhenStatusIsOk() throws Exception {
        doReturn(PHONE_BOOK_RECORDS).when(phoneBookRecordService).getAllRecordsByOwnerIdAndPhoneNumber(any(), any());

        mvc.perform(MockMvcRequestBuilders
                .get("/api/records/ownerIdAndPhone")
                .param("ownerId", String.valueOf(1L))
                .param("phoneNumber", "num1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAllRecordsByOwnerIdAndPhoneNumberWhenStatusIsNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/records/ownerIdAndPhone")
                .param("ownerId", String.valueOf(1L))
                .param("phoneNumber", "num1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllRecordsByOwnerIdAndPhoneNumberWhenStatusIsBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/records/ownerIdAndPhone")
                .param("ownerId", String.valueOf(1L))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}