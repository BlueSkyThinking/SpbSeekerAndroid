package stanevich.elizaveta.spbseeker.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import stanevich.elizaveta.spbseeker.databinding.FragmentTaskBinding

class TaskFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentTaskBinding.inflate(inflater)

        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application

        val viewModelFactory = TaskViewModelFactory(application)

        val taskViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(TaskViewModel::class.java)

        binding.taskViewModel = taskViewModel



        return binding.root
    }
}