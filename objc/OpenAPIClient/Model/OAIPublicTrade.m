#import "OAIPublicTrade.h"

@implementation OAIPublicTrade

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
  return [[JSONKeyMapper alloc] initWithModelToJSONDictionary:@{ @"direction": @"direction", @"tickDirection": @"tick_direction", @"timestamp": @"timestamp", @"price": @"price", @"tradeSeq": @"trade_seq", @"tradeId": @"trade_id", @"iv": @"iv", @"indexPrice": @"index_price", @"amount": @"amount", @"instrumentName": @"instrument_name" }];
}

/**
 * Indicates whether the property with the given name is optional.
 * If `propertyName` is optional, then return `YES`, otherwise return `NO`.
 * This method is used by `JSONModel`.
 */
+ (BOOL)propertyIsOptional:(NSString *)propertyName {

  NSArray *optionalProperties = @[@"iv", ];
  return [optionalProperties containsObject:propertyName];
}

@end
