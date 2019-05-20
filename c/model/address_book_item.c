#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "address_book_item.h"


    char* currencyaddress_book_item_ToString(currency_e currency){
    char *currencyArray[] =  { "BTC","ETH" };
        return currencyArray[currency];
    }

    currency_e currencyaddress_book_item_FromString(char* currency){
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
    char* typeaddress_book_item_ToString(type_e type){
    char *typeArray[] =  { "transfer","withdrawal" };
        return typeArray[type];
    }

    type_e typeaddress_book_item_FromString(char* type){
    int stringToReturn = 0;
    char *typeArray[] =  { "transfer","withdrawal" };
    size_t sizeofArray = sizeof(typeArray) / sizeof(typeArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(type, typeArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }

address_book_item_t *address_book_item_create(
    currency_e currency,
    char *address,
    type_e type,
    int creation_timestamp
    ) {
	address_book_item_t *address_book_item_local_var = malloc(sizeof(address_book_item_t));
    if (!address_book_item_local_var) {
        return NULL;
    }
	address_book_item_local_var->currency = currency;
	address_book_item_local_var->address = address;
	address_book_item_local_var->type = type;
	address_book_item_local_var->creation_timestamp = creation_timestamp;

	return address_book_item_local_var;
}


void address_book_item_free(address_book_item_t *address_book_item) {
    listEntry_t *listEntry;
    free(address_book_item->address);
	free(address_book_item);
}

cJSON *address_book_item_convertToJSON(address_book_item_t *address_book_item) {
	cJSON *item = cJSON_CreateObject();

	// address_book_item->currency
    
    if(cJSON_AddStringToObject(item, "currency", currencyaddress_book_item_ToString(address_book_item->currency)) == NULL)
    {
    goto fail; //Enum
    }


	// address_book_item->address
    if (!address_book_item->address) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "address", address_book_item->address) == NULL) {
    goto fail; //String
    }


	// address_book_item->type
    
    if(cJSON_AddStringToObject(item, "type", typeaddress_book_item_ToString(address_book_item->type)) == NULL)
    {
    goto fail; //Enum
    }
    


	// address_book_item->creation_timestamp
    if (!address_book_item->creation_timestamp) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "creation_timestamp", address_book_item->creation_timestamp) == NULL) {
    goto fail; //Numeric
    }

	return item;
fail:
	if (item) {
        cJSON_Delete(item);
    }
	return NULL;
}

address_book_item_t *address_book_item_parseFromJSON(cJSON *address_book_itemJSON){

    address_book_item_t *address_book_item_local_var = NULL;

    // address_book_item->currency
    cJSON *currency = cJSON_GetObjectItemCaseSensitive(address_book_itemJSON, "currency");
    if (!currency) {
        goto end;
    }

    currency_e currencyVariable;
    
    if(!cJSON_IsString(currency))
    {
    goto end; //Enum
    }
    currencyVariable = currencyaddress_book_item_FromString(currency->valuestring);

    // address_book_item->address
    cJSON *address = cJSON_GetObjectItemCaseSensitive(address_book_itemJSON, "address");
    if (!address) {
        goto end;
    }

    
    if(!cJSON_IsString(address))
    {
    goto end; //String
    }

    // address_book_item->type
    cJSON *type = cJSON_GetObjectItemCaseSensitive(address_book_itemJSON, "type");
    type_e typeVariable;
    if (type) { 
    if(!cJSON_IsString(type))
    {
    goto end; //Enum
    }
    typeVariable = typeaddress_book_item_FromString(type->valuestring);
    }

    // address_book_item->creation_timestamp
    cJSON *creation_timestamp = cJSON_GetObjectItemCaseSensitive(address_book_itemJSON, "creation_timestamp");
    if (!creation_timestamp) {
        goto end;
    }

    
    if(!cJSON_IsNumber(creation_timestamp))
    {
    goto end; //Numeric
    }


    address_book_item_local_var = address_book_item_create (
        currencyVariable,
        strdup(address->valuestring),
        type ? typeVariable : -1,
        creation_timestamp->valuedouble
        );

    return address_book_item_local_var;
end:
    return NULL;

}
