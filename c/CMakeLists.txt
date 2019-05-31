cmake_minimum_required (VERSION 2.6)
project (CGenerator)

cmake_policy(SET CMP0063 NEW)

set(CMAKE_C_VISIBILITY_PRESET default)
set(CMAKE_CXX_VISIBILITY_PRESET default)
set(CMAKE_VISIBILITY_INLINES_HIDDEN OFF)
set(CMAKE_BUILD_TYPE Debug)

set(pkgName "deribit_api")

find_package(CURL 7.58.0 REQUIRED)
if(CURL_FOUND)
	include_directories(${CURL_INCLUDE_DIR})
	set(PLATFORM_LIBRARIES ${PLATFORM_LIBRARIES} ${CURL_LIBRARIES} )
else(CURL_FOUND)
	message(FATAL_ERROR "Could not find the CURL library and development files.")
endif()

set(SRCS
    src/list.c
    src/apiKey.c
    src/apiClient.c
    external/cJSON.c
    model/address_book_item.c
    model/book_summary.c
    model/currency.c
    model/currency_portfolio.c
    model/currency_withdrawal_priorities.c
    model/deposit.c
    model/instrument.c
    model/key_number_pair.c
    model/order.c
    model/order_id_initial_margin_pair.c
    model/portfolio.c
    model/portfolio_eth.c
    model/position.c
    model/public_trade.c
    model/settlement.c
    model/trades_volumes.c
    model/transfer_item.c
    model/types.c
    model/user_trade.c
    model/withdrawal.c
    api/AccountManagementAPI.c
    api/AuthenticationAPI.c
    api/InternalAPI.c
    api/MarketDataAPI.c
    api/PrivateAPI.c
    api/PublicAPI.c
    api/SupportingAPI.c
    api/TradingAPI.c
    api/WalletAPI.c

)

set(HDRS
    include/apiClient.h
    include/list.h
    include/keyValuePair.h
    external/cJSON.h
    model/address_book_item.h
    model/book_summary.h
    model/currency.h
    model/currency_portfolio.h
    model/currency_withdrawal_priorities.h
    model/deposit.h
    model/instrument.h
    model/key_number_pair.h
    model/order.h
    model/order_id_initial_margin_pair.h
    model/portfolio.h
    model/portfolio_eth.h
    model/position.h
    model/public_trade.h
    model/settlement.h
    model/trades_volumes.h
    model/transfer_item.h
    model/types.h
    model/user_trade.h
    model/withdrawal.h
    api/AccountManagementAPI.h
    api/AuthenticationAPI.h
    api/InternalAPI.h
    api/MarketDataAPI.h
    api/PrivateAPI.h
    api/PublicAPI.h
    api/SupportingAPI.h
    api/TradingAPI.h
    api/WalletAPI.h

)

# Add library with project file with projectname as library name
add_library(${pkgName} SHARED ${SRCS} ${HDRS})
# Link dependent libraries
target_link_libraries(${pkgName} ${CURL_LIBRARIES} )
#install library to destination
install(TARGETS ${pkgName} DESTINATION ${CMAKE_INSTALL_PREFIX})

# Setting file variables to null
set(SRCS "")
set(HDRS "")


## This section shows how to use the above compiled libary to compile the source files
## set source files
#set(SRCS
#    unit-tests/manual-PetAPI.c
#    unit-tests/manual-StoreAPI.c
#    unit-tests/manual-UserAPI.c
#    unit-tests/manual-order.c
#    unit-tests/manual-user.c)

##set header files
#set(HDRS
#)

## loop over all files in SRCS variable
#foreach(SOURCE_FILE ${SRCS})
#    # Get only the file name from the file as add_executable doesnot support executable with slash("/")
#    get_filename_component(FILE_NAME_ONLY ${SOURCE_FILE} NAME_WE)
#    # Remove .c from the file name and set it as executable name
#    string( REPLACE ".c" "" EXECUTABLE_FILE ${FILE_NAME_ONLY})
#    # Add executable for every source file in  SRCS
#    add_executable(unit-${EXECUTABLE_FILE} ${SOURCE_FILE})
#    # Link above created libary to executable and dependent libary curl
#    target_link_libraries(unit-${EXECUTABLE_FILE} ${CURL_LIBRARIES} ${pkgName} )
#endforeach(SOURCE_FILE ${SRCS})
