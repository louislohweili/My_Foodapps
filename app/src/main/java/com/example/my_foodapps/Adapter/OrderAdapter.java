package com.example.my_foodapps.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_foodapps.R;
import com.example.my_foodapps.model.Order;
import com.example.my_foodapps.model.Store;


import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.orderHolder> {
    private List<Order> orders =new ArrayList<>();
    private OrderAdapter.OnItemClickListener listener;

    public void setOrders(List<Order> orders){
        this.orders = orders;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public orderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_view_list,parent,false);
        return new OrderAdapter.orderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull orderHolder holder, int position) {
        Order currentOrder =orders.get(position);
        holder.name.setText(currentOrder.getOrderName());
        holder.address.setText(currentOrder.getOrderAddress());
        holder.phome.setText(currentOrder.getOrderphone());
        holder.paymet.setText(currentOrder.getPayment());
        holder.total.setText(currentOrder.getOrdertotal());
        holder.prograss.setText(currentOrder.getStatus());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
    public Order getOrder (int position){
        return orders.get(position);
    }

    class orderHolder extends RecyclerView.ViewHolder {
        TextView name ,address,phome,total,paymet,prograss;

        public orderHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phome = itemView.findViewById(R.id.phone);
            address = itemView.findViewById(R.id.address);
            total =itemView.findViewById(R.id.total);
            paymet = itemView.findViewById(R.id.password);
            prograss = itemView.findViewById(R.id.work);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener !=null && position != RecyclerView.NO_POSITION ){
                        listener.OnItemClick(orders.get(position));
                    }
                }
            });
        }

    }
    public interface OnItemClickListener{
        void OnItemClick(Order order);
    }
    public void  setOnitemClickListener(OrderAdapter.OnItemClickListener listener){
        this.listener = listener;
    }
}
