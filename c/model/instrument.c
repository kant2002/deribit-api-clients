#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "instrument.h"


    char* quote_currencyinstrument_ToString(quote_currency_e quote_currency){
    char *quote_currencyArray[] =  { "USD" };
        return quote_currencyArray[quote_currency];
    }

    quote_currency_e quote_currencyinstrument_FromString(char* quote_currency){
    int stringToReturn = 0;
    char *quote_currencyArray[] =  { "USD" };
    size_t sizeofArray = sizeof(quote_currencyArray) / sizeof(quote_currencyArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(quote_currency, quote_currencyArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }
    char* kindinstrument_ToString(kind_e kind){
    char *kindArray[] =  { "future","option" };
        return kindArray[kind];
    }

    kind_e kindinstrument_FromString(char* kind){
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
    char* option_typeinstrument_ToString(option_type_e option_type){
    char *option_typeArray[] =  { "call","put" };
        return option_typeArray[option_type];
    }

    option_type_e option_typeinstrument_FromString(char* option_type){
    int stringToReturn = 0;
    char *option_typeArray[] =  { "call","put" };
    size_t sizeofArray = sizeof(option_typeArray) / sizeof(option_typeArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(option_type, option_typeArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }
    char* settlement_periodinstrument_ToString(settlement_period_e settlement_period){
    char *settlement_periodArray[] =  { "month","week","perpetual" };
        return settlement_periodArray[settlement_period];
    }

    settlement_period_e settlement_periodinstrument_FromString(char* settlement_period){
    int stringToReturn = 0;
    char *settlement_periodArray[] =  { "month","week","perpetual" };
    size_t sizeofArray = sizeof(settlement_periodArray) / sizeof(settlement_periodArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(settlement_period, settlement_periodArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }
    char* base_currencyinstrument_ToString(base_currency_e base_currency){
    char *base_currencyArray[] =  { "BTC","ETH" };
        return base_currencyArray[base_currency];
    }

    base_currency_e base_currencyinstrument_FromString(char* base_currency){
    int stringToReturn = 0;
    char *base_currencyArray[] =  { "BTC","ETH" };
    size_t sizeofArray = sizeof(base_currencyArray) / sizeof(base_currencyArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(base_currency, base_currencyArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }

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
    ) {
	instrument_t *instrument_local_var = malloc(sizeof(instrument_t));
    if (!instrument_local_var) {
        return NULL;
    }
	instrument_local_var->quote_currency = quote_currency;
	instrument_local_var->kind = kind;
	instrument_local_var->tick_size = tick_size;
	instrument_local_var->contract_size = contract_size;
	instrument_local_var->is_active = is_active;
	instrument_local_var->option_type = option_type;
	instrument_local_var->min_trade_amount = min_trade_amount;
	instrument_local_var->instrument_name = instrument_name;
	instrument_local_var->settlement_period = settlement_period;
	instrument_local_var->strike = strike;
	instrument_local_var->base_currency = base_currency;
	instrument_local_var->creation_timestamp = creation_timestamp;
	instrument_local_var->expiration_timestamp = expiration_timestamp;

	return instrument_local_var;
}


void instrument_free(instrument_t *instrument) {
    listEntry_t *listEntry;
    free(instrument->instrument_name);
	free(instrument);
}

cJSON *instrument_convertToJSON(instrument_t *instrument) {
	cJSON *item = cJSON_CreateObject();

	// instrument->quote_currency
    
    if(cJSON_AddStringToObject(item, "quote_currency", quote_currencyinstrument_ToString(instrument->quote_currency)) == NULL)
    {
    goto fail; //Enum
    }


	// instrument->kind
    
    if(cJSON_AddStringToObject(item, "kind", kindinstrument_ToString(instrument->kind)) == NULL)
    {
    goto fail; //Enum
    }


	// instrument->tick_size
    if (!instrument->tick_size) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "tick_size", instrument->tick_size) == NULL) {
    goto fail; //Numeric
    }


	// instrument->contract_size
    if (!instrument->contract_size) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "contract_size", instrument->contract_size) == NULL) {
    goto fail; //Numeric
    }


	// instrument->is_active
    if (!instrument->is_active) {
        goto fail;
    }
    
    if(cJSON_AddBoolToObject(item, "is_active", instrument->is_active) == NULL) {
    goto fail; //Bool
    }


	// instrument->option_type
    
    if(cJSON_AddStringToObject(item, "option_type", option_typeinstrument_ToString(instrument->option_type)) == NULL)
    {
    goto fail; //Enum
    }
    


	// instrument->min_trade_amount
    if (!instrument->min_trade_amount) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "min_trade_amount", instrument->min_trade_amount) == NULL) {
    goto fail; //Numeric
    }


	// instrument->instrument_name
    if (!instrument->instrument_name) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "instrument_name", instrument->instrument_name) == NULL) {
    goto fail; //String
    }


	// instrument->settlement_period
    
    if(cJSON_AddStringToObject(item, "settlement_period", settlement_periodinstrument_ToString(instrument->settlement_period)) == NULL)
    {
    goto fail; //Enum
    }


	// instrument->strike
    if(instrument->strike) { 
    if(cJSON_AddNumberToObject(item, "strike", instrument->strike) == NULL) {
    goto fail; //Numeric
    }
     } 


	// instrument->base_currency
    
    if(cJSON_AddStringToObject(item, "base_currency", base_currencyinstrument_ToString(instrument->base_currency)) == NULL)
    {
    goto fail; //Enum
    }


	// instrument->creation_timestamp
    if (!instrument->creation_timestamp) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "creation_timestamp", instrument->creation_timestamp) == NULL) {
    goto fail; //Numeric
    }


	// instrument->expiration_timestamp
    if (!instrument->expiration_timestamp) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "expiration_timestamp", instrument->expiration_timestamp) == NULL) {
    goto fail; //Numeric
    }

	return item;
