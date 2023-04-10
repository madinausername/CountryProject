package com.example.countryfragmentpr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.countryfragmentpr.databinding.FragmentListCountryBinding
import com.example.countryfragmentpr.model.CountryData
class ListCountryFragment : Fragment() {

    private lateinit var array_list:MutableList<CountryData>
    private var check_fav:Boolean=false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        array_list= mutableListOf()
        loadData()
        val binding= FragmentListCountryBinding.inflate(inflater,container,false)
        binding.rv.setHasFixedSize(true)
        var adapter=ClassAdapter(requireActivity(),array_list,object :ClassAdapter.CountryTo{
            override fun itemclick(countryData: CountryData) {
                val bundle= bundleOf("country" to countryData)
                findNavController().navigate(R.id.action_listCountryFragment_to_infoCountryFragment)
            }
        })
        binding.rv.adapter=adapter

        binding.fav.setOnClickListener {
            if(!check_fav){
                binding.fav.setImageResource(R.drawable.img_fav)
                check_fav=true
                var filter=array_list.filter { it.position }
                binding.fav.tag=1
                adapter= ClassAdapter(
                    requireActivity(),
                    filter as MutableList<CountryData>,
                    object : ClassAdapter.CountryTo {
                        override fun itemclick(countryData: CountryData) {
                            val bundle = bundleOf("country" to countryData)
                            findNavController().navigate(
                                R.id.action_listCountryFragment_to_infoCountryFragment,
                                bundle
                            )                        }
                    })
                binding.rv.adapter=adapter
            }
            else{
                binding.fav.setImageResource(R.drawable.new_list_button_image)
                check_fav=false
                 binding.fav.tag=0
                adapter= ClassAdapter(requireActivity(),array_list,object : ClassAdapter.CountryTo {
                    override fun itemclick(countryData: CountryData) {
                            val bundle = bundleOf("country" to countryData)
                            findNavController().navigate(
                                R.id.action_listCountryFragment_to_infoCountryFragment,
                                bundle
                            )
                    }
                })
                binding.rv.adapter=adapter
            }
        }
        val touch = object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.START
                return makeMovementFlags(dragFlags, swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                adapter.itemmove(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.itemposition(viewHolder.adapterPosition)
            }


        }
        val itemTouchHelper = ItemTouchHelper(touch)
        itemTouchHelper.attachToRecyclerView(binding.rv)

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rv)
        binding.rv.adapter = adapter
        return binding.root
    }

    private fun loadData() {
        array_list.add(
            CountryData(
                "Palestine",
                "38,93 mln",
                "652,860 km²",
                "Palestine  is a country with a beautiful view, consisting of 12 regions",
                R.drawable.uzb,
            )
        )
        array_list.add(
            CountryData(
                "Filippino",
                "2,87 mln",
                "27,400 km²",
                "Filippino  is a country with a beautiful view, consisting of 12 regions",
                        R.drawable.belgium
            )
        )

        array_list.add(
            CountryData(
                "Palestine",
                "38,93 mln",
                "652,860 km²",
                "Palestine  is a country with a beautiful view, consisting of 12 regions",
                R.drawable.uzb,
            )
        )
        array_list.add(
            CountryData(
                "Filippino",
                "2,87 mln",
                "27,400 km²",
                "Filippino  is a country with a beautiful view, consisting of 12 regions",
                R.drawable.belgium
            )
        )
        array_list.add(
            CountryData(
                "Palestine",
                "38,93 mln",
                "652,860 km²",
                "Palestine  is a country with a beautiful view, consisting of 12 regions",
                R.drawable.uzb,
            )
        )
        array_list.add(
            CountryData(
                "Filippino",
                "2,87 mln",
                "27,400 km²",
                "Filippino  is a country with a beautiful view, consisting of 12 regions",
                R.drawable.belgium
            )
        )

        array_list.add(
            CountryData(
                "Palestine",
                "38,93 mln",
                "652,860 km²",
                "Palestine  is a country with a beautiful view, consisting of 12 regions",
                R.drawable.uzb,
            )
        )
        array_list.add(
            CountryData(
                "Filippino",
                "2,87 mln",
                "27,400 km²",
                "Filippino  is a country with a beautiful view, consisting of 12 regions",
                R.drawable.belgium
            )
        )



    }

}