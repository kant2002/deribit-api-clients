#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "withdrawal.h"


    char* currencywithdrawal_ToString(currency_e currency){
    char *currencyArray[] =  { "BTC","ETH" };
        return currencyArray[currency];
    }

    currency_e currencywithdrawal_FromString(char* currency){
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
    char* statewithdrawal_ToString(state_e state){
    char *stateArray[] =  { "unconfirmed","confirmed","cancelled","completed","interrupted","rejected" };
        return stateArray[state];
    }

    state_e statewithdrawal_FromString(char* state){
    int stringToReturn = 0;
    char *stateArray[] =  { "unconfirmed","confirmed","cancelled","completed","interrupted","rejected" };
    size_t sizeofArray = sizeof(stateArray) / sizeof(stateArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(state, stateArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }

withdrawal_t *withdrawal_create(
    int updated_timestamp,
    double fee,
    int confirmed_timestamp,
    double amount,
    double priority,
    currency_e currency,
    state_e state,
    char *address,
    int created_timestamp,
    int id,
    char *transaction_id
    ) {
	withdrawal_t *withdrawal_local_var = malloc(sizeof(withdrawal_t));
    if (!withdrawal_local_var) {
        return NULL;
    }
	withdrawal_local_var->updated_timestamp = updated_timestamp;
	withdrawal_local_var->fee = fee;
	withdrawal_local_var->confirmed_timestamp = confirmed_timestamp;
	withdrawal_local_var->amount = amount;
	withdrawal_local_var->priority = priority;
	withdrawal_local_var->currency = currency;
	withdrawal_local_var->state = state;
	withdrawal_local_var->address = address;
	withdrawal_local_var->created_timestamp = created_timestamp;
	withdrawal_local_var->id = id;
	withdrawal_local_var->transaction_id = transaction_id;

	return withdrawal_local_var;
}


void withdrawal_free(withdrawal_t *withdrawal) {
    listEntry_t *listEntry;
    free(withdrawal->address);
    free(withdrawal->transaction_id);
	free(withdrawal);
}

cJSON *withdrawal_convertToJSON(withdrawal_t *withdrawal) {
	cJSON *item = cJSON_CreateObject();

	// withdrawal->updated_timestamp
    if (!withdrawal->updated_timestamp) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "updated_timestamp", withdrawal->updated_timestamp) == NULL) {
    goto fail; //Numeric
    }


	// withdrawal->fee
    if(withdrawal->fee) { 
    if(cJSON_AddNumberToObject(item, "fee", withdrawal->fee) == NULL) {
    goto fail; //Numeric
    }
     } 


	// withdrawal->confirmed_timestamp
    if(withdrawal->confirmed_timestamp) { 
    if(cJSON_AddNumberToObject(item, "confirmed_timestamp", withdrawal->confirmed_timestamp) == NULL) {
    goto fail; //Numeric
    }
     } 


	// withdrawal->amount
    if (!withdrawal->amount) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "amount", withdrawal->amount) == NULL) {
    goto fail; //Numeric
    }


	// withdrawal->priority
    if(withdrawal->priority) { 
    if(cJSON_AddNumberToObject(item, "priority", withdrawal->priority) == NULL) {
    goto fail; //Numeric
    }
     } 


	// withdrawal->currency
    
    if(cJSON_AddStringToObject(item, "currency", currencywithdrawal_ToString(withdrawal->currency)) == NULL)
    {
    goto fail; //Enum
    }


	// withdrawal->state
    
    if(cJSON_AddStringToObject(item, "state", statewithdrawal_ToString(withdrawal->state)) == NULL)
    {
    goto fail; //Enum
    }


	// withdrawal->address
    if (!withdrawal->address) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "address", withdrawal->address) == NULL) {
    goto fail; //String
    }


	// withdrawal->created_timestamp
    if(withdrawal->created_timestamp) { 
    if(cJSON_AddNumberToObject(item, "created_timestamp", withdrawal->created_timestamp) == NULL) {
    goto fail; //Numeric
    }
     } 


	// withdrawal->id
    if(withdrawal->id) { 
    if(cJSON_AddNumberToObject(item, "id", withdrawal->id) == NULL) {
    goto fail; //Numeric
    }
     } 


	// withdrawal->transaction_id
    if (!withdrawal->transaction_id) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "transaction_id", withdrawal->transaction_id) == NULL) {
    goto fail; //String
    }

	return item;
