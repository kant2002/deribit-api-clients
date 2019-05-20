#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "currency.h"


    char* coin_typecurrency_ToString(coin_type_e coin_type){
    char *coin_typeArray[] =  { "BITCOIN","ETHER" };
        return coin_typeArray[coin_type];
    }

    coin_type_e coin_typecurrency_FromString(char* coin_type){
    int stringToReturn = 0;
    char *coin_typeArray[] =  { "BITCOIN","ETHER" };
    size_t sizeofArray = sizeof(coin_typeArray) / sizeof(coin_typeArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(coin_type, coin_typeArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }

currency_t *currency_create(
    int min_confirmations,
    double min_withdrawal_fee,
    int disabled_deposit_address_creation,
    char *currency,
    char *currency_long,
    double withdrawal_fee,
    int fee_precision,
    list_t *withdrawal_priorities,
    coin_type_e coin_type
    ) {
	currency_t *currency_local_var = malloc(sizeof(currency_t));
    if (!currency_local_var) {
        return NULL;
    }
	currency_local_var->min_confirmations = min_confirmations;
	currency_local_var->min_withdrawal_fee = min_withdrawal_fee;
	currency_local_var->disabled_deposit_address_creation = disabled_deposit_address_creation;
	currency_local_var->currency = currency;
	currency_local_var->currency_long = currency_long;
	currency_local_var->withdrawal_fee = withdrawal_fee;
	currency_local_var->fee_precision = fee_precision;
	currency_local_var->withdrawal_priorities = withdrawal_priorities;
	currency_local_var->coin_type = coin_type;

	return currency_local_var;
}


void currency_free(currency_t *currency) {
    listEntry_t *listEntry;
    free(currency->currency);
    free(currency->currency_long);
	list_ForEach(listEntry, currency->withdrawal_priorities) {
		currency_withdrawal_priorities_free(listEntry->data);
	}
	list_free(currency->withdrawal_priorities);
	free(currency);
}

cJSON *currency_convertToJSON(currency_t *currency) {
	cJSON *item = cJSON_CreateObject();

	// currency->min_confirmations
    if(currency->min_confirmations) { 
    if(cJSON_AddNumberToObject(item, "min_confirmations", currency->min_confirmations) == NULL) {
    goto fail; //Numeric
    }
     } 


	// currency->min_withdrawal_fee
    if(currency->min_withdrawal_fee) { 
    if(cJSON_AddNumberToObject(item, "min_withdrawal_fee", currency->min_withdrawal_fee) == NULL) {
    goto fail; //Numeric
    }
     } 


	// currency->disabled_deposit_address_creation
    if(currency->disabled_deposit_address_creation) { 
    if(cJSON_AddBoolToObject(item, "disabled_deposit_address_creation", currency->disabled_deposit_address_creation) == NULL) {
    goto fail; //Bool
    }
     } 


	// currency->currency
    if (!currency->currency) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "currency", currency->currency) == NULL) {
    goto fail; //String
    }


	// currency->currency_long
    if (!currency->currency_long) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "currency_long", currency->currency_long) == NULL) {
    goto fail; //String
    }


	// currency->withdrawal_fee
    if (!currency->withdrawal_fee) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "withdrawal_fee", currency->withdrawal_fee) == NULL) {
    goto fail; //Numeric
    }


	// currency->fee_precision
    if(currency->fee_precision) { 
    if(cJSON_AddNumberToObject(item, "fee_precision", currency->fee_precision) == NULL) {
    goto fail; //Numeric
    }
     } 


	// currency->withdrawal_priorities
    if(currency->withdrawal_priorities) { 
    cJSON *withdrawal_priorities = cJSON_AddArrayToObject(item, "withdrawal_priorities");
    if(withdrawal_priorities == NULL) {
    goto fail; //nonprimitive container
    }

    listEntry_t *withdrawal_prioritiesListEntry;
    if (currency->withdrawal_priorities) {
    list_ForEach(withdrawal_prioritiesListEntry, currency->withdrawal_priorities) {
    cJSON *itemLocal = currency_withdrawal_priorities_convertToJSON(withdrawal_prioritiesListEntry->data);
    if(itemLocal == NULL) {
    goto fail;
    }
    cJSON_AddItemToArray(withdrawal_priorities, itemLocal);
    }
    }
     } 


	// currency->coin_type
    
    if(cJSON_AddStringToObject(item, "coin_type", coin_typecurrency_ToString(currency->coin_type)) == NULL)
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

