#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "trades_volumes.h"


    char* currency_pairtrades_volumes_ToString(currency_pair_e currency_pair){
    char *currency_pairArray[] =  { "btc_usd","eth_usd" };
        return currency_pairArray[currency_pair];
    }

    currency_pair_e currency_pairtrades_volumes_FromString(char* currency_pair){
    int stringToReturn = 0;
    char *currency_pairArray[] =  { "btc_usd","eth_usd" };
    size_t sizeofArray = sizeof(currency_pairArray) / sizeof(currency_pairArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(currency_pair, currency_pairArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }

trades_volumes_t *trades_volumes_create(
    double calls_volume,
    double puts_volume,
    currency_pair_e currency_pair,
    double futures_volume
    ) {
	trades_volumes_t *trades_volumes_local_var = malloc(sizeof(trades_volumes_t));
    if (!trades_volumes_local_var) {
        return NULL;
    }
	trades_volumes_local_var->calls_volume = calls_volume;
	trades_volumes_local_var->puts_volume = puts_volume;
	trades_volumes_local_var->currency_pair = currency_pair;
	trades_volumes_local_var->futures_volume = futures_volume;

	return trades_volumes_local_var;
}


void trades_volumes_free(trades_volumes_t *trades_volumes) {
    listEntry_t *listEntry;
	free(trades_volumes);
}

cJSON *trades_volumes_convertToJSON(trades_volumes_t *trades_volumes) {
	cJSON *item = cJSON_CreateObject();

	// trades_volumes->calls_volume
    if (!trades_volumes->calls_volume) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "calls_volume", trades_volumes->calls_volume) == NULL) {
    goto fail; //Numeric
    }


	// trades_volumes->puts_volume
    if (!trades_volumes->puts_volume) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "puts_volume", trades_volumes->puts_volume) == NULL) {
    goto fail; //Numeric
    }


	// trades_volumes->currency_pair
    
    if(cJSON_AddStringToObject(item, "currency_pair", currency_pairtrades_volumes_ToString(trades_volumes->currency_pair)) == NULL)
    {
    goto fail; //Enum
    }


	// trades_volumes->futures_volume
    if (!trades_volumes->futures_volume) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "futures_volume", trades_volumes->futures_volume) == NULL) {
    goto fail; //Numeric
    }

	return item;
fail:
	if (item) {
        cJSON_Delete(item);
    }
	return NULL;
}

trades_volumes_t *trades_volumes_parseFromJSON(cJSON *trades_volumesJSON){

    trades_volumes_t *trades_volumes_local_var = NULL;

    // trades_volumes->calls_volume
    cJSON *calls_volume = cJSON_GetObjectItemCaseSensitive(trades_volumesJSON, "calls_volume");
    if (!calls_volume) {
        goto end;
    }

    
    if(!cJSON_IsNumber(calls_volume))
    {
    goto end; //Numeric
    }

    // trades_volumes->puts_volume
    cJSON *puts_volume = cJSON_GetObjectItemCaseSensitive(trades_volumesJSON, "puts_volume");
    if (!puts_volume) {
        goto end;
    }

    
    if(!cJSON_IsNumber(puts_volume))
    {
    goto end; //Numeric
    }

    // trades_volumes->currency_pair
    cJSON *currency_pair = cJSON_GetObjectItemCaseSensitive(trades_volumesJSON, "currency_pair");
    if (!currency_pair) {
        goto end;
    }

    currency_pair_e currency_pairVariable;
    
    if(!cJSON_IsString(currency_pair))
    {
    goto end; //Enum
    }
    currency_pairVariable = currency_pairtrades_volumes_FromString(currency_pair->valuestring);

    // trades_volumes->futures_volume
    cJSON *futures_volume = cJSON_GetObjectItemCaseSensitive(trades_volumesJSON, "futures_volume");
    if (!futures_volume) {
        goto end;
    }

    
    if(!cJSON_IsNumber(futures_volume))
    {
    goto end; //Numeric
    }


    trades_volumes_local_var = trades_volumes_create (
        calls_volume->valuedouble,
        puts_volume->valuedouble,
        currency_pairVariable,
        futures_volume->valuedouble
        );

    return trades_volumes_local_var;
end:
    return NULL;

}
