package com.itzik.myimagesapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itzik.myimagesapp.R
import com.itzik.myimagesapp.Utils.Consts.NUMBER_COLUM
import com.itzik.myimagesapp.ui.adapter.ImageAdapterList
import com.itzik.myimagesapp.ui.model.Image
import com.itzik.myimagesapp.ui.viewmodel.FactoryViewModel
import com.itzik.myimagesapp.ui.viewmodel.ImageViewModel


class FragImageList: Fragment() {

    private var mRecyclerView: RecyclerView? = null
    private var imageViewModel: ImageViewModel? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.frag_list, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        mRecyclerView = view.findViewById(R.id.image_rv)
        mRecyclerView?.layoutManager = GridLayoutManager(requireContext(), NUMBER_COLUM)
        mRecyclerView?.adapter = ImageAdapterList(arrayListOf())
        initViewModel()
    }


    private fun initViewModel() {
        imageViewModel = ViewModelProvider(this, FactoryViewModel(requireActivity().application))
                .get(ImageViewModel::class.java)
        Log.d("itzik-FragImageList", "initViewModel")

        imageViewModel?.getImageList()?.observe(requireActivity()) { images ->
                    Log.d("itzik-FragImageList", "receive change images = ${images}")
            notifyImages(images)

        }

        imageViewModel?.fetchImageList()
    }

    private fun notifyImages(images: List<Image>) {
        (mRecyclerView?.adapter as ImageAdapterList).setItemAdapter(images)
    }
}