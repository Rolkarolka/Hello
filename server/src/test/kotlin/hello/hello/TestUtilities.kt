package hello.hello

import hello.hello.adapters.persistence.database.create
import hello.hello.adapters.persistence.database.drop
import hello.hello.adapters.persistence.database.init
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import org.springframework.core.io.ClassPathResource
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TestUtilities {

    fun correctUserName() = "test_user"

    fun correctAuthRequestData(): String =
        """{ "name": "test_user", "password": "yes, this is a hash" }"""

    fun correctSearchByIdRequestData(): String =
        """[{ "id": 1, "mode": "PUBLIC", "path": "cards/1.jpg", "category": "lawyer", "owner": "test_user" }]"""

    fun incorrectAuthRequestData(): String =
        """{ "name": "test_user", "password": "no, this is not a hash" }"""

    fun correctSetRequestData(id: Int, company: String?, name: String?, phone: String?, email: String?, category: String?, mode: String?): String =
        """{ "id": $id, "company": "$company", "name": "$name", "phone": "$phone", "email": "$email", "category": "$category", "mode": "$mode"}"""


    fun correctPutCardImageData(): MockMultipartFile =
        MockMultipartFile(
            "image", "photos-business-card.jpg",
            "image/jpeg", ClassPathResource("photos-business-card.jpg").file.readBytes()
        )

    fun createUser(mockMvc: MockMvc) {
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
            .header("Content-Type", "application/json")
            .content(correctAuthRequestData()))
    }

    fun createCard(mockMvc: MockMvc) {
        mockMvc.perform(MockMvcRequestBuilders.multipart("/card")
            .file(correctPutCardImageData())
            .param("ownerName", correctUserName()))
    }

    fun setCard(mockMvc: MockMvc, id: Int, company: String? = null, name: String? = null, phone: String? = null, email: String? = null, category: String? = null, mode: String? = null) {
        mockMvc.perform(MockMvcRequestBuilders.post("/card/set")
            .header("Content-Type", "application/json")
            .content(correctSetRequestData(id, company, name, phone, email, category, mode)))
    }

    @Autowired
    var env: Environment? = null

    @BeforeEach
    fun setup() {
        init(env!!)
        create()
    }

    @AfterEach
    fun cleanUp() {
        drop()
    }
}