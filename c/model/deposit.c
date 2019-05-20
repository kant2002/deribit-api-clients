#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "deposit.h"


    char* statedeposit_ToString(state_e state){
    char *stateArray[] =  { "pending","completed","rejected","replaced" };
        return stateArray[state];
    }

    state_e statedeposit_FromString(char* state){
    int stringToReturn = 0;
    char *stateArray[] =  { "pending","completed","rejected","replaced" };
    size_t sizeofArray = sizeof(stateArray) / sizeof(stateArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(state, stateArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }
    char* currencydeposit_ToString(currency_e currency){
    char *currencyArray[] =  { "BTC","ETH" };
        return currencyArray[currency];
    }

    currency_e currencydeposit_FromString(char* currency){
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

deposit_t *deposit_create(
    int updated_timestamp,
    state_e state,
    int received_timestamp,
    currency_e currency,
    char *address,
    double amount,
    char *transaction_id
    ) {
	deposit_t *deposit_local_var = malloc(sizeof(deposit_t));
    if (!deposit_local_var) {
        return NULL;
    }
	deposit_local_var->updated_timestamp = updated_timestamp;
	deposit_local_var->state = state;
	deposit_local_var->received_timestamp = received_timestamp;
	deposit_local_var->currency = currency;
	deposit_local_var->address = address;
	deposit_local_var->amount = amount;
	deposit_local_var->transaction_id = transaction_id;

	return deposit_local_var;
}


void deposit_free(deposit_t *deposit) {
    listEntry_t *listEntry;
    free(deposit->address);
    free(deposit->transaction_id);
	free(deposit);
}

cJSON *deposit_convertToJSON(deposit_t *deposit) {
	cJSON *item = cJSON_CreateObject();

	// deposit->updated_timestamp
    if (!deposit->updated_timestamp) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "updated_timestamp", deposit->updated_timestamp) == NULL) {
    goto fail; //Numeric
    }


	// deposit->state
    
    if(cJSON_AddStringToObject(item, "state", statedeposit_ToString(deposit->state)) == NULL)
    {
    goto fail; //Enum
    }


	// deposit->received_timestamp
    if (!deposit->received_timestamp) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "received_timestamp", deposit->received_timestamp) == NULL) {
    goto fail; //Numeric
    }


	// deposit->currency
    
    if(cJSON_AddStringToObject(item, "currency", currencydeposit_ToString(deposit->currency)) == NULL)
    {
    goto fail; //Enum
    }


	// deposit->address
    if (!deposit->address) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "address", deposit->address) == NULL) {
    goto fail; //String
    }


	// deposit->amount
    if (!deposit->amount) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "amount", deposit->amount) == NULL) {
    goto fail; //Numeric
    }


	// deposit->transaction_id
    if (!deposit->transaction_id) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "transaction_id", deposit->transaction_id) == NULL) {
    goto fail; //String
    }

	return item;
fail:
	if (item) {
        cJSON_Delete(item);
    }
	return NULL;
}

deposit_t *deposit_parseFromJSON(cJSON *depositJSON){

    deposit_t *deposit_local_var = NULL;

    // deposit->updated_timestamp
    cJSON *updated_timestamp = cJSON_GetObjectItemCaseSensitive(depositJSON, "updated_timestamp");
    if (!updated_timestamp) {
        goto end;
    }

    
    if(!cJSON_IsNumber(updated_timestamp))
    {
    goto end; //Numeric
    }

    // deposit->state
    cJSON *state = cJSON_GetObjectItemCaseSensitive(depositJSON, "state");
    if (!state) {
        goto end;
    }

    state_e stateVariable;
    
    if(!cJSON_IsString(state))
    {
    goto end; //Enum
    }
    stateVariable = statedeposit_FromString(state->valuestring);

    // deposit->received_timestamp
    cJSON *received_timestamp = cJSON_GetObjectItemCaseSensitive(depositJSON, "received_timestamp");
    if (!received_timestamp) {
        goto end;
    }

    
    if(!cJSON_IsNumber(received_timestamp))
    {
    goto end; //Numeric
    }

    // deposit->currency
    cJSON *currency = cJSON_GetObjectItemCaseSensitive(depositJSON, "currency");
    if (!currency) {
        goto end;
    }

    currency_e currencyVariable;
    
    if(!cJSON_IsString(currency))
    {
    goto end; //Enum
    }
    currencyVariable = currencydeposit_FromString(currency->valuestring);

    // deposit->address
    cJSON *address = cJSON_GetObjectItemCaseSensitive(depositJSON, "address");
    if (!address) {
        goto end;
    }

    
    if(!cJSON_IsString(address))
    {
    goto end; //String
    }

    // deposit->amount
    cJSON *amount = cJSON_GetObjectItemCaseSensitive(depositJSON, "amount");
    if (!amount) {
        goto end;
    }

    
    if(!cJSON_IsNumber(amount))
    {
    goto end; //Numeric
    }

    // deposit->transaction_id
    cJSON *transaction_id = cJSON_GetObjectItemCaseSensitive(depositJSON, "transaction_id");
    if (!transaction_id) {
        goto end;
    }

    
    if(!cJSON_IsString(transaction_id))
    {
    goto end; //String
    }


    deposit_local_var = deposit_create (
        updated_timestamp->valuedouble,
        stateVariable,
        received_timestamp->valuedouble,
        currencyVariable,
        strdup(address->valuestring),
        amount->valuedouble,
        strdup(transaction_id->valuestring)
        );

    return deposit_local_var;
end:
    return NULL;

}
