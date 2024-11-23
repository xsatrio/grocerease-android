package com.exal.grocerease.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    var _listItem = MutableLiveData<List<String>>() //hapus, buat test doang
}