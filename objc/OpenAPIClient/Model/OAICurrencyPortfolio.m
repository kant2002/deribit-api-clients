#import "OAICurrencyPortfolio.h"

@implementation OAICurrencyPortfolio

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
  return [[JSONKeyMapper alloc] initWithModelToJSONDictionary:@{ @"maintenanceMargin": @"maintenance_margin", @"availableWithdrawalFunds": @"available_withdrawal_funds", @"initialMargin": @"initial_margin", @"availableFunds": @"available_funds", @"currency": @"currency", @"marginBalance": @"margin_balance", @"equity": @"equity", @"balance": @"balance" }];
}

/**
 * Indicates whether the property with the given name is optional.
 * If `propertyName` is optional, then return `YES`, otherwise return `NO`.
 * This method is used by `JSONModel`.
 */
+ (BOOL)propertyIsOptional:(NSString *)propertyName {

  NSArray *optionalProperties = @[];
  return [optionalProperties containsObject:propertyName];
}

@end
