package com.example.adiblar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.adiblar.adapters.BottomNavigationPagerAdapter
import com.example.adiblar.adapters.ImageAdapter
import com.example.adiblar.db.AppDatabase
import com.example.adiblar.db.WriterEntity
import com.example.adiblar.models.Writer
import com.google.firebase.database.*
import com.like.LikeButton
import com.like.OnLikeListener
import kotlinx.android.synthetic.main.fragment_image.view.*
import kotlinx.android.synthetic.main.item_writer.view.*
import java.util.*
import kotlin.collections.ArrayList

private const val ARG_PARAM1 = "param1"

class ImageFragment : Fragment() {
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    private lateinit var root: View
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var list: ArrayList<Writer>
    private lateinit var writer: Writer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_image, container, false)
        val writerDao = AppDatabase.getDatabase(root.context).writerDao()
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("all/uzbek/" + param1)
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list = ArrayList()
                for (child in snapshot.children) {
                    val value = child.getValue(Writer::class.java)
                    list.add(value!!)
                }

                root.rv.adapter =
                    ImageAdapter(list, object : ImageAdapter.OnClickListener {
                        override fun onClickWriter(writer: Writer) {
                            val bundle = Bundle()
                            bundle.putSerializable("writer", writer)
                            Navigation.findNavController(root)
                                .navigate(R.id.writer_info_fragment, bundle)
                        }

                    })
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        return root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}