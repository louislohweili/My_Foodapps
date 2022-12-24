package com.example.my_foodapps.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.my_foodapps.R;
import com.example.my_foodapps.model.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreDataAdapter extends RecyclerView.Adapter<StoreDataAdapter.StoreHolder>{
    private List<Store> stores =new ArrayList<>();
    private OnItemClickListener listener;

    public void setStores(List<Store> stores){
        this.stores = stores;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StoreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_list_item,parent,false);
        return new  StoreHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreHolder holder, int position) {
        Store currrentStore = stores.get(position);
        holder.storename.setText(currrentStore.getStorename());
        holder.storeowner.setText(currrentStore.getStoreownername());
        holder.address.setText(currrentStore.getStoreaddress());
        holder.email.setText(currrentStore.getStoreemail());
        holder.pass.setText(currrentStore.getPass());

    }

    @Override
    public int getItemCount() {
        return stores.size();
    }
    public Store getStore(int position){
        return stores.get(position);
    }


    class StoreHolder extends RecyclerView.ViewHolder {
        TextView storename ;
        TextView  storeowner,address,email,pass;

        public StoreHolder(@NonNull View itemView) {
            super(itemView);
            storename = itemView.findViewById(R.id.storename);
            storeowner = itemView.findViewById(R.id.storeowner);
            address = itemView.findViewById(R.id.address);
            email =itemView.findViewById(R.id.email);
            pass = itemView.findViewById(R.id.password);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener !=null && position != RecyclerView.NO_POSITION ){
                        listener.OnItemClick(stores.get(position));
                    }
                }
            });
        }

    }
    public interface OnItemClickListener{
        void OnItemClick(Store store);
    }
public void  setOnitemClickListener(OnItemClickListener listener){
    this.listener = listener;
}
    }
