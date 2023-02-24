package com.example.tcmusic.feature.network.retrofit.fake

import com.example.tcmusic.core.network.model.*
import com.example.tcmusic.core.network.retrofit.fake.FakeRetrofitShazamNetwork
import kotlin.test.assertEquals
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test

/**
 * Created by TC on 22/02/2023.
 */

class FakeRetrofitShazamNetworkTest {
    private lateinit var fakeRetrofitShazamNetwork: FakeRetrofitShazamNetwork

    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalSerializationApi::class)
    @Before
    fun setup() {
        fakeRetrofitShazamNetwork = FakeRetrofitShazamNetwork(
            ioDispatcher = testDispatcher,
            networkJson = Json {
                ignoreUnknownKeys = true
                explicitNulls = false
            }
        )
    }

    @Test
    fun`Deserialization Of Chart`() = runTest(testDispatcher) {
        assertEquals(
            NetworkTrackV1(
                alias = null,
                layout = "5",
                type = "MUSIC",
                genres = null,
                key = "648859694",
                title = "Flowers",
                subtitle = "Miley Cyrus",
                share = NetworkTrackV1Share(
                    subject = "Flowers - Miley Cyrus",
                    text = "I used Shazam to discover Flowers by Miley Cyrus.",
                    href = "https://www.shazam.com/track/648859694/flowers",
                    image = "https://is1-ssl.mzstatic.com/image/thumb/Music123/v4/9e/80/c7/9e80c757-6994-4338-9e79-b92d5f75f788/196589561725.jpg/400x400cc.jpg",
                    twitter = "I used @Shazam to discover Flowers by Miley Cyrus.",
                    html = "https://www.shazam.com/snippets/email-share/648859694?lang=en&country=GB",
                    avatar = "https://is4-ssl.mzstatic.com/image/thumb/AMCArtistImages123/v4/b0/37/e1/b037e1f1-cf37-419e-66b2-2ecda585be40/c0ef2b77-e0ce-4518-b1a3-60596c96c801_ami-identity-b057dd0940cbcd4300602f5bf8130c3d-2023-01-05T15-01-46.780Z_cropped.png/800x800cc.jpg",
                    snapchat = "https://www.shazam.com/partner/sc/track/648859694"
                ),
                images = NetworkTrackV1Images(
                    background = "https://is4-ssl.mzstatic.com/image/thumb/AMCArtistImages123/v4/b0/37/e1/b037e1f1-cf37-419e-66b2-2ecda585be40/c0ef2b77-e0ce-4518-b1a3-60596c96c801_ami-identity-b057dd0940cbcd4300602f5bf8130c3d-2023-01-05T15-01-46.780Z_cropped.png/800x800cc.jpg",
                    coverart = "https://is1-ssl.mzstatic.com/image/thumb/Music123/v4/9e/80/c7/9e80c757-6994-4338-9e79-b92d5f75f788/196589561725.jpg/400x400cc.jpg",
                    coverarthq = "https://is1-ssl.mzstatic.com/image/thumb/Music123/v4/9e/80/c7/9e80c757-6994-4338-9e79-b92d5f75f788/196589561725.jpg/400x400cc.jpg",
                    joecolor = "b:082443p:8cb5e2s:77aedet:7198c2q:6193bf"
                ),
                hub = Hub(
                    type = "APPLEMUSIC",
                    image = "https://images.shazam.com/static/icons/hub/web/v5/applemusic.png",
                    actions = listOf(
                        Action(
                            name = "apple",
                            type = "applemusicplay",
                            id = "1663973562",
                            uri = null
                        ),
                        Action(
                            name = "apple",
                            type = "uri",
                            id = null,
                            uri = "https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview123/v4/73/65/47/7365473d-bf59-4ef4-308a-961055913fed/mzaf_7366703492386476934.plus.aac.ep.m4a"
                        )
                    ),
                    options = listOf(
                        Option(
                            caption = "OPEN",
                            actions = listOf(
                                Action(
                                    id = null,
                                    name = "hub:applemusic:deeplink",
                                    type = "applemusicopen",
                                    uri = "https://music.apple.com/gb/album/flowers/1663973555?i=1663973562&mttnagencyid=s2n&mttnsiteid=125115&mttn3pid=Apple-Shazam&mttnsub1=Shazam_web&mttnsub2=5348615A-616D-3235-3830-44754D6D5973&itscg=30201&app=music&itsct=Shazam_web"
                                ),
                                Action(
                                    id = null,
                                    name = "hub:applemusic:deeplink",
                                    type = "uri",
                                    uri = "https://music.apple.com/gb/album/flowers/1663973555?i=1663973562&mttnagencyid=s2n&mttnsiteid=125115&mttn3pid=Apple-Shazam&mttnsub1=Shazam_web&mttnsub2=5348615A-616D-3235-3830-44754D6D5973&itscg=30201&app=music&itsct=Shazam_web"
                                )
                            ),
                            beacondata = Beacondata(
                                type = "open",
                                providername = "applemusic"
                            ),
                            image = "https://images.shazam.com/static/icons/hub/web/v5/overflow-open-option.png",
                            type = "open",
                            listcaption = "Open in Apple Music",
                            overflowimage = "https://images.shazam.com/static/icons/hub/web/v5/applemusic-overflow.png",
                            colouroverflowimage = false,
                            providername = "applemusic"
                        )
                    ),
                    explicit = false,
                    displayname = "APPLE MUSIC"
                ),
                artists = listOf(
                    NetworkArtist(
                        alias = "miley-cyrus",
                        id = "42",
                        adamid = "137057909",
                        avatar = null,
                        data = null,
                        name = null
                    )
                ),
                releasedate = null,
                sections = null,
                url = "https://www.shazam.com/track/648859694/flowers",
                urlparams = null
            ),
            fakeRetrofitShazamNetwork.getWorldChart(0).first()
        )
    }

    @Test
    fun`Deserialization Of Search tracks`() = runTest(testDispatcher) {
        assertEquals(
            NetworkTrackV1(
                alias = null,
                layout = "5",
                type = "MUSIC",
                genres = null,
                key = "416484798",
                title = "There For You (feat. Jude Demorest) [From Star\" Season 2]",
                subtitle = "Star Cast",
                share = NetworkTrackV1Share(
                    subject = "There For You (feat. Jude Demorest) [From Star\" Season 2] - Star Cast",
                    text = "I used Shazam to discover There For You (feat. Jude Demorest) [From Star\" Season 2] by Star Cast.",
                    href = "https://www.shazam.com/track/416484798/there-for-you-feat-jude-demorest-from-star-season-2",
                    image = "https://is4-ssl.mzstatic.com/image/thumb/Music125/v4/fb/a5/1f/fba51f7f-4261-f35a-7ded-3a11b96d1d9b/00602567736202.rgb.jpg/400x400cc.jpg",
                    twitter = "I used @Shazam to discover There For You (feat. Jude Demorest) [From Star\" Season 2] by Star Cast.",
                    html = "https://www.shazam.com/snippets/email-share/416484798?lang=en&country=US",
                    avatar = "https://is1-ssl.mzstatic.com/image/thumb/Features125/v4/61/4b/be/614bbe89-19e0-1b63-05df-dd80894a269a/pr_source.png/800x800cc.jpg",
                    snapchat = "https://www.shazam.com/partner/sc/track/416484798"
                ),
                images = NetworkTrackV1Images(
                    background = "https://is1-ssl.mzstatic.com/image/thumb/Features125/v4/61/4b/be/614bbe89-19e0-1b63-05df-dd80894a269a/pr_source.png/800x800cc.jpg",
                    coverart = "https://is4-ssl.mzstatic.com/image/thumb/Music125/v4/fb/a5/1f/fba51f7f-4261-f35a-7ded-3a11b96d1d9b/00602567736202.rgb.jpg/400x400cc.jpg",
                    coverarthq = "https://is4-ssl.mzstatic.com/image/thumb/Music125/v4/fb/a5/1f/fba51f7f-4261-f35a-7ded-3a11b96d1d9b/00602567736202.rgb.jpg/400x400cc.jpg",
                    joecolor = "b:0c0c0cp:dbdbdbs:c1c1c1t:b1b1b1q:9d9d9d"
                ),
                hub = Hub(
                    type = "APPLEMUSIC",
                    image = "https://images.shazam.com/static/icons/hub/android/v5/applemusic_{scalefactor}.png",
                    actions = listOf(
                        Action(
                            id = "1386256482",
                            name = "apple",
                            type = "applemusicplay",
                            uri = null
                        ),
                        Action(
                            id = null,
                            name = "apple",
                            type = "uri",
                            uri = "https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview125/v4/c0/f3/30/c0f33090-b09a-cfec-37fa-5b86639c9222/mzaf_1875581420265807947.plus.aac.p.m4a"
                        )
                    ),
                    options = listOf(
                        Option(
                            caption = "OPEN",
                            actions = listOf(
                                Action(
                                    id = null,
                                    name = "hub:applemusic:deeplink",
                                    type = "intent",
                                    uri = "intent://music.apple.com/us/album/there-for-you-feat-jude-demorest-from-star-season-2/1386255961?i=1386256482&mttnagencyid=s2n&mttnsiteid=125115&mttn3pid=Apple-Shazam&mttnsub1=Shazam_android_am&mttnsub2=5348615A-616D-3235-3830-44754D6D5973&itscg=30201&app=music&itsct=Shazam_android_am#Intent;scheme=http;package=com.apple.android.music;action=android.intent.action.VIEW;end"
                                ),
                                Action(
                                    id = "1386256482",
                                    name = "hub:applemusic:connect",
                                    type = "applemusicconnect",
                                    uri = "https://unsupported.shazam.com"
                                ),
                                Action(
                                    id = null,
                                    name = "hub:applemusic:androidstore",
                                    type = "uri",
                                    uri = "https://play.google.com/store/apps/details?id=com.apple.android.music&referrer=utm_source=https%3A%2F%2Fmusic.apple.com%2Fsubscribe%3Fmttnagencyid%3Ds2n%26mttnsiteid%3D125115%26mttn3pid%3DApple-Shazam%26mttnsub1%3DShazam_android_am%26mttnsub2%3D5348615A-616D-3235-3830-44754D6D5973%26itscg%3D30201%26app%3Dmusic%26itsct%3DShazam_android_am"
                                )
                            ),
                            beacondata = Beacondata(
                                type = "open",
                                providername = "applemusic"
                            ),
                            image = "https://images.shazam.com/static/icons/hub/android/v5/overflow-open-option_{scalefactor}.png",
                            type = "open",
                            listcaption = "Open in Apple Music",
                            overflowimage = "https://images.shazam.com/static/icons/hub/android/v5/applemusic-overflow_{scalefactor}.png",
                            colouroverflowimage = false,
                            providername = "applemusic"
                        )
                    ),
                    explicit = false,
                    displayname = "APPLE MUSIC"
                ),
                artists = listOf(
                    NetworkArtist(
                        alias = null,
                        id = "42",
                        adamid = "1183631929",
                        avatar = null,
                        data = null,
                        name = null
                    )
                ),
                releasedate = null,
                sections = null,
                url = "https://www.shazam.com/track/416484798/there-for-you-feat-jude-demorest-from-star-season-2",
                urlparams = null
            ),
            fakeRetrofitShazamNetwork.searchTracks("star", "SONGS", 0).tracks?.hits?.firstOrNull()?.track
        )
    }

    @Test
    fun`Deserialization Of Search artist`() = runTest(testDispatcher) {
        assertEquals(
            NetworkArtist(
                adamid = "1183631929",
                alias = null,
                avatar = "https://is4-ssl.mzstatic.com/image/thumb/Features125/v4/61/4b/be/614bbe89-19e0-1b63-05df-dd80894a269a/pr_source.png/800x800bb.jpg",
                data = null,
                id = null,
                name = "Star Cast"
            ),
            fakeRetrofitShazamNetwork.searchArtists("star", "ARTISTS", 0).artists?.hits?.firstOrNull()?.artist
        )
    }
}
