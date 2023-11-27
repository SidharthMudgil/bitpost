package com.sidharth.bitpost.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sidharth.bitpost.R
import com.sidharth.bitpost.core.utils.NetworkUtils
import com.sidharth.bitpost.databinding.ActivityMainBinding
import com.sidharth.bitpost.presentation.fragments.HomeFragmentDirections
import com.sidharth.bitpost.presentation.fragments.ResultFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var navHostFragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(activityMainBinding.root)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        if (NetworkUtils.isNetworkConnected(this).not()) {
            runOnUiThread {
                navHostFragment?.findNavController()?.navigate(
                    HomeFragmentDirections.actionHomeFragmentToNoNetworkFragment()
                )
            }
        }
        setupNetworkCallback()
    }


    override fun onPause() {
        super.onPause()
        NetworkUtils.stopNetworkCallback(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        NetworkUtils.stopNetworkCallback(this)
    }

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        navHostFragment?.findNavController()?.currentDestination?.let {
            if (it.id != R.id.noNetworkFragment) {
                super.onBackPressed()
            }
        }
    }

    private fun setupNetworkCallback() {
        NetworkUtils.startNetworkCallback(
            context = applicationContext,
            onConnectionAvailable = {
                if (navHostFragment?.findNavController()?.currentDestination?.id == R.id.noNetworkFragment) {
                    runOnUiThread {
                        navHostFragment?.findNavController()?.navigateUp()
                    }
                }
            },
            onConnectionLost = {
                val action = when (navHostFragment?.findNavController()?.currentDestination?.id) {
                    R.id.homeFragment -> HomeFragmentDirections.actionHomeFragmentToNoNetworkFragment()
                    R.id.resultFragment -> ResultFragmentDirections.actionResultFragmentToNoNetworkFragment()
                    else -> null
                }
                action?.let {
                    runOnUiThread {
                        navHostFragment?.findNavController()?.navigate(it)
                    }
                }
            }
        )
    }
}