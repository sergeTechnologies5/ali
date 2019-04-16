package adapters;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kraapp.R;

import java.util.ArrayList;
import java.util.List;

import models.Services;

/**
 * Created by dd on 09.05.2017.
 */
public class DataAdapter extends ListAdapter<Services, DataAdapter.ViewHolder> {



    public DataAdapter() {
        super(DIFF_CALLBACK);
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Services note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {

        this.listener = listener;
    }
    private static final DiffUtil.ItemCallback<Services> DIFF_CALLBACK = new DiffUtil.ItemCallback<Services>() {
        @Override
        public boolean areItemsTheSame(@NonNull Services note, @NonNull Services t1) {
            return note.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Services note, @NonNull Services t1) {
            return note.getServicetitle().equals(t1.getServicetitle())
                    && note.getDescription().equals(t1.getDescription())
                    && note.getFee() == t1.getFee();
        }
    };

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {

        Services android = getItem(position);
        holder.tv_name.setText(android.getServicetitle());
        holder.tv_version.setText(android.getDescription());
        holder.tv_api_level.setText(String.valueOf(android.getFee()));
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private TextView tv_version;
        private TextView tv_api_level;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_version = itemView.findViewById(R.id.tv_version);
            tv_api_level = itemView.findViewById(R.id.tv_api_level);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });

        }
    }
}
