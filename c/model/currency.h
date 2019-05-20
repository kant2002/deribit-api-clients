/*
 * currency.h
 *
 * 
 */

#ifndef _currency_H_
#define _currency_H_

#include <string.h>
#include "../external/cJSON.h"
#include "../include/list.h"
#include "../include/keyValuePair.h"
#include "currency_withdrawal_priorities.h"

                typedef enum  {  BITCOIN, ETHER } coin_type_e;

        char* coin_type_ToString(coin_type_e coin_type);

        coin_type_e coin_type_FromString(char* coin_type);


typedef struct currency_t {
    int min_confirmations; //numeric
    double min_withdrawal_fee; //numeric
    int disabled_deposit_address_creation; //boolean
    char *currency; // string
    char *currency_long; // string
    double withdrawal_fee; //numeric
    int fee_precision; //numeric
    list_t *withdrawal_priorities; //nonprimitive container
    coin_type_e coin_type; //enum

} currency_t;

currency_t *currency_create(
    int min_confirmations,
    double min_withdrawal_fee,
    int disabled_deposit_address_creation,
    char *currency,
    char *currency_long,
    double withdrawal_fee,
    int fee_precision,
    list_t *withdrawal_priorities,
    coin_type_e coin_type
);

void currency_free(currency_t *currency);

currency_t *currency_parseFromJSON(cJSON *currencyJSON);

cJSON *currency_convertToJSON(currency_t *currency);

#endif /* _currency_H_ */

