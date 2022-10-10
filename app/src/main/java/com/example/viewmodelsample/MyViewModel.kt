package com.example.viewmodelsample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * 절대 참조하면 안되는 것
 * View, Lifecycle, 기타 activity context 가지는 클래스
 * */

class MyViewModel : ViewModel() {

    private val _title = "Title Text"

    /* 1. LiveData 인스턴스 생성 */
    val title: MutableLiveData<String> by lazy {
        MutableLiveData<String>(_title)
    }

    fun setTitle(newData: String) {
        title.postValue(newData)
    }

    /* 2. Activity에서 만든 옵저버를 연결 */
    // data binding이라 패스~

    companion object {
        // Real apps would use a wrapper on the result type to handle this.
        const val LOADING_STRING = "Loading..."
    }
}


/**
 * Factory for [LiveDataViewModel].
 */
object LiveDataVMFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return MyViewModel() as T
    }
}