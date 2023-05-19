package com.memad.coretempalte.utils

import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.memad.coretempalte.R


/*fun Context.unAvailableFeature(
    permission: String,
    permissionUnavailableMessage: String,
    permissionLauncher: ActivityResultLauncher<String>
) {
    MaterialAlertDialogBuilder(this)
        .setTitle(getString(R.string.unavilable))
        .setMessage(permissionUnavailableMessage)
        .setPositiveButton(getString(R.string.grant)) { _, _ ->
            permissionLauncher.launch(permission)
        }
        .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.cancel()
        }
        .create()
        .show()
}

fun Context.permissionExplanation(
    permissionNeededMessage: String,
    positiveButtonFunction: () -> Unit
) {
    MaterialAlertDialogBuilder(this)
        .setTitle(getString(R.string.permission_needed))
        .setMessage(
            permissionNeededMessage
        )
        .setPositiveButton(getString(R.string.ok)) { _, _ ->
            positiveButtonFunction()
        }
        .setNegativeButton(getString(R.string.no_thanks)) { dialog, _ ->
            dialog.dismiss()
        }
        .create()
        .show()
}

fun Context.checkmPermission(
    fragment: Fragment,
    permission: String,
    ifGrantedFunction: () -> Unit,
    permissionNeededMessage: String,
    permissionLauncher: ActivityResultLauncher<String>
) {

    when {
        PermissionChecker.checkSelfPermission(this, permission)
                == PermissionChecker.PERMISSION_GRANTED -> {
            ifGrantedFunction()
        }
        fragment.shouldShowRequestPermissionRationale(permission) -> {
            permissionExplanation(permissionNeededMessage) {
                permissionLauncher.launch(permission)
            }
        }
        else -> {
            permissionLauncher.launch(permission)
        }
    }
}*/


private val readContactsUnavailableMessage = "Getting your Contacts is unavailable " +
        "because you denied the Permission, you can Grant us the Permission to get " +
        "your Contacts"
private val readContactsNeededMessage =
    "This permission is needed to read your contacts. to help you to hide them from Social Media apps\n" +
            "this permission is primary in the app the whole app is relying on it."


private val writeContactsUnavailableMessage = "Write to your Contacts is unavailable " +
        "because you denied the Permission, you can Grant us the Permission to add new contacts " +
        "to your Contacts"

private val writeContactsNeededMessage =
    "This permission is needed to write to your contacts. to help you to hide them from Social Media apps\n" +
            "this permission is primary in the app the whole app is relying on it."

private val readStateUnavailableMessage = "Read Call States is unavailable " +
        "because you denied the Permission, you can Grant us the Permission to Read incoming calls "

private val readStateNeededMessage =
    "This permission is primary to read call states of your incoming calls.\n" +
            "this permission is primary in the app the whole app is relying on it."

private var readContactsPermissionLauncher =
    registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            //getContacts()
        } else {
            //unAvailableFeature(readContactsUnavailableMessage)
        }
    }

requireContext().checkmPermission(
parentFragmentManager.fragments.first(), Manifest.permission.WRITE_CONTACTS,
{}, readContactsNeededMessage, readContactsPermissionLauncher
)