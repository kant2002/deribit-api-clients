#import "OAIWithdrawal.h"

@implementation OAIWithdrawal

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
  return [[JSONKeyMapper alloc] initWithModelToJSONDictionary:@{ @"updatedTimestamp": @"updated_timestamp", @"fee": @"fee", @"confirmedTimestamp": @"confirmed_timestamp", @"amount": @"amount", @"priority": @"priority", @"currency": @"currency", @"state": @"state", @"address": @"address", @"createdTimestamp": @"created_timestamp", @"_id": @"id", @"transactionId": @"transaction_id" }];
}

/**
 * Indicates whether the property with the given name is optional.
 * If `propertyName` is optional, then return `YES`, otherwise return `NO`.
 * This method is used by `JSONModel`.
 */
+ (BOOL)propertyIsOptional:(NSString *)propertyName {

  NSArray *optionalProperties = @[@"fee", @"confirmedTimestamp", @"priority", @"createdTimestamp", @"_id", ];
  return [optionalProperties containsObject:propertyName];
}

@end
