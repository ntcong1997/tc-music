package com.example.data.remote

import com.example.data.BuildConfig

/**
 * Created by TC on 13/10/2022.
 */

const val SHAZAM_DOMAIN_API = "https://${BuildConfig.X_RapidAPI_Host}"

const val API_GET_WORLD_CHART = "${SHAZAM_DOMAIN_API}/v1/charts/world"