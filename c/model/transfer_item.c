#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "transfer_item.h"


    char* directiontransfer_item_ToString(direction_e direction){
    char *directionArray[] =  { "payment","income" };
        return directionArray[direction];
    }

    direction_e directiontransfer_item_FromString(char* direction){
    int stringToReturn = 0;
    char *directionArray[] =  { "payment","income" };
    size_t sizeofArray = sizeof(directionArray) / sizeof(directionArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(direction, directionArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }
    char* currencytransfer_item_ToString(currency_e currency){
    char *currencyArray[] =  { "BTC","ETH" };
        return currencyArray[currency];
    }

    currency_e currencytransfer_item_FromString(char* currency){
    int stringToReturn = 0;
    char *currencyArray[] =  { "BTC","ETH" };
    size_t sizeofArray = sizeof(currencyArray) / sizeof(currencyArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(currency, currencyArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }
    char* statetransfer_item_ToString(state_e state){
    char *stateArray[] =  { "prepared","confirmed","cancelled","waiting_for_admin","rejection_reason" };
        return stateArray[state];
    }

    state_e statetransfer_item_FromString(char* state){
    int stringToReturn = 0;
    char *stateArray[] =  { "prepared","confirmed","cancelled","waiting_for_admin","rejection_reason" };
    size_t sizeofArray = sizeof(stateArray) / sizeof(stateArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(state, stateArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }
    char* typetransfer_item_ToString(type_e type){
    char *typeArray[] =  { "user","subaccount" };
        return typeArray[type];
    }

    type_e typetransfer_item_FromString(char* type){
    int stringToReturn = 0;
    char *typeArray[] =  { "user","subaccount" };
    size_t sizeofArray = sizeof(typeArray) / sizeof(typeArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(type, typeArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }

transfer_item_t *transfer_item_create(
    int updated_timestamp,
    direction_e direction,
    double amount,
    char *other_side,
    currency_e currency,
    state_e state,
    int created_timestamp,
    type_e type,
    int id
    ) {
	transfer_item_t *transfer_item_local_var = malloc(sizeof(transfer_item_t));
    if (!transfer_item_local_var) {
        return NULL;
    }
	transfer_item_local_var->updated_timestamp = updated_timestamp;
	transfer_item_local_var->direction = direction;
	transfer_item_local_var->amount = amount;
	transfer_item_local_var->other_side = other_side;
	transfer_item_local_var->currency = currency;
	transfer_item_local_var->state = state;
	transfer_item_local_var->created_timestamp = created_timestamp;
	transfer_item_local_var->type = type;
	transfer_item_local_var->id = id;

	return transfer_item_local_var;
}


void transfer_item_free(transfer_item_t *transfer_item) {
    listEntry_t *listEntry;
    free(transfer_item->other_side);
	free(transfer_item);
}

cJSON *transfer_item_convertToJSON(transfer_item_t *transfer_item) {
	cJSON *item = cJSON_CreateObject();

	// transfer_item->updated_timestamp
    if (!transfer_item->updated_timestamp) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "updated_timestamp", transfer_item->updated_timestamp) == NULL) {
    goto fail; //Numeric
    }


	// transfer_item->direction
    
    if(cJSON_AddStringToObject(item, "direction", directiontransfer_item_ToString(transfer_item->direction)) == NULL)
    {
    goto fail; //Enum
    }
    


	// transfer_item->amount
    if (!transfer_item->amount) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "amount", transfer_item->amount) == NULL) {
    goto fail; //Numeric
    }


	// transfer_item->other_side
    if (!transfer_item->other_side) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "other_side", transfer_item->other_side) == NULL) {
    goto fail; //String
    }


	// transfer_item->currency
    
    if(cJSON_AddStringToObject(item, "currency", currencytransfer_item_ToString(transfer_item->currency)) == NULL)
    {
    goto fail; //Enum
    }


	// transfer_item->state
    
    if(cJSON_AddStringToObject(item, "state", statetransfer_item_ToString(transfer_item->state)) == NULL)
    {
    goto fail; //Enum
    }


	// transfer_item->created_timestamp
    if (!transfer_item->created_timestamp) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "created_timestamp", transfer_item->created_timestamp) == NULL) {
    goto fail; //Numeric
    }


	// transfer_item->type
    
    if(cJSON_AddStringToObject(item, "type", typetransfer_item_ToString(transfer_item->type)) == NULL)
    {
    goto fail; //Enum
    }


	// transfer_item->id
    if (!transfer_item->id) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "id", transfer_item->id) == NULL) {
    goto fail; //Numeric
    }

	return item;
