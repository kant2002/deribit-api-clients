/*
 * order.h
 *
 * 
 */

#ifndef _order_H_
#define _order_H_

#include <string.h>
#include "../external/cJSON.h"
#include "../include/list.h"
#include "../include/keyValuePair.h"

                typedef enum  {  buy, sell } direction_e;

        char* direction_ToString(direction_e direction);

        direction_e direction_FromString(char* direction);
                typedef enum  {  good_til_cancelled, fill_or_kill, immediate_or_cancel } time_in_force_e;

        char* time_in_force_ToString(time_in_force_e time_in_force);

        time_in_force_e time_in_force_FromString(char* time_in_force);
                typedef enum  {  open, filled, rejected, cancelled, untriggered, triggered } order_state_e;

        char* order_state_ToString(order_state_e order_state);

        order_state_e order_state_FromString(char* order_state);
                typedef enum  {  usd, implv } advanced_e;

        char* advanced_ToString(advanced_e advanced);

        advanced_e advanced_FromString(char* advanced);
                typedef enum  {  market, limit, stop_market, stop_limit } order_type_e;

        char* order_type_ToString(order_type_e order_type);

        order_type_e order_type_FromString(char* order_type);
                typedef enum  {  market } original_order_type_e;

        char* original_order_type_ToString(original_order_type_e original_order_type);

        original_order_type_e original_order_type_FromString(char* original_order_type);
                typedef enum  {  index_price, mark_price, last_price } trigger_e;

        char* trigger_ToString(trigger_e trigger);

        trigger_e trigger_FromString(char* trigger);


typedef struct order_t {
    direction_e direction; //enum
    int reduce_only; //boolean
    int triggered; //boolean
    char *order_id; // string
    double price; //numeric
    time_in_force_e time_in_force; //enum
    int api; //boolean
    order_state_e order_state; //enum
    double implv; //numeric
    advanced_e advanced; //enum
    int post_only; //boolean
    double usd; //numeric
    double stop_price; //numeric
    order_type_e order_type; //enum
    int last_update_timestamp; //numeric
    original_order_type_e original_order_type; //enum
    double max_show; //numeric
    double profit_loss; //numeric
    int is_liquidation; //boolean
    double filled_amount; //numeric
    char *label; // string
    double commission; //numeric
    double amount; //numeric
    trigger_e trigger; //enum
    char *instrument_name; // string
    int creation_timestamp; //numeric
    double average_price; //numeric

} order_t;

order_t *order_create(
    direction_e direction,
    int reduce_only,
    int triggered,
    char *order_id,
    double price,
    time_in_force_e time_in_force,
    int api,
    order_state_e order_state,
    double implv,
    advanced_e advanced,
    int post_only,
    double usd,
    double stop_price,
    order_type_e order_type,
    int last_update_timestamp,
    original_order_type_e original_order_type,
    double max_show,
    double profit_loss,
    int is_liquidation,
    double filled_amount,
    char *label,
    double commission,
    double amount,
    trigger_e trigger,
    char *instrument_name,
    int creation_timestamp,
    double average_price
);

void order_free(order_t *order);

order_t *order_parseFromJSON(cJSON *orderJSON);

cJSON *order_convertToJSON(order_t *order);

#endif /* _order_H_ */

