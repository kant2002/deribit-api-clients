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
SessionManagementAPI_privateDisableCancelOnDisconnectGet(apiClient_t *apiClient);


// Enable Cancel On Disconnect for the connection. This does not change the default account setting.
//
object_t*
SessionManagementAPI_privateEnableCancelOnDisconnectGet(apiClient_t *apiClient);


// Stop sending heartbeat messages.
//
object_t*
SessionManagementAPI_publicDisableHeartbeatGet(apiClient_t *apiClient);


// Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send `heartbeat` messages and `test_request` messages. Your software should respond to `test_request` messages by sending a `/api/v2/public/test` request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.
//
object_t*
SessionManagementAPI_publicSetHeartbeatGet(apiClient_t *apiClient ,double interval);


