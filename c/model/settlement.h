/*
 * settlement.h
 *
 * 
 */

#ifndef _settlement_H_
#define _settlement_H_

#include <string.h>
#include "../external/cJSON.h"
#include "../include/list.h"
#include "../include/keyValuePair.h"

                typedef enum  {  settlement, delivery, bankruptcy } type_e;

        char* type_ToString(type_e type);

        type_e type_FromString(char* type);


typedef struct settlement_t {
    double session_profit_loss; //numeric
    double mark_price; //numeric
    double funding; //numeric
    double socialized; //numeric
    double session_bankrupcy; //numeric
    int timestamp; //numeric
    double profit_loss; //numeric
    double funded; //numeric
    double index_price; //numeric
    double session_tax; //numeric
    double session_tax_rate; //numeric
    char *instrument_name; // string
    double position; //numeric
    type_e type; //enum

} settlement_t;

settlement_t *settlement_create(
    double session_profit_loss,
    double mark_price,
    double funding,
    double socialized,
    double session_bankrupcy,
    int timestamp,
    double profit_loss,
    double funded,
    double index_price,
    double session_tax,
    double session_tax_rate,
    char *instrument_name,
    double position,
    type_e type
);

void settlement_free(settlement_t *settlement);

settlement_t *settlement_parseFromJSON(cJSON *settlementJSON);

cJSON *settlement_convertToJSON(settlement_t *settlement);

#endif /* _settlement_H_ */

