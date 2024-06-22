package com.livmas.ui.fragemnts

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.livmas.ui.R
import java.util.Calendar

class DatePickerFragment(
    private val showNegativeButton: Boolean = true,
    private val startDate: Calendar = Calendar.getInstance(),
    private val minAvailableDate: Calendar? = null,
    private val maxAvailableDate: Calendar? = null,
    private val listener: (Calendar?) -> Unit
) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker.
        val year = startDate[Calendar.YEAR]
        val month = startDate[Calendar.MONTH]
        val day = startDate[Calendar.DAY_OF_MONTH]


        // Create a new instance of DatePickerDialog and return it.
        return DatePickerDialog(requireContext(), R.style.DatePickerTheme, this, year, month, day).apply {
            if (showNegativeButton) {
                setButton(
                    Dialog.BUTTON_NEGATIVE,
                    "Сбросить"
                ) { _, _ ->
                    listener(null)
                }

                setButton(
                    Dialog.BUTTON_NEUTRAL,
                    "Отмена"
                ) { _, _ -> }
            }

            maxAvailableDate?.let {
                datePicker.maxDate = it.timeInMillis
            }

            minAvailableDate?.let {
                datePicker.minDate = it.timeInMillis
            }
        }
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, day)
        }
        listener(calendar)
    }
}
