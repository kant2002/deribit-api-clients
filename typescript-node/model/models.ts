export * from './addressBookItem';
export * from './bookSummary';
export * from './currency';
export * from './currencyPortfolio';
export * from './currencyWithdrawalPriorities';
export * from './deposit';
export * from './instrument';
export * from './keyNumberPair';
export * from './order';
export * from './orderIdInitialMarginPair';
export * from './portfolio';
export * from './portfolioEth';
export * from './position';
export * from './publicTrade';
export * from './settlement';
export * from './tradesVolumes';
export * from './transferItem';
export * from './types';
export * from './userTrade';
export * from './withdrawal';

import localVarRequest = require('request');

import { AddressBookItem } from './addressBookItem';
import { BookSummary } from './bookSummary';
import { Currency } from './currency';
import { CurrencyPortfolio } from './currencyPortfolio';
import { CurrencyWithdrawalPriorities } from './currencyWithdrawalPriorities';
import { Deposit } from './deposit';
import { Instrument } from './instrument';
import { KeyNumberPair } from './keyNumberPair';
import { Order } from './order';
import { OrderIdInitialMarginPair } from './orderIdInitialMarginPair';
import { Portfolio } from './portfolio';
import { PortfolioEth } from './portfolioEth';
import { Position } from './position';
import { PublicTrade } from './publicTrade';
import { Settlement } from './settlement';
import { TradesVolumes } from './tradesVolumes';
import { TransferItem } from './transferItem';
import { Types } from './types';
import { UserTrade } from './userTrade';
import { Withdrawal } from './withdrawal';

/* tslint:disable:no-unused-variable */
let primitives = [
                    "string",
                    "boolean",
                    "double",
                    "integer",
                    "long",
                    "float",
                    "number",
                    "any"
                 ];
                 
let enumsMap: {[index: string]: any} = {
        "AddressBookItem.CurrencyEnum": AddressBookItem.CurrencyEnum,
        "AddressBookItem.TypeEnum": AddressBookItem.TypeEnum,
        "Currency.CoinTypeEnum": Currency.CoinTypeEnum,
        "CurrencyPortfolio.CurrencyEnum": CurrencyPortfolio.CurrencyEnum,
        "Deposit.StateEnum": Deposit.StateEnum,
        "Deposit.CurrencyEnum": Deposit.CurrencyEnum,
        "Instrument.QuoteCurrencyEnum": Instrument.QuoteCurrencyEnum,
        "Instrument.KindEnum": Instrument.KindEnum,
        "Instrument.OptionTypeEnum": Instrument.OptionTypeEnum,
        "Instrument.SettlementPeriodEnum": Instrument.SettlementPeriodEnum,
        "Instrument.BaseCurrencyEnum": Instrument.BaseCurrencyEnum,
        "Order.DirectionEnum": Order.DirectionEnum,
        "Order.TimeInForceEnum": Order.TimeInForceEnum,
        "Order.OrderStateEnum": Order.OrderStateEnum,
        "Order.AdvancedEnum": Order.AdvancedEnum,
        "Order.OrderTypeEnum": Order.OrderTypeEnum,
        "Order.OriginalOrderTypeEnum": Order.OriginalOrderTypeEnum,
        "Order.TriggerEnum": Order.TriggerEnum,
        "PortfolioEth.CurrencyEnum": PortfolioEth.CurrencyEnum,
        "Position.DirectionEnum": Position.DirectionEnum,
        "Position.KindEnum": Position.KindEnum,
        "PublicTrade.DirectionEnum": PublicTrade.DirectionEnum,
        "PublicTrade.TickDirectionEnum": PublicTrade.TickDirectionEnum,
        "Settlement.TypeEnum": Settlement.TypeEnum,
        "TradesVolumes.CurrencyPairEnum": TradesVolumes.CurrencyPairEnum,
        "TransferItem.DirectionEnum": TransferItem.DirectionEnum,
        "TransferItem.CurrencyEnum": TransferItem.CurrencyEnum,
        "TransferItem.StateEnum": TransferItem.StateEnum,
        "TransferItem.TypeEnum": TransferItem.TypeEnum,
        "UserTrade.DirectionEnum": UserTrade.DirectionEnum,
        "UserTrade.FeeCurrencyEnum": UserTrade.FeeCurrencyEnum,
        "UserTrade.OrderTypeEnum": UserTrade.OrderTypeEnum,
        "UserTrade.StateEnum": UserTrade.StateEnum,
        "UserTrade.TickDirectionEnum": UserTrade.TickDirectionEnum,
        "UserTrade.LiquidityEnum": UserTrade.LiquidityEnum,
        "Withdrawal.CurrencyEnum": Withdrawal.CurrencyEnum,
        "Withdrawal.StateEnum": Withdrawal.StateEnum,
}

let typeMap: {[index: string]: any} = {
    "AddressBookItem": AddressBookItem,
    "BookSummary": BookSummary,
    "Currency": Currency,
    "CurrencyPortfolio": CurrencyPortfolio,
    "CurrencyWithdrawalPriorities": CurrencyWithdrawalPriorities,
    "Deposit": Deposit,
    "Instrument": Instrument,
    "KeyNumberPair": KeyNumberPair,
    "Order": Order,
    "OrderIdInitialMarginPair": OrderIdInitialMarginPair,
    "Portfolio": Portfolio,
    "PortfolioEth": PortfolioEth,
    "Position": Position,
    "PublicTrade": PublicTrade,
    "Settlement": Settlement,
    "TradesVolumes": TradesVolumes,
    "TransferItem": TransferItem,
    "Types": Types,
    "UserTrade": UserTrade,
    "Withdrawal": Withdrawal,
}

