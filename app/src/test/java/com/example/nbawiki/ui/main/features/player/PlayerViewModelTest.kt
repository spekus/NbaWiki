package com.example.nbawiki.ui.main.features.player


class PlayerViewModelTest {

//    @Rule
//    @JvmField
//    val instantExecutorRule = InstantTaskExecutorRule()
//
//    val mockRepository = Mockito.mock(Repository::class.java)
//
//    lateinit var viewModel: PlayerViewModel
//    val platerId = 1
//
//    @Before
//    fun setUp() {
//        MockitoAnnotations.initMocks(this)
//
//        Mockito.`when`(mockRepository.refreshThePlayer(1))
//            .thenReturn(MutableLiveData<Player>(
//                Player()
//            ))
//    }
//
//    @Test
//    fun initializePlayerData_validID_liveDataUpdatedWithPlayer() {
//
//        viewModel =
//            PlayerViewModel(
//                mockRepository
//            )
//        viewModel.initializePlayerData(platerId)
//        viewModel.player.observeForever {}
//
//        assertNotNull(viewModel.player.value)
//    }
//
//    @Test
//    fun initializePlayerData_validID_playerWithSameId() {
//
//        Mockito.`when`(mockRepository.refreshThePlayer(platerId))
//            .thenReturn(MutableLiveData<Player>(
//                Player(id = platerId)
//            ))
//
//        viewModel =
//            PlayerViewModel(
//                mockRepository
//            )
//        viewModel.initializePlayerData(platerId)
//        viewModel.player.observeForever {}
//
//        assertEquals(platerId, viewModel.player.value!!.id)
//    }
//
//    @Test
//    fun initializePlayerData_validId_callRepoOnlyOnce() {
//        viewModel =
//            PlayerViewModel(
//                mockRepository
//            )
//        viewModel.initializePlayerData(platerId)
//
//        Mockito.verify(mockRepository, times(0)).refreshTeams()
//        Mockito.verify(mockRepository, times(0)).refreshTheTeam( ArgumentMatchers.anyInt())
//        Mockito.verify(mockRepository, times(1)).refreshThePlayer( ArgumentMatchers.anyInt())
//    }
}

