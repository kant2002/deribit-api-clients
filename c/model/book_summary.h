/*
 * book_summary.h
 *
 * 
 */

#ifndef _book_summary_H_
#define _book_summary_H_

#include <string.h>
#include "../external/cJSON.h"
#include "../include/list.h"
#include "../include/keyValuePair.h"



typedef struct book_summary_t {
    char *underlying_index; // string
    double volume; //numeric
    double volume_usd; //numeric
    double underlying_price; //numeric
    double bid_price; //numeric
    double open_interest; //numeric
    char *quote_currency; // string
    double high; //numeric
    double estimated_delivery_price; //numeric
    double last; //numeric
    double mid_price; //numeric
    double interest_rate; //numeric
    double funding_8h; //numeric
    double mark_price; //numeric
    double ask_price; //numeric
    char *instrument_name; // string
    double low; //numeric
    char *base_currency; // string
    int creation_timestamp; //numeric
    double current_funding; //numeric

} book_summary_t;

book_summary_t *book_summary_create(
    char *underlying_index,
    double volume,
    double volume_usd,
    double underlying_price,
    double bid_price,
    double open_interest,
    char *quote_currency,
    double high,
    double estimated_delivery_price,
    double last,
    double mid_price,
    double interest_rate,
    double funding_8h,
    double mark_price,
    double ask_price,
    char *instrument_name,
    double low,
    char *base_currency,
    int creation_timestamp,
    double current_funding
);

void book_summary_free(book_summary_t *book_summary);

book_summary_t *book_summary_parseFromJSON(cJSON *book_summaryJSON);

cJSON *book_summary_convertToJSON(book_summary_t *book_summary);

#endif /* _book_summary_H_ */

