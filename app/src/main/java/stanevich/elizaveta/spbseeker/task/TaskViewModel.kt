package stanevich.elizaveta.spbseeker.task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import stanevich.elizaveta.spbseeker.network.TaskApi
import stanevich.elizaveta.spbseeker.network.TaskProperty

enum class TaskApiStatus { LOADING, DONE, ERROR }

class TaskViewModel(
    application: Application
) : AndroidViewModel(application) {


    private val _status = MutableLiveData<TaskApiStatus>()

    val status: LiveData<TaskApiStatus>
        get() = _status

    private val _properties = MutableLiveData<TaskProperty>()

    val properties: LiveData<TaskProperty>
        get() = _properties

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        getTaskProperties()
    }

    private fun getTaskProperties() {
        coroutineScope.launch {
            val listResult: TaskProperty
            val getPropertiesDeferred = TaskApi.retrofitService.getTask(133)
            try {
                _status.value = TaskApiStatus.LOADING
                listResult = getPropertiesDeferred.await()
                _status.value = TaskApiStatus.DONE
                _properties.value = listResult
            } catch (e: Exception) {
                _status.value = TaskApiStatus.ERROR
            }
        }
    }
}