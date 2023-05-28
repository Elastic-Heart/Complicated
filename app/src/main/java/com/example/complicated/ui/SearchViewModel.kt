package com.example.complicated.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.complicated.domain.AvrilSongsState
import com.example.complicated.domain.FilterAvrilSongsUseCase
import com.example.complicated.domain.GetAvrilSongsUseCase
import com.martini.complicated.mylibrary.CoroutineDispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SearchViewModel constructor(
    private val getAvrilSongsUseCase: GetAvrilSongsUseCase,
    private val filterAvrilSongsUseCase: FilterAvrilSongsUseCase,
    private val dispatcher: CoroutineDispatchers,
) : ViewModel() {

    private val debounceDelay = 500L

    private val _state = MutableStateFlow<AvrilSongsState>(AvrilSongsState.Loading)

    val state: StateFlow<AvrilSongsState> = _state.asStateFlow()

    private var job: Job? = null

    init {
        getAvrilSongsUseCase()
            .flowOn(dispatcher.io)
            .onEach { _state.value = it }
            .launchIn(viewModelScope)
    }

    fun refresh() {
        getAvrilSongsUseCase()
            .flowOn(dispatcher.io)
            .onEach { _state.value = it }
            .launchIn(viewModelScope)
    }

    operator fun invoke(text: CharSequence?) {
        job?.cancel()
        job = null

        job = viewModelScope.launch(dispatcher.io) {
            delay(debounceDelay)

            filterAvrilSongsUseCase(text)
                .flowOn(dispatcher.io)
                .onEach { _state.value = it }
                .launchIn(this)
        }
    }

//    companion object {
//        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(
//                modelClass: Class<T>,
//            ): T {
//
//                val source = AvrilLavigneIncredibleDataSource(
//                    avrilService = RetrofitBuilder.avrilService
//                )
//
//                return SearchViewModel(
//                    dispatcher = AppCoroutineDispatchers(),
//                    getAvrilSongsUseCase = GetAvrilSongsUseCase(
//                        avrilDataSource = source
//                    ),
//                    filterAvrilSongsUseCase = FilterAvrilSongsUseCase(
//                        avrilDataSource = source,
//                        condoUnitFilter = CondoNameFilter()
//                    )
//                ) as T
//            }
//        }
//    }
}