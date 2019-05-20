#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "types.h"



types_t *types_create(
    ) {
	types_t *types_local_var = malloc(sizeof(types_t));
    if (!types_local_var) {
        return NULL;
    }

	return types_local_var;
}


void types_free(types_t *types) {
    listEntry_t *listEntry;
	free(types);
}

cJSON *types_convertToJSON(types_t *types) {
	cJSON *item = cJSON_CreateObject();
	return item;
fail:
	if (item) {
        cJSON_Delete(item);
    }
	return NULL;
}

types_t *types_parseFromJSON(cJSON *typesJSON){

    types_t *types_local_var = NULL;


    types_local_var = types_create (
        );

    return types_local_var;
end:
    return NULL;

}
