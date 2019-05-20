#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "order.h"


    char* directionorder_ToString(direction_e direction){
    char *directionArray[] =  { "buy","sell" };
        return directionArray[direction];
    }

    direction_e directionorder_FromString(char* direction){
    int stringToReturn = 0;
    char *directionArray[] =  { "buy","sell" };
    size_t sizeofArray = sizeof(directionArray) / sizeof(directionArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(direction, directionArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }
    char* time_in_forceorder_ToString(time_in_force_e time_in_force){
    char *time_in_forceArray[] =  { "good_til_cancelled","fill_or_kill","immediate_or_cancel" };
        return time_in_forceArray[time_in_force];
    }

    time_in_force_e time_in_forceorder_FromString(char* time_in_force){
    int stringToReturn = 0;
    char *time_in_forceArray[] =  { "good_til_cancelled","fill_or_kill","immediate_or_cancel" };
    size_t sizeofArray = sizeof(time_in_forceArray) / sizeof(time_in_forceArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(time_in_force, time_in_forceArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }
    char* order_stateorder_ToString(order_state_e order_state){
    char *order_stateArray[] =  { "open","filled","rejected","cancelled","untriggered","triggered" };
        return order_stateArray[order_state];
    }

    order_state_e order_stateorder_FromString(char* order_state){
    int stringToReturn = 0;
    char *order_stateArray[] =  { "open","filled","rejected","cancelled","untriggered","triggered" };
    size_t sizeofArray = sizeof(order_stateArray) / sizeof(order_stateArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(order_state, order_stateArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }
    char* advancedorder_ToString(advanced_e advanced){
    char *advancedArray[] =  { "usd","implv" };
        return advancedArray[advanced];
    }

    advanced_e advancedorder_FromString(char* advanced){
    int stringToReturn = 0;
    char *advancedArray[] =  { "usd","implv" };
    size_t sizeofArray = sizeof(advancedArray) / sizeof(advancedArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(advanced, advancedArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }
    char* order_typeorder_ToString(order_type_e order_type){
    char *order_typeArray[] =  { "market","limit","stop_market","stop_limit" };
        return order_typeArray[order_type];
    }

    order_type_e order_typeorder_FromString(char* order_type){
    int stringToReturn = 0;
    char *order_typeArray[] =  { "market","limit","stop_market","stop_limit" };
    size_t sizeofArray = sizeof(order_typeArray) / sizeof(order_typeArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(order_type, order_typeArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }
    char* original_order_typeorder_ToString(original_order_type_e original_order_type){
    char *original_order_typeArray[] =  { "market" };
        return original_order_typeArray[original_order_type];
    }

    original_order_type_e original_order_typeorder_FromString(char* original_order_type){
    int stringToReturn = 0;
    char *original_order_typeArray[] =  { "market" };
    size_t sizeofArray = sizeof(original_order_typeArray) / sizeof(original_order_typeArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(original_order_type, original_order_typeArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }
    char* triggerorder_ToString(trigger_e trigger){
    char *triggerArray[] =  { "index_price","mark_price","last_price" };
        return triggerArray[trigger];
    }

    trigger_e triggerorder_FromString(char* trigger){
    int stringToReturn = 0;
    char *triggerArray[] =  { "index_price","mark_price","last_price" };
    size_t sizeofArray = sizeof(triggerArray) / sizeof(triggerArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(trigger, triggerArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }

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
    ) {
	order_t *order_local_var = malloc(sizeof(order_t));
    if (!order_local_var) {
        return NULL;
    }
	order_local_var->direction = direction;
	order_local_var->reduce_only = reduce_only;
	order_local_var->triggered = triggered;
	order_local_var->order_id = order_id;
	order_local_var->price = price;
	order_local_var->time_in_force = time_in_force;
	order_local_var->api = api;
	order_local_var->order_state = order_state;
	order_local_var->implv = implv;
	order_local_var->advanced = advanced;
	order_local_var->post_only = post_only;
	order_local_var->usd = usd;
	order_local_var->stop_price = stop_price;
	order_local_var->order_type = order_type;
	order_local_var->last_update_timestamp = last_update_timestamp;
	order_local_var->original_order_type = original_order_type;
	order_local_var->max_show = max_show;
	order_local_var->profit_loss = profit_loss;
	order_local_var->is_liquidation = is_liquidation;
	order_local_var->filled_amount = filled_amount;
	order_local_var->label = label;
	order_local_var->commission = commission;
	order_local_var->amount = amount;
	order_local_var->trigger = trigger;
	order_local_var->instrument_name = instrument_name;
	order_local_var->creation_timestamp = creation_timestamp;
	order_local_var->average_price = average_price;

	return order_local_var;
}


void order_free(order_t *order) {
    listEntry_t *listEntry;
    free(order->order_id);
    free(order->label);
    free(order->instrument_name);
	free(order);
}

cJSON *order_convertToJSON(order_t *order) {
	cJSON *item = cJSON_CreateObject();

	// order->direction
    
    if(cJSON_AddStringToObject(item, "direction", directionorder_ToString(order->direction)) == NULL)
    {
    goto fail; //Enum
    }


	// order->reduce_only
    if(order->reduce_only) { 
    if(cJSON_AddBoolToObject(item, "reduce_only", order->reduce_only) == NULL) {
    goto fail; //Bool
    }
     } 


	// order->triggered
    if(order->triggered) { 
    if(cJSON_AddBoolToObject(item, "triggered", order->triggered) == NULL) {
    goto fail; //Bool
    }
     } 


	// order->order_id
    if (!order->order_id) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "order_id", order->order_id) == NULL) {
    goto fail; //String
    }


	// order->price
    if (!order->price) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "price", order->price) == NULL) {
    goto fail; //Numeric
    }


	// order->time_in_force
    
    if(cJSON_AddStringToObject(item, "time_in_force", time_in_forceorder_ToString(order->time_in_force)) == NULL)
    {
    goto fail; //Enum
    }


	// order->api
    if (!order->api) {
        goto fail;
    }
    
    if(cJSON_AddBoolToObject(item, "api", order->api) == NULL) {
    goto fail; //Bool
    }


	// order->order_state
    
    if(cJSON_AddStringToObject(item, "order_state", order_stateorder_ToString(order->order_state)) == NULL)
    {
    goto fail; //Enum
    }


	// order->implv
    if(order->implv) { 
    if(cJSON_AddNumberToObject(item, "implv", order->implv) == NULL) {
    goto fail; //Numeric
    }
     } 


	// order->advanced
    
    if(cJSON_AddStringToObject(item, "advanced", advancedorder_ToString(order->advanced)) == NULL)
    {
    goto fail; //Enum
    }
    


	// order->post_only
    if (!order->post_only) {
        goto fail;
    }
    
    if(cJSON_AddBoolToObject(item, "post_only", order->post_only) == NULL) {
    goto fail; //Bool
    }


	// order->usd
    if(order->usd) { 
    if(cJSON_AddNumberToObject(item, "usd", order->usd) == NULL) {
    goto fail; //Numeric
    }
     } 


	// order->stop_price
    if(order->stop_price) { 
    if(cJSON_AddNumberToObject(item, "stop_price", order->stop_price) == NULL) {
    goto fail; //Numeric
    }
     } 


	// order->order_type
    
    if(cJSON_AddStringToObject(item, "order_type", order_typeorder_ToString(order->order_type)) == NULL)
    {
    goto fail; //Enum
    }


	// order->last_update_timestamp
    if (!order->last_update_timestamp) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "last_update_timestamp", order->last_update_timestamp) == NULL) {
    goto fail; //Numeric
    }


	// order->original_order_type
    
    if(cJSON_AddStringToObject(item, "original_order_type", original_order_typeorder_ToString(order->original_order_type)) == NULL)
    {
    goto fail; //Enum
    }
    


	// order->max_show
    if (!order->max_show) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "max_show", order->max_show) == NULL) {
    goto fail; //Numeric
    }


	// order->profit_loss
    if(order->profit_loss) { 
    if(cJSON_AddNumberToObject(item, "profit_loss", order->profit_loss) == NULL) {
    goto fail; //Numeric
    }
     } 


	// order->is_liquidation
    if (!order->is_liquidation) {
        goto fail;
    }
    
    if(cJSON_AddBoolToObject(item, "is_liquidation", order->is_liquidation) == NULL) {
    goto fail; //Bool
    }


	// order->filled_amount
    if(order->filled_amount) { 
    if(cJSON_AddNumberToObject(item, "filled_amount", order->filled_amount) == NULL) {
    goto fail; //Numeric
    }
     } 


	// order->label
    if (!order->label) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "label", order->label) == NULL) {
    goto fail; //String
    }


	// order->commission
    if(order->commission) { 
    if(cJSON_AddNumberToObject(item, "commission", order->commission) == NULL) {
    goto fail; //Numeric
    }
     } 


	// order->amount
    if(order->amount) { 
    if(cJSON_AddNumberToObject(item, "amount", order->amount) == NULL) {
    goto fail; //Numeric
    }
     } 


	// order->trigger
    
    if(cJSON_AddStringToObject(item, "trigger", triggerorder_ToString(order->trigger)) == NULL)
    {
    goto fail; //Enum
    }
    


	// order->instrument_name
    if(order->instrument_name) { 
    if(cJSON_AddStringToObject(item, "instrument_name", order->instrument_name) == NULL) {
    goto fail; //String
    }
     } 


	// order->creation_timestamp
    if (!order->creation_timestamp) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "creation_timestamp", order->creation_timestamp) == NULL) {
    goto fail; //Numeric
    }


	// order->average_price
    if(order->average_price) { 
    if(cJSON_AddNumberToObject(item, "average_price", order->average_price) == NULL) {
    goto fail; //Numeric
    }
     } 

	return item;
fail:
	if (item) {
        cJSON_Delete(item);
    }
	return NULL;
}

order_t *order_parseFromJSON(cJSON *orderJSON){

    order_t *order_local_var = NULL;

    // order->direction
    cJSON *direction = cJSON_GetObjectItemCaseSensitive(orderJSON, "direction");
    if (!direction) {
        goto end;
    }

    direction_e directionVariable;
    
    if(!cJSON_IsString(direction))
    {
    goto end; //Enum
    }
    directionVariable = directionorder_FromString(direction->valuestring);

    // order->reduce_only
    cJSON *reduce_only = cJSON_GetObjectItemCaseSensitive(orderJSON, "reduce_only");
    if (reduce_only) { 
    if(!cJSON_IsBool(reduce_only))
    {
    goto end; //Bool
    }
    }

    // order->triggered
    cJSON *triggered = cJSON_GetObjectItemCaseSensitive(orderJSON, "triggered");
    if (triggered) { 
    if(!cJSON_IsBool(triggered))
    {
    goto end; //Bool
    }
    }

    // order->order_id
    cJSON *order_id = cJSON_GetObjectItemCaseSensitive(orderJSON, "order_id");
    if (!order_id) {
        goto end;
    }

    
    if(!cJSON_IsString(order_id))
    {
    goto end; //String
    }

    // order->price
    cJSON *price = cJSON_GetObjectItemCaseSensitive(orderJSON, "price");
    if (!price) {
        goto end;
    }

    
    if(!cJSON_IsNumber(price))
    {
    goto end; //Numeric
    }

    // order->time_in_force
    cJSON *time_in_force = cJSON_GetObjectItemCaseSensitive(orderJSON, "time_in_force");
    if (!time_in_force) {
        goto end;
    }

    time_in_force_e time_in_forceVariable;
    
    if(!cJSON_IsString(time_in_force))
    {
    goto end; //Enum
    }
    time_in_forceVariable = time_in_forceorder_FromString(time_in_force->valuestring);

    // order->api
    cJSON *api = cJSON_GetObjectItemCaseSensitive(orderJSON, "api");
    if (!api) {
        goto end;
    }

    
    if(!cJSON_IsBool(api))
    {
    goto end; //Bool
    }

    // order->order_state
    cJSON *order_state = cJSON_GetObjectItemCaseSensitive(orderJSON, "order_state");
    if (!order_state) {
        goto end;
    }

    order_state_e order_stateVariable;
    
    if(!cJSON_IsString(order_state))
    {
    goto end; //Enum
    }
    order_stateVariable = order_stateorder_FromString(order_state->valuestring);

    // order->implv
    cJSON *implv = cJSON_GetObjectItemCaseSensitive(orderJSON, "implv");
    if (implv) { 
    if(!cJSON_IsNumber(implv))
    {
    goto end; //Numeric
    }
    }

    // order->advanced
    cJSON *advanced = cJSON_GetObjectItemCaseSensitive(orderJSON, "advanced");
    advanced_e advancedVariable;
    if (advanced) { 
    if(!cJSON_IsString(advanced))
    {
    goto end; //Enum
    }
    advancedVariable = advancedorder_FromString(advanced->valuestring);
    }

    // order->post_only
    cJSON *post_only = cJSON_GetObjectItemCaseSensitive(orderJSON, "post_only");
    if (!post_only) {
        goto end;
    }

    
    if(!cJSON_IsBool(post_only))
    {
    goto end; //Bool
    }

    // order->usd
    cJSON *usd = cJSON_GetObjectItemCaseSensitive(orderJSON, "usd");
    if (usd) { 
    if(!cJSON_IsNumber(usd))
    {
    goto end; //Numeric
    }
    }

    // order->stop_price
    cJSON *stop_price = cJSON_GetObjectItemCaseSensitive(orderJSON, "stop_price");
    if (stop_price) { 
    if(!cJSON_IsNumber(stop_price))
    {
    goto end; //Numeric
    }
    }

    // order->order_type
    cJSON *order_type = cJSON_GetObjectItemCaseSensitive(orderJSON, "order_type");
    if (!order_type) {
        goto end;
    }

    order_type_e order_typeVariable;
    
    if(!cJSON_IsString(order_type))
    {
    goto end; //Enum
    }
    order_typeVariable = order_typeorder_FromString(order_type->valuestring);

    // order->last_update_timestamp
    cJSON *last_update_timestamp = cJSON_GetObjectItemCaseSensitive(orderJSON, "last_update_timestamp");
    if (!last_update_timestamp) {
        goto end;
    }

    
    if(!cJSON_IsNumber(last_update_timestamp))
    {
    goto end; //Numeric
    }

    // order->original_order_type
    cJSON *original_order_type = cJSON_GetObjectItemCaseSensitive(orderJSON, "original_order_type");
    original_order_type_e original_order_typeVariable;
    if (original_order_type) { 
    if(!cJSON_IsString(original_order_type))
    {
    goto end; //Enum
    }
    original_order_typeVariable = original_order_typeorder_FromString(original_order_type->valuestring);
    }

    // order->max_show
    cJSON *max_show = cJSON_GetObjectItemCaseSensitive(orderJSON, "max_show");
    if (!max_show) {
        goto end;
    }

    
    if(!cJSON_IsNumber(max_show))
    {
    goto end; //Numeric
    }

    // order->profit_loss
    cJSON *profit_loss = cJSON_GetObjectItemCaseSensitive(orderJSON, "profit_loss");
    if (profit_loss) { 
    if(!cJSON_IsNumber(profit_loss))
    {
    goto end; //Numeric
    }
    }

    // order->is_liquidation
    cJSON *is_liquidation = cJSON_GetObjectItemCaseSensitive(orderJSON, "is_liquidation");
    if (!is_liquidation) {
        goto end;
    }

    
    if(!cJSON_IsBool(is_liquidation))
    {
    goto end; //Bool
    }

    // order->filled_amount
    cJSON *filled_amount = cJSON_GetObjectItemCaseSensitive(orderJSON, "filled_amount");
    if (filled_amount) { 
    if(!cJSON_IsNumber(filled_amount))
    {
    goto end; //Numeric
    }
    }

    // order->label
    cJSON *label = cJSON_GetObjectItemCaseSensitive(orderJSON, "label");
    if (!label) {
        goto end;
    }

    
    if(!cJSON_IsString(label))
    {
    goto end; //String
    }

    // order->commission
    cJSON *commission = cJSON_GetObjectItemCaseSensitive(orderJSON, "commission");
    if (commission) { 
    if(!cJSON_IsNumber(commission))
    {
    goto end; //Numeric
    }
    }

    // order->amount
    cJSON *amount = cJSON_GetObjectItemCaseSensitive(orderJSON, "amount");
    if (amount) { 
    if(!cJSON_IsNumber(amount))
    {
    goto end; //Numeric
    }
    }

    // order->trigger
    cJSON *trigger = cJSON_GetObjectItemCaseSensitive(orderJSON, "trigger");
    trigger_e triggerVariable;
    if (trigger) { 
    if(!cJSON_IsString(trigger))
    {
    goto end; //Enum
    }
    triggerVariable = triggerorder_FromString(trigger->valuestring);
    }

    // order->instrument_name
    cJSON *instrument_name = cJSON_GetObjectItemCaseSensitive(orderJSON, "instrument_name");
    if (instrument_name) { 
    if(!cJSON_IsString(instrument_name))
    {
    goto end; //String
    }
    }

    // order->creation_timestamp
    cJSON *creation_timestamp = cJSON_GetObjectItemCaseSensitive(orderJSON, "creation_timestamp");
    if (!creation_timestamp) {
        goto end;
    }

    
    if(!cJSON_IsNumber(creation_timestamp))
    {
    goto end; //Numeric
    }

    // order->average_price
    cJSON *average_price = cJSON_GetObjectItemCaseSensitive(orderJSON, "average_price");
    if (average_price) { 
    if(!cJSON_IsNumber(average_price))
    {
    goto end; //Numeric
    }
    }


    order_local_var = order_create (
        directionVariable,
        reduce_only ? reduce_only->valueint : 0,
        triggered ? triggered->valueint : 0,
        strdup(order_id->valuestring),
        price->valuedouble,
        time_in_forceVariable,
        api->valueint,
        order_stateVariable,
        implv ? implv->valuedouble : 0,
        advanced ? advancedVariable : -1,
        post_only->valueint,
        usd ? usd->valuedouble : 0,
        stop_price ? stop_price->valuedouble : 0,
        order_typeVariable,
        last_update_timestamp->valuedouble,
        original_order_type ? original_order_typeVariable : -1,
        max_show->valuedouble,
        profit_loss ? profit_loss->valuedouble : 0,
        is_liquidation->valueint,
        filled_amount ? filled_amount->valuedouble : 0,
        strdup(label->valuestring),
        commission ? commission->valuedouble : 0,
        amount ? amount->valuedouble : 0,
        trigger ? triggerVariable : -1,
        instrument_name ? strdup(instrument_name->valuestring) : NULL,
        creation_timestamp->valuedouble,
        average_price ? average_price->valuedouble : 0
        );

    return order_local_var;
end:
    return NULL;

}
