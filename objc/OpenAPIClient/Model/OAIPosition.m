#import "OAIPosition.h"

@implementation OAIPosition

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
  return [[JSONKeyMapper alloc] initWithModelToJSONDictionary:@{ @"direction": @"direction", @"averagePriceUsd": @"average_price_usd", @"estimatedLiquidationPrice": @"estimated_liquidation_price", @"floatingProfitLoss": @"floating_profit_loss", @"floatingProfitLossUsd": @"floating_profit_loss_usd", @"openOrdersMargin": @"open_orders_margin", @"totalProfitLoss": @"total_profit_loss", @"realizedProfitLoss": @"realized_profit_loss", @"delta": @"delta", @"initialMargin": @"initial_margin", @"size": @"size", @"maintenanceMargin": @"maintenance_margin", @"kind": @"kind", @"markPrice": @"mark_price", @"averagePrice": @"average_price", @"settlementPrice": @"settlement_price", @"indexPrice": @"index_price", @"instrumentName": @"instrument_name", @"sizeCurrency": @"size_currency" }];
}

/**
 * Indicates whether the property with the given name is optional.
 * If `propertyName` is optional, then return `YES`, otherwise return `NO`.
 * This method is used by `JSONModel`.
 */
+ (BOOL)propertyIsOptional:(NSString *)propertyName {

  NSArray *optionalProperties = @[@"averagePriceUsd", @"estimatedLiquidationPrice", @"floatingProfitLossUsd", @"realizedProfitLoss", @"sizeCurrency"];
  return [optionalProperties containsObject:propertyName];
}

@end
