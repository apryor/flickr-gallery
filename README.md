Android project created for a take home assessment using the Flickr API.

## Key Features
- Browse recent photos
- Search for photos by keyword
- Expand photos and view additional information
- Infinite scroll
- Responsive UI

## Libraries Used
- Ktor for building and executing network requests
- KotlinX Serialization for deserializing JSON API responses into usable objects
- Jetpack Compose for user interface
- Coil for image loading
- Coroutines for all async functioniality
- Koin for dependency injection

## Architectural Approach
- MVVM with Service / Repository
- Generic paginator (admittedly over-engineered for this use case)

## Other notes
- See TODOs for a few things I would like to do
- If I were to implement caching and/or have a use case where I wanted a local database, I'd use Room
- Ktor, Koin, Coil, and Jetpack Compose were all new-to-me libraries (outside of using Compose for a few hours on a tutorial project), that I wanted to use for my own learning. It was really enjoyable to see first hand how all these libraries work together, and I definitely want to keep using them.

Recorded video of app: https://photos.app.goo.gl/9kLtmbejtREiHXQX9