fail:
	if (item) {
        cJSON_Delete(item);
    }
	return NULL;
}

instrument_t *instrument_parseFromJSON(cJSON *instrumentJSON){

    instrument_t *instrument_local_var = NULL;

    // instrument->quote_currency
    cJSON *quote_currency = cJSON_GetObjectItemCaseSensitive(instrumentJSON, "quote_currency");
    if (!quote_currency) {
        goto end;
    }

    quote_currency_e quote_currencyVariable;
    
    if(!cJSON_IsString(quote_currency))
    {
    goto end; //Enum
    }
    quote_currencyVariable = quote_currencyinstrument_FromString(quote_currency->valuestring);

    // instrument->kind
    cJSON *kind = cJSON_GetObjectItemCaseSensitive(instrumentJSON, "kind");
    if (!kind) {
        goto end;
    }

    kind_e kindVariable;
    
    if(!cJSON_IsString(kind))
    {
    goto end; //Enum
    }
    kindVariable = kindinstrument_FromString(kind->valuestring);

    // instrument->tick_size
    cJSON *tick_size = cJSON_GetObjectItemCaseSensitive(instrumentJSON, "tick_size");
    if (!tick_size) {
        goto end;
    }

    
    if(!cJSON_IsNumber(tick_size))
    {
    goto end; //Numeric
    }

    // instrument->contract_size
    cJSON *contract_size = cJSON_GetObjectItemCaseSensitive(instrumentJSON, "contract_size");
    if (!contract_size) {
        goto end;
    }

    
    if(!cJSON_IsNumber(contract_size))
    {
    goto end; //Numeric
    }

    // instrument->is_active
    cJSON *is_active = cJSON_GetObjectItemCaseSensitive(instrumentJSON, "is_active");
    if (!is_active) {
        goto end;
    }

    
    if(!cJSON_IsBool(is_active))
    {
    goto end; //Bool
    }

    // instrument->option_type
    cJSON *option_type = cJSON_GetObjectItemCaseSensitive(instrumentJSON, "option_type");
    option_type_e option_typeVariable;
    if (option_type) { 
    if(!cJSON_IsString(option_type))
    {
    goto end; //Enum
    }
    option_typeVariable = option_typeinstrument_FromString(option_type->valuestring);
    }

    // instrument->min_trade_amount
    cJSON *min_trade_amount = cJSON_GetObjectItemCaseSensitive(instrumentJSON, "min_trade_amount");
    if (!min_trade_amount) {
        goto end;
    }

    
    if(!cJSON_IsNumber(min_trade_amount))
    {
    goto end; //Numeric
    }

    // instrument->instrument_name
    cJSON *instrument_name = cJSON_GetObjectItemCaseSensitive(instrumentJSON, "instrument_name");
    if (!instrument_name) {
        goto end;
    }

    
    if(!cJSON_IsString(instrument_name))
    {
    goto end; //String
    }

    // instrument->settlement_period
    cJSON *settlement_period = cJSON_GetObjectItemCaseSensitive(instrumentJSON, "settlement_period");
    if (!settlement_period) {
        goto end;
    }

    settlement_period_e settlement_periodVariable;
    
    if(!cJSON_IsString(settlement_period))
    {
    goto end; //Enum
    }
    settlement_periodVariable = settlement_periodinstrument_FromString(settlement_period->valuestring);

    // instrument->strike
    cJSON *strike = cJSON_GetObjectItemCaseSensitive(instrumentJSON, "strike");
    if (strike) { 
    if(!cJSON_IsNumber(strike))
    {
    goto end; //Numeric
    }
    }

    // instrument->base_currency
    cJSON *base_currency = cJSON_GetObjectItemCaseSensitive(instrumentJSON, "base_currency");
    if (!base_currency) {
        goto end;
    }

    base_currency_e base_currencyVariable;
    
    if(!cJSON_IsString(base_currency))
    {
    goto end; //Enum
    }
    base_currencyVariable = base_currencyinstrument_FromString(base_currency->valuestring);

    // instrument->creation_timestamp
    cJSON *creation_timestamp = cJSON_GetObjectItemCaseSensitive(instrumentJSON, "creation_timestamp");
    if (!creation_timestamp) {
        goto end;
    }

    
    if(!cJSON_IsNumber(creation_timestamp))
    {
    goto end; //Numeric
    }

    // instrument->expiration_timestamp
    cJSON *expiration_timestamp = cJSON_GetObjectItemCaseSensitive(instrumentJSON, "expiration_timestamp");
    if (!expiration_timestamp) {
        goto end;
    }

    
    if(!cJSON_IsNumber(expiration_timestamp))
    {
    goto end; //Numeric
    }


    instrument_local_var = instrument_create (
        quote_currencyVariable,
        kindVariable,
        tick_size->valuedouble,
        contract_size->valuedouble,
        is_active->valueint,
        option_type ? option_typeVariable : -1,
        min_trade_amount->valuedouble,
        strdup(instrument_name->valuestring),
        settlement_periodVariable,
        strike ? strike->valuedouble : 0,
        base_currencyVariable,
        creation_timestamp->valuedouble,
        expiration_timestamp->valuedouble
        );

    return instrument_local_var;
end:
    return NULL;

}
