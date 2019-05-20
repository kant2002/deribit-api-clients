#import "OAISettlement.h"

@implementation OAISettlement

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
  return [[JSONKeyMapper alloc] initWithModelToJSONDictionary:@{ @"sessionProfitLoss": @"session_profit_loss", @"markPrice": @"mark_price", @"funding": @"funding", @"socialized": @"socialized", @"sessionBankrupcy": @"session_bankrupcy", @"timestamp": @"timestamp", @"profitLoss": @"profit_loss", @"funded": @"funded", @"indexPrice": @"index_price", @"sessionTax": @"session_tax", @"sessionTaxRate": @"session_tax_rate", @"instrumentName": @"instrument_name", @"position": @"position", @"type": @"type" }];
}

/**
 * Indicates whether the property with the given name is optional.
 * If `propertyName` is optional, then return `YES`, otherwise return `NO`.
 * This method is used by `JSONModel`.
 */
+ (BOOL)propertyIsOptional:(NSString *)propertyName {

  NSArray *optionalProperties = @[@"markPrice", @"socialized", @"sessionBankrupcy", @"profitLoss", @"funded", @"sessionTax", @"sessionTaxRate", ];
  return [optionalProperties containsObject:propertyName];
}

@end
