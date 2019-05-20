/*
 * currency_portfolio.h
 *
 * 
 */

#ifndef _currency_portfolio_H_
#define _currency_portfolio_H_

#include <string.h>
#include "../external/cJSON.h"
#include "../include/list.h"
#include "../include/keyValuePair.h"

                typedef enum  {  btc, eth } currency_e;

        char* currency_ToString(currency_e currency);

        currency_e currency_FromString(char* currency);


typedef struct currency_portfolio_t {
    double maintenance_margin; //numeric
    double available_withdrawal_funds; //numeric
    double initial_margin; //numeric
    double available_funds; //numeric
    currency_e currency; //enum
    double margin_balance; //numeric
    double equity; //numeric
    double balance; //numeric

} currency_portfolio_t;

currency_portfolio_t *currency_portfolio_create(
    double maintenance_margin,
    double available_withdrawal_funds,
    double initial_margin,
    double available_funds,
    currency_e currency,
    double margin_balance,
    double equity,
    double balance
);

void currency_portfolio_free(currency_portfolio_t *currency_portfolio);

currency_portfolio_t *currency_portfolio_parseFromJSON(cJSON *currency_portfolioJSON);

cJSON *currency_portfolio_convertToJSON(currency_portfolio_t *currency_portfolio);

#endif /* _currency_portfolio_H_ */

