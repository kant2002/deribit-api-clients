/*
 * deposit.h
 *
 * 
 */

#ifndef _deposit_H_
#define _deposit_H_

#include <string.h>
#include "../external/cJSON.h"
#include "../include/list.h"
#include "../include/keyValuePair.h"

                typedef enum  {  pending, completed, rejected, replaced } state_e;

        char* state_ToString(state_e state);

        state_e state_FromString(char* state);
                typedef enum  {  BTC, ETH } currency_e;

        char* currency_ToString(currency_e currency);

        currency_e currency_FromString(char* currency);


typedef struct deposit_t {
    int updated_timestamp; //numeric
    state_e state; //enum
    int received_timestamp; //numeric
    currency_e currency; //enum
    char *address; // string
    double amount; //numeric
    char *transaction_id; // string

} deposit_t;

deposit_t *deposit_create(
    int updated_timestamp,
    state_e state,
    int received_timestamp,
    currency_e currency,
    char *address,
    double amount,
    char *transaction_id
);

void deposit_free(deposit_t *deposit);

deposit_t *deposit_parseFromJSON(cJSON *depositJSON);

cJSON *deposit_convertToJSON(deposit_t *deposit);

#endif /* _deposit_H_ */