fail:
	if (item) {
        cJSON_Delete(item);
    }
	return NULL;
}

withdrawal_t *withdrawal_parseFromJSON(cJSON *withdrawalJSON){

    withdrawal_t *withdrawal_local_var = NULL;

    // withdrawal->updated_timestamp
    cJSON *updated_timestamp = cJSON_GetObjectItemCaseSensitive(withdrawalJSON, "updated_timestamp");
    if (!updated_timestamp) {
        goto end;
    }

    
    if(!cJSON_IsNumber(updated_timestamp))
    {
    goto end; //Numeric
    }

    // withdrawal->fee
    cJSON *fee = cJSON_GetObjectItemCaseSensitive(withdrawalJSON, "fee");
    if (fee) { 
    if(!cJSON_IsNumber(fee))
    {
    goto end; //Numeric
    }
    }

    // withdrawal->confirmed_timestamp
    cJSON *confirmed_timestamp = cJSON_GetObjectItemCaseSensitive(withdrawalJSON, "confirmed_timestamp");
    if (confirmed_timestamp) { 
    if(!cJSON_IsNumber(confirmed_timestamp))
    {
    goto end; //Numeric
    }
    }

    // withdrawal->amount
    cJSON *amount = cJSON_GetObjectItemCaseSensitive(withdrawalJSON, "amount");
    if (!amount) {
        goto end;
    }

    
    if(!cJSON_IsNumber(amount))
    {
    goto end; //Numeric
    }

    // withdrawal->priority
    cJSON *priority = cJSON_GetObjectItemCaseSensitive(withdrawalJSON, "priority");
    if (priority) { 
    if(!cJSON_IsNumber(priority))
    {
    goto end; //Numeric
    }
    }

    // withdrawal->currency
    cJSON *currency = cJSON_GetObjectItemCaseSensitive(withdrawalJSON, "currency");
    if (!currency) {
        goto end;
    }

    currency_e currencyVariable;
    
    if(!cJSON_IsString(currency))
    {
    goto end; //Enum
    }
    currencyVariable = currencywithdrawal_FromString(currency->valuestring);

    // withdrawal->state
    cJSON *state = cJSON_GetObjectItemCaseSensitive(withdrawalJSON, "state");
    if (!state) {
        goto end;
    }

    state_e stateVariable;
    
    if(!cJSON_IsString(state))
    {
    goto end; //Enum
    }
    stateVariable = statewithdrawal_FromString(state->valuestring);

    // withdrawal->address
    cJSON *address = cJSON_GetObjectItemCaseSensitive(withdrawalJSON, "address");
    if (!address) {
        goto end;
    }

    
    if(!cJSON_IsString(address))
    {
    goto end; //String
    }

    // withdrawal->created_timestamp
    cJSON *created_timestamp = cJSON_GetObjectItemCaseSensitive(withdrawalJSON, "created_timestamp");
    if (created_timestamp) { 
    if(!cJSON_IsNumber(created_timestamp))
    {
    goto end; //Numeric
    }
    }

    // withdrawal->id
    cJSON *id = cJSON_GetObjectItemCaseSensitive(withdrawalJSON, "id");
    if (id) { 
    if(!cJSON_IsNumber(id))
    {
    goto end; //Numeric
    }
    }

    // withdrawal->transaction_id
    cJSON *transaction_id = cJSON_GetObjectItemCaseSensitive(withdrawalJSON, "transaction_id");
    if (!transaction_id) {
        goto end;
    }

    
    if(!cJSON_IsString(transaction_id))
    {
    goto end; //String
    }


    withdrawal_local_var = withdrawal_create (
        updated_timestamp->valuedouble,
        fee ? fee->valuedouble : 0,
        confirmed_timestamp ? confirmed_timestamp->valuedouble : 0,
        amount->valuedouble,
        priority ? priority->valuedouble : 0,
        currencyVariable,
        stateVariable,
        strdup(address->valuestring),
        created_timestamp ? created_timestamp->valuedouble : 0,
        id ? id->valuedouble : 0,
        strdup(transaction_id->valuestring)
        );

    return withdrawal_local_var;
end:
    return NULL;

}
