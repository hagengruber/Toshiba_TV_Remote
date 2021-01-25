package com.example.tvremotenavigationdrawer.ui.home

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.socket_test.Send_Frame
import com.example.tvremotenavigationdrawer.R
import java.io.File
import java.io.FileInputStream
import java.lang.IllegalStateException
import kotlin.concurrent.thread


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        // val textView: TextView = root.findViewById(R.id.text_home)
        //homeViewModel.text.observe(viewLifecycleOwner, Observer {
        //    textView.text = it
        //})

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*
        val m = view.findViewById(R.id.home_string_ip) as TextView

        if (m.text == "R") {
            val s = getString(R.string.settings_ip_edit)
            m.setText(s)
        }
*/

        view.findViewById<ImageButton>(R.id.imagebutton_source).setOnClickListener { thread { doSocket("1056", true) } }
        view.findViewById<ImageButton>(R.id.imagebutton_up).setOnClickListener { thread { doSocket("1020", true) } }
        view.findViewById<ImageButton>(R.id.imagebutton_down).setOnClickListener { thread { doSocket("1019", true) } }
        view.findViewById<ImageButton>(R.id.imagebutton_ok).setOnClickListener { thread { doSocket("1053", true) } }
        view.findViewById<ImageButton>(R.id.imagebutton_right).setOnClickListener { thread { doSocket("1022", true) } }
        view.findViewById<ImageButton>(R.id.imagebutton_left).setOnClickListener { thread { doSocket("1021", true) } }

    }

    fun get_ip_address() : String {

        val directory = File(Environment.getExternalStorageDirectory().toString() + File.separator + "tvRemote")

        try {
            println("\n\n1\n\n")

            println("2/1")
            if (directory.exists()) {

                val f = File(directory.path + "/" + "data" + ".txt")

                if(f.exists()) {
                    val t = FileInputStream(directory.path.toString() + "/" + "data" + ".txt")
                    val line = File(directory.path + "/" + "data" + ".txt").readLines().first()
                    println("Inhalt:")
                    println(line)
                    println("Inhalt Ende")
                    return line
                } else {
                    return ""
                }

            } else {
                return ""
            }
            println("\n\n2\n\n")

        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }

    }

    fun doSocket(message: String, send_message : Boolean) : Boolean {

        val ip = get_ip_address()
        if (ip == "") {

            try {
                requireActivity().runOnUiThread { Toast.makeText(activity, "Du hast noch keine IP-Adresse angegeben. Bitte überprüfe die Settings", Toast.LENGTH_SHORT).show() }
            } catch (e : IllegalStateException) {
                return true
            }

            return false

        }

        val port = 4660

        val frame = Send_Frame()
        val text = frame.run(ip, port, message)

        if (send_message && text != "") { requireActivity().runOnUiThread { Toast.makeText(activity, text, Toast.LENGTH_SHORT).show() } }

        return true

    }


}