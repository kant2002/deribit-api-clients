#import "OAIUserTrade.h"

@implementation OAIUserTrade

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
  return [[JSONKeyMapper alloc] initWithModelToJSONDictionary:@{ @"direction": @"direction", @"feeCurrency": @"fee_currency", @"orderId": @"order_id", @"timestamp": @"timestamp", @"price": @"price", @"iv": @"iv", @"tradeId": @"trade_id", @"fee": @"fee", @"orderType": @"order_type", @"tradeSeq": @"trade_seq", @"selfTrade": @"self_trade", @"state": @"state", @"label": @"label", @"indexPrice": @"index_price", @"amount": @"amount", @"instrumentName": @"instrument_name", @"tickDirection": @"tick_direction", @"matchingId": @"matching_id", @"liquidity": @"liquidity" }];
}

/**
 * Indicates whether the property with the given name is optional.
 * If `propertyName` is optional, then return `YES`, otherwise return `NO`.
 * This method is used by `JSONModel`.
 */
+ (BOOL)propertyIsOptional:(NSString *)propertyName {

  NSArray *optionalProperties = @[@"iv", @"orderType", @"label", @"liquidity"];
  return [optionalProperties containsObject:propertyName];
}

@end
