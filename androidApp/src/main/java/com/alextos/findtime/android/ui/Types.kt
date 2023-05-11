package com.alextos.findtime.android.ui

import androidx.compose.runtime.Composable

typealias OnAddType = (List<String>) -> Unit
typealias OnDismissType = () -> Unit
typealias composeFun = @Composable () -> Unit
typealias topBarFun = @Composable (Int) -> Unit
@Composable fun emptyComposable() {}