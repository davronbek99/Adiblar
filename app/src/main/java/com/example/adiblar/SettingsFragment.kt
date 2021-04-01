package com.example.adiblar

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.example.adiblar.models.Writer
import com.example.adiblar.sharedPreference.MySharedPreferences
import kotlinx.android.synthetic.main.fragment_settings.view.*
import kotlinx.android.synthetic.main.item_language.view.*
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SettingsFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var root: View
    private lateinit var writer: Writer
    private lateinit var mySharedPreference: MySharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_settings, container, false)
        mySharedPreference = MySharedPreferences(root.context)
        if (mySharedPreference.getBoolean() == true) {
            root.switch_rejim!!.isChecked = true
        }

        root.switch_rejim!!.setOnCheckedChangeListener { p0, p1 ->

            if (root.switch_rejim!!.isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                mySharedPreference.setBoolean(true)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                mySharedPreference.setBoolean(false)
            }
        }


        root.light_language_tv_id.setOnClickListener {
            val alertDialog = AlertDialog.Builder(root.context).create()
            val view = inflater.inflate(R.layout.item_language, null)
            alertDialog.setView(view)
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.window?.setGravity(Gravity.CENTER)
            if (mySharedPreference.getLang() == "uz") {
                view.radio1.isChecked = true
            } else {
                view.radio2.isChecked = true
            }
            view.radio_group.setOnCheckedChangeListener { p0, p1 ->

                when (p1) {
                    R.id.radio1 -> {
                        mySharedPreference.setLang("uz")
                    }
                    R.id.radio2 -> {
                        mySharedPreference.setLang("ru")
                    }
                }
            }

            view.cancel_tv.setOnClickListener {
                alertDialog.dismiss()
            }
            view.ok_tv.setOnClickListener {
                forRestartIntent2()
                alertDialog.dismiss()
            }

            alertDialog.show()
        }


        root.share_card_id.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val shareBody = "${writer.name}\n${writer.img}"
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            this.startActivity(sharingIntent)

        }

        root.info_card_id.setOnClickListener {
            val dialog = AlertDialog.Builder(root.context).create()
            val view = inflater.inflate(R.layout.item_dialog, null, false)
            dialog.setView(view)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setGravity(Gravity.CENTER)
            dialog.show()
        }
        return root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun forRestartIntent2() {
        val intent = Intent(root.context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        (activity as MainActivity).finish()
    }
    private fun setLocale() {
        val locale = Locale(mySharedPreference.getLang())
        Locale.setDefault(locale)
        val config: Configuration = resources.configuration
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)
    }

}