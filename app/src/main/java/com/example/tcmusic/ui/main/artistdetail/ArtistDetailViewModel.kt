package com.example.tcmusic.ui.main.artistdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.artist.GetArtistDetailUseCase
import com.example.model.Artist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.domain.Result

/**
 * Created by TC on 09/01/2023.
 */

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(
    private val getArtistDetailUseCase: GetArtistDetailUseCase
) : ViewModel() {
    private val _artist = MutableStateFlow<Artist?>(null)
    val artist = _artist.asStateFlow()

    fun getArtistDetail(artistId: String?) {
        viewModelScope.launch {
            val resultGetArtistDetail = getArtistDetailUseCase(artistId)
            if (resultGetArtistDetail is Result.Success) {
                _artist.value = resultGetArtistDetail.data
            }
        }
    }
}