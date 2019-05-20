#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "portfolio.h"



portfolio_t *portfolio_create(
    portfolio_eth_t *eth,
    portfolio_eth_t *btc
    ) {
	portfolio_t *portfolio_local_var = malloc(sizeof(portfolio_t));
    if (!portfolio_local_var) {
        return NULL;
    }
	portfolio_local_var->eth = eth;
	portfolio_local_var->btc = btc;

	return portfolio_local_var;
}


void portfolio_free(portfolio_t *portfolio) {
    listEntry_t *listEntry;
    portfolio_eth_free(portfolio->eth);
    portfolio_eth_free(portfolio->btc);
	free(portfolio);
}

cJSON *portfolio_convertToJSON(portfolio_t *portfolio) {
	cJSON *item = cJSON_CreateObject();

	// portfolio->eth
    if (!portfolio->eth) {
        goto fail;
    }
    
    cJSON *eth_local_JSON = portfolio_eth_convertToJSON(portfolio->eth);
    if(eth_local_JSON == NULL) {
    goto fail; //model
    }
    cJSON_AddItemToObject(item, "eth", eth_local_JSON);
    if(item->child == NULL) {
    goto fail;
    }


	// portfolio->btc
    if (!portfolio->btc) {
        goto fail;
    }
    
    cJSON *btc_local_JSON = portfolio_eth_convertToJSON(portfolio->btc);
    if(btc_local_JSON == NULL) {
    goto fail; //model
    }
    cJSON_AddItemToObject(item, "btc", btc_local_JSON);
    if(item->child == NULL) {
    goto fail;
    }

	return item;
fail:
	if (item) {
        cJSON_Delete(item);
    }
	return NULL;
}

portfolio_t *portfolio_parseFromJSON(cJSON *portfolioJSON){

    portfolio_t *portfolio_local_var = NULL;

    // portfolio->eth
    cJSON *eth = cJSON_GetObjectItemCaseSensitive(portfolioJSON, "eth");
    if (!eth) {
        goto end;
    }

    portfolio_eth_t *eth_local_nonprim = NULL;
    
    eth_local_nonprim = portfolio_eth_parseFromJSON(eth); //nonprimitive

    // portfolio->btc
    cJSON *btc = cJSON_GetObjectItemCaseSensitive(portfolioJSON, "btc");
    if (!btc) {
        goto end;
    }

    portfolio_eth_t *btc_local_nonprim = NULL;
    
    btc_local_nonprim = portfolio_eth_parseFromJSON(btc); //nonprimitive


    portfolio_local_var = portfolio_create (
        eth_local_nonprim,
        btc_local_nonprim
        );

    return portfolio_local_var;
end:
    return NULL;

}
