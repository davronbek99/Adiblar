package com.example.adiblar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.Navigation
import com.example.adiblar.adapters.ImageAdapter
import com.example.adiblar.models.Writer
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_search.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    lateinit var root: View
    lateinit var imageAdapter: ImageAdapter
    lateinit var list: ArrayList<Writer>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_search, container, false)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("all/uzbek/" + param1)
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list = ArrayList()
                for (child in snapshot.children) {
                    val value = child.getValue(Writer::class.java)
                    list.add(value!!)

                }
                setAdapter(list)
                root.search_id.addTextChangedListener {
                    var writerList = ArrayList<Writer>()
                    list.forEach { writer ->
                        if (writer.name?.toLowerCase()?.contains(it.toString().toLowerCase())!!) {
                            writerList.add(writer)
                        }
                    }
                    setAdapter(writerList)
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun setAdapter(l: ArrayList<Writer>) {
        imageAdapter = ImageAdapter(l, object : ImageAdapter.OnClickListener {
            override fun onClickWriter(writer: Writer) {
                val bundle = Bundle()
                bundle.putSerializable("writer", writer)
                Navigation.findNavController(root).navigate(R.id.writer_info_fragment, bundle)
            }

        })
        root.search_rv.adapter = imageAdapter
    }
}