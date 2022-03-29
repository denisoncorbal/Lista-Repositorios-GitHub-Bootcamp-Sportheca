package br.com.dgc.lista_repositorios_github_bootcamp_sportheca.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import br.com.dgc.lista_repositorios_github_bootcamp_sportheca.R
import br.com.dgc.lista_repositorios_github_bootcamp_sportheca.core.createDialog
import br.com.dgc.lista_repositorios_github_bootcamp_sportheca.core.createProgressDialog
import br.com.dgc.lista_repositorios_github_bootcamp_sportheca.core.hidesoftKeyboard
import br.com.dgc.lista_repositorios_github_bootcamp_sportheca.databinding.ActivityMainBinding
import br.com.dgc.lista_repositorios_github_bootcamp_sportheca.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val viewModel by viewModel<MainViewModel>()

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter by lazy { RepoListAdapter() }

    private val dialog by lazy { createProgressDialog() }

    companion object{
        private const val TAG = "TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.rvRepos.adapter = adapter

        viewModel.repos.observe(this){
            when(it){
                MainViewModel.State.loading -> dialog.show()
                is MainViewModel.State.Error -> {
                    createDialog{
                        setMessage(it.error.message)
                    }.show()
                    dialog.dismiss()
                }
                is MainViewModel.State.Sucess -> {
                    dialog.dismiss()
                    adapter.submitList(it.list)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let{ viewModel.getRepoList(it) }
        binding.root.hidesoftKeyboard()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.e(TAG, "onQueryTextChange: $newText")
        return false
    }


}