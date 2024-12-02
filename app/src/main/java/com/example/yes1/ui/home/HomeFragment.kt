package com.example.yes1.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.yes1.databinding.FragmentHomeBinding

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yes1.IngredientAdapter
import com.example.yes1.IngredientItem
import com.example.yes1.R

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var counter = 5
    private lateinit var adapter: IngredientAdapter
    private var isExpanded = false

    private val dummyData = listOf(
        IngredientItem("Boneless, skinless \nchicken breast halves", "231 kacl", "6", false),
        IngredientItem("Ranch salad dressing", "N/A", "1/2 cup", false),
        IngredientItem("Dry bread crumbs", "231 kacl", "1/2 cup", false),
        IngredientItem("Grated parmesan /ncheese", "N/A", "1 teaspoon", false),
        IngredientItem("Black pepper", "231 kacl", "1 teaspoon", false),
        IngredientItem("Salt", "N/A", "1 teaspoon", false),
    )
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as? AppCompatActivity)?.supportActionBar?.setBackgroundDrawable(
            ColorDrawable(ContextCompat.getColor(requireContext(), R.color.green))
        )
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Remove the title from the ActionBar and hide the space
        (activity as? AppCompatActivity)?.apply {
            supportActionBar?.apply {
                title = "Custom Recipe"
                setDisplayShowTitleEnabled(false)
            }
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.back)
        }

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        setupRecyclerView()
        click()
        setupArrowButton()
        circleView()

        return root
    }

    private fun setupRecyclerView() {
        adapter = IngredientAdapter(dummyData)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }
    private fun setupArrowButton() {
        binding.arrowIcon.setOnClickListener {
            // Toggle the visibility of the expandable layout
            if (binding.expandableLayout.visibility == View.GONE) {
                binding.expandableLayout.visibility = View.VISIBLE
                binding.arrowIcon.setImageResource(R.drawable.baseline_keyboard_arrow_up_24)  // Change arrow to up
            } else {
                binding.expandableLayout.visibility = View.GONE
                binding.arrowIcon.setImageResource(R.drawable.baseline_keyboard_arrow_down_24)  // Change arrow to down
            }
        }
    }




    @SuppressLint("SetTextI18n")
    private fun click() {
        binding.btnMinus.setOnClickListener {
            if (counter > 0) {
                counter--
                binding.tvCounter.text = counter.toString()
            }
        }
        binding.btnPlus.setOnClickListener {
            counter++
            binding.tvCounter.text = counter.toString()
        }
        binding.btnToggle.setOnClickListener {
            isExpanded = !isExpanded
            if (isExpanded) {
                binding.tvExpandableText.maxLines = Integer.MAX_VALUE // Expand to show all text
                binding.btnToggle.setImageResource(R.drawable.baseline_keyboard_arrow_up_24) // Change the arrow direction
            } else {
                binding.tvExpandableText.maxLines = 3
                binding.btnToggle.setImageResource(R.drawable.baseline_keyboard_arrow_down_24) // Change the arrow direction
            }
        }
    }
    private fun circleView(){
        binding.circle1.setPercentage(71)
        binding.circle1.setGradientColors(resources.getColor(R.color.b1),resources.getColor(R.color.b2))
        binding.circle2.setPercentage(45)
        binding.circle2.setGradientColors(resources.getColor(R.color.o1),resources.getColor(R.color.o2))
        binding.circle3.setPercentage(80)
        binding.circle3.setGradientColors(resources.getColor(R.color.g1),resources.getColor(R.color.g2))
        binding.circle4.setPercentage(20)
        binding.circle4.setGradientColors(resources.getColor(R.color.p1),resources.getColor(R.color.p2))
        binding.circle5.setPercentage(75)
        binding.circle5.setGradientColors(resources.getColor(R.color.r1),resources.getColor(R.color.r2))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
