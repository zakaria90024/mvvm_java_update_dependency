package com.example.mvvm_java_update_dependency.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_java_update_dependency.R;
import com.example.mvvm_java_update_dependency.model.Customer;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.AdapterViewHolder> {

    private List<Customer> customerList;
    private OnCustomerClickListener onCustomerClickListener;

    public CustomerAdapter(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public void setItemClickListener(OnCustomerClickListener onCustomerClickListener){
        this.onCustomerClickListener = onCustomerClickListener;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer,null);
        AdapterViewHolder adapterViewHolder = new AdapterViewHolder(view,onCustomerClickListener);
        return adapterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {

        Customer singleGenre = customerList.get(position);
        //holder.mImageView.setImageBitmap(DataConverter.convertByteArray2Image(singleGenre.getImage()));
        holder.textViewTitle.setText(""+singleGenre.getStrCustomerName());
        holder.textViewModifiedDate.setText(""+singleGenre.getStrPhone());
    }

//    get single row from db
//    public Customer getGenreAt(int position){
//        Customer genre = genreList.get(position);
//        genre.setUid(genreList.get(position).getUid());
//        return genre;
//    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mImageView;
        private TextView textViewTitle, textViewModifiedDate;
        private ConstraintLayout constaintLayout;
        private OnCustomerClickListener onGenreClickListener;

        public AdapterViewHolder(@NonNull View itemView, OnCustomerClickListener onGenreClickListener) {
            super(itemView);
            this.onGenreClickListener = onGenreClickListener;
            //mImageView = itemView.findViewById(R.id.image_view);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewModifiedDate = itemView.findViewById(R.id.textViewModifiedDate);
            constaintLayout = itemView.findViewById(R.id.constaintLayout);
            constaintLayout.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Customer currentGenre = customerList.get(position);
            onGenreClickListener.onCustomerClick(currentGenre);
        }
    }

    public interface OnCustomerClickListener {
        void onCustomerClick(Customer customerList);
    }
}
