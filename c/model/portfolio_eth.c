#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "portfolio_eth.h"


    char* currencyportfolio_eth_ToString(currency_e currency){
    char *currencyArray[] =  { "btc","eth" };
        return currencyArray[currency];
    }

    currency_e currencyportfolio_eth_FromString(char* currency){
    int stringToReturn = 0;
    char *currencyArray[] =  { "btc","eth" };
    size_t sizeofArray = sizeof(currencyArray) / sizeof(currencyArray[0]);
    while(stringToReturn < sizeofArray) {
        if(strcmp(currency, currencyArray[stringToReturn]) == 0) {
            return stringToReturn;
        }
        stringToReturn++;
    }
    return 0;
    }

portfolio_eth_t *portfolio_eth_create(
    double maintenance_margin,
    double available_withdrawal_funds,
    double initial_margin,
    double available_funds,
    currency_e currency,
    double margin_balance,
    double equity,
    double balance
    ) {
	portfolio_eth_t *portfolio_eth_local_var = malloc(sizeof(portfolio_eth_t));
    if (!portfolio_eth_local_var) {
        return NULL;
    }
	portfolio_eth_local_var->maintenance_margin = maintenance_margin;
	portfolio_eth_local_var->available_withdrawal_funds = available_withdrawal_funds;
	portfolio_eth_local_var->initial_margin = initial_margin;
	portfolio_eth_local_var->available_funds = available_funds;
	portfolio_eth_local_var->currency = currency;
	portfolio_eth_local_var->margin_balance = margin_balance;
	portfolio_eth_local_var->equity = equity;
	portfolio_eth_local_var->balance = balance;

	return portfolio_eth_local_var;
}


void portfolio_eth_free(portfolio_eth_t *portfolio_eth) {
    listEntry_t *listEntry;
	free(portfolio_eth);
}

cJSON *portfolio_eth_convertToJSON(portfolio_eth_t *portfolio_eth) {
	cJSON *item = cJSON_CreateObject();

	// portfolio_eth->maintenance_margin
    if (!portfolio_eth->maintenance_margin) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "maintenance_margin", portfolio_eth->maintenance_margin) == NULL) {
    goto fail; //Numeric
    }


	// portfolio_eth->available_withdrawal_funds
    if (!portfolio_eth->available_withdrawal_funds) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "available_withdrawal_funds", portfolio_eth->available_withdrawal_funds) == NULL) {
    goto fail; //Numeric
    }


	// portfolio_eth->initial_margin
    if (!portfolio_eth->initial_margin) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "initial_margin", portfolio_eth->initial_margin) == NULL) {
    goto fail; //Numeric
    }


	// portfolio_eth->available_funds
    if (!portfolio_eth->available_funds) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "available_funds", portfolio_eth->available_funds) == NULL) {
    goto fail; //Numeric
    }


	// portfolio_eth->currency
    
    if(cJSON_AddStringToObject(item, "currency", currencyportfolio_eth_ToString(portfolio_eth->currency)) == NULL)
    {
    goto fail; //Enum
    }


	// portfolio_eth->margin_balance
    if (!portfolio_eth->margin_balance) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "margin_balance", portfolio_eth->margin_balance) == NULL) {
    goto fail; //Numeric
    }


	// portfolio_eth->equity
    if (!portfolio_eth->equity) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "equity", portfolio_eth->equity) == NULL) {
    goto fail; //Numeric
    }


	// portfolio_eth->balance
    if (!portfolio_eth->balance) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "balance", portfolio_eth->balance) == NULL) {
    goto fail; //Numeric
    }

	return item;
fail:
	if (item) {
        cJSON_Delete(item);
    }
	return NULL;
}

portfolio_eth_t *portfolio_eth_parseFromJSON(cJSON *portfolio_ethJSON){

    portfolio_eth_t *portfolio_eth_local_var = NULL;

    // portfolio_eth->maintenance_margin
    cJSON *maintenance_margin = cJSON_GetObjectItemCaseSensitive(portfolio_ethJSON, "maintenance_margin");
    if (!maintenance_margin) {
        goto end;
    }

    
    if(!cJSON_IsNumber(maintenance_margin))
    {
    goto end; //Numeric
    }

    // portfolio_eth->available_withdrawal_funds
    cJSON *available_withdrawal_funds = cJSON_GetObjectItemCaseSensitive(portfolio_ethJSON, "available_withdrawal_funds");
    if (!available_withdrawal_funds) {
        goto end;
    }

    
    if(!cJSON_IsNumber(available_withdrawal_funds))
    {
    goto end; //Numeric
    }

    // portfolio_eth->initial_margin
    cJSON *initial_margin = cJSON_GetObjectItemCaseSensitive(portfolio_ethJSON, "initial_margin");
    if (!initial_margin) {
        goto end;
    }

    
    if(!cJSON_IsNumber(initial_margin))
    {
    goto end; //Numeric
    }

    // portfolio_eth->available_funds
    cJSON *available_funds = cJSON_GetObjectItemCaseSensitive(portfolio_ethJSON, "available_funds");
    if (!available_funds) {
        goto end;
    }

    
    if(!cJSON_IsNumber(available_funds))
    {
    goto end; //Numeric
    }

    // portfolio_eth->currency
    cJSON *currency = cJSON_GetObjectItemCaseSensitive(portfolio_ethJSON, "currency");
    if (!currency) {
        goto end;
    }

    currency_e currencyVariable;
    
    if(!cJSON_IsString(currency))
    {
    goto end; //Enum
    }
    currencyVariable = currencyportfolio_eth_FromString(currency->valuestring);

    // portfolio_eth->margin_balance
    cJSON *margin_balance = cJSON_GetObjectItemCaseSensitive(portfolio_ethJSON, "margin_balance");
    if (!margin_balance) {
        goto end;
    }

    
    if(!cJSON_IsNumber(margin_balance))
    {
    goto end; //Numeric
    }

    // portfolio_eth->equity
    cJSON *equity = cJSON_GetObjectItemCaseSensitive(portfolio_ethJSON, "equity");
    if (!equity) {
        goto end;
    }

    
    if(!cJSON_IsNumber(equity))
    {
    goto end; //Numeric
    }

    // portfolio_eth->balance
    cJSON *balance = cJSON_GetObjectItemCaseSensitive(portfolio_ethJSON, "balance");
    if (!balance) {
        goto end;
    }

    
    if(!cJSON_IsNumber(balance))
    {
    goto end; //Numeric
    }


    portfolio_eth_local_var = portfolio_eth_create (
        maintenance_margin->valuedouble,
        available_withdrawal_funds->valuedouble,
        initial_margin->valuedouble,
        available_funds->valuedouble,
        currencyVariable,
        margin_balance->valuedouble,
        equity->valuedouble,
        balance->valuedouble
        );

    return portfolio_eth_local_var;
end:
    return NULL;

}
