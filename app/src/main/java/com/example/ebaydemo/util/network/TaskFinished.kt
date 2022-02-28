package com.example.ebaydemo.util.network

interface TaskFinished<T> {
    fun onTaskFinished(data: T)
}