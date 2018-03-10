package com.example.myapplication

import com.example.myapplication.util.crashlibrary.CrashReportingTree
import timber.log.Timber

class ProdApp : App() {

    override fun createTimberTree(): Timber.Tree = CrashReportingTree()
}