package com.sidharth.bitpost.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sidharth.bitpost.domain.model.ContentResult
import com.sidharth.bitpost.domain.usecase.GenerateContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val generateContentUseCase: GenerateContentUseCase
) : ViewModel() {
    private val _result = MutableStateFlow<ContentResult>(ContentResult.Loading)
    val result: StateFlow<ContentResult> = _result

    suspend fun fetchResult(
        prompt: String
    ) = viewModelScope.launch {
        generateContentUseCase.invoke(prompt)
            .collectLatest {
                _result.emit(it)
            }
    }
}