fail:
	if (item) {
        cJSON_Delete(item);
    }
	return NULL;
}

transfer_item_t *transfer_item_parseFromJSON(cJSON *transfer_itemJSON){

    transfer_item_t *transfer_item_local_var = NULL;

    // transfer_item->updated_timestamp
    cJSON *updated_timestamp = cJSON_GetObjectItemCaseSensitive(transfer_itemJSON, "updated_timestamp");
    if (!updated_timestamp) {
        goto end;
    }

    
    if(!cJSON_IsNumber(updated_timestamp))
    {
    goto end; //Numeric
    }

    // transfer_item->direction
    cJSON *direction = cJSON_GetObjectItemCaseSensitive(transfer_itemJSON, "direction");
    direction_e directionVariable;
    if (direction) { 
    if(!cJSON_IsString(direction))
    {
    goto end; //Enum
    }
    directionVariable = directiontransfer_item_FromString(direction->valuestring);
    }

    // transfer_item->amount
    cJSON *amount = cJSON_GetObjectItemCaseSensitive(transfer_itemJSON, "amount");
    if (!amount) {
        goto end;
    }

    
    if(!cJSON_IsNumber(amount))
    {
    goto end; //Numeric
    }

    // transfer_item->other_side
    cJSON *other_side = cJSON_GetObjectItemCaseSensitive(transfer_itemJSON, "other_side");
    if (!other_side) {
        goto end;
    }

    
    if(!cJSON_IsString(other_side))
    {
    goto end; //String
    }

    // transfer_item->currency
    cJSON *currency = cJSON_GetObjectItemCaseSensitive(transfer_itemJSON, "currency");
    if (!currency) {
        goto end;
    }

    currency_e currencyVariable;
    
    if(!cJSON_IsString(currency))
    {
    goto end; //Enum
    }
    currencyVariable = currencytransfer_item_FromString(currency->valuestring);

    // transfer_item->state
    cJSON *state = cJSON_GetObjectItemCaseSensitive(transfer_itemJSON, "state");
    if (!state) {
        goto end;
    }

    state_e stateVariable;
    
    if(!cJSON_IsString(state))
    {
    goto end; //Enum
    }
    stateVariable = statetransfer_item_FromString(state->valuestring);

    // transfer_item->created_timestamp
    cJSON *created_timestamp = cJSON_GetObjectItemCaseSensitive(transfer_itemJSON, "created_timestamp");
    if (!created_timestamp) {
        goto end;
    }

    
    if(!cJSON_IsNumber(created_timestamp))
    {
    goto end; //Numeric
    }

    // transfer_item->type
    cJSON *type = cJSON_GetObjectItemCaseSensitive(transfer_itemJSON, "type");
    if (!type) {
        goto end;
    }

    type_e typeVariable;
    
    if(!cJSON_IsString(type))
    {
    goto end; //Enum
    }
    typeVariable = typetransfer_item_FromString(type->valuestring);

    // transfer_item->id
    cJSON *id = cJSON_GetObjectItemCaseSensitive(transfer_itemJSON, "id");
    if (!id) {
        goto end;
    }

    
    if(!cJSON_IsNumber(id))
    {
    goto end; //Numeric
    }


    transfer_item_local_var = transfer_item_create (
        updated_timestamp->valuedouble,
        direction ? directionVariable : -1,
        amount->valuedouble,
        strdup(other_side->valuestring),
        currencyVariable,
        stateVariable,
        created_timestamp->valuedouble,
        typeVariable,
        id->valuedouble
        );

    return transfer_item_local_var;
end:
    return NULL;

}
