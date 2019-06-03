QT += network

HEADERS += \
# Models
    $${PWD}/OAIAddress_book_item.h \
    $${PWD}/OAIBook_summary.h \
    $${PWD}/OAICurrency.h \
    $${PWD}/OAICurrency_portfolio.h \
    $${PWD}/OAICurrency_withdrawal_priorities.h \
    $${PWD}/OAIDeposit.h \
    $${PWD}/OAIInstrument.h \
    $${PWD}/OAIKey_number_pair.h \
    $${PWD}/OAIOrder.h \
    $${PWD}/OAIOrder_id_initial_margin_pair.h \
    $${PWD}/OAIPortfolio.h \
    $${PWD}/OAIPortfolio_eth.h \
    $${PWD}/OAIPosition.h \
    $${PWD}/OAIPublic_trade.h \
    $${PWD}/OAISettlement.h \
    $${PWD}/OAITrades_volumes.h \
    $${PWD}/OAITransfer_item.h \
    $${PWD}/OAITypes.h \
    $${PWD}/OAIUser_trade.h \
    $${PWD}/OAIWithdrawal.h \
# APIs
    $${PWD}/OAIAccountManagementApi.h \
    $${PWD}/OAIAuthenticationApi.h \
    $${PWD}/OAIInternalApi.h \
    $${PWD}/OAIMarketDataApi.h \
    $${PWD}/OAIPrivateApi.h \
    $${PWD}/OAIPublicApi.h \
    $${PWD}/OAISupportingApi.h \
    $${PWD}/OAITradingApi.h \
    $${PWD}/OAIWalletApi.h \
# Others
    $${PWD}/OAIHelpers.h \
    $${PWD}/OAIHttpRequest.h \
    $${PWD}/OAIObject.h
    $${PWD}/OAIEnum.h    

SOURCES += \
# Models
    $${PWD}/OAIAddress_book_item.cpp \
    $${PWD}/OAIBook_summary.cpp \
    $${PWD}/OAICurrency.cpp \
    $${PWD}/OAICurrency_portfolio.cpp \
    $${PWD}/OAICurrency_withdrawal_priorities.cpp \
    $${PWD}/OAIDeposit.cpp \
    $${PWD}/OAIInstrument.cpp \
    $${PWD}/OAIKey_number_pair.cpp \
    $${PWD}/OAIOrder.cpp \
    $${PWD}/OAIOrder_id_initial_margin_pair.cpp \
    $${PWD}/OAIPortfolio.cpp \
    $${PWD}/OAIPortfolio_eth.cpp \
    $${PWD}/OAIPosition.cpp \
    $${PWD}/OAIPublic_trade.cpp \
    $${PWD}/OAISettlement.cpp \
    $${PWD}/OAITrades_volumes.cpp \
    $${PWD}/OAITransfer_item.cpp \
    $${PWD}/OAITypes.cpp \
    $${PWD}/OAIUser_trade.cpp \
    $${PWD}/OAIWithdrawal.cpp \
# APIs
    $${PWD}/OAIAccountManagementApi.cpp \
    $${PWD}/OAIAuthenticationApi.cpp \
    $${PWD}/OAIInternalApi.cpp \
    $${PWD}/OAIMarketDataApi.cpp \
    $${PWD}/OAIPrivateApi.cpp \
    $${PWD}/OAIPublicApi.cpp \
    $${PWD}/OAISupportingApi.cpp \
    $${PWD}/OAITradingApi.cpp \
    $${PWD}/OAIWalletApi.cpp \
# Others
    $${PWD}/OAIHelpers.cpp \
    $${PWD}/OAIHttpRequest.cpp

