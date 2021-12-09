package com.example.birthdayapp.newbday

import android.os.Bundle
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
import com.example.birthdayapp.databinding.FragmentNewBirthdayBinding
import com.example.birthdayapp.date.DatePickerFragment
import java.util.*

class NewBirthdayFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNewBirthdayBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_new_birthday,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = BirthdayDatabase.getInstance(application).birthdayDatabaseDao
        val viewModelFactory = NewBirthdayViewModelFactory(BirthdayRepository(dataSource))

        val newBirthdayViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(NewBirthdayViewModel::class.java)

        binding.newBirthdayViewModel = newBirthdayViewModel

        newBirthdayViewModel.navigateBack.observe(viewLifecycleOwner, {
            if (it == true) {
                this.findNavController().navigate(NewBirthdayFragmentDirections.actionNewBirthdayFragmentToMainFragment())
                newBirthdayViewModel.doneNavigating()
            }
        })

        val n = NewBirthdayFragmentArgs.fromBundle(requireArguments()).n
        Toast.makeText(context, "It will be your ${n + 1}. recorded birthday", Toast.LENGTH_SHORT).show()


        binding.dateButton.setOnClickListener {
            val newFragment = DatePickerFragment { datePicker, y, m, d ->
                newBirthdayViewModel.date = GregorianCalendar(y, m - 1, d).time
            }
            activity?.let { newFragment.show(it.supportFragmentManager, "datePicker") }
        }


        binding.okButton.setOnClickListener {
            val name = binding.name.text.toString()

            newBirthdayViewModel.onOk(name)
        }

        return binding.root
    }
}