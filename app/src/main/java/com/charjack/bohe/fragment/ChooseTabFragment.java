package com.charjack.bohe.fragment;


import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.charjack.bohe.BaseActivity;
import com.charjack.bohe.MainActivity;
import com.charjack.bohe.R;
import com.charjack.bohe.utils.BaseUtils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseTabFragment extends Fragment implements View.OnClickListener{

    private TextView orderview;
    private ImageView closeview;
    private Context ctx;
    ArrayList<String> tabnames;
    ArrayList<String> yutabnames;
    private boolean isfinish = false;

    private GridView gridview_select,gridview_unselect;

    public chooseTabFragmentListener chooseTabListener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.ctx = (MainActivity) context;
    }
    public ChooseTabFragment() {
        // Required empty public constructor
    }

    public static ChooseTabFragment getInstance(String title){
        ChooseTabFragment p = new ChooseTabFragment();
        Bundle b = new Bundle();
        b.putString("title", title);
        p.setArguments(b);
        return p;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_choose_tab, container, false);
        orderview = (TextView) view.findViewById(R.id.orderview);
        closeview = (ImageView) view.findViewById(R.id.closeview);
        orderview.setOnClickListener(this);
        closeview.setOnClickListener(this);

        String[] tabnameary = BaseUtils.getTablelist(ctx);
        String[] yutabnameary = BaseUtils.yugetTablelist(ctx);
        tabnames=new ArrayList<String>();
        yutabnames=new ArrayList<String>();
        if (tabnameary != null && tabnameary.length > 0) {
            for (String value : tabnameary) {
                tabnames.add(value);
            }
        }

        if (yutabnameary != null && yutabnameary.length > 0) {
            for (String value : yutabnameary) {
                yutabnames.add(value);
            }
        }

        final myGridViewAdapter GridViewAdapter = new myGridViewAdapter(ctx,tabnames,isfinish);
        final myGridViewAdapter yuGridViewAdapter = new myGridViewAdapter(ctx,yutabnames,false);
        gridview_select = (GridView) view.findViewById(R.id.gridview_select);
        gridview_unselect = (GridView) view.findViewById(R.id.gridview_unselect);
        gridview_select.setAdapter(GridViewAdapter);
        gridview_unselect.setAdapter(yuGridViewAdapter);

        gridview_select.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                System.out.println("-----" + position);
                if (isfinish == true) {
                    yutabnames.add(tabnames.get(position));
                    BaseUtils.yusetTablelist(ctx,yutabnames);

                    tabnames.remove(position);
                    BaseUtils.setTablelist(ctx, tabnames);
                    System.out.println("-----" + tabnames);

                    String[] tabnameary = BaseUtils.getTablelist(ctx);
//                    tabnames = new ArrayList<String>();
//                    if (tabnameary != null && tabnameary.length > 0) {
//                        for (String value : tabnameary) {
//                            tabnames.add(value);
//                        }
//                    }
                    System.out.println("-----" + tabnames);
                    gridview_select.setAdapter(new myGridViewAdapter(ctx, tabnames, isfinish));
                    gridview_unselect.setAdapter(new myGridViewAdapter(ctx, yutabnames, false));
                }
            }
        });

        gridview_unselect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                System.out.println("-----" + position);
                if (isfinish == true) {
                    tabnames.add(yutabnames.get(position));
                    BaseUtils.setTablelist(ctx, tabnames);

                    yutabnames.remove(position);
                    BaseUtils.yusetTablelist(ctx, yutabnames);
//                    System.out.println("-----" + tabnames);
//                    String[] tabnameary = BaseUtils.getTablelist(ctx);
//                    tabnames = new ArrayList<String>();
//                    if (tabnameary != null && tabnameary.length > 0) {
//                        for (String value : tabnameary) {
//                            tabnames.add(value);
//                        }
//                    }

                    System.out.println("-----" + tabnames);
                    gridview_select.setAdapter(new myGridViewAdapter(ctx, tabnames, isfinish));
                    gridview_unselect.setAdapter(new myGridViewAdapter(ctx, yutabnames, false));
                }
            }
        });



        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.orderview:
                if(isfinish == false) {
                    isfinish = true;
                    orderview.setText("完成");
                    orderview.setTextColor(getResources().getColor(R.color.green));
                    gridview_select.setAdapter(new myGridViewAdapter(ctx, tabnames, isfinish));
                }else{
                    isfinish = false;
                    orderview.setText("排序或删除");
                    orderview.setTextColor(getResources().getColor(R.color.timeColor));
                    gridview_select.setAdapter(new myGridViewAdapter(ctx, tabnames, isfinish));
                }
                break;
            case R.id.closeview:
                chooseTabListener.closefragment();
                break;
        }
    }

    public chooseTabFragmentListener getChooseTabListener() {
        return chooseTabListener;
    }

    public void setChooseTabListener(chooseTabFragmentListener chooseTabListener) {
        this.chooseTabListener = chooseTabListener;
    }

    public interface chooseTabFragmentListener{
        public void closefragment();
    }

    public class myGridViewAdapter extends BaseAdapter{

       private ArrayList<String> list = new ArrayList<>();
        private Context ctxs;
        private boolean isfinish = false;
        public myGridViewAdapter(Context context,ArrayList<String> list,boolean isfinish){
            this.list = list;
            this.ctxs = context;
            this.isfinish = isfinish;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh = new ViewHolder();
            if(convertView ==null){
                if(isfinish == true)
                {
                    convertView = LayoutInflater.from(ctxs).inflate(R.layout.finish_grid_item, parent, false);
                }else{
                    convertView = LayoutInflater.from(ctxs).inflate(R.layout.unfinish_grid_item, parent, false);
                }

                vh.button = (Button) convertView.findViewById(R.id.bt1);
//                vh.button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if(isfinish == true) {
//                            System.out.println("-----buttonclick");
//                        }
//                    }
//                });
                convertView.setTag(vh);
            }
            vh = (ViewHolder) convertView.getTag();
            vh.button.setText(list.get(position));
            return convertView;
        }
    }

    public class ViewHolder{
       private Button button;
    }

}
