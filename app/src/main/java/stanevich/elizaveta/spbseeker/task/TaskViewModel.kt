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

class TaskViewModel(
    application: Application
) : AndroidViewModel(application) {

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
            val getPropertiesDeferred = TaskApi.retrofitService.getTask(133)
            _properties.value = getPropertiesDeferred.await()
        }
    }
}