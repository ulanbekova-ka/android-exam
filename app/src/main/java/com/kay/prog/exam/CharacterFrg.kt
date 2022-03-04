package com.kay.prog.exam

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
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
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess {
                    binding.apply {
                        Glide.with(requireContext()).load(it.image).into(img)
                        name.text = getString(R.string.name, it.name)
                        status.text = getString(R.string.status, it.status)
                        species.text = getString(R.string.species, it.species)
                        type.text = getString(R.string.type, it.type)
                        gender.text = getString(R.string.gender, it.gender)
                        origin.text = getString(R.string.origin, it.origin?.name ?: "")
                        location.text = getString(R.string.location, it.location?.name ?: "")
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