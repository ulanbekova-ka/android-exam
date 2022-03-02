package com.kay.prog.exam

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kay.prog.exam.databinding.FrgCharacterBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterFrg : Fragment (R.layout.frg_character) {
    private var _binding: FrgCharacterBinding? = null
    private val binding get() = _binding!!

    private val rickAndMortyApi get() = Injector.rickAndMortyApi
    private lateinit var listener: Navigate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FrgCharacterBinding.bind(view)

        listener = context as Navigate

        val id = arguments?.getLong("id")
        if (id != null) {
            rickAndMortyApi.getCharacter(id)
                .subscribeOn(Schedulers.io())
                .map {
                    it.first()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess {
                    binding.apply {
//                        img.resources =
                        name.text = it.name
                        status.text = it.status
                        species.text = it.species
                        location.text = it.location.toString()
                    }
                }
                .subscribe()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}