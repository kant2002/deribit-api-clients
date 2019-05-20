/*
 * address_book_item.h
 *
 * 
 */

#ifndef _address_book_item_H_
#define _address_book_item_H_

#include <string.h>
#include "../external/cJSON.h"
#include "../include/list.h"
#include "../include/keyValuePair.h"

                typedef enum  {  BTC, ETH } currency_e;

        char* currency_ToString(currency_e currency);

        currency_e currency_FromString(char* currency);
                typedef enum  {  transfer, withdrawal } type_e;

        char* type_ToString(type_e type);

        type_e type_FromString(char* type);


typedef struct address_book_item_t {
    currency_e currency; //enum
    char *address; // string
    type_e type; //enum
    int creation_timestamp; //numeric

} address_book_item_t;

address_book_item_t *address_book_item_create(
    currency_e currency,
    char *address,
    type_e type,
    int creation_timestamp
);

void address_book_item_free(address_book_item_t *address_book_item);

address_book_item_t *address_book_item_parseFromJSON(cJSON *address_book_itemJSON);

cJSON *address_book_item_convertToJSON(address_book_item_t *address_book_item);

#endif /* _address_book_item_H_ */

