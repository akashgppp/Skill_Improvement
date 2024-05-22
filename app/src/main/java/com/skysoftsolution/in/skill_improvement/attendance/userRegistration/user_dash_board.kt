package com.skysoftsolution.`in`.skill_improvement.attendance.userRegistration

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
import com.skysoftsolution.`in`.skill_improvement.databinding.ActivityUserDashBoardBinding

class user_dash_board : AppCompatActivity() {
    private lateinit var binding: ActivityUserDashBoardBinding;
    private val viewModel: DashBoardViewModel = DashBoardViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.userList.observe(this, Observer { userList ->
            // Update UI with user list data
            val adapter = CustomAdapterForDash(this, userList)
            binding.gridLayout.adapter = adapter
            adapter.notifyDataSetChanged()
        })

        // Add a sample user
        val newUser1 = ModuleForUse(5, "Registration", R.drawable.write_pad)
        viewModel.addModule(newUser1)
        val newUser = ModuleForUse(6, "Recognition", R.drawable.video_recorder)
        viewModel.addModule(newUser)
    }
}