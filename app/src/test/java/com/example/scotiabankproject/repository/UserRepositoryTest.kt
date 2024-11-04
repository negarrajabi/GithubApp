package com.example.scotiabankproject.repository
import com.example.scotiabankproject.model.User
import com.example.scotiabankproject.network.GithubApi
import com.example.scotiabankproject.repository.UserRepository
import com.example.scotiabankproject.network.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class UserRepositoryTest {

    private lateinit var repository: UserRepository
    private lateinit var api: GithubApi

    @Before
    fun setup() {
        api = mockk()
        repository = UserRepository(api)
    }

    @Test
    fun `when API call is successful then getUser returns Success`() = runTest {
        val mockUser = User(name = "TestUser", avatar_url = "https://example.com/avatar")
        coEvery { api.getUser("testuser") } returns Response.success(mockUser)
        val result = repository.getUser("testuser")
        assert(result is Result.Success)
        assertEquals(mockUser, (result as Result.Success).data)
    }

    @Test
    fun `when API call returns 404 then getUser returns Error `() = runTest {
        coEvery { api.getUser("unknownuser") } returns Response.error(404, ResponseBody.create(null, ""))
        val result = repository.getUser("unknownuser")
        assert(result is Result.Error)
    }
}
