package com.app.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.app.SpringBootMvcTestApplication;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = SpringBootMvcTestApplication.class)
public class HelloControllerTest {

    //mockMvc TomcatサーバへデプロイすることなくHttpリクエスト・レスポンスを扱うためのMockオブジェクト
    @Autowired
    private MockMvc mockMvc;

    @Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
	}

    // getリクエストでviewを指定し、httpステータスでリクエストの成否を判定
    @Test
    public void init処理が走って200が返る() throws Exception {
        // andDo(print())でリクエスト・レスポンスを表示
        this.mockMvc.perform(MockMvcRequestBuilders.get("/hello/init")).andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void init処理でモデルのメッセージにhelloが渡される() throws Exception {
        this.mockMvc.perform(get("/hello/init"))
            .andExpect(model().attribute("message", "hello!"));
    }

    @Test
    public void init処理でモデルへユーザEntityが格納される() throws Exception {
        this.mockMvc.perform(get("/hello/init"))
            .andExpect(model()
                    .attribute("user", hasProperty("userName", is("test0")))
                    );
    }

    @Test
    public void init処理でモデルのフォームへユーザリストが格納される() throws Exception {
        this.mockMvc.perform(get("/hello/init"))
            .andExpect(model().attribute("dbForm", hasProperty("userList", hasItem(hasProperty("userName", is("test1"))))));
    }

}
