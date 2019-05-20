/*
 * user_trade.h
 *
 * 
 */

#ifndef _user_trade_H_
#define _user_trade_H_

#include <string.h>
#include "../external/cJSON.h"
#include "../include/list.h"
#include "../include/keyValuePair.h"

                typedef enum  {  buy, sell } direction_e;

        char* direction_ToString(direction_e direction);

        direction_e direction_FromString(char* direction);
                typedef enum  {  BTC, ETH } fee_currency_e;

        char* fee_currency_ToString(fee_currency_e fee_currency);

        fee_currency_e fee_currency_FromString(char* fee_currency);
                typedef enum  {  limit, market, liquidation } order_type_e;

        char* order_type_ToString(order_type_e order_type);

        order_type_e order_type_FromString(char* order_type);
                typedef enum  {  open, filled, rejected, cancelled, untriggered, archive } state_e;

        char* state_ToString(state_e state);

        state_e state_FromString(char* state);
                typedef enum  {  _0, _1, _2, _3 } tick_direction_e;

        char* tick_direction_ToString(tick_direction_e tick_direction);

        tick_direction_e tick_direction_FromString(char* tick_direction);
                typedef enum  {  M, T } liquidity_e;

        char* liquidity_ToString(liquidity_e liquidity);

        liquidity_e liquidity_FromString(char* liquidity);


typedef struct user_trade_t {
    direction_e direction; //enum
    fee_currency_e fee_currency; //enum
    char *order_id; // string
    int timestamp; //numeric
    double price; //numeric
    double iv; //numeric
    char *trade_id; // string
    double fee; //numeric
    order_type_e order_type; //enum
    int trade_seq; //numeric
    int self_trade; //boolean
    state_e state; //enum
    char *label; // string
    double index_price; //numeric
    double amount; //numeric
    char *instrument_name; // string
    int tick_direction; //numeric
    char *matching_id; // string
    liquidity_e liquidity; //enum

} user_trade_t;

user_trade_t *user_trade_create(
    direction_e direction,
    fee_currency_e fee_currency,
    char *order_id,
    int timestamp,
    double price,
    double iv,
    char *trade_id,
    double fee,
    order_type_e order_type,
    int trade_seq,
    int self_trade,
    state_e state,
    char *label,
    double index_price,
    double amount,
    char *instrument_name,
    int tick_direction,
    char *matching_id,
    liquidity_e liquidity
);

void user_trade_free(user_trade_t *user_trade);

user_trade_t *user_trade_parseFromJSON(cJSON *user_tradeJSON);

cJSON *user_trade_convertToJSON(user_trade_t *user_trade);

#endif /* _user_trade_H_ */

