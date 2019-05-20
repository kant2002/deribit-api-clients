#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "user_trade.h"


    char* directionuser_trade_ToString(direction_e direction){
    char *directionArray[] =  { "buy","sell" };
        return directionArray[direction];
    }

    direction_e directionuser_trade_FromString(char* direction){
    int stringToReturn = 0;
    char *directionArray[] =  { "buy","sell" };
    size_t sizeofArray = sizeof(directionArray) / sizeof(directionArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(direction, directionArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }
    char* fee_currencyuser_trade_ToString(fee_currency_e fee_currency){
    char *fee_currencyArray[] =  { "BTC","ETH" };
        return fee_currencyArray[fee_currency];
    }

    fee_currency_e fee_currencyuser_trade_FromString(char* fee_currency){
    int stringToReturn = 0;
    char *fee_currencyArray[] =  { "BTC","ETH" };
    size_t sizeofArray = sizeof(fee_currencyArray) / sizeof(fee_currencyArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(fee_currency, fee_currencyArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }
    char* order_typeuser_trade_ToString(order_type_e order_type){
    char *order_typeArray[] =  { "limit","market","liquidation" };
        return order_typeArray[order_type];
    }

    order_type_e order_typeuser_trade_FromString(char* order_type){
    int stringToReturn = 0;
    char *order_typeArray[] =  { "limit","market","liquidation" };
    size_t sizeofArray = sizeof(order_typeArray) / sizeof(order_typeArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(order_type, order_typeArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }
    char* stateuser_trade_ToString(state_e state){
    char *stateArray[] =  { "open","filled","rejected","cancelled","untriggered","archive" };
        return stateArray[state];
    }

    state_e stateuser_trade_FromString(char* state){
    int stringToReturn = 0;
    char *stateArray[] =  { "open","filled","rejected","cancelled","untriggered","archive" };
    size_t sizeofArray = sizeof(stateArray) / sizeof(stateArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(state, stateArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }
    char* tick_directionuser_trade_ToString(tick_direction_e tick_direction){
    char *tick_directionArray[] =  { "_0","_1","_2","_3" };
        return tick_directionArray[tick_direction];
    }

    tick_direction_e tick_directionuser_trade_FromString(char* tick_direction){
    int stringToReturn = 0;
    char *tick_directionArray[] =  { "_0","_1","_2","_3" };
    size_t sizeofArray = sizeof(tick_directionArray) / sizeof(tick_directionArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(tick_direction, tick_directionArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }
    char* liquidityuser_trade_ToString(liquidity_e liquidity){
    char *liquidityArray[] =  { "M","T" };
        return liquidityArray[liquidity];
    }

    liquidity_e liquidityuser_trade_FromString(char* liquidity){
    int stringToReturn = 0;
    char *liquidityArray[] =  { "M","T" };
    size_t sizeofArray = sizeof(liquidityArray) / sizeof(liquidityArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(liquidity, liquidityArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }

user_trade_t *user_trade_create(
    direction_e direction,
    fee_currency_e fee_currency,
    char *order_id,
    int timestamp,
    double price,
    double iv,
    char *trade_id,
    double fee,
    order_type_e order_type,
    int trade_seq,
    int self_trade,
    state_e state,
    char *label,
    double index_price,
    double amount,
    char *instrument_name,
    int tick_direction,
    char *matching_id,
    liquidity_e liquidity
    ) {
	user_trade_t *user_trade_local_var = malloc(sizeof(user_trade_t));
    if (!user_trade_local_var) {
        return NULL;
    }
	user_trade_local_var->direction = direction;
	user_trade_local_var->fee_currency = fee_currency;
	user_trade_local_var->order_id = order_id;
	user_trade_local_var->timestamp = timestamp;
	user_trade_local_var->price = price;
	user_trade_local_var->iv = iv;
	user_trade_local_var->trade_id = trade_id;
	user_trade_local_var->fee = fee;
	user_trade_local_var->order_type = order_type;
	user_trade_local_var->trade_seq = trade_seq;
	user_trade_local_var->self_trade = self_trade;
	user_trade_local_var->state = state;
	user_trade_local_var->label = label;
	user_trade_local_var->index_price = index_price;
	user_trade_local_var->amount = amount;
	user_trade_local_var->instrument_name = instrument_name;
	user_trade_local_var->tick_direction = tick_direction;
	user_trade_local_var->matching_id = matching_id;
	user_trade_local_var->liquidity = liquidity;

	return user_trade_local_var;
}


void user_trade_free(user_trade_t *user_trade) {
    listEntry_t *listEntry;
    free(user_trade->order_id);
    free(user_trade->trade_id);
    free(user_trade->label);
    free(user_trade->instrument_name);
    free(user_trade->matching_id);
	free(user_trade);
}

cJSON *user_trade_convertToJSON(user_trade_t *user_trade) {
	cJSON *item = cJSON_CreateObject();

	// user_trade->direction
    
    if(cJSON_AddStringToObject(item, "direction", directionuser_trade_ToString(user_trade->direction)) == NULL)
    {
    goto fail; //Enum
    }


	// user_trade->fee_currency
    
    if(cJSON_AddStringToObject(item, "fee_currency", fee_currencyuser_trade_ToString(user_trade->fee_currency)) == NULL)
    {
    goto fail; //Enum
    }


	// user_trade->order_id
    if (!user_trade->order_id) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "order_id", user_trade->order_id) == NULL) {
    goto fail; //String
    }


	// user_trade->timestamp
    if (!user_trade->timestamp) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "timestamp", user_trade->timestamp) == NULL) {
    goto fail; //Numeric
    }


	// user_trade->price
    if (!user_trade->price) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "price", user_trade->price) == NULL) {
    goto fail; //Numeric
    }


	// user_trade->iv
    if(user_trade->iv) { 
    if(cJSON_AddNumberToObject(item, "iv", user_trade->iv) == NULL) {
    goto fail; //Numeric
    }
     } 


	// user_trade->trade_id
    if (!user_trade->trade_id) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "trade_id", user_trade->trade_id) == NULL) {
    goto fail; //String
    }


	// user_trade->fee
    if (!user_trade->fee) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "fee", user_trade->fee) == NULL) {
    goto fail; //Numeric
    }


	// user_trade->order_type
    
    if(cJSON_AddStringToObject(item, "order_type", order_typeuser_trade_ToString(user_trade->order_type)) == NULL)
    {
    goto fail; //Enum
    }
    


	// user_trade->trade_seq
    if (!user_trade->trade_seq) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "trade_seq", user_trade->trade_seq) == NULL) {
    goto fail; //Numeric
    }


	// user_trade->self_trade
    if (!user_trade->self_trade) {
        goto fail;
    }
    
    if(cJSON_AddBoolToObject(item, "self_trade", user_trade->self_trade) == NULL) {
    goto fail; //Bool
    }


	// user_trade->state
    
    if(cJSON_AddStringToObject(item, "state", stateuser_trade_ToString(user_trade->state)) == NULL)
    {
    goto fail; //Enum
    }


	// user_trade->label
    if(user_trade->label) { 
    if(cJSON_AddStringToObject(item, "label", user_trade->label) == NULL) {
    goto fail; //String
    }
     } 


	// user_trade->index_price
    if (!user_trade->index_price) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "index_price", user_trade->index_price) == NULL) {
    goto fail; //Numeric
    }


	// user_trade->amount
    if (!user_trade->amount) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "amount", user_trade->amount) == NULL) {
    goto fail; //Numeric
    }


	// user_trade->instrument_name
    if (!user_trade->instrument_name) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "instrument_name", user_trade->instrument_name) == NULL) {
    goto fail; //String
    }


	// user_trade->tick_direction
    
    if(cJSON_AddNumberToObject(item, "tick_direction", user_trade->tick_direction) == NULL) {
    goto fail; //Numeric
    }


	// user_trade->matching_id
    if (!user_trade->matching_id) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "matching_id", user_trade->matching_id) == NULL) {
    goto fail; //String
    }


	// user_trade->liquidity
    
    if(cJSON_AddStringToObject(item, "liquidity", liquidityuser_trade_ToString(user_trade->liquidity)) == NULL)
    {
    goto fail; //Enum
    }
    

	return item;
