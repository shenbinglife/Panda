package io.github.shenbinglife.panda

import io.github.shenbinglife.common.base.util.GsonUtil
import io.github.shenbinglife.panda.entity.User
import io.github.shenbinglife.panda.web.Application
import org.hamcrest.CoreMatchers.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(classes = [Application::class])
@RunWith(SpringJUnit4ClassRunner::class)
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private var mvc: MockMvc? = null

    @Test
    @Throws(Exception::class)
    fun getUsers() {
        mvc!!.perform(MockMvcRequestBuilders.get("/users")
                .param("page", "1")
                .param("pageSize", "10")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("content", notNullValue()))
    }

    @Test
    fun create() {
        val user = User()
        user.password = "123456"
        user.name = "Tom"
        user.age = 10

        var createdUser: User? = null

        mvc!!.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(GsonUtil.toJson(user)))
                .andExpect(jsonPath("name", containsString("Tom")))
                .andExpect(jsonPath("age", equalTo(10)))
                .andDo {
                    var str = it.response.contentAsString
                    createdUser = GsonUtil.fromJson(str, User::class.java)
                }
        Assert.assertThat(createdUser!!.name, equalTo("Tom"))
        Assert.assertThat(createdUser!!.password, nullValue())
    }
}