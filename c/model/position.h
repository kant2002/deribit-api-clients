/*
 * position.h
 *
 * 
 */

#ifndef _position_H_
#define _position_H_

#include <string.h>
#include "../external/cJSON.h"
#include "../include/list.h"
#include "../include/keyValuePair.h"

                typedef enum  {  buy, sell } direction_e;

        char* direction_ToString(direction_e direction);

        direction_e direction_FromString(char* direction);
                typedef enum  {  future, option } kind_e;

        char* kind_ToString(kind_e kind);

        kind_e kind_FromString(char* kind);


typedef struct position_t {
    direction_e direction; //enum
    double average_price_usd; //numeric
    double estimated_liquidation_price; //numeric
    double floating_profit_loss; //numeric
    double floating_profit_loss_usd; //numeric
    double open_orders_margin; //numeric
    double total_profit_loss; //numeric
    double realized_profit_loss; //numeric
    double delta; //numeric
    double initial_margin; //numeric
    double size; //numeric
    double maintenance_margin; //numeric
    kind_e kind; //enum
    double mark_price; //numeric
    double average_price; //numeric
    double settlement_price; //numeric
    double index_price; //numeric
    char *instrument_name; // string
    double size_currency; //numeric

} position_t;

position_t *position_create(
    direction_e direction,
    double average_price_usd,
    double estimated_liquidation_price,
    double floating_profit_loss,
    double floating_profit_loss_usd,
    double open_orders_margin,
    double total_profit_loss,
    double realized_profit_loss,
    double delta,
    double initial_margin,
    double size,
    double maintenance_margin,
    kind_e kind,
    double mark_price,
    double average_price,
    double settlement_price,
    double index_price,
    char *instrument_name,
    double size_currency
);

void position_free(position_t *position);

position_t *position_parseFromJSON(cJSON *positionJSON);

cJSON *position_convertToJSON(position_t *position);

#endif /* _position_H_ */

