#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "book_summary.h"



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
    ) {
	book_summary_t *book_summary_local_var = malloc(sizeof(book_summary_t));
    if (!book_summary_local_var) {
        return NULL;
    }
	book_summary_local_var->underlying_index = underlying_index;
	book_summary_local_var->volume = volume;
	book_summary_local_var->volume_usd = volume_usd;
	book_summary_local_var->underlying_price = underlying_price;
	book_summary_local_var->bid_price = bid_price;
	book_summary_local_var->open_interest = open_interest;
	book_summary_local_var->quote_currency = quote_currency;
	book_summary_local_var->high = high;
	book_summary_local_var->estimated_delivery_price = estimated_delivery_price;
	book_summary_local_var->last = last;
	book_summary_local_var->mid_price = mid_price;
	book_summary_local_var->interest_rate = interest_rate;
	book_summary_local_var->funding_8h = funding_8h;
	book_summary_local_var->mark_price = mark_price;
	book_summary_local_var->ask_price = ask_price;
	book_summary_local_var->instrument_name = instrument_name;
	book_summary_local_var->low = low;
	book_summary_local_var->base_currency = base_currency;
	book_summary_local_var->creation_timestamp = creation_timestamp;
	book_summary_local_var->current_funding = current_funding;

	return book_summary_local_var;
}


void book_summary_free(book_summary_t *book_summary) {
    listEntry_t *listEntry;
    free(book_summary->underlying_index);
    free(book_summary->quote_currency);
    free(book_summary->instrument_name);
    free(book_summary->base_currency);
	free(book_summary);
}