fail:
	if (item) {
        cJSON_Delete(item);
    }
	return NULL;
}

user_trade_t *user_trade_parseFromJSON(cJSON *user_tradeJSON){

    user_trade_t *user_trade_local_var = NULL;

    // user_trade->direction
    cJSON *direction = cJSON_GetObjectItemCaseSensitive(user_tradeJSON, "direction");
    if (!direction) {
        goto end;
    }

    direction_e directionVariable;
    
    if(!cJSON_IsString(direction))
    {
    goto end; //Enum
    }
    directionVariable = directionuser_trade_FromString(direction->valuestring);

    // user_trade->fee_currency
    cJSON *fee_currency = cJSON_GetObjectItemCaseSensitive(user_tradeJSON, "fee_currency");
    if (!fee_currency) {
        goto end;
    }

    fee_currency_e fee_currencyVariable;
    
    if(!cJSON_IsString(fee_currency))
    {
    goto end; //Enum
    }
    fee_currencyVariable = fee_currencyuser_trade_FromString(fee_currency->valuestring);

    // user_trade->order_id
    cJSON *order_id = cJSON_GetObjectItemCaseSensitive(user_tradeJSON, "order_id");
    if (!order_id) {
        goto end;
    }

    
    if(!cJSON_IsString(order_id))
    {
    goto end; //String
    }

    // user_trade->timestamp
    cJSON *timestamp = cJSON_GetObjectItemCaseSensitive(user_tradeJSON, "timestamp");
    if (!timestamp) {
        goto end;
    }

    
    if(!cJSON_IsNumber(timestamp))
    {
    goto end; //Numeric
    }

    // user_trade->price
    cJSON *price = cJSON_GetObjectItemCaseSensitive(user_tradeJSON, "price");
    if (!price) {
        goto end;
    }

    
    if(!cJSON_IsNumber(price))
    {
    goto end; //Numeric
    }

    // user_trade->iv
    cJSON *iv = cJSON_GetObjectItemCaseSensitive(user_tradeJSON, "iv");
    if (iv) { 
    if(!cJSON_IsNumber(iv))
    {
    goto end; //Numeric
    }
    }

    // user_trade->trade_id
    cJSON *trade_id = cJSON_GetObjectItemCaseSensitive(user_tradeJSON, "trade_id");
    if (!trade_id) {
        goto end;
    }

    
    if(!cJSON_IsString(trade_id))
    {
    goto end; //String
    }

    // user_trade->fee
    cJSON *fee = cJSON_GetObjectItemCaseSensitive(user_tradeJSON, "fee");
    if (!fee) {
        goto end;
    }

    
    if(!cJSON_IsNumber(fee))
    {
    goto end; //Numeric
    }

    // user_trade->order_type
    cJSON *order_type = cJSON_GetObjectItemCaseSensitive(user_tradeJSON, "order_type");
    order_type_e order_typeVariable;
    if (order_type) { 
    if(!cJSON_IsString(order_type))
    {
    goto end; //Enum
    }
    order_typeVariable = order_typeuser_trade_FromString(order_type->valuestring);
    }

    // user_trade->trade_seq
    cJSON *trade_seq = cJSON_GetObjectItemCaseSensitive(user_tradeJSON, "trade_seq");
    if (!trade_seq) {
        goto end;
    }

    
    if(!cJSON_IsNumber(trade_seq))
    {
    goto end; //Numeric
    }

    // user_trade->self_trade
    cJSON *self_trade = cJSON_GetObjectItemCaseSensitive(user_tradeJSON, "self_trade");
    if (!self_trade) {
        goto end;
    }

    
    if(!cJSON_IsBool(self_trade))
    {
    goto end; //Bool
    }

    // user_trade->state
    cJSON *state = cJSON_GetObjectItemCaseSensitive(user_tradeJSON, "state");
    if (!state) {
        goto end;
    }

    state_e stateVariable;
    
    if(!cJSON_IsString(state))
    {
    goto end; //Enum
    }
    stateVariable = stateuser_trade_FromString(state->valuestring);

    // user_trade->label
    cJSON *label = cJSON_GetObjectItemCaseSensitive(user_tradeJSON, "label");
    if (label) { 
    if(!cJSON_IsString(label))
    {
    goto end; //String
    }
    }

    // user_trade->index_price
    cJSON *index_price = cJSON_GetObjectItemCaseSensitive(user_tradeJSON, "index_price");
    if (!index_price) {
        goto end;
    }

    
    if(!cJSON_IsNumber(index_price))
    {
    goto end; //Numeric
    }

    // user_trade->amount
    cJSON *amount = cJSON_GetObjectItemCaseSensitive(user_tradeJSON, "amount");
    if (!amount) {
        goto end;
    }

    
    if(!cJSON_IsNumber(amount))
    {
    goto end; //Numeric
    }

    // user_trade->instrument_name
    cJSON *instrument_name = cJSON_GetObjectItemCaseSensitive(user_tradeJSON, "instrument_name");
    if (!instrument_name) {
        goto end;
    }

    
    if(!cJSON_IsString(instrument_name))
    {
    goto end; //String
    }

    // user_trade->tick_direction
    cJSON *tick_direction = cJSON_GetObjectItemCaseSensitive(user_tradeJSON, "tick_direction");
    if (!tick_direction) {
        goto end;
    }

    
    if(!cJSON_IsNumber(tick_direction))
    {
    goto end; //Numeric
    }

    // user_trade->matching_id
    cJSON *matching_id = cJSON_GetObjectItemCaseSensitive(user_tradeJSON, "matching_id");
    if (!matching_id) {
        goto end;
    }

    
    if(!cJSON_IsString(matching_id))
    {
    goto end; //String
    }

    // user_trade->liquidity
    cJSON *liquidity = cJSON_GetObjectItemCaseSensitive(user_tradeJSON, "liquidity");
    liquidity_e liquidityVariable;
    if (liquidity) { 
    if(!cJSON_IsString(liquidity))
    {
    goto end; //Enum
    }
    liquidityVariable = liquidityuser_trade_FromString(liquidity->valuestring);
    }


    user_trade_local_var = user_trade_create (
        directionVariable,
        fee_currencyVariable,
        strdup(order_id->valuestring),
        timestamp->valuedouble,
        price->valuedouble,
        iv ? iv->valuedouble : 0,
        strdup(trade_id->valuestring),
        fee->valuedouble,
        order_type ? order_typeVariable : -1,
        trade_seq->valuedouble,
        self_trade->valueint,
        stateVariable,
        label ? strdup(label->valuestring) : NULL,
        index_price->valuedouble,
        amount->valuedouble,
        strdup(instrument_name->valuestring),
        tick_direction->valuedouble,
        strdup(matching_id->valuestring),
        liquidity ? liquidityVariable : -1
        );

    return user_trade_local_var;
end:
    return NULL;

}
