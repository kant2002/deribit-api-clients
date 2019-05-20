/*
 * order_id_initial_margin_pair.h
 *
 * 
 */

#ifndef _order_id_initial_margin_pair_H_
#define _order_id_initial_margin_pair_H_

#include <string.h>
#include "../external/cJSON.h"
#include "../include/list.h"
#include "../include/keyValuePair.h"



typedef struct order_id_initial_margin_pair_t {
    char *order_id; // string
    double initial_margin; //numeric

} order_id_initial_margin_pair_t;

order_id_initial_margin_pair_t *order_id_initial_margin_pair_create(
    char *order_id,
    double initial_margin
);

void order_id_initial_margin_pair_free(order_id_initial_margin_pair_t *order_id_initial_margin_pair);

order_id_initial_margin_pair_t *order_id_initial_margin_pair_parseFromJSON(cJSON *order_id_initial_margin_pairJSON);

cJSON *order_id_initial_margin_pair_convertToJSON(order_id_initial_margin_pair_t *order_id_initial_margin_pair);

#endif /* _order_id_initial_margin_pair_H_ */

