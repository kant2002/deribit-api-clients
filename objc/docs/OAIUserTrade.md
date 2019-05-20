# OAIUserTrade

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | **NSString*** | Trade direction of the taker | 
**feeCurrency** | **NSString*** | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**orderId** | **NSString*** | Id of the user order (maker or taker), i.e. subscriber&#39;s order id that took part in the trade | 
**timestamp** | **NSNumber*** | The timestamp of the trade | 
**price** | **NSNumber*** | The price of the trade | 
**iv** | **NSNumber*** | Option implied volatility for the price (Option only) | [optional] 
**tradeId** | **NSString*** | Unique (per currency) trade identifier | 
**fee** | **NSNumber*** | User&#39;s fee in units of the specified &#x60;fee_currency&#x60; | 
**orderType** | **NSString*** | Order type: &#x60;\&quot;limit&#x60;, &#x60;\&quot;market\&quot;&#x60;, or &#x60;\&quot;liquidation\&quot;&#x60; | [optional] 
**tradeSeq** | **NSNumber*** | The sequence number of the trade within instrument | 
**selfTrade** | **NSNumber*** | &#x60;true&#x60; if the trade is against own order. This can only happen when your account has self-trading enabled. Contact an administrator if you think you need that | 
**state** | **NSString*** | order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60; or &#x60;\&quot;archive\&quot;&#x60; (if order was archived) | 
**label** | **NSString*** | User defined label (presented only when previously set for order by user) | [optional] 
**indexPrice** | **NSNumber*** | Index Price at the moment of trade | 
**amount** | **NSNumber*** | Trade amount. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**instrumentName** | **NSString*** | Unique instrument identifier | 
**tickDirection** | **NSNumber*** | Direction of the \&quot;tick\&quot; (&#x60;0&#x60; &#x3D; Plus Tick, &#x60;1&#x60; &#x3D; Zero-Plus Tick, &#x60;2&#x60; &#x3D; Minus Tick, &#x60;3&#x60; &#x3D; Zero-Minus Tick). | 
**matchingId** | **NSString*** | Always &#x60;null&#x60;, except for a self-trade which is possible only if self-trading is switched on for the account (in that case this will be id of the maker order of the subscriber) | 
**liquidity** | **NSString*** | Describes what was role of users order: &#x60;\&quot;M\&quot;&#x60; when it was maker order, &#x60;\&quot;T\&quot;&#x60; when it was taker order | [optional] 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


