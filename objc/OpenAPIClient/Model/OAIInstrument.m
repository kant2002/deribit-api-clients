#import "OAIInstrument.h"

@implementation OAIInstrument

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
  return [[JSONKeyMapper alloc] initWithModelToJSONDictionary:@{ @"quoteCurrency": @"quote_currency", @"kind": @"kind", @"tickSize": @"tick_size", @"contractSize": @"contract_size", @"isActive": @"is_active", @"optionType": @"option_type", @"minTradeAmount": @"min_trade_amount", @"instrumentName": @"instrument_name", @"settlementPeriod": @"settlement_period", @"strike": @"strike", @"baseCurrency": @"base_currency", @"creationTimestamp": @"creation_timestamp", @"expirationTimestamp": @"expiration_timestamp" }];
}

/**
 * Indicates whether the property with the given name is optional.
 * If `propertyName` is optional, then return `YES`, otherwise return `NO`.
 * This method is used by `JSONModel`.
 */
+ (BOOL)propertyIsOptional:(NSString *)propertyName {

  NSArray *optionalProperties = @[@"optionType", @"strike", ];
  return [optionalProperties containsObject:propertyName];
}

@end
