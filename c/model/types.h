/*
 * types.h
 *
 * 
 */

#ifndef _types_H_
#define _types_H_

#include <string.h>
#include "../external/cJSON.h"
#include "../include/list.h"
#include "../include/keyValuePair.h"



typedef struct types_t {

} types_t;

types_t *types_create(
);

void types_free(types_t *types);

types_t *types_parseFromJSON(cJSON *typesJSON);

cJSON *types_convertToJSON(types_t *types);

#endif /* _types_H_ */

