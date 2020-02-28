package com.example.jogodemimica.screens.score


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.example.jogodemimica.R
import com.example.jogodemimica.databinding.ScoreFragmentBinding

/**
 * A simple [Fragment] subclass.
 */
class ScoreFragment : Fragment() {

    private lateinit var binding: ScoreFragmentBinding
    val args: ScoreFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.score_fragment, container, false)
        setHasOptionsMenu(true)
        binding.scoreNumber.text = args.finalScore.toString()
        binding.playAgainButton.setOnClickListener { navigate() }
        return binding.root
    }

    fun navigate()
    {
        findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToGameFragment())
    }

    /** Share Menu Methods*/
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share_menu,menu)
        if(getShareIntent().resolveActivity(activity!!.packageManager) == null)
            menu.findItem(R.id.share_menu).isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.share_menu -> share()
        }
        return super.onOptionsItemSelected(item)
    }

    fun getShareIntent() = ShareCompat.IntentBuilder.from(this.requireActivity()).setText("...").setType("text/plain").intent

    fun share() = startActivity(getShareIntent())


}
