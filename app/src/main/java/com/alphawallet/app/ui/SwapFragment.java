package com.alphawallet.app.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alphawallet.app.R;
import com.alphawallet.app.ui.widget.adapter.SpinnerAdapter;
import com.alphawallet.app.ui.widget.entity.SpinnerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SwapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SwapFragment extends BaseFragment implements SpinnerAdapter.ItemClickListener {

    private LinearLayout ll1Body, ll2Body;
    private TextView tvSelectedItem1, tvSelectedItem2;
    private ImageView icon1, icon2;
    private String selectedItem1 = "Wjxn", selectedItem2 = "Wjxn";
    boolean isSpinnerShowing1 = false, isSpinnerShowing2 = false;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SwapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SwapFragment newInstance(String param1, String param2) {
        SwapFragment fragment = new SwapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SwapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_swap, container, false);
        toolbar(view);
        setToolbarTitle(R.string.swap_label);
        init(view);

        return view;
    }

    private void init( View view) {

        LinearLayout ll1Top = view.findViewById(R.id.ll1Top);
        LinearLayout ll2Top = view.findViewById(R.id.ll2Top);
        RecyclerView recyclerView1 = view.findViewById(R.id.recyclerView1);
        RecyclerView recyclerView2 = view.findViewById(R.id.recyclerView2);

        ll1Body = view.findViewById(R.id.ll1Body);
        ll2Body = view.findViewById(R.id.ll2Body);
        tvSelectedItem1 = view.findViewById(R.id.tvSelectedItem1);
        tvSelectedItem2 = view.findViewById(R.id.tvSelectedItem2);
        icon1 = view.findViewById(R.id.icon1);
        icon2 = view.findViewById(R.id.icon2);

        tvSelectedItem1.setText(selectedItem1);
        tvSelectedItem2.setText(selectedItem2);

        icon1.setImageResource(R.drawable.ic_wjxn);
        icon2.setImageResource(R.drawable.ic_wjxn);

        ll1Top.setOnClickListener(v -> {setVisibility1(); isSpinnerShowing2 = false; ll2Body.setVisibility(View.GONE);});
        ll2Top.setOnClickListener(v -> {setVisibility2(); isSpinnerShowing1 = false; ll1Body.setVisibility(View.GONE);});

        recyclerView1.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView2.setLayoutManager(new LinearLayoutManager(requireContext()));

        List<SpinnerItem> list = new ArrayList<>();

        SpinnerItem item1 = new SpinnerItem("WJXN", R.drawable.ic_wjxn);
        SpinnerItem item2 = new SpinnerItem("J-USD", R.drawable.ic_j_usd);
        SpinnerItem item3 = new SpinnerItem("J-INR", R.drawable.ic_j_inr);

        list.add(item1);
        list.add(item2);
        list.add(item3);

        SpinnerAdapter adapter1 = new SpinnerAdapter(1, list, this);
        SpinnerAdapter adapter2 = new SpinnerAdapter(2, list, this);

        recyclerView1.setAdapter(adapter1);
        recyclerView2.setAdapter(adapter2);
    }

    private void setVisibility1(){
        isSpinnerShowing1 = !isSpinnerShowing1;
        if(isSpinnerShowing1){
            ll1Body.setVisibility(View.VISIBLE);
        } else {
            ll1Body.setVisibility(View.GONE);
        }
    }

    private void setVisibility2(){
        isSpinnerShowing2 = !isSpinnerShowing2;
        if(isSpinnerShowing2){
            ll2Body.setVisibility(View.VISIBLE);
        } else {
            ll2Body.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(SpinnerItem item, int spinnerNo) {
        if(spinnerNo == 1) {
            selectedItem1 = item.getName();
            tvSelectedItem1.setText(selectedItem1);
            icon1.setImageResource(item.getIcon());
            setVisibility1();
        } else {
            selectedItem2 = item.getName();
            tvSelectedItem2.setText(selectedItem2);
            icon2.setImageResource(item.getIcon());
            setVisibility2();
        }
    }
}