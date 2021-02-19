import React from 'react';
import InfiniteScroll from 'react-infinite-scroll-component';
import getDatabaseItems from '../dynamodb/database';
import SimpleCard from './card';

class InfinityScroll extends React.Component {
  state = {
    items: getDatabaseItems("twitter"),
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

  render() {
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
            <SimpleCard key={index} test='test'>
            </SimpleCard>
          ))}
        </InfiniteScroll>
      </div>
    );
  }
}

export default InfinityScroll;