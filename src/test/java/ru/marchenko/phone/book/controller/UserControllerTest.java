package ru.marchenko.phone.book.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.marchenko.phone.book.model.User;
import ru.marchenko.phone.book.service.UserService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Created by Vladislav Marchenko on 02.04.2021
 */
@WebMvcTest(UserController.class)
class UserControllerTest {

    private final List<User> USERS = Arrays.asList(
            new User("name1"),
            new User("name2"),
            new User("name3")
    );
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;

    @Test
    void getAllUsersWhenStatusIsOk() throws Exception {
        doReturn(USERS).when(userService).getAll();

        mvc.perform(MockMvcRequestBuilders
                .get("/api/users")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(USERS.get(0).getName())));

    }

    @Test
    void getAllUsersWhenStatusIsNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/users")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getUserByIdWhenStatusIsOk() throws Exception {
        doReturn(USERS.get(0)).when(userService).getById(1L);

        mvc.perform(MockMvcRequestBuilders
                .get("/api/users/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(USERS.get(0).getName())));
    }

    @Test
    void getUserByIdWhenStatusIsNotFound() throws Exception {
        doReturn(null).when(userService).getById(1L);

        mvc.perform(MockMvcRequestBuilders
                .get("/api/users/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void saveUserWhenStatusIsCreated() throws Exception {
        doReturn(USERS.get(0)).when(userService).save(USERS.get(0));

        mvc.perform(MockMvcRequestBuilders
                .post("/api/users")
                .content(new ObjectMapper().writeValueAsString(USERS.get(0)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(USERS.get(0).getName())));
    }

    @Test
    void saveUserWhenStatusIsBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/users")
                .content(new ObjectMapper().writeValueAsString(null))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void saveUserWhenStatusIsInternalServerError() throws Exception {
        doReturn(null).when(userService).save(USERS.get(0));

        mvc.perform(MockMvcRequestBuilders
                .post("/api/users")
                .content(new ObjectMapper().writeValueAsString(USERS.get(0)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    void updateUserWhenStatusIsBadRequest() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .put("/api/users")
                .content(new ObjectMapper().writeValueAsString(null))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateUserIsInternalServerError() throws Exception {
        doReturn(null).when(userService).update(any());
        doReturn(USERS.get(0)).when(userService).getById(any());

        mvc.perform(MockMvcRequestBuilders
                .put("/api/users")
                .content(new ObjectMapper().writeValueAsString(USERS.get(0)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    void updateUserWhenStatusIsNotFound() throws Exception {
        doReturn(null).when(userService).getById(any());

        mvc.perform(MockMvcRequestBuilders
                .put("/api/users")
                .content(new ObjectMapper().writeValueAsString(USERS.get(0)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    void updateUserWhenStatusIsOk() throws Exception {
        doReturn(USERS.get(0)).when(userService).update(any());
        doReturn(USERS.get(0)).when(userService).getById(any());

        mvc.perform(MockMvcRequestBuilders
                .put("/api/users")
                .content(new ObjectMapper().writeValueAsString(USERS.get(0)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(USERS.get(0).getName())));
    }

    @Test
    void deleteUserByIdWhenStatusIsNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/api/users/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteUserByIdWhenStatusIsOk() throws Exception {
        doReturn(USERS.get(0)).when(userService).getById(any());
        mvc.perform(MockMvcRequestBuilders
                .delete("/api/users/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getUserByNameWhenStatusIsBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/users/name")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getUserByNameWhenStatusIsOk() throws Exception {
        doReturn(USERS).when(userService).getByName(anyString());

        mvc.perform(MockMvcRequestBuilders
                .get("/api/users/name")
                .param("name", "name")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getUserByNameWhenStatusIsNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/users/name")
                .param("name", "name1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}