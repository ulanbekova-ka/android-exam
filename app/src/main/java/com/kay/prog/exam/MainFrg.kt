package com.kay.prog.exam

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kay.prog.exam.database.Character
import com.kay.prog.exam.databinding.FrgMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainFrg : Fragment(R.layout.frg_main) {
    private var _binding: FrgMainBinding? = null
    private val binding get() = _binding!!

    private val dbInstance get() = Injector.database
    private val rickAndMortyApi get() = Injector.rickAndMortyApi
    private lateinit var listener: Navigate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FrgMainBinding.bind(view)

        listener = context as Navigate

        val adapter = Adapter {
            listener.openItem(it)
        }

        rickAndMortyApi.getAllCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { lst ->
                val list = mutableListOf<Character>()
                lst.forEach {
                    val character = Character(it.id, it.name, it.status, it.species, it.location, it.image)
                    list.add(character)
                    dbInstance.characterDao().insert(character)
                }
                adapter.setData(list)
            }
            .subscribe()

        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))

        binding.swipe.setOnRefreshListener {
            rickAndMortyApi.getAllCharacters()
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}