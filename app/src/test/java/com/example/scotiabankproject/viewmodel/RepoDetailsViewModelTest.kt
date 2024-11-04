package com.example.scotiabankproject.viewmodel
import com.example.scotiabankproject.model.Repo
import com.example.scotiabankproject.viewmodel.RepoDetailsViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RepoDetailsViewModelTest {

    private lateinit var viewModel: RepoDetailsViewModel
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)
    private val mockRepo = Repo(name = "TestRepo", forks = 100, stargazers_count = 50, description = "", updated_at = "20241010")

    @Before
    fun setup() {
        viewModel = RepoDetailsViewModel()
    }

    @Test
    fun `setRepoDetail sets repoDetail correctly`() = testScope.runTest {

        viewModel.setRepoDetail(mockRepo, totalForks = 2000)

        val repoDetail = viewModel.repoDetail.first()
        assertEquals(mockRepo, repoDetail)
    }

    @Test
    fun `setRepoDetail sets showForkBadge to true when totalForks is greater than 5000`() = testScope.runTest {
        viewModel.setRepoDetail(mockRepo, totalForks = 6000)

        val showForkBadge = viewModel.showForkBadge.first()
        assertTrue(showForkBadge)
    }

    @Test
    fun `setRepoDetail sets showForkBadge to false when totalForks is 5000 or less`() = testScope.runTest {
        viewModel.setRepoDetail(mockRepo, totalForks = 5000)

        val showForkBadge = viewModel.showForkBadge.first()
        assertFalse(showForkBadge)
    }
}


