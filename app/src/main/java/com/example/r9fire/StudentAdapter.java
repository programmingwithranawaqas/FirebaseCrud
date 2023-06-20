package com.example.r9fire;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textfield.TextInputEditText;

public class StudentAdapter extends FirebaseRecyclerAdapter <Student, StudentAdapter.ViewHolder>{


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public StudentAdapter(@NonNull FirebaseRecyclerOptions<Student> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Student model) {
         holder.tvsId.setText(model.getsId());
         holder.tvsName.setText(model.getsName());
         holder.tvSsem.setText(model.getsSem());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.single_student_layout, parent, false);

        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvsId, tvsName, tvSsem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvsId = itemView.findViewById(R.id.tvsId);
            tvsName = itemView.findViewById(R.id.tvsName);
            tvSsem = itemView.findViewById(R.id.tvsSem);


        }
    }

}
