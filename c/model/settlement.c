#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "settlement.h"


    char* typesettlement_ToString(type_e type){
    char *typeArray[] =  { "settlement","delivery","bankruptcy" };
        return typeArray[type];
    }

    type_e typesettlement_FromString(char* type){
    int stringToReturn = 0;
    char *typeArray[] =  { "settlement","delivery","bankruptcy" };
    size_t sizeofArray = sizeof(typeArray) / sizeof(typeArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(type, typeArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }

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
    ) {
	settlement_t *settlement_local_var = malloc(sizeof(settlement_t));
    if (!settlement_local_var) {
        return NULL;
    }
	settlement_local_var->session_profit_loss = session_profit_loss;
	settlement_local_var->mark_price = mark_price;
	settlement_local_var->funding = funding;
	settlement_local_var->socialized = socialized;
	settlement_local_var->session_bankrupcy = session_bankrupcy;
	settlement_local_var->timestamp = timestamp;
	settlement_local_var->profit_loss = profit_loss;
	settlement_local_var->funded = funded;
	settlement_local_var->index_price = index_price;
	settlement_local_var->session_tax = session_tax;
	settlement_local_var->session_tax_rate = session_tax_rate;
	settlement_local_var->instrument_name = instrument_name;
	settlement_local_var->position = position;
	settlement_local_var->type = type;

	return settlement_local_var;
}


void settlement_free(settlement_t *settlement) {
    listEntry_t *listEntry;
    free(settlement->instrument_name);
	free(settlement);
}

cJSON *settlement_convertToJSON(settlement_t *settlement) {
	cJSON *item = cJSON_CreateObject();

	// settlement->session_profit_loss
    if (!settlement->session_profit_loss) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "session_profit_loss", settlement->session_profit_loss) == NULL) {
    goto fail; //Numeric
    }


	// settlement->mark_price
    if(settlement->mark_price) { 
    if(cJSON_AddNumberToObject(item, "mark_price", settlement->mark_price) == NULL) {
    goto fail; //Numeric
    }
     } 


	// settlement->funding
    if (!settlement->funding) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "funding", settlement->funding) == NULL) {
    goto fail; //Numeric
    }


	// settlement->socialized
    if(settlement->socialized) { 
    if(cJSON_AddNumberToObject(item, "socialized", settlement->socialized) == NULL) {
    goto fail; //Numeric
    }
     } 


	// settlement->session_bankrupcy
    if(settlement->session_bankrupcy) { 
    if(cJSON_AddNumberToObject(item, "session_bankrupcy", settlement->session_bankrupcy) == NULL) {
    goto fail; //Numeric
    }
     } 


	// settlement->timestamp
    if (!settlement->timestamp) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "timestamp", settlement->timestamp) == NULL) {
    goto fail; //Numeric
    }


	// settlement->profit_loss
    if(settlement->profit_loss) { 
    if(cJSON_AddNumberToObject(item, "profit_loss", settlement->profit_loss) == NULL) {
    goto fail; //Numeric
    }
     } 


	// settlement->funded
    if(settlement->funded) { 
    if(cJSON_AddNumberToObject(item, "funded", settlement->funded) == NULL) {
    goto fail; //Numeric
    }
     } 


	// settlement->index_price
    if (!settlement->index_price) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "index_price", settlement->index_price) == NULL) {
    goto fail; //Numeric
    }


	// settlement->session_tax
    if(settlement->session_tax) { 
    if(cJSON_AddNumberToObject(item, "session_tax", settlement->session_tax) == NULL) {
    goto fail; //Numeric
    }
     } 


	// settlement->session_tax_rate
    if(settlement->session_tax_rate) { 
    if(cJSON_AddNumberToObject(item, "session_tax_rate", settlement->session_tax_rate) == NULL) {
    goto fail; //Numeric
    }
     } 


	// settlement->instrument_name
    if (!settlement->instrument_name) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "instrument_name", settlement->instrument_name) == NULL) {
    goto fail; //String
    }


	// settlement->position
    if (!settlement->position) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "position", settlement->position) == NULL) {
    goto fail; //Numeric
    }


	// settlement->type
    
    if(cJSON_AddStringToObject(item, "type", typesettlement_ToString(settlement->type)) == NULL)
    {
    goto fail; //Enum
    }

	return item;
fail:
	if (item) {
        cJSON_Delete(item);
    }
	return NULL;
}

