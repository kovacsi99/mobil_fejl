package com.example.birthdayapp.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.birthdayapp.R
import com.example.birthdayapp.database.BirthdayDatabase
import com.example.birthdayapp.database.BirthdayRepository
import com.example.birthdayapp.databinding.FragmentMainBinding
import com.example.birthdayapp.date.dayOfYear
import java.util.*

class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val binding: FragmentMainBinding   = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = BirthdayDatabase.getInstance(application).birthdayDatabaseDao
        val viewModelFactory = MainFragmentViewModelFactory(BirthdayRepository(dataSource))

        val mainFragmentViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(MainFragmentViewModel::class.java)

        binding.mainFragmentViewModel = mainFragmentViewModel

        val adapter = BirthdayAdapter(OnItemClickListener { item ->
            run {
                val calendar: Calendar = Calendar.getInstance()
                calendar.time = item.date
                val dayOfYear: Int = dayOfYear(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
                Toast.makeText(context, "${item.name} war born on the ${dayOfYear}. day of the year", Toast.LENGTH_LONG).show()
            }
        })

        binding.list.adapter = adapter

        mainFragmentViewModel.items.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })


        binding.setLifecycleOwner(this)

        mainFragmentViewModel.navigateToNew.observe(viewLifecycleOwner, {
            if (it == true) {
                this.findNavController().navigate(MainFragmentDirections.actionMainFragmentToNewBirthdayFragment(
                    mainFragmentViewModel.items.value?.size?.toLong() ?: 0L
                ))
                mainFragmentViewModel.doneNavigating()
            }
        })

        return binding.root
    }

}