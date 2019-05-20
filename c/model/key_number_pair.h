/*
 * key_number_pair.h
 *
 * 
 */

#ifndef _key_number_pair_H_
#define _key_number_pair_H_

#include <string.h>
#include "../external/cJSON.h"
#include "../include/list.h"
#include "../include/keyValuePair.h"



typedef struct key_number_pair_t {
    char *name; // string
    double value; //numeric

} key_number_pair_t;

key_number_pair_t *key_number_pair_create(
    char *name,
    double value
);

void key_number_pair_free(key_number_pair_t *key_number_pair);

key_number_pair_t *key_number_pair_parseFromJSON(cJSON *key_number_pairJSON);

cJSON *key_number_pair_convertToJSON(key_number_pair_t *key_number_pair);

#endif /* _key_number_pair_H_ */

