package test.oubowu.com.badgeviewdemo;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SimpleFragment extends Fragment {

    private static final String TITLE = "param1";

    private String mTitle;

    public static SimpleFragment newInstance(String title) {
        SimpleFragment fragment = new SimpleFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public SimpleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(10));
        recyclerView.setAdapter(new SimpleAdapter());
        return view;
    }

    class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;
        private GridLayoutManager.LayoutParams lp;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);

            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            if (position == 0) {
                outRect.top = space;
            }

        }
    }

    class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.RecyclerViewHolder> {

        class RecyclerViewHolder extends RecyclerView.ViewHolder {

            TextView title;

            public RecyclerViewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.tv_title);
            }
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerViewHolder viewHolder = new RecyclerViewHolder
                    (LayoutInflater.from(getActivity()).inflate(R.layout.fragment_simple_item, parent, false));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
            holder.title.setText(mTitle);
        }

        @Override
        public int getItemCount() {
            return 20;
        }

    }

}
