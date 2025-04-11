package com.where.to.go.domain.model

data class RequestState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String? = null
)