export class ObjectSerializer {
    public static findCorrectType(data: any, expectedType: string) {
        if (data == undefined) {
            return expectedType;
        } else if (primitives.indexOf(expectedType.toLowerCase()) !== -1) {
            return expectedType;
        } else if (expectedType === "Date") {
            return expectedType;
        } else {
            if (enumsMap[expectedType]) {
                return expectedType;
            }

            if (!typeMap[expectedType]) {
                return expectedType; // w/e we don't know the type
            }

            // Check the discriminator
            let discriminatorProperty = typeMap[expectedType].discriminator;
            if (discriminatorProperty == null) {
                return expectedType; // the type does not have a discriminator. use it.
            } else {
                if (data[discriminatorProperty]) {
                    var discriminatorType = data[discriminatorProperty];
                    if(typeMap[discriminatorType]){
                        return discriminatorType; // use the type given in the discriminator
                    } else {
                        return expectedType; // discriminator did not map to a type
                    }
                } else {
                    return expectedType; // discriminator was not present (or an empty string)
                }
            }
        }
    }

    public static serialize(data: any, type: string) {
        if (data == undefined) {
            return data;
        } else if (primitives.indexOf(type.toLowerCase()) !== -1) {
            return data;
        } else if (type.lastIndexOf("Array<", 0) === 0) { // string.startsWith pre es6
            let subType: string = type.replace("Array<", ""); // Array<Type> => Type>
            subType = subType.substring(0, subType.length - 1); // Type> => Type
            let transformedData: any[] = [];
            for (let index in data) {
                let date = data[index];
                transformedData.push(ObjectSerializer.serialize(date, subType));
            }
            return transformedData;
        } else if (type === "Date") {
            return data.toISOString();
        } else {
            if (enumsMap[type]) {
                return data;
            }
            if (!typeMap[type]) { // in case we dont know the type
                return data;
            }
            
            // Get the actual type of this object
            type = this.findCorrectType(data, type);

            // get the map for the correct type.
            let attributeTypes = typeMap[type].getAttributeTypeMap();
            let instance: {[index: string]: any} = {};
            for (let index in attributeTypes) {
                let attributeType = attributeTypes[index];
                instance[attributeType.baseName] = ObjectSerializer.serialize(data[attributeType.name], attributeType.type);
            }
            return instance;
        }
    }

    public static deserialize(data: any, type: string) {
        // polymorphism may change the actual type.
        type = ObjectSerializer.findCorrectType(data, type);
        if (data == undefined) {
            return data;
        } else if (primitives.indexOf(type.toLowerCase()) !== -1) {
            return data;
        } else if (type.lastIndexOf("Array<", 0) === 0) { // string.startsWith pre es6
            let subType: string = type.replace("Array<", ""); // Array<Type> => Type>
            subType = subType.substring(0, subType.length - 1); // Type> => Type
            let transformedData: any[] = [];
            for (let index in data) {
                let date = data[index];
                transformedData.push(ObjectSerializer.deserialize(date, subType));
            }
            return transformedData;
        } else if (type === "Date") {
            return new Date(data);
        } else {
            if (enumsMap[type]) {// is Enum
                return data;
            }

            if (!typeMap[type]) { // dont know the type
                return data;
            }
            let instance = new typeMap[type]();
            let attributeTypes = typeMap[type].getAttributeTypeMap();
            for (let index in attributeTypes) {
                let attributeType = attributeTypes[index];
                instance[attributeType.name] = ObjectSerializer.deserialize(data[attributeType.baseName], attributeType.type);
            }
            return instance;
        }
    }
}

export interface Authentication {
    /**
    * Apply authentication settings to header and query params.
    */
    applyToRequest(requestOptions: localVarRequest.Options): void;
}

export class HttpBasicAuth implements Authentication {
    public username: string = '';
    public password: string = '';

    applyToRequest(requestOptions: localVarRequest.Options): void {
        requestOptions.auth = {
            username: this.username, password: this.password
        }
    }
}

export class ApiKeyAuth implements Authentication {
    public apiKey: string = '';

    constructor(private location: string, private paramName: string) {
    }

    applyToRequest(requestOptions: localVarRequest.Options): void {
        if (this.location == "query") {
            (<any>requestOptions.qs)[this.paramName] = this.apiKey;
        } else if (this.location == "header" && requestOptions && requestOptions.headers) {
            requestOptions.headers[this.paramName] = this.apiKey;
        }
    }
}

export class OAuth implements Authentication {
    public accessToken: string = '';

    applyToRequest(requestOptions: localVarRequest.Options): void {
        if (requestOptions && requestOptions.headers) {
            requestOptions.headers["Authorization"] = "Bearer " + this.accessToken;
        }
    }
}

export class VoidAuth implements Authentication {
    public username: string = '';
    public password: string = '';

    applyToRequest(_: localVarRequest.Options): void {
        // Do nothing
    }
}