/*
 * trades_volumes.h
 *
 * 
 */

#ifndef _trades_volumes_H_
#define _trades_volumes_H_

#include <string.h>
#include "../external/cJSON.h"
#include "../include/list.h"
#include "../include/keyValuePair.h"

                typedef enum  {  btc_usd, eth_usd } currency_pair_e;

        char* currency_pair_ToString(currency_pair_e currency_pair);

        currency_pair_e currency_pair_FromString(char* currency_pair);


typedef struct trades_volumes_t {
    double calls_volume; //numeric
    double puts_volume; //numeric
    currency_pair_e currency_pair; //enum
    double futures_volume; //numeric

} trades_volumes_t;

trades_volumes_t *trades_volumes_create(
    double calls_volume,
    double puts_volume,
    currency_pair_e currency_pair,
    double futures_volume
);

void trades_volumes_free(trades_volumes_t *trades_volumes);

trades_volumes_t *trades_volumes_parseFromJSON(cJSON *trades_volumesJSON);

cJSON *trades_volumes_convertToJSON(trades_volumes_t *trades_volumes);

#endif /* _trades_volumes_H_ */

