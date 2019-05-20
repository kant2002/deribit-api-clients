#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "currency_withdrawal_priorities.h"



currency_withdrawal_priorities_t *currency_withdrawal_priorities_create(
    char *name,
    double value
    ) {
	currency_withdrawal_priorities_t *currency_withdrawal_priorities_local_var = malloc(sizeof(currency_withdrawal_priorities_t));
    if (!currency_withdrawal_priorities_local_var) {
        return NULL;
    }
	currency_withdrawal_priorities_local_var->name = name;
	currency_withdrawal_priorities_local_var->value = value;

	return currency_withdrawal_priorities_local_var;
}


void currency_withdrawal_priorities_free(currency_withdrawal_priorities_t *currency_withdrawal_priorities) {
    listEntry_t *listEntry;
    free(currency_withdrawal_priorities->name);
	free(currency_withdrawal_priorities);
}

cJSON *currency_withdrawal_priorities_convertToJSON(currency_withdrawal_priorities_t *currency_withdrawal_priorities) {
	cJSON *item = cJSON_CreateObject();

	// currency_withdrawal_priorities->name
    if (!currency_withdrawal_priorities->name) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "name", currency_withdrawal_priorities->name) == NULL) {
    goto fail; //String
    }


	// currency_withdrawal_priorities->value
    if (!currency_withdrawal_priorities->value) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "value", currency_withdrawal_priorities->value) == NULL) {
    goto fail; //Numeric
    }

	return item;
fail:
	if (item) {
        cJSON_Delete(item);
    }
	return NULL;
}

currency_withdrawal_priorities_t *currency_withdrawal_priorities_parseFromJSON(cJSON *currency_withdrawal_prioritiesJSON){

    currency_withdrawal_priorities_t *currency_withdrawal_priorities_local_var = NULL;

    // currency_withdrawal_priorities->name
    cJSON *name = cJSON_GetObjectItemCaseSensitive(currency_withdrawal_prioritiesJSON, "name");
    if (!name) {
        goto end;
    }

    
    if(!cJSON_IsString(name))
    {
    goto end; //String
    }

    // currency_withdrawal_priorities->value
    cJSON *value = cJSON_GetObjectItemCaseSensitive(currency_withdrawal_prioritiesJSON, "value");
    if (!value) {
        goto end;
    }

    
    if(!cJSON_IsNumber(value))
    {
    goto end; //Numeric
    }


    currency_withdrawal_priorities_local_var = currency_withdrawal_priorities_create (
        strdup(name->valuestring),
        value->valuedouble
        );

    return currency_withdrawal_priorities_local_var;
end:
    return NULL;

}
