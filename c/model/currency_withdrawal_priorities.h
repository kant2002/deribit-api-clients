/*
 * currency_withdrawal_priorities.h
 *
 * 
 */

#ifndef _currency_withdrawal_priorities_H_
#define _currency_withdrawal_priorities_H_

#include <string.h>
#include "../external/cJSON.h"
#include "../include/list.h"
#include "../include/keyValuePair.h"



typedef struct currency_withdrawal_priorities_t {
    char *name; // string
    double value; //numeric

} currency_withdrawal_priorities_t;

currency_withdrawal_priorities_t *currency_withdrawal_priorities_create(
    char *name,
    double value
);

void currency_withdrawal_priorities_free(currency_withdrawal_priorities_t *currency_withdrawal_priorities);

currency_withdrawal_priorities_t *currency_withdrawal_priorities_parseFromJSON(cJSON *currency_withdrawal_prioritiesJSON);

cJSON *currency_withdrawal_priorities_convertToJSON(currency_withdrawal_priorities_t *currency_withdrawal_priorities);

#endif /* _currency_withdrawal_priorities_H_ */

