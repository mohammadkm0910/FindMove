package com.mohammad.kk.findmove.util

import org.koin.dsl.module

val rootSnackBarProvider = module {
    single { RootSnackBar()}
}