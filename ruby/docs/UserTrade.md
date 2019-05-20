# OpenapiClient::UserTrade

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | **String** | Trade direction of the taker | 
**fee_currency** | **String** | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**order_id** | **String** | Id of the user order (maker or taker), i.e. subscriber&#39;s order id that took part in the trade | 
**timestamp** | **Integer** | The timestamp of the trade | 
**price** | **Float** | The price of the trade | 
**iv** | **Float** | Option implied volatility for the price (Option only) | [optional] 
**trade_id** | **String** | Unique (per currency) trade identifier | 
**fee** | **Float** | User&#39;s fee in units of the specified &#x60;fee_currency&#x60; | 
**order_type** | **String** | Order type: &#x60;\&quot;limit&#x60;, &#x60;\&quot;market\&quot;&#x60;, or &#x60;\&quot;liquidation\&quot;&#x60; | [optional] 
**trade_seq** | **Integer** | The sequence number of the trade within instrument | 
**self_trade** | **Boolean** | &#x60;true&#x60; if the trade is against own order. This can only happen when your account has self-trading enabled. Contact an administrator if you think you need that | 
**state** | **String** | order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60; or &#x60;\&quot;archive\&quot;&#x60; (if order was archived) | 
**label** | **String** | User defined label (presented only when previously set for order by user) | [optional] 
**index_price** | **Float** | Index Price at the moment of trade | 
**amount** | **Float** | Trade amount. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**instrument_name** | **String** | Unique instrument identifier | 
**tick_direction** | **Integer** | Direction of the \&quot;tick\&quot; (&#x60;0&#x60; &#x3D; Plus Tick, &#x60;1&#x60; &#x3D; Zero-Plus Tick, &#x60;2&#x60; &#x3D; Minus Tick, &#x60;3&#x60; &#x3D; Zero-Minus Tick). | 
**matching_id** | **String** | Always &#x60;null&#x60;, except for a self-trade which is possible only if self-trading is switched on for the account (in that case this will be id of the maker order of the subscriber) | 
**liquidity** | **String** | Describes what was role of users order: &#x60;\&quot;M\&quot;&#x60; when it was maker order, &#x60;\&quot;T\&quot;&#x60; when it was taker order | [optional] 

## Code Sample

```ruby
require 'OpenapiClient'

instance = OpenapiClient::UserTrade.new(direction: null,
                                 fee_currency: null,
                                 order_id: null,
                                 timestamp: 1517329113791,
                                 price: null,
                                 iv: null,
                                 trade_id: null,
                                 fee: null,
                                 order_type: null,
                                 trade_seq: null,
                                 self_trade: null,
                                 state: null,
                                 label: null,
                                 index_price: null,
                                 amount: null,
                                 instrument_name: BTC-PERPETUAL,
                                 tick_direction: null,
                                 matching_id: null,
                                 liquidity: null)
```


