package com.ixs.testing.hilt.utils

import java.lang.Exception

sealed class DataState<out R> {
    data class Success<out T>(val data:T): DataState<T>()
    data class Error(val exception: Exception): DataState<Nothing>()
    object loading:DataState<Nothing>()
}