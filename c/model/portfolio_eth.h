/*
 * portfolio_eth.h
 *
 * 
 */

#ifndef _portfolio_eth_H_
#define _portfolio_eth_H_

#include <string.h>
#include "../external/cJSON.h"
#include "../include/list.h"
#include "../include/keyValuePair.h"

                typedef enum  {  btc, eth } currency_e;

        char* currency_ToString(currency_e currency);

        currency_e currency_FromString(char* currency);


typedef struct portfolio_eth_t {
    double maintenance_margin; //numeric
    double available_withdrawal_funds; //numeric
    double initial_margin; //numeric
    double available_funds; //numeric
    currency_e currency; //enum
    double margin_balance; //numeric
    double equity; //numeric
    double balance; //numeric

} portfolio_eth_t;

portfolio_eth_t *portfolio_eth_create(
    double maintenance_margin,
    double available_withdrawal_funds,
    double initial_margin,
    double available_funds,
    currency_e currency,
    double margin_balance,
    double equity,
    double balance
);

void portfolio_eth_free(portfolio_eth_t *portfolio_eth);

portfolio_eth_t *portfolio_eth_parseFromJSON(cJSON *portfolio_ethJSON);

cJSON *portfolio_eth_convertToJSON(portfolio_eth_t *portfolio_eth);

#endif /* _portfolio_eth_H_ */

