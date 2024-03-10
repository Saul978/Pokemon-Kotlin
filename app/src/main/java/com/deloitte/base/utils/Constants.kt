package com.deloitte.base.utils

object Constants {

    object General {
        const val HTTP_CONNECT_TIMEOUT = 60 * 1000.toLong()
        const val HTTP_READ_TIMEOUT = 60 * 1000.toLong()
        const val NETWORK_VIEW_TYPE = 2
        const val PRODUCT_VIEW_TYPE = 1
    }

    object Api {
        const val URL_API = "https://pokeapi.co/api/v2/"
    }

    object TokenApi {

    }


    object Arguments {

    }

    object Preferences {
        const val PREF_NAME = "preferences"
    }

    object ErrorCode {
        const val DEFAULT_ERROR_CODE = 400

    }
}