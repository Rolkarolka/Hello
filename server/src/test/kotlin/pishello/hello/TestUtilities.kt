package pishello.hello

import org.springframework.core.io.ClassPathResource
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

open class TestUtilities {

    fun correctUserName() = "test_user"

    fun correctAuthRequestData(): String =
        """{ "name": "test_user", "password": "yes, this is a hash" }"""

    fun correctSearchByIdRequestData(): String =
        """[{ "id": 1, "mode": "PUBLIC", "path": "cards/1.jpg", "category": "lawyer", "owner": "test_user" }]"""

    fun incorrectAuthRequestData(): String =
        """{ "name": "test_user", "password": "no, this is not a hash" }"""

    fun correctSetRequestData(id: Int, mode: String?, category: String?): String =
        """{ "id": $id, "mode": "$mode", "category": "$category" }"""

    fun correctEditRequestData(id: Int, company: String?, name: String?, phone: String?, email: String?, category: String?, mode: String?): String =
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

    fun setCard(mockMvc: MockMvc, id: Int, mode: String?, category: String?) {
        mockMvc.perform(MockMvcRequestBuilders.post("/card/set")
            .header("Content-Type", "application/json")
            .content(correctSetRequestData(id, mode, category)))
    }

    fun editCard(mockMvc: MockMvc, id: Int, company: String?, name: String?, phone: String?, email: String?, category: String?, mode: String?) {
        mockMvc.perform(MockMvcRequestBuilders.post("/card/changedata")
            .header("Content-Type", "application/json")
            .content(correctEditRequestData(id, company, name, phone, email, category, mode)))
    }
}