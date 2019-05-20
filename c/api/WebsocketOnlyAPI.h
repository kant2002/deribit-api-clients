#include <stdlib.h>
#include <stdio.h>
#include "../include/apiClient.h"
#include "../include/list.h"
#include "../external/cJSON.h"
#include "../include/keyValuePair.h"
#include "../model/object.h"


// Disable Cancel On Disconnect for the connection. This does not change the default account setting.
//
object_t*
WebsocketOnlyAPI_privateDisableCancelOnDisconnectGet(apiClient_t *apiClient);


// Enable Cancel On Disconnect for the connection. This does not change the default account setting.
//
object_t*
WebsocketOnlyAPI_privateEnableCancelOnDisconnectGet(apiClient_t *apiClient);


// Gracefully close websocket connection, when COD (Cancel On Disconnect) is enabled orders are not cancelled
//
void
WebsocketOnlyAPI_privateLogoutGet(apiClient_t *apiClient);


// Subscribe to one or more channels.
//
// Subscribe to one or more channels.  The name of the channel determines what information will be provided, and in what form. 
//
object_t*
WebsocketOnlyAPI_privateSubscribeGet(apiClient_t *apiClient ,list_t * channels);


// Unsubscribe from one or more channels.
//
object_t*
WebsocketOnlyAPI_privateUnsubscribeGet(apiClient_t *apiClient ,list_t * channels);


// Stop sending heartbeat messages.
//
object_t*
WebsocketOnlyAPI_publicDisableHeartbeatGet(apiClient_t *apiClient);


// Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
//
object_t*
WebsocketOnlyAPI_publicHelloGet(apiClient_t *apiClient ,char * client_name ,char * client_version);


// Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send `heartbeat` messages and `test_request` messages. Your software should respond to `test_request` messages by sending a `/api/v2/public/test` request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.
//
object_t*
WebsocketOnlyAPI_publicSetHeartbeatGet(apiClient_t *apiClient ,double interval);


// Subscribe to one or more channels.
//
// Subscribe to one or more channels.  This is the same method as [/private/subscribe](#private_subscribe), but it can only be used for 'public' channels. 
//
object_t*
WebsocketOnlyAPI_publicSubscribeGet(apiClient_t *apiClient ,list_t * channels);


// Unsubscribe from one or more channels.
//
object_t*
WebsocketOnlyAPI_publicUnsubscribeGet(apiClient_t *apiClient ,list_t * channels);


