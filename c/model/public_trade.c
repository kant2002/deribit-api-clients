#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "public_trade.h"


    char* directionpublic_trade_ToString(direction_e direction){
    char *directionArray[] =  { "buy","sell" };
        return directionArray[direction];
    }

    direction_e directionpublic_trade_FromString(char* direction){
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
    char* tick_directionpublic_trade_ToString(tick_direction_e tick_direction){
    char *tick_directionArray[] =  { "_0","_1","_2","_3" };
        return tick_directionArray[tick_direction];
    }

    tick_direction_e tick_directionpublic_trade_FromString(char* tick_direction){
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

public_trade_t *public_trade_create(
    direction_e direction,
    int tick_direction,
    int timestamp,
    double price,
    int trade_seq,
    char *trade_id,
    double iv,
    double index_price,
    double amount,
    char *instrument_name
    ) {
	public_trade_t *public_trade_local_var = malloc(sizeof(public_trade_t));
    if (!public_trade_local_var) {
        return NULL;
    }
	public_trade_local_var->direction = direction;
	public_trade_local_var->tick_direction = tick_direction;
	public_trade_local_var->timestamp = timestamp;
	public_trade_local_var->price = price;
	public_trade_local_var->trade_seq = trade_seq;
	public_trade_local_var->trade_id = trade_id;
	public_trade_local_var->iv = iv;
	public_trade_local_var->index_price = index_price;
	public_trade_local_var->amount = amount;
	public_trade_local_var->instrument_name = instrument_name;

	return public_trade_local_var;
}


void public_trade_free(public_trade_t *public_trade) {
    listEntry_t *listEntry;
    free(public_trade->trade_id);
    free(public_trade->instrument_name);
	free(public_trade);
}

cJSON *public_trade_convertToJSON(public_trade_t *public_trade) {
	cJSON *item = cJSON_CreateObject();

	// public_trade->direction
    
    if(cJSON_AddStringToObject(item, "direction", directionpublic_trade_ToString(public_trade->direction)) == NULL)
    {
    goto fail; //Enum
    }


	// public_trade->tick_direction
    
    if(cJSON_AddNumberToObject(item, "tick_direction", public_trade->tick_direction) == NULL) {
    goto fail; //Numeric
    }


	// public_trade->timestamp
    if (!public_trade->timestamp) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "timestamp", public_trade->timestamp) == NULL) {
    goto fail; //Numeric
    }


	// public_trade->price
    if (!public_trade->price) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "price", public_trade->price) == NULL) {
    goto fail; //Numeric
    }


	// public_trade->trade_seq
    if (!public_trade->trade_seq) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "trade_seq", public_trade->trade_seq) == NULL) {
    goto fail; //Numeric
    }


	// public_trade->trade_id
    if (!public_trade->trade_id) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "trade_id", public_trade->trade_id) == NULL) {
    goto fail; //String
    }


	// public_trade->iv
    if(public_trade->iv) { 
    if(cJSON_AddNumberToObject(item, "iv", public_trade->iv) == NULL) {
    goto fail; //Numeric
    }
     } 


	// public_trade->index_price
    if (!public_trade->index_price) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "index_price", public_trade->index_price) == NULL) {
    goto fail; //Numeric
    }


	// public_trade->amount
    if (!public_trade->amount) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "amount", public_trade->amount) == NULL) {
    goto fail; //Numeric
    }


	// public_trade->instrument_name
    if (!public_trade->instrument_name) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "instrument_name", public_trade->instrument_name) == NULL) {
    goto fail; //String
    }

	return item;
fail:
	if (item) {
        cJSON_Delete(item);
    }
	return NULL;
}

public_trade_t *public_trade_parseFromJSON(cJSON *public_tradeJSON){

    public_trade_t *public_trade_local_var = NULL;

    // public_trade->direction
    cJSON *direction = cJSON_GetObjectItemCaseSensitive(public_tradeJSON, "direction");
    if (!direction) {
        goto end;
    }

    direction_e directionVariable;
    
    if(!cJSON_IsString(direction))
    {
    goto end; //Enum
    }
    directionVariable = directionpublic_trade_FromString(direction->valuestring);

    // public_trade->tick_direction
    cJSON *tick_direction = cJSON_GetObjectItemCaseSensitive(public_tradeJSON, "tick_direction");
    if (!tick_direction) {
        goto end;
    }

    
    if(!cJSON_IsNumber(tick_direction))
    {
    goto end; //Numeric
    }

    // public_trade->timestamp
    cJSON *timestamp = cJSON_GetObjectItemCaseSensitive(public_tradeJSON, "timestamp");
    if (!timestamp) {
        goto end;
    }

    
    if(!cJSON_IsNumber(timestamp))
    {
    goto end; //Numeric
    }

    // public_trade->price
    cJSON *price = cJSON_GetObjectItemCaseSensitive(public_tradeJSON, "price");
    if (!price) {
        goto end;
    }

    
    if(!cJSON_IsNumber(price))
    {
    goto end; //Numeric
    }

    // public_trade->trade_seq
    cJSON *trade_seq = cJSON_GetObjectItemCaseSensitive(public_tradeJSON, "trade_seq");
    if (!trade_seq) {
        goto end;
    }

    
    if(!cJSON_IsNumber(trade_seq))
    {
    goto end; //Numeric
    }

    // public_trade->trade_id
    cJSON *trade_id = cJSON_GetObjectItemCaseSensitive(public_tradeJSON, "trade_id");
    if (!trade_id) {
        goto end;
    }

    
    if(!cJSON_IsString(trade_id))
    {
    goto end; //String
    }

    // public_trade->iv
    cJSON *iv = cJSON_GetObjectItemCaseSensitive(public_tradeJSON, "iv");
    if (iv) { 
    if(!cJSON_IsNumber(iv))
    {
    goto end; //Numeric
    }
    }

    // public_trade->index_price
    cJSON *index_price = cJSON_GetObjectItemCaseSensitive(public_tradeJSON, "index_price");
    if (!index_price) {
        goto end;
    }

    
    if(!cJSON_IsNumber(index_price))
    {
    goto end; //Numeric
    }

    // public_trade->amount
    cJSON *amount = cJSON_GetObjectItemCaseSensitive(public_tradeJSON, "amount");
    if (!amount) {
        goto end;
    }

    
    if(!cJSON_IsNumber(amount))
    {
    goto end; //Numeric
    }

    // public_trade->instrument_name
    cJSON *instrument_name = cJSON_GetObjectItemCaseSensitive(public_tradeJSON, "instrument_name");
    if (!instrument_name) {
        goto end;
    }

    
    if(!cJSON_IsString(instrument_name))
    {
    goto end; //String
    }


    public_trade_local_var = public_trade_create (
        directionVariable,
        tick_direction->valuedouble,
        timestamp->valuedouble,
        price->valuedouble,
        trade_seq->valuedouble,
        strdup(trade_id->valuestring),
        iv ? iv->valuedouble : 0,
        index_price->valuedouble,
        amount->valuedouble,
        strdup(instrument_name->valuestring)
        );

    return public_trade_local_var;
end:
    return NULL;

}
