#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "key_number_pair.h"



key_number_pair_t *key_number_pair_create(
    char *name,
    double value
    ) {
	key_number_pair_t *key_number_pair_local_var = malloc(sizeof(key_number_pair_t));
    if (!key_number_pair_local_var) {
        return NULL;
    }
	key_number_pair_local_var->name = name;
	key_number_pair_local_var->value = value;

	return key_number_pair_local_var;
}


void key_number_pair_free(key_number_pair_t *key_number_pair) {
    listEntry_t *listEntry;
    free(key_number_pair->name);
	free(key_number_pair);
}

cJSON *key_number_pair_convertToJSON(key_number_pair_t *key_number_pair) {
	cJSON *item = cJSON_CreateObject();

	// key_number_pair->name
    if (!key_number_pair->name) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "name", key_number_pair->name) == NULL) {
    goto fail; //String
    }


	// key_number_pair->value
    if (!key_number_pair->value) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "value", key_number_pair->value) == NULL) {
    goto fail; //Numeric
    }

	return item;
fail:
	if (item) {
        cJSON_Delete(item);
    }
	return NULL;
}

key_number_pair_t *key_number_pair_parseFromJSON(cJSON *key_number_pairJSON){

    key_number_pair_t *key_number_pair_local_var = NULL;

    // key_number_pair->name
    cJSON *name = cJSON_GetObjectItemCaseSensitive(key_number_pairJSON, "name");
    if (!name) {
        goto end;
    }

    
    if(!cJSON_IsString(name))
    {
    goto end; //String
    }

    // key_number_pair->value
    cJSON *value = cJSON_GetObjectItemCaseSensitive(key_number_pairJSON, "value");
    if (!value) {
        goto end;
    }

    
    if(!cJSON_IsNumber(value))
    {
    goto end; //Numeric
    }


    key_number_pair_local_var = key_number_pair_create (
        strdup(name->valuestring),
        value->valuedouble
        );

    return key_number_pair_local_var;
end:
    return NULL;

}
