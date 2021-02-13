package com.placeholder_webapp.backend.api.adapter.internal.common;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

public class DynamoDbClient implements Database {
  private static final String TEST_TABLE_NAME = "Incli";
  private static final String TRENDING_ITEM_TABLE_NAME = "trending_item";
  // TODO: 2021-02-13 Inject credentials from file on VM
  private static final BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIA6NDTPEEF7XQYNTDS", "ONBjij4TJAwnfr2YYnuAFgJWfADZ2cJmz+C9HCJr");

  private static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
    .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
    .withRegion(Regions.EU_NORTH_1)
    .build();
  private static DynamoDB dynamoDB = new DynamoDB(client);

  public DynamoDbClient() {
  }

  @Override
  public void addItem(TrendingResponse trendingResponse) {
    Item serviceItem = trendingResponse.toServiceSpecificDto().toDto();
    Item trendingItem = trendingResponse.toTrendingItemDto();

    Table trendingTable = dynamoDB.getTable(TRENDING_ITEM_TABLE_NAME);
    String serviceTableName = trendingResponse.getService().name().toLowerCase();
    Table serviceTable = dynamoDB.getTable(serviceTableName);

    trendingTable.putItem(trendingItem);
    serviceTable.putItem(serviceItem);
  }
}
