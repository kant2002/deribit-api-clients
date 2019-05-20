#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "position.h"


    char* directionposition_ToString(direction_e direction){
    char *directionArray[] =  { "buy","sell" };
        return directionArray[direction];
    }

    direction_e directionposition_FromString(char* direction){
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
    char* kindposition_ToString(kind_e kind){
    char *kindArray[] =  { "future","option" };
        return kindArray[kind];
    }

    kind_e kindposition_FromString(char* kind){
    int stringToReturn = 0;
    char *kindArray[] =  { "future","option" };
    size_t sizeofArray = sizeof(kindArray) / sizeof(kindArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(kind, kindArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }

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
    ) {
	position_t *position_local_var = malloc(sizeof(position_t));
    if (!position_local_var) {
        return NULL;
    }
	position_local_var->direction = direction;
	position_local_var->average_price_usd = average_price_usd;
	position_local_var->estimated_liquidation_price = estimated_liquidation_price;
	position_local_var->floating_profit_loss = floating_profit_loss;
	position_local_var->floating_profit_loss_usd = floating_profit_loss_usd;
	position_local_var->open_orders_margin = open_orders_margin;
	position_local_var->total_profit_loss = total_profit_loss;
	position_local_var->realized_profit_loss = realized_profit_loss;
	position_local_var->delta = delta;
	position_local_var->initial_margin = initial_margin;
	position_local_var->size = size;
	position_local_var->maintenance_margin = maintenance_margin;
	position_local_var->kind = kind;
	position_local_var->mark_price = mark_price;
	position_local_var->average_price = average_price;
	position_local_var->settlement_price = settlement_price;
	position_local_var->index_price = index_price;
	position_local_var->instrument_name = instrument_name;
	position_local_var->size_currency = size_currency;

	return position_local_var;
}


void position_free(position_t *position) {
    listEntry_t *listEntry;
    free(position->instrument_name);
	free(position);
}

cJSON *position_convertToJSON(position_t *position) {
	cJSON *item = cJSON_CreateObject();

	// position->direction
    
    if(cJSON_AddStringToObject(item, "direction", directionposition_ToString(position->direction)) == NULL)
    {
    goto fail; //Enum
    }


	// position->average_price_usd
    if(position->average_price_usd) { 
    if(cJSON_AddNumberToObject(item, "average_price_usd", position->average_price_usd) == NULL) {
    goto fail; //Numeric
    }
     } 


	// position->estimated_liquidation_price
    if(position->estimated_liquidation_price) { 
    if(cJSON_AddNumberToObject(item, "estimated_liquidation_price", position->estimated_liquidation_price) == NULL) {
    goto fail; //Numeric
    }
     } 


	// position->floating_profit_loss
    if (!position->floating_profit_loss) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "floating_profit_loss", position->floating_profit_loss) == NULL) {
    goto fail; //Numeric
    }


	// position->floating_profit_loss_usd
    if(position->floating_profit_loss_usd) { 
    if(cJSON_AddNumberToObject(item, "floating_profit_loss_usd", position->floating_profit_loss_usd) == NULL) {
    goto fail; //Numeric
    }
     } 


	// position->open_orders_margin
    if (!position->open_orders_margin) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "open_orders_margin", position->open_orders_margin) == NULL) {
    goto fail; //Numeric
    }


	// position->total_profit_loss
    if (!position->total_profit_loss) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "total_profit_loss", position->total_profit_loss) == NULL) {
    goto fail; //Numeric
    }


	// position->realized_profit_loss
    if(position->realized_profit_loss) { 
    if(cJSON_AddNumberToObject(item, "realized_profit_loss", position->realized_profit_loss) == NULL) {
    goto fail; //Numeric
    }
     } 


	// position->delta
    if (!position->delta) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "delta", position->delta) == NULL) {
    goto fail; //Numeric
    }


	// position->initial_margin
    if (!position->initial_margin) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "initial_margin", position->initial_margin) == NULL) {
    goto fail; //Numeric
    }


	// position->size
    if (!position->size) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "size", position->size) == NULL) {
    goto fail; //Numeric
    }


	// position->maintenance_margin
    if (!position->maintenance_margin) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "maintenance_margin", position->maintenance_margin) == NULL) {
    goto fail; //Numeric
    }


	// position->kind
    
    if(cJSON_AddStringToObject(item, "kind", kindposition_ToString(position->kind)) == NULL)
    {
    goto fail; //Enum
    }


	// position->mark_price
    if (!position->mark_price) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "mark_price", position->mark_price) == NULL) {
    goto fail; //Numeric
    }


	// position->average_price
    if (!position->average_price) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "average_price", position->average_price) == NULL) {
    goto fail; //Numeric
    }


	// position->settlement_price
    if (!position->settlement_price) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "settlement_price", position->settlement_price) == NULL) {
    goto fail; //Numeric
    }


	// position->index_price
    if (!position->index_price) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "index_price", position->index_price) == NULL) {
    goto fail; //Numeric
    }


	// position->instrument_name
    if (!position->instrument_name) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "instrument_name", position->instrument_name) == NULL) {
    goto fail; //String
    }


	// position->size_currency
    if(position->size_currency) { 
    if(cJSON_AddNumberToObject(item, "size_currency", position->size_currency) == NULL) {
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

position_t *position_parseFromJSON(cJSON *positionJSON){

    position_t *position_local_var = NULL;

    // position->direction
    cJSON *direction = cJSON_GetObjectItemCaseSensitive(positionJSON, "direction");
    if (!direction) {
        goto end;
    }

    direction_e directionVariable;
    
    if(!cJSON_IsString(direction))
    {
    goto end; //Enum
    }
    directionVariable = directionposition_FromString(direction->valuestring);

    // position->average_price_usd
    cJSON *average_price_usd = cJSON_GetObjectItemCaseSensitive(positionJSON, "average_price_usd");
    if (average_price_usd) { 
    if(!cJSON_IsNumber(average_price_usd))
    {
    goto end; //Numeric
    }
    }

    // position->estimated_liquidation_price
    cJSON *estimated_liquidation_price = cJSON_GetObjectItemCaseSensitive(positionJSON, "estimated_liquidation_price");
    if (estimated_liquidation_price) { 
    if(!cJSON_IsNumber(estimated_liquidation_price))
    {
    goto end; //Numeric
    }
    }

    // position->floating_profit_loss
    cJSON *floating_profit_loss = cJSON_GetObjectItemCaseSensitive(positionJSON, "floating_profit_loss");
    if (!floating_profit_loss) {
        goto end;
    }

    
    if(!cJSON_IsNumber(floating_profit_loss))
    {
    goto end; //Numeric
    }

    // position->floating_profit_loss_usd
    cJSON *floating_profit_loss_usd = cJSON_GetObjectItemCaseSensitive(positionJSON, "floating_profit_loss_usd");
    if (floating_profit_loss_usd) { 
    if(!cJSON_IsNumber(floating_profit_loss_usd))
    {
    goto end; //Numeric
    }
    }

    // position->open_orders_margin
    cJSON *open_orders_margin = cJSON_GetObjectItemCaseSensitive(positionJSON, "open_orders_margin");
    if (!open_orders_margin) {
        goto end;
    }

    
    if(!cJSON_IsNumber(open_orders_margin))
    {
    goto end; //Numeric
    }

    // position->total_profit_loss
    cJSON *total_profit_loss = cJSON_GetObjectItemCaseSensitive(positionJSON, "total_profit_loss");
    if (!total_profit_loss) {
        goto end;
    }

    
    if(!cJSON_IsNumber(total_profit_loss))
    {
    goto end; //Numeric
    }

    // position->realized_profit_loss
    cJSON *realized_profit_loss = cJSON_GetObjectItemCaseSensitive(positionJSON, "realized_profit_loss");
    if (realized_profit_loss) { 
    if(!cJSON_IsNumber(realized_profit_loss))
    {
    goto end; //Numeric
    }
    }

    // position->delta
    cJSON *delta = cJSON_GetObjectItemCaseSensitive(positionJSON, "delta");
    if (!delta) {
        goto end;
    }

    
    if(!cJSON_IsNumber(delta))
    {
    goto end; //Numeric
    }

    // position->initial_margin
    cJSON *initial_margin = cJSON_GetObjectItemCaseSensitive(positionJSON, "initial_margin");
    if (!initial_margin) {
        goto end;
    }

    
    if(!cJSON_IsNumber(initial_margin))
    {
    goto end; //Numeric
    }

    // position->size
    cJSON *size = cJSON_GetObjectItemCaseSensitive(positionJSON, "size");
    if (!size) {
        goto end;
    }

    
    if(!cJSON_IsNumber(size))
    {
    goto end; //Numeric
    }

    // position->maintenance_margin
    cJSON *maintenance_margin = cJSON_GetObjectItemCaseSensitive(positionJSON, "maintenance_margin");
    if (!maintenance_margin) {
        goto end;
    }

    
    if(!cJSON_IsNumber(maintenance_margin))
    {
    goto end; //Numeric
    }

    // position->kind
    cJSON *kind = cJSON_GetObjectItemCaseSensitive(positionJSON, "kind");
    if (!kind) {
        goto end;
    }

    kind_e kindVariable;
    
    if(!cJSON_IsString(kind))
    {
    goto end; //Enum
    }
    kindVariable = kindposition_FromString(kind->valuestring);

    // position->mark_price
    cJSON *mark_price = cJSON_GetObjectItemCaseSensitive(positionJSON, "mark_price");
    if (!mark_price) {
        goto end;
    }

    
    if(!cJSON_IsNumber(mark_price))
    {
    goto end; //Numeric
    }

    // position->average_price
    cJSON *average_price = cJSON_GetObjectItemCaseSensitive(positionJSON, "average_price");
    if (!average_price) {
        goto end;
    }

    
    if(!cJSON_IsNumber(average_price))
    {
    goto end; //Numeric
    }

    // position->settlement_price
    cJSON *settlement_price = cJSON_GetObjectItemCaseSensitive(positionJSON, "settlement_price");
    if (!settlement_price) {
        goto end;
    }

    
    if(!cJSON_IsNumber(settlement_price))
    {
    goto end; //Numeric
    }

    // position->index_price
    cJSON *index_price = cJSON_GetObjectItemCaseSensitive(positionJSON, "index_price");
    if (!index_price) {
        goto end;
    }

    
    if(!cJSON_IsNumber(index_price))
    {
    goto end; //Numeric
    }

    // position->instrument_name
    cJSON *instrument_name = cJSON_GetObjectItemCaseSensitive(positionJSON, "instrument_name");
    if (!instrument_name) {
        goto end;
    }

    
    if(!cJSON_IsString(instrument_name))
    {
    goto end; //String
    }

    // position->size_currency
    cJSON *size_currency = cJSON_GetObjectItemCaseSensitive(positionJSON, "size_currency");
    if (size_currency) { 
    if(!cJSON_IsNumber(size_currency))
    {
    goto end; //Numeric
    }
    }


    position_local_var = position_create (
        directionVariable,
        average_price_usd ? average_price_usd->valuedouble : 0,
        estimated_liquidation_price ? estimated_liquidation_price->valuedouble : 0,
        floating_profit_loss->valuedouble,
        floating_profit_loss_usd ? floating_profit_loss_usd->valuedouble : 0,
        open_orders_margin->valuedouble,
        total_profit_loss->valuedouble,
        realized_profit_loss ? realized_profit_loss->valuedouble : 0,
        delta->valuedouble,
        initial_margin->valuedouble,
        size->valuedouble,
        maintenance_margin->valuedouble,
        kindVariable,
        mark_price->valuedouble,
        average_price->valuedouble,
        settlement_price->valuedouble,
        index_price->valuedouble,
        strdup(instrument_name->valuestring),
        size_currency ? size_currency->valuedouble : 0
        );

    return position_local_var;
end:
    return NULL;

}
