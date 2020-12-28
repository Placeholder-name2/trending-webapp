import React from 'react';
import TwitterItem from './TwitterItem';
import { Tweet } from '../../../node_modules/react-twitter-widgets';

class SpotifyApi extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      spotifyUrl: '',
      playlistName: '',
    };
  }

  componentDidMount() {
    this.getFeaturedItems();
  }
  getFeaturedItems() {
    const proxy = 'https://cors-anywhere.herokuapp.com/';
    const api = `${proxy}https://api.spotify.com/v1/browse/featured-playlists`; //?limit=50`;
    const token =
      'BQDuNAM5WjKwIkuZUIkY5fIsuoPlD56YDGr5xZwJvdcFnpRBvU8sqboUIxFQvw-JguvEEBkJlw6BtkEcNbrbNvF6YuhDKONkN7epIW09cDDri5IqmyZVdHIcDXvqv60vWlzl2bYbGiXBBiYOfEKJLn1uOiKTyJQ';

    console.log();
    fetch(api, {
      // FIXME: token will time-out
      headers: new Headers({
        Authorization:
          'Bearer BQCqup1nB9u0mIzwYLtu_L4u0ntYSa63sNchtIYrwjhZK0U6dCZDyi2kmEJxGIJLXLZ8AH_TgIsuivCnjGIh60J0IXmJvIdpRAsAAX_cYlSrTBpyg4QQYfyj1aro03YUR3bk85LAnaBMqcwwIRepldLaYuHmlqE',
      }),
    })
      .then((result) => result.json())
      .then((spotifyFeaturedResult) => {
        console.log(spotifyFeaturedResult);
        const index = Math.floor(Math.random() * spotifyFeaturedResult.playlists.items.length);
        this.setState({
          spotifyUrl: spotifyFeaturedResult.playlists.items[index].external_urls.spotify.replace(
            'https://open.spotify.com/',
            'https://open.spotify.com/embed/'
          ),
          playlistName: spotifyFeaturedResult.playlists.items[index].description,
          isLoaded: true,
        });
      });
  }

  render() {
    const { error, isLoaded, spotifyUrl, playlistName } = this.state;
    if (error) {
      return <div>Error: Fail</div>;
    } else if (!isLoaded) {
      return <ul>Loading...</ul>;
    } else {
      console.log(spotifyUrl);
      return (
        <ul>
          <div>Trending right now on Spotify: {playlistName}</div>
          <iframe
            title="spotify-test"
            src={spotifyUrl}
            width="300"
            height="380"
            frameborder="0"
            allowtransparency="true"
            allow="encrypted-media"
          ></iframe>
        </ul>
      );
    }
  }
}
export default SpotifyApi;
