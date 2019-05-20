#import "OAICurrency.h"

@implementation OAICurrency

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
  return [[JSONKeyMapper alloc] initWithModelToJSONDictionary:@{ @"minConfirmations": @"min_confirmations", @"minWithdrawalFee": @"min_withdrawal_fee", @"disabledDepositAddressCreation": @"disabled_deposit_address_creation", @"currency": @"currency", @"currencyLong": @"currency_long", @"withdrawalFee": @"withdrawal_fee", @"feePrecision": @"fee_precision", @"withdrawalPriorities": @"withdrawal_priorities", @"coinType": @"coin_type" }];
}

/**
 * Indicates whether the property with the given name is optional.
 * If `propertyName` is optional, then return `YES`, otherwise return `NO`.
 * This method is used by `JSONModel`.
 */
+ (BOOL)propertyIsOptional:(NSString *)propertyName {

  NSArray *optionalProperties = @[@"minConfirmations", @"minWithdrawalFee", @"disabledDepositAddressCreation", @"feePrecision", @"withdrawalPriorities", ];
  return [optionalProperties containsObject:propertyName];
}

@end
