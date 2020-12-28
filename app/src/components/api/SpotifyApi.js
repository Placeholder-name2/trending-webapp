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

    console.log();
    fetch(api, {
      // FIXME: token will time-out
      headers: new Headers({
        Authorization:
          'Bearer BQAGXZO5x045BT_NDepK_TzDM2rZMkpcuCIUJlz0LucWxzmArFMvEIp1PdepHLt1mvmBMW2KxKwgjjA1ZuvDSs8M95r-Yjyhc_-f3mlEHQV3qU-11D8a_dYVC4iCk8MMAbpt-MurNirVdlEqtVPxp3ElA34Dn3U',
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
