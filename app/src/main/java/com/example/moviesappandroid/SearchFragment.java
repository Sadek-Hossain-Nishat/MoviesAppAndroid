package com.example.moviesappandroid;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class SearchFragment extends Fragment {

    SearchView searchViewfromsearch;


    private ViewPager viewPagersearch;
    private TabLayout tabLayoutsearch;






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater1=TransitionInflater.from(getActivity());
        setEnterTransition(inflater1.inflateTransition(R.transition.trans_anim));






    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_search, container, false);
        searchViewfromsearch=view.findViewById(R.id.searchviewfromsearch);

        viewPagersearch=view.findViewById(R.id.viewpagesearchpanel);
        tabLayoutsearch=view.findViewById(R.id.tablayoutsearcpanel);

        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPagerAdapter.addFragment(new AllFragment(),"All");

        viewPagerAdapter.addFragment(new ActionFragment(),"Action");
        viewPagerAdapter.addFragment(new ComedyFragment(),"Comedy");
        viewPagerAdapter.addFragment(new DramaFragment(),"Drama");
        viewPagerAdapter.addFragment(new RomanceFragment(),"Romance");


        viewPagersearch.setAdapter(viewPagerAdapter);
        tabLayoutsearch.setupWithViewPager(viewPagersearch);

        searchViewfromsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getActivity(), ""+"Search is submitted successfully", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getActivity(), ""+"Search is changed", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        return view;
    }


    //class ViewPagerAdapter
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        ViewPagerAdapter(FragmentManager fm){
            super(fm);
            this.fragments=new ArrayList<>();
            this.titles=new ArrayList<>();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
        public void addFragment(Fragment fragment,String title){
            fragments.add(fragment);
            titles.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }


    }





}