# TC Music
==================

**TC Music** is a fully functional Android app built entirely with Kotlin and Jetpack Compose. It
follows Android design and development best practices and is intended to be a useful reference
for developers. 

The app is currently in development.

# Features

**TC Music** is an app which allows users listen to music, create playlists and download music
to listen offline

# Architecture

The **TC Music** app follows the
[official architecture guidance](https://developer.android.com/topic/architecture)
and [Now in Android app](https://github.com/android/nowinandroid)

# API

The **TC Music** app is using [Shazam Core API](https://rapidapi.com/tipsters/api/shazam-core) to get data.

# Build

To build app, you need to add file secrets.defaults.properties into project.
The secrets.defaults.properties file will contains:
```text
SHAZAM_DOMAIN="shazam-core.p.rapidapi.com"
SHAZAM_API_KEY="YOUR_SHAZAM_API_KEY"
```