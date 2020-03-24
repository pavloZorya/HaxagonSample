package com.pavlo.zoria.haxagonalsample.view.user

import android.os.Bundle
import android.view.*
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pavlo.zoria.haxagonalsample.HexagonArchitectureApplication
import com.pavlo.zoria.haxagonalsample.R
import com.pavlo.zoria.haxagonalsample.view.UsersRecyclerViewAdapter
import com.pavlo.zoria.haxagonalsample.view.dagger.module.DaggerUsersListComponent
import com.pavlo.zoria.haxagonalsample.view.dagger.module.UsersListFragmentModule
import com.pavlo.zoria.haxagonalsample.view.user.model.UserModel
import kotlinx.android.synthetic.main.fragment_users_list.*


class UsersListFragment : Fragment(), UsersListContract.View {

    private lateinit var presenter: UsersListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val usersListComponent = DaggerUsersListComponent.builder()
            .applicationComponent(HexagonArchitectureApplication.getComponent())
            .usersListFragmentModule(UsersListFragmentModule(this)).build()
        presenter = usersListComponent.presenter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        context
        return inflater.inflate(R.layout.fragment_users_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.apply {
            adapter = UsersRecyclerViewAdapter(arrayListOf())
            layoutManager = LinearLayoutManager(context!!, RecyclerView.VERTICAL, false)
        }

        presenter.handleEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.stopHandling()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.users_list_menu, menu)
        val toggle = menu.findItem(R.id.subscriber_toggle)
        configureSubscriberSwitch(toggle)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun showUsers(users: List<UserModel>) {
        (recyclerView.adapter as UsersRecyclerViewAdapter).addItems(users, 0)
        recyclerView.smoothScrollToPosition(0)
    }

    private fun configureSubscriberSwitch(toggle: MenuItem) {
        val actionView = toggle.actionView as Switch
        actionView.isChecked = true
        actionView.setOnCheckedChangeListener { _, isChecked ->
            presenter.toggleHandling(isChecked)
        }
    }
}
