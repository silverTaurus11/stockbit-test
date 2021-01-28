package com.project.bibit_test.ui.logout

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.project.bibit_test.R
import org.koin.android.viewmodel.ext.android.viewModel

class LogoutDialog : DialogFragment() {

    companion object{
        private const val TAG = "LogoutDialog"

        fun show(supportFragmentManager: FragmentManager, newLogOutListener: LogOutListener){
            if(supportFragmentManager.findFragmentByTag(TAG) == null){
                LogoutDialog().apply {
                    logoutListener = newLogOutListener
                }.show(supportFragmentManager, TAG)
            }
        }
    }

    private val logoutViewModel: LogoutViewModel by viewModel()


    var logoutListener: LogOutListener ?= null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.logout_dialog)
                    .setPositiveButton(R.string.ok_label) { _, _ ->
                        logoutListener?.doThirdPartyLogoutAction()
                        logoutViewModel.logout()
                        Handler(Looper.getMainLooper()).postDelayed({
                            logoutListener?.doAfterLogout()
                            dismissAllowingStateLoss()
                        }, 150)
                    }
                    .setNegativeButton(R.string.cancel_label) { _, _ ->
                        dismissAllowingStateLoss()
                    }
            builder.setCancelable(false).create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    interface LogOutListener{
        fun doAfterLogout()
        fun doThirdPartyLogoutAction()
    }
}
