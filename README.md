# <span style="color: #4CAF50;">Playlist API Documentation</span>
This API provides endpoints to manage users, playlists, and genres, integrating with the Spotify service for retrieving music genres.

## <span style="color: #2196F3;">Available Routes</span>
A continuación, se describirán los distintos endpoints disponibles en la API:

### <span style="color: #FF5722;">Authentication</span>
- **POST** `/auth/login`: Authenticates a user and returns a JWT token.
- **POST** `/auth/register`: Registers a new user.

### <span style="color: #FF5722;">Genres</span>
- **GET** `/api/genres`: Retrieves a list of music genres from the Spotify API.

### <span style="color: #FF5722;">Playlists</span>
- **GET** `/lists`: Lists all available playlists.
- **GET** `/lists/{listName}`: Retrieves a specific playlist by its name.
- **POST** `/lists`: Creates a new playlist.
- **DELETE** `/lists/{listName}`: Deletes a specific playlist by its name.