cJSON *book_summary_convertToJSON(book_summary_t *book_summary) {
	cJSON *item = cJSON_CreateObject();

	// book_summary->underlying_index
    if(book_summary->underlying_index) { 
    if(cJSON_AddStringToObject(item, "underlying_index", book_summary->underlying_index) == NULL) {
    goto fail; //String
    }
     } 


	// book_summary->volume
    if (!book_summary->volume) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "volume", book_summary->volume) == NULL) {
    goto fail; //Numeric
    }


	// book_summary->volume_usd
    if(book_summary->volume_usd) { 
    if(cJSON_AddNumberToObject(item, "volume_usd", book_summary->volume_usd) == NULL) {
    goto fail; //Numeric
    }
     } 


	// book_summary->underlying_price
    if(book_summary->underlying_price) { 
    if(cJSON_AddNumberToObject(item, "underlying_price", book_summary->underlying_price) == NULL) {
    goto fail; //Numeric
    }
     } 


	// book_summary->bid_price
    if (!book_summary->bid_price) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "bid_price", book_summary->bid_price) == NULL) {
    goto fail; //Numeric
    }


	// book_summary->open_interest
    if (!book_summary->open_interest) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "open_interest", book_summary->open_interest) == NULL) {
    goto fail; //Numeric
    }


	// book_summary->quote_currency
    if (!book_summary->quote_currency) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "quote_currency", book_summary->quote_currency) == NULL) {
    goto fail; //String
    }


	// book_summary->high
    if (!book_summary->high) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "high", book_summary->high) == NULL) {
    goto fail; //Numeric
    }


	// book_summary->estimated_delivery_price
    if(book_summary->estimated_delivery_price) { 
    if(cJSON_AddNumberToObject(item, "estimated_delivery_price", book_summary->estimated_delivery_price) == NULL) {
    goto fail; //Numeric
    }
     } 


	// book_summary->last
    if (!book_summary->last) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "last", book_summary->last) == NULL) {
    goto fail; //Numeric
    }


	// book_summary->mid_price
    if (!book_summary->mid_price) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "mid_price", book_summary->mid_price) == NULL) {
    goto fail; //Numeric
    }


	// book_summary->interest_rate
    if(book_summary->interest_rate) { 
    if(cJSON_AddNumberToObject(item, "interest_rate", book_summary->interest_rate) == NULL) {
    goto fail; //Numeric
    }
     } 


	// book_summary->funding_8h
    if(book_summary->funding_8h) { 
    if(cJSON_AddNumberToObject(item, "funding_8h", book_summary->funding_8h) == NULL) {
    goto fail; //Numeric
    }
     } 


	// book_summary->mark_price
    if (!book_summary->mark_price) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "mark_price", book_summary->mark_price) == NULL) {
    goto fail; //Numeric
    }


	// book_summary->ask_price
    if (!book_summary->ask_price) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "ask_price", book_summary->ask_price) == NULL) {
    goto fail; //Numeric
    }


	// book_summary->instrument_name
    if (!book_summary->instrument_name) {
        goto fail;
    }
    
    if(cJSON_AddStringToObject(item, "instrument_name", book_summary->instrument_name) == NULL) {
    goto fail; //String
    }


	// book_summary->low
    if (!book_summary->low) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "low", book_summary->low) == NULL) {
    goto fail; //Numeric
    }


	// book_summary->base_currency
    if(book_summary->base_currency) { 
    if(cJSON_AddStringToObject(item, "base_currency", book_summary->base_currency) == NULL) {
    goto fail; //String
    }
     } 


	// book_summary->creation_timestamp
    if (!book_summary->creation_timestamp) {
        goto fail;
    }
    
    if(cJSON_AddNumberToObject(item, "creation_timestamp", book_summary->creation_timestamp) == NULL) {
    goto fail; //Numeric
    }


	// book_summary->current_funding
    if(book_summary->current_funding) { 
    if(cJSON_AddNumberToObject(item, "current_funding", book_summary->current_funding) == NULL) {
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

book_summary_t *book_summary_parseFromJSON(cJSON *book_summaryJSON){

    book_summary_t *book_summary_local_var = NULL;

    // book_summary->underlying_index
    cJSON *underlying_index = cJSON_GetObjectItemCaseSensitive(book_summaryJSON, "underlying_index");
    if (underlying_index) { 
    if(!cJSON_IsString(underlying_index))
    {
    goto end; //String
    }
    }

    // book_summary->volume
    cJSON *volume = cJSON_GetObjectItemCaseSensitive(book_summaryJSON, "volume");
    if (!volume) {
        goto end;
    }

    
    if(!cJSON_IsNumber(volume))
    {
    goto end; //Numeric
    }

    // book_summary->volume_usd
    cJSON *volume_usd = cJSON_GetObjectItemCaseSensitive(book_summaryJSON, "volume_usd");
    if (volume_usd) { 
    if(!cJSON_IsNumber(volume_usd))
    {
    goto end; //Numeric
    }
    }

    // book_summary->underlying_price
    cJSON *underlying_price = cJSON_GetObjectItemCaseSensitive(book_summaryJSON, "underlying_price");
    if (underlying_price) { 
    if(!cJSON_IsNumber(underlying_price))
    {
    goto end; //Numeric
    }
    }

    // book_summary->bid_price
    cJSON *bid_price = cJSON_GetObjectItemCaseSensitive(book_summaryJSON, "bid_price");
    if (!bid_price) {
        goto end;
    }

    
    if(!cJSON_IsNumber(bid_price))
    {
    goto end; //Numeric
    }

    // book_summary->open_interest
    cJSON *open_interest = cJSON_GetObjectItemCaseSensitive(book_summaryJSON, "open_interest");
    if (!open_interest) {
        goto end;
    }

    
    if(!cJSON_IsNumber(open_interest))
    {
    goto end; //Numeric
    }

    // book_summary->quote_currency
    cJSON *quote_currency = cJSON_GetObjectItemCaseSensitive(book_summaryJSON, "quote_currency");
    if (!quote_currency) {
        goto end;
    }

    
    if(!cJSON_IsString(quote_currency))
    {
    goto end; //String
    }

    // book_summary->high
    cJSON *high = cJSON_GetObjectItemCaseSensitive(book_summaryJSON, "high");
    if (!high) {
        goto end;
    }

    
    if(!cJSON_IsNumber(high))
    {
    goto end; //Numeric
    }

    // book_summary->estimated_delivery_price
    cJSON *estimated_delivery_price = cJSON_GetObjectItemCaseSensitive(book_summaryJSON, "estimated_delivery_price");
    if (estimated_delivery_price) { 
    if(!cJSON_IsNumber(estimated_delivery_price))
    {
    goto end; //Numeric
    }
    }

    // book_summary->last
    cJSON *last = cJSON_GetObjectItemCaseSensitive(book_summaryJSON, "last");
    if (!last) {
        goto end;
    }

    
    if(!cJSON_IsNumber(last))
    {
    goto end; //Numeric
    }

    // book_summary->mid_price
    cJSON *mid_price = cJSON_GetObjectItemCaseSensitive(book_summaryJSON, "mid_price");
    if (!mid_price) {
        goto end;
    }

    
    if(!cJSON_IsNumber(mid_price))
    {
    goto end; //Numeric
    }

    // book_summary->interest_rate
    cJSON *interest_rate = cJSON_GetObjectItemCaseSensitive(book_summaryJSON, "interest_rate");
    if (interest_rate) { 
    if(!cJSON_IsNumber(interest_rate))
    {
    goto end; //Numeric
    }
    }

    // book_summary->funding_8h
    cJSON *funding_8h = cJSON_GetObjectItemCaseSensitive(book_summaryJSON, "funding_8h");
    if (funding_8h) { 
    if(!cJSON_IsNumber(funding_8h))
    {
    goto end; //Numeric
    }
    }

    // book_summary->mark_price
    cJSON *mark_price = cJSON_GetObjectItemCaseSensitive(book_summaryJSON, "mark_price");
    if (!mark_price) {
        goto end;
    }

    
    if(!cJSON_IsNumber(mark_price))
    {
    goto end; //Numeric
    }

    // book_summary->ask_price
    cJSON *ask_price = cJSON_GetObjectItemCaseSensitive(book_summaryJSON, "ask_price");
    if (!ask_price) {
        goto end;
    }

    
    if(!cJSON_IsNumber(ask_price))
    {
    goto end; //Numeric
    }

    // book_summary->instrument_name
    cJSON *instrument_name = cJSON_GetObjectItemCaseSensitive(book_summaryJSON, "instrument_name");
    if (!instrument_name) {
        goto end;
    }

    
    if(!cJSON_IsString(instrument_name))
    {
    goto end; //String
    }

    // book_summary->low
    cJSON *low = cJSON_GetObjectItemCaseSensitive(book_summaryJSON, "low");
    if (!low) {
        goto end;
    }

    
    if(!cJSON_IsNumber(low))
    {
    goto end; //Numeric
    }

    // book_summary->base_currency
    cJSON *base_currency = cJSON_GetObjectItemCaseSensitive(book_summaryJSON, "base_currency");
    if (base_currency) { 
    if(!cJSON_IsString(base_currency))
    {
    goto end; //String
    }
    }

    // book_summary->creation_timestamp
    cJSON *creation_timestamp = cJSON_GetObjectItemCaseSensitive(book_summaryJSON, "creation_timestamp");
    if (!creation_timestamp) {
        goto end;
    }

    
    if(!cJSON_IsNumber(creation_timestamp))
    {
    goto end; //Numeric
    }

    // book_summary->current_funding
    cJSON *current_funding = cJSON_GetObjectItemCaseSensitive(book_summaryJSON, "current_funding");
    if (current_funding) { 
    if(!cJSON_IsNumber(current_funding))
    {
    goto end; //Numeric
    }
    }


    book_summary_local_var = book_summary_create (
        underlying_index ? strdup(underlying_index->valuestring) : NULL,
        volume->valuedouble,
        volume_usd ? volume_usd->valuedouble : 0,
        underlying_price ? underlying_price->valuedouble : 0,
        bid_price->valuedouble,
        open_interest->valuedouble,
        strdup(quote_currency->valuestring),
        high->valuedouble,
        estimated_delivery_price ? estimated_delivery_price->valuedouble : 0,
        last->valuedouble,
        mid_price->valuedouble,
        interest_rate ? interest_rate->valuedouble : 0,
        funding_8h ? funding_8h->valuedouble : 0,
        mark_price->valuedouble,
        ask_price->valuedouble,
        strdup(instrument_name->valuestring),
        low->valuedouble,
        base_currency ? strdup(base_currency->valuestring) : NULL,
        creation_timestamp->valuedouble,
        current_funding ? current_funding->valuedouble : 0
        );

    return book_summary_local_var;
end:
    return NULL;

}
