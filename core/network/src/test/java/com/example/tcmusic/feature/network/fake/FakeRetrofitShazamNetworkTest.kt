package com.example.tcmusic.feature.network.fake

import com.example.tcmusic.core.network.datasource.fake.FakeRetrofitShazamNetwork
import com.example.tcmusic.core.network.model.*
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by TC on 22/02/2023.
 */

class FakeRetrofitShazamNetworkTest {
    private lateinit var fakeRetrofitShazamNetwork: FakeRetrofitShazamNetwork

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        fakeRetrofitShazamNetwork = FakeRetrofitShazamNetwork(
            ioDispatcher = testDispatcher,
            networkJson = Json { ignoreUnknownKeys = true }
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
                                ActionX(
                                    name = "hub:applemusic:deeplink",
                                    type = "applemusicopen",
                                    uri = "https://music.apple.com/gb/album/flowers/1663973555?i=1663973562&mttnagencyid=s2n&mttnsiteid=125115&mttn3pid=Apple-Shazam&mttnsub1=Shazam_web&mttnsub2=5348615A-616D-3235-3830-44754D6D5973&itscg=30201&app=music&itsct=Shazam_web"
                                ),
                                ActionX(
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
}