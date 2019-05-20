#import "OAIBookSummary.h"

@implementation OAIBookSummary

- (instancetype)init {
  self = [super init];
  if (self) {
    // initialize property's default value, if any
    
  }
  return self;
}


/**
 * Maps json key to property name.
 * This method is used by `JSONModel`.
 */
+ (JSONKeyMapper *)keyMapper {
  return [[JSONKeyMapper alloc] initWithModelToJSONDictionary:@{ @"underlyingIndex": @"underlying_index", @"volume": @"volume", @"volumeUsd": @"volume_usd", @"underlyingPrice": @"underlying_price", @"bidPrice": @"bid_price", @"openInterest": @"open_interest", @"quoteCurrency": @"quote_currency", @"high": @"high", @"estimatedDeliveryPrice": @"estimated_delivery_price", @"last": @"last", @"midPrice": @"mid_price", @"interestRate": @"interest_rate", @"funding8h": @"funding_8h", @"markPrice": @"mark_price", @"askPrice": @"ask_price", @"instrumentName": @"instrument_name", @"low": @"low", @"baseCurrency": @"base_currency", @"creationTimestamp": @"creation_timestamp", @"currentFunding": @"current_funding" }];
}

/**
 * Indicates whether the property with the given name is optional.
 * If `propertyName` is optional, then return `YES`, otherwise return `NO`.
 * This method is used by `JSONModel`.
 */
+ (BOOL)propertyIsOptional:(NSString *)propertyName {

  NSArray *optionalProperties = @[@"underlyingIndex", @"volumeUsd", @"underlyingPrice", @"estimatedDeliveryPrice", @"interestRate", @"funding8h", @"baseCurrency", @"currentFunding"];
  return [optionalProperties containsObject:propertyName];
}

@end
