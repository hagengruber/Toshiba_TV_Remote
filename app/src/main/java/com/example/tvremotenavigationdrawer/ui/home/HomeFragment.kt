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

        // Click Listener
        // Send a Message to doSocket() with Ascii Code
        view.findViewById<ImageButton>(R.id.imagebutton_source).setOnClickListener { thread { doSocket("1056", true) } }
        view.findViewById<ImageButton>(R.id.imagebutton_up).setOnClickListener { thread { doSocket("1020", true) } }
        view.findViewById<ImageButton>(R.id.imagebutton_down).setOnClickListener { thread { doSocket("1019", true) } }
        view.findViewById<ImageButton>(R.id.imagebutton_ok).setOnClickListener { thread { doSocket("1053", true) } }
        view.findViewById<ImageButton>(R.id.imagebutton_right).setOnClickListener { thread { doSocket("1022", true) } }
        view.findViewById<ImageButton>(R.id.imagebutton_left).setOnClickListener { thread { doSocket("1021", true) } }
        view.findViewById<ImageButton>(R.id.imageButton_setting).setOnClickListener { thread { doSocket("1048", true) } }
        view.findViewById<ImageButton>(R.id.imagebutton_back).setOnClickListener { thread { doSocket("1010", true) } }
        view.findViewById<ImageButton>(R.id.imagebutton_exit).setOnClickListener { thread { doSocket("1037", true) } }

    }

    // Returns IP-Adress from data.txt
    fun get_ip_address() : String {

        // Writeable Directory
        val directory = File(Environment.getExternalStorageDirectory().toString() + File.separator + "tvRemote")

        try {

            if (directory.exists()) {

                val f = File(directory.path + "/" + "data" + ".txt")

                if(f.exists()) {

                    // If both Directory and File exist, return IP
                    val line = File(directory.path + "/" + "data" + ".txt").readLines().first()
                    return line

                } else {
                    // If File does not exist, return ""
                    return ""
                }

            } else {
                // If Directory does not exist, return ""
                return ""
            }

        } catch (e: Exception) {
            // Something wrong :(
            e.printStackTrace()
            return ""
        }

    }

    // Calls Send_Frame().run()
    // Send Frame
    fun doSocket(message: String, send_message : Boolean) : Boolean {

        // Get IP
        val ip = get_ip_address()
        // If there's no current IP
        if (ip == "") {

            try {
                // MessageBox
                // ToDO: English Text
                requireActivity().runOnUiThread { Toast.makeText(activity, "Du hast noch keine IP-Adresse angegeben. Bitte überprüfe die Settings", Toast.LENGTH_SHORT).show() }
            } catch (e : IllegalStateException) {
                // If MessageBox fales return true
                return true
            }

            // If MessageBox was successfull, return true
            return false

        }

        // Port for TV
        val port = 4660

        // Create Object Send_Frame()
        val frame = Send_Frame()
        // Send Frame
        // Returns Message from run()
        val text = frame.run(ip, port, message)

        // If send_message true = Create MessageBox with Error Message from run()
        // If send_message false = Dont create MessageBox (for audio Button)
        // If there's a Error Message from run
        if (send_message && text != "") { requireActivity().runOnUiThread { Toast.makeText(activity, text, Toast.LENGTH_SHORT).show() } }

        return true

    }


}