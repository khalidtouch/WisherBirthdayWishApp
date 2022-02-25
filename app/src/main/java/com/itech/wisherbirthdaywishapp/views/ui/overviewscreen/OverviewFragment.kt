package com.itech.wisherbirthdaywishapp.views.ui.overviewscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.itech.wisherbirthdaywishapp.R
import com.itech.wisherbirthdaywishapp.databinding.FragmentOverviewBinding
import com.itech.wisherbirthdaywishapp.model.UpcomingScreenModel
import com.itech.wisherbirthdaywishapp.views.ui.upcomingscreen.UpcomingScreenAdapter
import com.itech.wisherbirthdaywishapp.views.utils.UpcomingScreenClickInterface

class OverviewFragment : Fragment(), UpcomingScreenClickInterface {
    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!
    private lateinit var upcomingScreenAdapter: UpcomingScreenAdapter
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var overFragmentLayout: ConstraintLayout

    //bottom sheet items
    private lateinit var btnSendGift: Button
    private lateinit var imgGift: ImageView
    private lateinit var tvBottomSheetHeader: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.overviewFragmentLayout.setOnClickListener {
            closeBottomSheet()
        }

        bottomSheetDialog = BottomSheetDialog(requireActivity()).apply {
            setContentView(R.layout.gift_friend_bottom_sheet_layout)
            initializeBottomSheetItems(this)
        }


        val listOfFriends = mutableListOf(
            UpcomingScreenModel("Konohamaru Sarutobi", "02 Feb"),
            UpcomingScreenModel("Sakura Haruno", "22 Jun"),
            UpcomingScreenModel("Hyuga Hinata", "09 Dec"),
            UpcomingScreenModel("Hatake Kakashi", "15 Jan"),
            UpcomingScreenModel("Toneri Ōtsutsuki", "05 Mar"),
            UpcomingScreenModel("Uchiha Madara", "20 May"),
            UpcomingScreenModel("Uchiha Sasuke", "19 Aug"),
            UpcomingScreenModel("Uzumaki Naruto", "15 Apr"),
            UpcomingScreenModel("Uchiha Sarada", "23 Nov"),
            UpcomingScreenModel("Rock Lee", "30 Sep"),
            UpcomingScreenModel("Akimichi Choji", "11 Jul"),
            UpcomingScreenModel("Inuzuka Kiba", "28 Oct"),
        )

        upcomingScreenAdapter = UpcomingScreenAdapter(listOfFriends, this)
        binding.overviewScreenRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = upcomingScreenAdapter
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun clickFriendCard(position: Int) {
        Toast.makeText(requireContext(), "You clicked item $position", Toast.LENGTH_SHORT).show()
    }


    override fun showGiftBottomSheet(position: Int) {
        bottomSheetDialog.show()

    }

    override fun closeBottomSheet() {
        bottomSheetDialog.dismiss()
    }

    private fun initializeBottomSheetItems(dialog: BottomSheetDialog) {
        btnSendGift = dialog.findViewById<Button>(R.id.btnGiftFriendBottomSheet)!!
        imgGift = dialog.findViewById<ImageView>(R.id.imgGiftFriendBottomSheet)!!
        tvBottomSheetHeader = dialog.findViewById<TextView>(R.id.headerGiftFriendBottomSheet)!!
    }
}