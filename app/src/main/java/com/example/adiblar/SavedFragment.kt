package com.example.adiblar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.adiblar.adapters.SavedWriterAdapter
import com.example.adiblar.db.AppDatabase
import com.example.adiblar.db.WriterEntity
import com.example.adiblar.models.Writer
import kotlinx.android.synthetic.main.fragment_saved.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SaqlanganFragment : Fragment() {
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
    private lateinit var writerAdapter: SavedWriterAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_saved, container, false)
        val writerDao = AppDatabase.getDatabase(root.context).writerDao()
        val allWriter = writerDao.getAllWriter()
        writerAdapter = SavedWriterAdapter(object : SavedWriterAdapter.OnItemClickListener {
            override fun onItemClick(writerEntity: WriterEntity) {
                var writer = Writer()
                writer.id = writerEntity.id
                writer.img = writerEntity.imgUrl
                writer.born = writerEntity.born
                writer.desc = writerEntity.desc
                writer.name = writerEntity.name
                val bundle = Bundle()
                bundle.putSerializable("writer", writer)
                Navigation.findNavController(root).navigate(R.id.writer_info_fragment, bundle)
            }

        })
        allWriter.observe(viewLifecycleOwner, {
            writerAdapter.submitList(it)
        })
        root.saved_rv.adapter = writerAdapter
        return root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SaqlanganFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    
}