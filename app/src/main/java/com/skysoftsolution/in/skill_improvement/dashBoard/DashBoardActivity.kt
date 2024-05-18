package com.skysoftsolution.`in`.skill_improvement.dashBoard

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.skysoftsolution.`in`.paymentintegration.dashboard.adapter.CustomAdapterForDash
import com.skysoftsolution.`in`.paymentintegration.dashboard.entity.ModuleForUse
import com.skysoftsolution.`in`.paymentintegration.dashboard.viewModel.DashBoardViewModel
import com.skysoftsolution.`in`.skill_improvement.R
import com.skysoftsolution.`in`.skill_improvement.databinding.ActivityDashBoardBinding

class DashBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashBoardBinding
    private val viewModel: DashBoardViewModel = DashBoardViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //  DateAndTimeWork();
        viewModel.userList.observe(this, Observer { userList ->
            // Update UI with user list data
            val adapter = CustomAdapterForDash(this, userList)
            binding.gridLayout.adapter = adapter
            adapter.notifyDataSetChanged()
        })

        // Add a sample user
        val newUser1 = ModuleForUse(1, "Hand Writing", R.drawable.write_pad)
        viewModel.addModule(newUser1)
        val newUser = ModuleForUse(2, "Video Recorder", R.drawable.video_recorder)
        viewModel.addModule(newUser)
        val newUser3 = ModuleForUse(3, "Camera X ", R.drawable.baseline_camera_24)
         viewModel.addModule(newUser3)
    }
    /*    private fun DateAndTimeWork() {
            binding.TimeCurrent.format12Hour = "hh:mm aa"
            val currentDate = System.currentTimeMillis()
            // Format date with "month day year" pattern
            val sdf = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
            val formattedDate = sdf.format(Date(currentDate))
            binding.monthCurrent.text = formattedDate
        }*/
}