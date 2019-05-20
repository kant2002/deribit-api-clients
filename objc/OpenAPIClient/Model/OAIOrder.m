#import "OAIOrder.h"

@implementation OAIOrder

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
  return [[JSONKeyMapper alloc] initWithModelToJSONDictionary:@{ @"direction": @"direction", @"reduceOnly": @"reduce_only", @"triggered": @"triggered", @"orderId": @"order_id", @"price": @"price", @"timeInForce": @"time_in_force", @"api": @"api", @"orderState": @"order_state", @"implv": @"implv", @"advanced": @"advanced", @"postOnly": @"post_only", @"usd": @"usd", @"stopPrice": @"stop_price", @"orderType": @"order_type", @"lastUpdateTimestamp": @"last_update_timestamp", @"originalOrderType": @"original_order_type", @"maxShow": @"max_show", @"profitLoss": @"profit_loss", @"isLiquidation": @"is_liquidation", @"filledAmount": @"filled_amount", @"label": @"label", @"commission": @"commission", @"amount": @"amount", @"trigger": @"trigger", @"instrumentName": @"instrument_name", @"creationTimestamp": @"creation_timestamp", @"averagePrice": @"average_price" }];
}

/**
 * Indicates whether the property with the given name is optional.
 * If `propertyName` is optional, then return `YES`, otherwise return `NO`.
 * This method is used by `JSONModel`.
 */
+ (BOOL)propertyIsOptional:(NSString *)propertyName {

  NSArray *optionalProperties = @[@"reduceOnly", @"triggered", @"implv", @"advanced", @"usd", @"stopPrice", @"originalOrderType", @"profitLoss", @"filledAmount", @"commission", @"amount", @"trigger", @"instrumentName", @"averagePrice"];
  return [optionalProperties containsObject:propertyName];
}

@end
