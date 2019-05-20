/*
 * instrument.h
 *
 * 
 */

#ifndef _instrument_H_
#define _instrument_H_

#include <string.h>
#include "../external/cJSON.h"
#include "../include/list.h"
#include "../include/keyValuePair.h"

                typedef enum  {  USD } quote_currency_e;

        char* quote_currency_ToString(quote_currency_e quote_currency);

        quote_currency_e quote_currency_FromString(char* quote_currency);
                typedef enum  {  future, option } kind_e;

        char* kind_ToString(kind_e kind);

        kind_e kind_FromString(char* kind);
                typedef enum  {  call, put } option_type_e;

        char* option_type_ToString(option_type_e option_type);

        option_type_e option_type_FromString(char* option_type);
                typedef enum  {  month, week, perpetual } settlement_period_e;

        char* settlement_period_ToString(settlement_period_e settlement_period);

        settlement_period_e settlement_period_FromString(char* settlement_period);
                typedef enum  {  BTC, ETH } base_currency_e;

        char* base_currency_ToString(base_currency_e base_currency);

        base_currency_e base_currency_FromString(char* base_currency);


typedef struct instrument_t {
    quote_currency_e quote_currency; //enum
    kind_e kind; //enum
    double tick_size; //numeric
    int contract_size; //numeric
    int is_active; //boolean
    option_type_e option_type; //enum
    double min_trade_amount; //numeric
    char *instrument_name; // string
    settlement_period_e settlement_period; //enum
    double strike; //numeric
    base_currency_e base_currency; //enum
    int creation_timestamp; //numeric
    int expiration_timestamp; //numeric

} instrument_t;

instrument_t *instrument_create(
    quote_currency_e quote_currency,
    kind_e kind,
    double tick_size,
    int contract_size,
    int is_active,
    option_type_e option_type,
    double min_trade_amount,
    char *instrument_name,
    settlement_period_e settlement_period,
    double strike,
    base_currency_e base_currency,
    int creation_timestamp,
    int expiration_timestamp
);

void instrument_free(instrument_t *instrument);

instrument_t *instrument_parseFromJSON(cJSON *instrumentJSON);

cJSON *instrument_convertToJSON(instrument_t *instrument);

#endif /* _instrument_H_ */

