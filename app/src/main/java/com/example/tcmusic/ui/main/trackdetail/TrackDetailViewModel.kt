package com.example.tcmusic.ui.main.trackdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Result
import com.example.domain.usecase.track.GetTrackDetailUseCase
import com.example.model.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Created by TC on 01/12/2022.
 */

@HiltViewModel
class TrackDetailViewModel @Inject constructor(
    private val getTrackDetailUseCase: GetTrackDetailUseCase
) : ViewModel() {
    private val _track = MutableStateFlow<Track?>(null)
    val track = _track.asStateFlow()

    fun getTrackDetail(trackId: String?) {
        viewModelScope.launch {
            val resultGetTrackDetail = getTrackDetailUseCase(trackId)
            if (resultGetTrackDetail is Result.Success) {
                _track.value = resultGetTrackDetail.data
            }
        }
    }
}