settlement_t *settlement_parseFromJSON(cJSON *settlementJSON){

    settlement_t *settlement_local_var = NULL;

    // settlement->session_profit_loss
    cJSON *session_profit_loss = cJSON_GetObjectItemCaseSensitive(settlementJSON, "session_profit_loss");
    if (!session_profit_loss) {
        goto end;
    }

    
    if(!cJSON_IsNumber(session_profit_loss))
    {
    goto end; //Numeric
    }

    // settlement->mark_price
    cJSON *mark_price = cJSON_GetObjectItemCaseSensitive(settlementJSON, "mark_price");
    if (mark_price) { 
    if(!cJSON_IsNumber(mark_price))
    {
    goto end; //Numeric
    }
    }

    // settlement->funding
    cJSON *funding = cJSON_GetObjectItemCaseSensitive(settlementJSON, "funding");
    if (!funding) {
        goto end;
    }

    
    if(!cJSON_IsNumber(funding))
    {
    goto end; //Numeric
    }

    // settlement->socialized
    cJSON *socialized = cJSON_GetObjectItemCaseSensitive(settlementJSON, "socialized");
    if (socialized) { 
    if(!cJSON_IsNumber(socialized))
    {
    goto end; //Numeric
    }
    }

    // settlement->session_bankrupcy
    cJSON *session_bankrupcy = cJSON_GetObjectItemCaseSensitive(settlementJSON, "session_bankrupcy");
    if (session_bankrupcy) { 
    if(!cJSON_IsNumber(session_bankrupcy))
    {
    goto end; //Numeric
    }
    }

    // settlement->timestamp
    cJSON *timestamp = cJSON_GetObjectItemCaseSensitive(settlementJSON, "timestamp");
    if (!timestamp) {
        goto end;
    }

    
    if(!cJSON_IsNumber(timestamp))
    {
    goto end; //Numeric
    }

    // settlement->profit_loss
    cJSON *profit_loss = cJSON_GetObjectItemCaseSensitive(settlementJSON, "profit_loss");
    if (profit_loss) { 
    if(!cJSON_IsNumber(profit_loss))
    {
    goto end; //Numeric
    }
    }

    // settlement->funded
    cJSON *funded = cJSON_GetObjectItemCaseSensitive(settlementJSON, "funded");
    if (funded) { 
    if(!cJSON_IsNumber(funded))
    {
    goto end; //Numeric
    }
    }

    // settlement->index_price
    cJSON *index_price = cJSON_GetObjectItemCaseSensitive(settlementJSON, "index_price");
    if (!index_price) {
        goto end;
    }

    
    if(!cJSON_IsNumber(index_price))
    {
    goto end; //Numeric
    }

    // settlement->session_tax
    cJSON *session_tax = cJSON_GetObjectItemCaseSensitive(settlementJSON, "session_tax");
    if (session_tax) { 
    if(!cJSON_IsNumber(session_tax))
    {
    goto end; //Numeric
    }
    }

    // settlement->session_tax_rate
    cJSON *session_tax_rate = cJSON_GetObjectItemCaseSensitive(settlementJSON, "session_tax_rate");
    if (session_tax_rate) { 
    if(!cJSON_IsNumber(session_tax_rate))
    {
    goto end; //Numeric
    }
    }

    // settlement->instrument_name
    cJSON *instrument_name = cJSON_GetObjectItemCaseSensitive(settlementJSON, "instrument_name");
    if (!instrument_name) {
        goto end;
    }

    
    if(!cJSON_IsString(instrument_name))
    {
    goto end; //String
    }

    // settlement->position
    cJSON *position = cJSON_GetObjectItemCaseSensitive(settlementJSON, "position");
    if (!position) {
        goto end;
    }

    
    if(!cJSON_IsNumber(position))
    {
    goto end; //Numeric
    }

    // settlement->type
    cJSON *type = cJSON_GetObjectItemCaseSensitive(settlementJSON, "type");
    if (!type) {
        goto end;
    }

    type_e typeVariable;
    
    if(!cJSON_IsString(type))
    {
    goto end; //Enum
    }
    typeVariable = typesettlement_FromString(type->valuestring);


    settlement_local_var = settlement_create (
        session_profit_loss->valuedouble,
        mark_price ? mark_price->valuedouble : 0,
        funding->valuedouble,
        socialized ? socialized->valuedouble : 0,
        session_bankrupcy ? session_bankrupcy->valuedouble : 0,
        timestamp->valuedouble,
        profit_loss ? profit_loss->valuedouble : 0,
        funded ? funded->valuedouble : 0,
        index_price->valuedouble,
        session_tax ? session_tax->valuedouble : 0,
        session_tax_rate ? session_tax_rate->valuedouble : 0,
        strdup(instrument_name->valuestring),
        position->valuedouble,
        typeVariable
        );

    return settlement_local_var;
end:
    return NULL;

}