currency_t *currency_parseFromJSON(cJSON *currencyJSON){

    currency_t *currency_local_var = NULL;

    // currency->min_confirmations
    cJSON *min_confirmations = cJSON_GetObjectItemCaseSensitive(currencyJSON, "min_confirmations");
    if (min_confirmations) { 
    if(!cJSON_IsNumber(min_confirmations))
    {
    goto end; //Numeric
    }
    }

    // currency->min_withdrawal_fee
    cJSON *min_withdrawal_fee = cJSON_GetObjectItemCaseSensitive(currencyJSON, "min_withdrawal_fee");
    if (min_withdrawal_fee) { 
    if(!cJSON_IsNumber(min_withdrawal_fee))
    {
    goto end; //Numeric
    }
    }

    // currency->disabled_deposit_address_creation
    cJSON *disabled_deposit_address_creation = cJSON_GetObjectItemCaseSensitive(currencyJSON, "disabled_deposit_address_creation");
    if (disabled_deposit_address_creation) { 
    if(!cJSON_IsBool(disabled_deposit_address_creation))
    {
    goto end; //Bool
    }
    }

    // currency->currency
    cJSON *currency = cJSON_GetObjectItemCaseSensitive(currencyJSON, "currency");
    if (!currency) {
        goto end;
    }

    
    if(!cJSON_IsString(currency))
    {
    goto end; //String
    }

    // currency->currency_long
    cJSON *currency_long = cJSON_GetObjectItemCaseSensitive(currencyJSON, "currency_long");
    if (!currency_long) {
        goto end;
    }

    
    if(!cJSON_IsString(currency_long))
    {
    goto end; //String
    }

    // currency->withdrawal_fee
    cJSON *withdrawal_fee = cJSON_GetObjectItemCaseSensitive(currencyJSON, "withdrawal_fee");
    if (!withdrawal_fee) {
        goto end;
    }

    
    if(!cJSON_IsNumber(withdrawal_fee))
    {
    goto end; //Numeric
    }

    // currency->fee_precision
    cJSON *fee_precision = cJSON_GetObjectItemCaseSensitive(currencyJSON, "fee_precision");
    if (fee_precision) { 
    if(!cJSON_IsNumber(fee_precision))
    {
    goto end; //Numeric
    }
    }

    // currency->withdrawal_priorities
    cJSON *withdrawal_priorities = cJSON_GetObjectItemCaseSensitive(currencyJSON, "withdrawal_priorities");
    list_t *withdrawal_prioritiesList;
    if (withdrawal_priorities) { 
    cJSON *withdrawal_priorities_local_nonprimitive;
    if(!cJSON_IsArray(withdrawal_priorities)){
        goto end; //nonprimitive container
    }

    withdrawal_prioritiesList = list_create();

    cJSON_ArrayForEach(withdrawal_priorities_local_nonprimitive,withdrawal_priorities )
    {
        if(!cJSON_IsObject(withdrawal_priorities_local_nonprimitive)){
            goto end;
        }
        currency_withdrawal_priorities_t *withdrawal_prioritiesItem = currency_withdrawal_priorities_parseFromJSON(withdrawal_priorities_local_nonprimitive);

        list_addElement(withdrawal_prioritiesList, withdrawal_prioritiesItem);
    }
    }

    // currency->coin_type
    cJSON *coin_type = cJSON_GetObjectItemCaseSensitive(currencyJSON, "coin_type");
    if (!coin_type) {
        goto end;
    }

    coin_type_e coin_typeVariable;
    
    if(!cJSON_IsString(coin_type))
    {
    goto end; //Enum
    }
    coin_typeVariable = coin_typecurrency_FromString(coin_type->valuestring);


    currency_local_var = currency_create (
        min_confirmations ? min_confirmations->valuedouble : 0,
        min_withdrawal_fee ? min_withdrawal_fee->valuedouble : 0,
        disabled_deposit_address_creation ? disabled_deposit_address_creation->valueint : 0,
        strdup(currency->valuestring),
        strdup(currency_long->valuestring),
        withdrawal_fee->valuedouble,
        fee_precision ? fee_precision->valuedouble : 0,
        withdrawal_priorities ? withdrawal_prioritiesList : NULL,
        coin_typeVariable
        );

    return currency_local_var;
end:
    return NULL;

}
