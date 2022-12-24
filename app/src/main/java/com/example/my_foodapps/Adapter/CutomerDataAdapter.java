package com.example.my_foodapps.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.my_foodapps.R;

import com.example.my_foodapps.model.Customer;


import java.util.ArrayList;
import java.util.List;

public class CutomerDataAdapter extends RecyclerView.Adapter<CutomerDataAdapter.CustomerViewHolder> {

    // private OnItemClickListener listener;
    private List<Customer> customers = new ArrayList<>();
    private CutomerDataAdapter.OnItemClickListener listener;


    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list_item, parent, false);
        return new CustomerViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int i) {
        Customer currentcustomer = customers.get(i);
        //holder.id.setText(currentcustomer.getCust_id());
        holder.fullname.setText(currentcustomer.getFullname());
        holder.address.setText(currentcustomer.getAddress());
        holder.email.setText(currentcustomer.getEmail());
        holder.phoneno.setText(currentcustomer.getPhoneno());

    }

    @Override
    public int getItemCount() {
        return customers.size();

    }

    public Customer getcustomer(int position) {
        return customers.get(position);
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
        notifyDataSetChanged();
    }


    class CustomerViewHolder extends RecyclerView.ViewHolder {

        TextView id,fullname, address, email,phoneno;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.cust_id);
            fullname = itemView.findViewById(R.id.fullname);
            address = itemView.findViewById(R.id.address);
            email = itemView.findViewById(R.id.email);
            phoneno = itemView.findViewById(R.id.phoneno);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.OnItemClick(customers.get(position));
                    }
                }
            });
        }

    }

    public interface OnItemClickListener {
        void OnItemClick(Customer customer);
    }

    public void setOnitemClickListener(CutomerDataAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


}


