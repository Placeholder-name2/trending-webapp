import React from 'react';
import InfiniteScroll from 'react-infinite-scroll-component';
import getDatabaseItems from '../dynamodb/database';
import SimpleCard from './card';

class InfinityScroll extends React.Component {
  state = {
    items: Array.from({ length: 1 }),
    databaseItems: null,
    isLoaded: false
  };

  fetchMoreData = () => {
    // a fake async api call like which sends
    // 20 more records in 1.5 secs
    setTimeout(() => {
      this.setState({
        items: this.state.items.concat(Array.from({ length: 1 })),
      });
    }, 1500);
  };

  componentDidMount() {
    this.setState({
      databaseItems: getDatabaseItems("twitter"),
    })
    console.log("component did mount???????")
  }

  render() {
    if (this.state.databaseItems) {
      console.log("testinggggg" + this.state.databaseItems)
      return (
        <div>
          <h1>Trending is coming</h1>
          <hr />
          <InfiniteScroll
            dataLength={this.state.items.length}
            next={this.fetchMoreData}
            hasMore={true}
            loader={<h4>Loading...</h4>}
          >
            {this.state.items.map((i, index) => (
              <SimpleCard
              // key={index}
              // hashtag={this.state.databaseItems.Items[index].trending_hashtag}
              // tweetid={this.state.databaseItems.Items[index].tweet_id}
              >
              </SimpleCard>

            ))}
          </InfiniteScroll>
        </div>
      );
    } else {
      return <div>Loading!</div>
    }
  }
}

export default InfinityScroll;