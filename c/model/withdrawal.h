/*
 * withdrawal.h
 *
 * 
 */

#ifndef _withdrawal_H_
#define _withdrawal_H_

#include <string.h>
#include "../external/cJSON.h"
#include "../include/list.h"
#include "../include/keyValuePair.h"

                typedef enum  {  BTC, ETH } currency_e;

        char* currency_ToString(currency_e currency);

        currency_e currency_FromString(char* currency);
                typedef enum  {  unconfirmed, confirmed, cancelled, completed, interrupted, rejected } state_e;

        char* state_ToString(state_e state);

        state_e state_FromString(char* state);


typedef struct withdrawal_t {
    int updated_timestamp; //numeric
    double fee; //numeric
    int confirmed_timestamp; //numeric
    double amount; //numeric
    double priority; //numeric
    currency_e currency; //enum
    state_e state; //enum
    char *address; // string
    int created_timestamp; //numeric
    int id; //numeric
    char *transaction_id; // string

} withdrawal_t;

withdrawal_t *withdrawal_create(
    int updated_timestamp,
    double fee,
    int confirmed_timestamp,
    double amount,
    double priority,
    currency_e currency,
    state_e state,
    char *address,
    int created_timestamp,
    int id,
    char *transaction_id
);

void withdrawal_free(withdrawal_t *withdrawal);

withdrawal_t *withdrawal_parseFromJSON(cJSON *withdrawalJSON);

cJSON *withdrawal_convertToJSON(withdrawal_t *withdrawal);

#endif /* _withdrawal_H_ */

