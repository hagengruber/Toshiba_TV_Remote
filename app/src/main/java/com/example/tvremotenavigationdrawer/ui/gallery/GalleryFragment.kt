package com.example.tvremotenavigationdrawer.ui.gallery

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tvremotenavigationdrawer.R
import com.example.tvremotenavigationdrawer.ui.home.HomeFragment
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
                ViewModelProvider(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        // val textView: TextView = root.findViewById(R.id.text_gallery)
        // galleryViewModel.text.observe(viewLifecycleOwner, Observer {
        //     textView.text = it
        // })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ToDo: Code für Einstellungen schreiben
        val s = HomeFragment()

        view.findViewById<EditText>(R.id.ip).setText(s.get_ip_address())

        view.findViewById<Button>(R.id.button_change).setOnClickListener {

            val directory = File(Environment.getExternalStorageDirectory().toString() + File.separator + "tvRemote")

            if (!directory.exists()) { directory.mkdir() }

            val f = File(directory.path + "/" + "data" + ".txt")

            if (f.exists()) { f.delete() }

            val text = view.findViewById<EditText>(R.id.ip).text.toString()
            println("Inhalt von EditText:")
            println(text)
            println("Inhalt ENDE")

            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(text.toByteArray())
            fo.close()

            requireActivity().runOnUiThread { Toast.makeText(activity, "Einstellungen gespeichert", Toast.LENGTH_SHORT).show() }

        }

}

}