/*
 * public_trade.h
 *
 * 
 */

#ifndef _public_trade_H_
#define _public_trade_H_

#include <string.h>
#include "../external/cJSON.h"
#include "../include/list.h"
#include "../include/keyValuePair.h"

                typedef enum  {  buy, sell } direction_e;

        char* direction_ToString(direction_e direction);

        direction_e direction_FromString(char* direction);
                typedef enum  {  _0, _1, _2, _3 } tick_direction_e;

        char* tick_direction_ToString(tick_direction_e tick_direction);

        tick_direction_e tick_direction_FromString(char* tick_direction);


typedef struct public_trade_t {
    direction_e direction; //enum
    int tick_direction; //numeric
    int timestamp; //numeric
    double price; //numeric
    int trade_seq; //numeric
    char *trade_id; // string
    double iv; //numeric
    double index_price; //numeric
    double amount; //numeric
    char *instrument_name; // string

} public_trade_t;

public_trade_t *public_trade_create(
    direction_e direction,
    int tick_direction,
    int timestamp,
    double price,
    int trade_seq,
    char *trade_id,
    double iv,
    double index_price,
    double amount,
    char *instrument_name
);

void public_trade_free(public_trade_t *public_trade);

public_trade_t *public_trade_parseFromJSON(cJSON *public_tradeJSON);

cJSON *public_trade_convertToJSON(public_trade_t *public_trade);

#endif /* _public_trade_H_ */

