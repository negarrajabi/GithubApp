package com.example.scotiabankproject.viewmodel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.scotiabankproject.model.User
import com.example.scotiabankproject.repository.UserRepository
import com.example.scotiabankproject.network.Result
import com.example.scotiabankproject.viewmodel.UserViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.assertEquals

@ExperimentalCoroutinesApi
class UserViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: UserViewModel
    private lateinit var repository: UserRepository

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = UserViewModel(repository)

        // Default behavior for getUserRepos
        coEvery { repository.getUserRepos(any()) } returns Result.Success(emptyList())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @Test
    fun `fetchUserData updates userData with Success when repository call is successful`() = runTest {
        val mockUser = User(name = "TestUser", avatar_url = "https://example.com/avatar")
        coEvery { repository.getUser("testuser") } returns Result.Success(mockUser)
        coEvery { repository.getUserRepos("testuser") } returns Result.Success(emptyList())

        viewModel.fetchUserData("testuser")
        advanceUntilIdle()

        assert(viewModel.userData.first() is Result.Success)
        assertEquals(mockUser, (viewModel.userData.first() as Result.Success).data)
    }

    @Test
    fun `fetchUserData updates userData with Error when repository call fails`() = runTest {
        coEvery { repository.getUser("unknownuser") } returns Result.Error("User not found")

        viewModel.fetchUserData("unknownuser")
        advanceUntilIdle()

        assert(viewModel.userData.first() is Result.Error)
        assertEquals("User not found", (viewModel.userData.first() as Result.Error).message)
    }

    @Test
    fun `fetchUserData updates reposData with Error when repository repos call fails`() = runTest {
        val mockUser = User(name = "TestUser", avatar_url = "https://example.com/avatar")
        coEvery { repository.getUser("testuser") } returns Result.Success(mockUser)
        coEvery { repository.getUserRepos("testuser") } returns Result.Error("Repository fetch error")

        viewModel.fetchUserData("testuser")
        advanceUntilIdle()

        assert(viewModel.reposData.first() is Result.Error)
        assertEquals("Repository fetch error", (viewModel.reposData.first() as Result.Error).message)
    }
}
