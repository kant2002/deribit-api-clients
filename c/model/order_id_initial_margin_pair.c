#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "order_id_initial_margin_pair.h"



order_id_initial_margin_pair_t *order_id_initial_margin_pair_create(
    char *order_id,
    double initial_margin
    ) {
	order_id_initial_margin_pair_t *order_id_initial_margin_pair_local_var = malloc(sizeof(order_id_initial_margin_pair_t));
    if (!order_id_initial_margin_pair_local_var) {
        return NULL;
    }
	order_id_initial_margin_pair_local_var->order_id = order_id;
	order_id_initial_margin_pair_local_var->initial_margin = initial_margin;

	return order_id_initial_margin_pair_local_var;
}


void order_id_initial_margin_pair_free(order_id_initial_margin_pair_t *order_id_initial_margin_pair) {
    listEntry_t *listEntry;
    free(order_id_initial_margin_pair->order_id);
	free(order_id_initial_margin_pair);
}

cJSON *order_id_initial_margin_pair_convertToJSON(order_id_initial_margin_pair_t *order_id_initial_margin_pair) {
	cJSON *item = cJSON_CreateObject();

	// order_id_initial_margin_pair->order_id
    if (!order_id_initial_margin_pair->order_id) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "order_id", order_id_initial_margin_pair->order_id) == NULL) {
    goto fail; //String
    }


	// order_id_initial_margin_pair->initial_margin
    if (!order_id_initial_margin_pair->initial_margin) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "initial_margin", order_id_initial_margin_pair->initial_margin) == NULL) {
    goto fail; //Numeric
    }

	return item;
fail:
	if (item) {
        cJSON_Delete(item);
    }
	return NULL;
}

order_id_initial_margin_pair_t *order_id_initial_margin_pair_parseFromJSON(cJSON *order_id_initial_margin_pairJSON){

    order_id_initial_margin_pair_t *order_id_initial_margin_pair_local_var = NULL;

    // order_id_initial_margin_pair->order_id
    cJSON *order_id = cJSON_GetObjectItemCaseSensitive(order_id_initial_margin_pairJSON, "order_id");
    if (!order_id) {
        goto end;
    }

    
    if(!cJSON_IsString(order_id))
    {
    goto end; //String
    }

    // order_id_initial_margin_pair->initial_margin
    cJSON *initial_margin = cJSON_GetObjectItemCaseSensitive(order_id_initial_margin_pairJSON, "initial_margin");
    if (!initial_margin) {
        goto end;
    }

    
    if(!cJSON_IsNumber(initial_margin))
    {
    goto end; //Numeric
    }


    order_id_initial_margin_pair_local_var = order_id_initial_margin_pair_create (
        strdup(order_id->valuestring),
        initial_margin->valuedouble
        );

    return order_id_initial_margin_pair_local_var;
end:
    return NULL;

}
