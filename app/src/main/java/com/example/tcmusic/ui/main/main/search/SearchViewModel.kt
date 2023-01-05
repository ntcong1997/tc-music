package com.example.tcmusic.ui.main.main.search

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.domain.usecase.track.SearchTracksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import com.example.domain.Result

/**
 * Created by TC on 05/01/2023.
 */

@FlowPreview
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchTracksUseCase: SearchTracksUseCase
) : ViewModel() {
    private val _textSearch = MutableStateFlow("")
    val textSearch = _textSearch.asStateFlow()

    val tracks = _textSearch
        .debounce(2000L)
        .distinctUntilChanged()
        .flatMapConcat {
            if (it.length > 1) searchTracksUseCase(it)
            else flow { Result.Error(Exception("not enough character")) }
        }
        .map {
            if (it is Result.Success) it.data
            else PagingData.empty()
        }

    fun search(text: String) {
        _textSearch.value = text
    }
}