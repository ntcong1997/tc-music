package com.example.tcmusic.ui.main.trackdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Track
import com.example.domain.usecase.track.GetTrackDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.domain.Result

/**
 * Created by TC on 01/12/2022.
 */

@HiltViewModel
class TrackDetailViewModel @Inject constructor(
    private val getTrackDetailUseCase: GetTrackDetailUseCase
) : ViewModel() {
    private val _track = Channel<Track>(Channel.CONFLATED)
    val track = _track.receiveAsFlow()

    fun getTrackDetail(trackId: String?) {
        viewModelScope.launch {
            val resultGetTrackDetail = getTrackDetailUseCase(trackId)
            if (resultGetTrackDetail is Result.Success) {
                _track.trySend(resultGetTrackDetail.data)
            }
        }
    }
}