#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "currency_portfolio.h"


    char* currencycurrency_portfolio_ToString(currency_e currency){
    char *currencyArray[] =  { "btc","eth" };
        return currencyArray[currency];
    }

    currency_e currencycurrency_portfolio_FromString(char* currency){
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

currency_portfolio_t *currency_portfolio_create(
    double maintenance_margin,
    double available_withdrawal_funds,
    double initial_margin,
    double available_funds,
    currency_e currency,
    double margin_balance,
    double equity,
    double balance
    ) {
	currency_portfolio_t *currency_portfolio_local_var = malloc(sizeof(currency_portfolio_t));
    if (!currency_portfolio_local_var) {
        return NULL;
    }
	currency_portfolio_local_var->maintenance_margin = maintenance_margin;
	currency_portfolio_local_var->available_withdrawal_funds = available_withdrawal_funds;
	currency_portfolio_local_var->initial_margin = initial_margin;
	currency_portfolio_local_var->available_funds = available_funds;
	currency_portfolio_local_var->currency = currency;
	currency_portfolio_local_var->margin_balance = margin_balance;
	currency_portfolio_local_var->equity = equity;
	currency_portfolio_local_var->balance = balance;

	return currency_portfolio_local_var;
}


void currency_portfolio_free(currency_portfolio_t *currency_portfolio) {
    listEntry_t *listEntry;
	free(currency_portfolio);
}

cJSON *currency_portfolio_convertToJSON(currency_portfolio_t *currency_portfolio) {
	cJSON *item = cJSON_CreateObject();

	// currency_portfolio->maintenance_margin
    if (!currency_portfolio->maintenance_margin) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "maintenance_margin", currency_portfolio->maintenance_margin) == NULL) {
    goto fail; //Numeric
    }


	// currency_portfolio->available_withdrawal_funds
    if (!currency_portfolio->available_withdrawal_funds) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "available_withdrawal_funds", currency_portfolio->available_withdrawal_funds) == NULL) {
    goto fail; //Numeric
    }


	// currency_portfolio->initial_margin
    if (!currency_portfolio->initial_margin) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "initial_margin", currency_portfolio->initial_margin) == NULL) {
    goto fail; //Numeric
    }


	// currency_portfolio->available_funds
    if (!currency_portfolio->available_funds) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "available_funds", currency_portfolio->available_funds) == NULL) {
    goto fail; //Numeric
    }


	// currency_portfolio->currency
    
    if(cJSON_AddStringToObject(item, "currency", currencycurrency_portfolio_ToString(currency_portfolio->currency)) == NULL)
    {
    goto fail; //Enum
    }


	// currency_portfolio->margin_balance
    if (!currency_portfolio->margin_balance) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "margin_balance", currency_portfolio->margin_balance) == NULL) {
    goto fail; //Numeric
    }


	// currency_portfolio->equity
    if (!currency_portfolio->equity) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "equity", currency_portfolio->equity) == NULL) {
    goto fail; //Numeric
    }


	// currency_portfolio->balance
    if (!currency_portfolio->balance) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "balance", currency_portfolio->balance) == NULL) {
    goto fail; //Numeric
    }

	return item;
fail:
	if (item) {
        cJSON_Delete(item);
    }
	return NULL;
}

currency_portfolio_t *currency_portfolio_parseFromJSON(cJSON *currency_portfolioJSON){

    currency_portfolio_t *currency_portfolio_local_var = NULL;

    // currency_portfolio->maintenance_margin
    cJSON *maintenance_margin = cJSON_GetObjectItemCaseSensitive(currency_portfolioJSON, "maintenance_margin");
    if (!maintenance_margin) {
        goto end;
    }

    
    if(!cJSON_IsNumber(maintenance_margin))
    {
    goto end; //Numeric
    }

    // currency_portfolio->available_withdrawal_funds
    cJSON *available_withdrawal_funds = cJSON_GetObjectItemCaseSensitive(currency_portfolioJSON, "available_withdrawal_funds");
    if (!available_withdrawal_funds) {
        goto end;
    }

    
    if(!cJSON_IsNumber(available_withdrawal_funds))
    {
    goto end; //Numeric
    }

    // currency_portfolio->initial_margin
    cJSON *initial_margin = cJSON_GetObjectItemCaseSensitive(currency_portfolioJSON, "initial_margin");
    if (!initial_margin) {
        goto end;
    }

    
    if(!cJSON_IsNumber(initial_margin))
    {
    goto end; //Numeric
    }

    // currency_portfolio->available_funds
    cJSON *available_funds = cJSON_GetObjectItemCaseSensitive(currency_portfolioJSON, "available_funds");
    if (!available_funds) {
        goto end;
    }

    
    if(!cJSON_IsNumber(available_funds))
    {
    goto end; //Numeric
    }

    // currency_portfolio->currency
    cJSON *currency = cJSON_GetObjectItemCaseSensitive(currency_portfolioJSON, "currency");
    if (!currency) {
        goto end;
    }

    currency_e currencyVariable;
    
    if(!cJSON_IsString(currency))
    {
    goto end; //Enum
    }
    currencyVariable = currencycurrency_portfolio_FromString(currency->valuestring);

    // currency_portfolio->margin_balance
    cJSON *margin_balance = cJSON_GetObjectItemCaseSensitive(currency_portfolioJSON, "margin_balance");
    if (!margin_balance) {
        goto end;
    }

    
    if(!cJSON_IsNumber(margin_balance))
    {
    goto end; //Numeric
    }

    // currency_portfolio->equity
    cJSON *equity = cJSON_GetObjectItemCaseSensitive(currency_portfolioJSON, "equity");
    if (!equity) {
        goto end;
    }

    
    if(!cJSON_IsNumber(equity))
    {
    goto end; //Numeric
    }

    // currency_portfolio->balance
    cJSON *balance = cJSON_GetObjectItemCaseSensitive(currency_portfolioJSON, "balance");
    if (!balance) {
        goto end;
    }

    
    if(!cJSON_IsNumber(balance))
    {
    goto end; //Numeric
    }


    currency_portfolio_local_var = currency_portfolio_create (
        maintenance_margin->valuedouble,
        available_withdrawal_funds->valuedouble,
        initial_margin->valuedouble,
        available_funds->valuedouble,
        currencyVariable,
        margin_balance->valuedouble,
        equity->valuedouble,
        balance->valuedouble
        );

    return currency_portfolio_local_var;
end:
    return NULL;

}
