package com.example.data.remote

import com.example.data.BuildConfig

/**
 * Created by TC on 13/10/2022.
 */

const val SHAZAM_DOMAIN_API = "https://${BuildConfig.X_RapidAPI_Host}"

const val API_GET_WORLD_CHART = "$SHAZAM_DOMAIN_API/v1/charts/world"
const val API_GET_WORLD_CHART_BY_GENRE = "$SHAZAM_DOMAIN_API/v1/charts/genre-world"
const val API_GET_TRACK_DETAIL = "$SHAZAM_DOMAIN_API/v1/tracks/details"
