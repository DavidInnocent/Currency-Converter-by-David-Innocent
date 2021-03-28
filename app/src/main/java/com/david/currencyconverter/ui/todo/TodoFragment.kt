package com.david.currencyconverter.ui.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.david.currencyconverter.databinding.FragmentTodoBinding


class TodoFragment : Fragment() {

    lateinit var fragmentTodoBinding: FragmentTodoBinding;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentTodoBinding= FragmentTodoBinding.inflate(inflater,container,false)
        return fragmentTodoBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}