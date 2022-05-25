package com.example.tvremotenavigationdrawer.ui.gallery

import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tvremotenavigationdrawer.BuildConfig
import com.example.tvremotenavigationdrawer.R
import com.example.tvremotenavigationdrawer.ui.home.HomeFragment
import java.io.File
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

        // Create Object for IP-Address
        val s = HomeFragment()

        // Set Text from EditText to current IP
        // Returns "" or IP in String
        view.findViewById<EditText>(R.id.ip).setText(s.get_ip_address())
        view.findViewById<EditText>(R.id.port).setText(s.get_port())

        // If Settings changed
        view.findViewById<Button>(R.id.button_change).setOnClickListener {
/*
try {
    var myExternalFile: File
    myExternalFile = File(
        Environment.getExternalStorageDirectory().toString() + File.separator + "tvRemote",
        "test.txt"
    )
    val fos: FileOutputStream = FileOutputStream(myExternalFile)
    val text =
        view.findViewById<EditText>(R.id.ip).text.toString() + ";" + view.findViewById<EditText>(R.id.port).text.toString()
    fos.write(text.toByteArray())
    fos.close()

}catch (e : java.io.IOException) {
    requireActivity().runOnUiThread { Toast.makeText(activity, ""+e, Toast.LENGTH_SHORT).show() }
}



*/









            val directory = File(Environment.getExternalStorageDirectory().toString() + File.separator + "tvRemote")

            try {

                // Get writeable Directory
                // val directory = File(Environment.getExternalStorageDirectory().toString() + File.separator + "tvRemote")

                // Create Directory if does not exist
                /*if (!directory.exists()) {
                    directory.mkdir()
                }*/

                val regex = """^(?:[0-9]{1,3}\.){3}[0-9]{1,3}${'$'}""".toRegex()

                if (BuildConfig.DEBUG && !regex.containsMatchIn(view.findViewById<EditText>(R.id.ip).text.toString())) {
                    requireActivity().runOnUiThread { Toast.makeText(activity, "IP-Address is wrong", Toast.LENGTH_SHORT).show() }
                } else {

                    if (control_port(view.findViewById<EditText>(R.id.port).text.toString())) {

                        // Creates File "data.txt" -> stores all data
                        /*val f = File(directory.path + "/" + "data" + ".txt")
                        if (f.exists()) {
                            f.delete()
                        }

                        // Get value of EditText (custom IP-Address)
                        val text = view.findViewById<EditText>(R.id.ip).text.toString() + ";" + view.findViewById<EditText>(R.id.port).text.toString()

                        // Saves Data in File
                        f.createNewFile()
                        val fo = FileOutputStream(f)
                        fo.write(text.toByteArray())
                        fo.close()
                        */
                        // Popupbox for Success
                        // ToDo: English text
                        requireActivity().runOnUiThread { Toast.makeText(activity, "Settings saved", Toast.LENGTH_SHORT).show() }

                    } else { requireActivity().runOnUiThread { Toast.makeText(activity, "Port must be a Number", Toast.LENGTH_SHORT).show() } }

                }

            } catch (e : java.io.IOException) {
                requireActivity().runOnUiThread { Toast.makeText(activity, ""+MediaStore.Files(), Toast.LENGTH_SHORT).show() }
            }

        }

}

    fun control_port(text : String) : Boolean {

        try {
            text.toInt()
            return true
        } catch (e : java.lang.NumberFormatException) {
            println("Fehler:")
            println(e.toString())
            println("Ende")
            return false
        }

    }

}