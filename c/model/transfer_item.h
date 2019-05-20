/*
 * transfer_item.h
 *
 * 
 */

#ifndef _transfer_item_H_
#define _transfer_item_H_

#include <string.h>
#include "../external/cJSON.h"
#include "../include/list.h"
#include "../include/keyValuePair.h"

                typedef enum  {  payment, income } direction_e;

        char* direction_ToString(direction_e direction);

        direction_e direction_FromString(char* direction);
                typedef enum  {  BTC, ETH } currency_e;

        char* currency_ToString(currency_e currency);

        currency_e currency_FromString(char* currency);
                typedef enum  {  prepared, confirmed, cancelled, waiting_for_admin, rejection_reason } state_e;

        char* state_ToString(state_e state);

        state_e state_FromString(char* state);
                typedef enum  {  user, subaccount } type_e;

        char* type_ToString(type_e type);

        type_e type_FromString(char* type);


typedef struct transfer_item_t {
    int updated_timestamp; //numeric
    direction_e direction; //enum
    double amount; //numeric
    char *other_side; // string
    currency_e currency; //enum
    state_e state; //enum
    int created_timestamp; //numeric
    type_e type; //enum
    int id; //numeric

} transfer_item_t;

transfer_item_t *transfer_item_create(
    int updated_timestamp,
    direction_e direction,
    double amount,
    char *other_side,
    currency_e currency,
    state_e state,
    int created_timestamp,
    type_e type,
    int id
);

void transfer_item_free(transfer_item_t *transfer_item);

transfer_item_t *transfer_item_parseFromJSON(cJSON *transfer_itemJSON);

cJSON *transfer_item_convertToJSON(transfer_item_t *transfer_item);

#endif /* _transfer_item_H_ */

