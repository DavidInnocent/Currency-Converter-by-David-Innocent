package com.david.currencyconverter.ui.todo

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.david.currencyconverter.databinding.FragmentTodoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class TodoFragment : Fragment() {

    lateinit var fragmentTodoBinding: FragmentTodoBinding;
    private val todoViewmodel:TodoViewmodel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentTodoBinding= FragmentTodoBinding.inflate(inflater,container,false)
        return fragmentTodoBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentTodoBinding.convert.setOnClickListener{
            todoViewmodel.convert(
                fragmentTodoBinding.textInputLayout.editText?.text.toString()
            ,fragmentTodoBinding.spFrom.selectedItem.toString(),
                fragmentTodoBinding.spTo.selectedItem.toString()

            )
            lifecycleScope.launchWhenStarted {
                todoViewmodel.conversion.collect{
                    when(it){
                        is TodoViewmodel.CurrencyEvent.Success ->{
                            fragmentTodoBinding.progressBar.isVisible=false
                            fragmentTodoBinding.textView2.setTextColor(Color.BLUE)
                            fragmentTodoBinding.textView2.text= it.resulText
                        }
                        is TodoViewmodel.CurrencyEvent.Failure ->{
                            fragmentTodoBinding.progressBar.isVisible=true
                            fragmentTodoBinding.textView2.setTextColor(Color.RED)
                            fragmentTodoBinding.textView2.text= it.errorText
                        }
                        is TodoViewmodel.CurrencyEvent.Loading ->{
                            fragmentTodoBinding.progressBar.isVisible=true
                        }
                        else -> Unit
                    }
                }
            }
        }

    }
}