package stanevich.elizaveta.spbseeker

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import stanevich.elizaveta.spbseeker.network.TaskProperty
import stanevich.elizaveta.spbseeker.task.TaskApiStatus

@BindingAdapter("imgUrl")
fun bindImage(imgView: ImageView, task: TaskProperty?) {
    task?.let {
        val imgUri = it.imgUrlList[0].toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}

@BindingAdapter("status")
fun bindStatus(progressBar: ProgressBar, taskStatus: TaskApiStatus?) {
    when (taskStatus) {
        TaskApiStatus.LOADING -> {
            progressBar.visibility = View.VISIBLE
        }
        TaskApiStatus.DONE -> {
            progressBar.visibility = View.GONE
        }
        TaskApiStatus.ERROR -> {
            progressBar.visibility = View.GONE
        }
    }
}

@BindingAdapter("statusToolBar")
fun bindStatusToolBar(constraintLayout: ConstraintLayout, taskStatus: TaskApiStatus?) {
    when (taskStatus) {
        TaskApiStatus.LOADING -> {
            constraintLayout.visibility = View.GONE
        }
        TaskApiStatus.DONE -> {
            constraintLayout.visibility = View.VISIBLE
        }
        TaskApiStatus.ERROR -> {
            constraintLayout.visibility = View.GONE
        }
    }
}
