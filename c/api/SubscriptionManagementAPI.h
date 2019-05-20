#include <stdlib.h>
#include <stdio.h>
#include "../include/apiClient.h"
#include "../include/list.h"
#include "../external/cJSON.h"
#include "../include/keyValuePair.h"
#include "../model/object.h"


// Subscribe to one or more channels.
//
// Subscribe to one or more channels.  The name of the channel determines what information will be provided, and in what form. 
//
object_t*
SubscriptionManagementAPI_privateSubscribeGet(apiClient_t *apiClient ,list_t * channels);


// Unsubscribe from one or more channels.
//
object_t*
SubscriptionManagementAPI_privateUnsubscribeGet(apiClient_t *apiClient ,list_t * channels);


// Subscribe to one or more channels.
//
// Subscribe to one or more channels.  This is the same method as [/private/subscribe](#private_subscribe), but it can only be used for 'public' channels. 
//
object_t*
SubscriptionManagementAPI_publicSubscribeGet(apiClient_t *apiClient ,list_t * channels);


// Unsubscribe from one or more channels.
//
object_t*
SubscriptionManagementAPI_publicUnsubscribeGet(apiClient_t *apiClient ,list_t * channels);


