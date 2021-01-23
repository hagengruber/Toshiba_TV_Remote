package com.example.tvremotenavigationdrawer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.socket_test.Send_Frame
import com.example.tvremotenavigationdrawer.R
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
        view.findViewById<ImageButton>(R.id.imagebutton_source).setOnClickListener { thread { doSocket("1056") } }
        view.findViewById<ImageButton>(R.id.imagebutton_up).setOnClickListener { thread { doSocket("1020") } }
        view.findViewById<ImageButton>(R.id.imagebutton_down).setOnClickListener { thread { doSocket("1019") } }
        view.findViewById<ImageButton>(R.id.imagebutton_ok).setOnClickListener { thread { doSocket("1053") } }
        /*view.findViewById<Button>(R.id.settings_change).setOnClickListener {

            val ip_test = view.findViewById(R.id.ip) as TextView
            val ip = ip_test.text
            val textView: TextView = view.findViewById(R.id.home_string_ip) as TextView
            textView.text = ip

        }*/

    }

    fun doSocket(message: String){

        val address = "192.168.8.114"
        val port = 4660

        val frame = Send_Frame()
        frame.run(address, port, message)

    }


}