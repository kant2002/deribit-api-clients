/*
 * portfolio.h
 *
 * 
 */

#ifndef _portfolio_H_
#define _portfolio_H_

#include <string.h>
#include "../external/cJSON.h"
#include "../include/list.h"
#include "../include/keyValuePair.h"
#include "portfolio_eth.h"



typedef struct portfolio_t {
    portfolio_eth_t *eth; //model
    portfolio_eth_t *btc; //model

} portfolio_t;

portfolio_t *portfolio_create(
    portfolio_eth_t *eth,
    portfolio_eth_t *btc
);

void portfolio_free(portfolio_t *portfolio);

portfolio_t *portfolio_parseFromJSON(cJSON *portfolioJSON);

cJSON *portfolio_convertToJSON(portfolio_t *portfolio);

#endif /* _portfolio_H_ */

