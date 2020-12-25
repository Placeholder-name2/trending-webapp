import React from 'react'
import TwitterItem from './TwitterItem'
import { Tweet } from '../../../node_modules/react-twitter-widgets'

class TwitterApi extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            items: [TwitterItem],
            twitterId: "",
            featuringHashtag: ""
        };
    }
    componentDidMount() {

        const proxy = 'https://cors-anywhere.herokuapp.com/'
        const api = `${proxy}https://api.twitter.com/1.1/trends/place.json?id=1`
        fetch(api, {
            headers: new Headers({
                'Authorization': 'Bearer AAAAAAAAAAAAAAAAAAAAAFiyKAEAAAAACvhQhi7bqt7Va64ZVz0tjMHzfzw%3DWJXMBvo6MfSjTgjPO0FeIDyc1maDlIIVkjZ66HIh1AoTjDpv8D'
                //Do we need origin or x-requested-with headers?
            })
        })
            .then(res => res.json())
            .then(result => {
                console.log(result)
                return result[0].trends
            })
            .then(trends =>
                fetch(`${proxy}https://api.twitter.com/1.1/search/tweets.json?q=` + trends[0].query, {
                    headers: new Headers({
                        'Authorization': 'Bearer AAAAAAAAAAAAAAAAAAAAAFiyKAEAAAAACvhQhi7bqt7Va64ZVz0tjMHzfzw%3DWJXMBvo6MfSjTgjPO0FeIDyc1maDlIIVkjZ66HIh1AoTjDpv8D'
                    })
                })
                    .then(searchRes => searchRes.json())
                    .then(
                        (searchResult) => {
                            console.log(searchResult)
                            this.setState({
                                isloaded: true,
                                twitterId: searchResult.statuses[0].id_str,
                                featuringHashtag: trends.name
                            })
                        },
                        (error) => {
                            this.setState({
                                isLoaded: true,
                                error
                            });
                        }
                    )
            );
    }
    render() {
        const { error, isLoaded, twitterId, featuringHashtag } = this.state;
        if (error) {
            return <div>Error: Fail</div>;
        } else if (!isLoaded) {
            console.log(twitterId, isLoaded, featuringHashtag)
            return (
                <ul>
                    <div>Trending right now on Twitter: {featuringHashtag}</div>
                    <Tweet tweetId={twitterId}></Tweet>
                </ul>
            );
        } else {
            console.log(twitterId)
            return (
                <ul>
                    <Tweet tweetId={twitterId}></Tweet>
                </ul>
            );
        }
    }
}
export default TwitterApi;