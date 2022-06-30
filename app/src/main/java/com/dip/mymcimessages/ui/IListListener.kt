package com.dip.mymcimessages.ui

interface IListListener {
    fun showListDeleteDialog()
    fun listChanged(size: Int)
}