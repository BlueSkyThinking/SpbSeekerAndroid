package stanevich.elizaveta.spbseeker

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import stanevich.elizaveta.spbseeker.network.TaskProperty

@BindingAdapter("imgUrl")
fun bindImage(imgView: ImageView, task: TaskProperty?) {
    task?.let{
        val imgUri = it.imgUrlList[0].toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}
