package com.example.my_foodapps.Adapter;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_foodapps.R;
import com.example.my_foodapps.model.Cart;
import com.example.my_foodapps.model.FoodMenuDetails;

import java.util.ArrayList;
import java.util.List;

public class FoodMenuDetailAdapter extends RecyclerView.Adapter<FoodMenuDetailAdapter.FoodMenuDetailView> {
    private List<FoodMenuDetails> foodMenuDetailsList = new ArrayList<>();
    private onImageClickListener listener;

    public void setListener(onImageClickListener listener) {
        this.listener = listener;
    }

    public void setfoodmenudetailList(List<FoodMenuDetails> foodMenuDetailsList) {
        this.foodMenuDetailsList = foodMenuDetailsList;
        notifyDataSetChanged();
    }

    public interface onImageClickListener {
        void onImageClick(FoodMenuDetails foodMenuDetails);
    }

    @NonNull
    @Override
    public FoodMenuDetailView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foodmenu_item_list, parent, false);

        return new FoodMenuDetailView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodMenuDetailView holder, int position) {
        FoodMenuDetails foodMenuDetails = foodMenuDetailsList.get(position);
        holder.imageView.setImageBitmap(BitmapFactory.decodeByteArray(foodMenuDetails.getImageUrl()
                , 0, foodMenuDetails.getImageUrl().length));

        holder.textfoodname.setText("Food Name" + foodMenuDetails.getItemname());
        holder.textprice.setText("Price" + foodMenuDetails.getPrice());
        holder.textrate.setText("Rating" + foodMenuDetails.getRating());

    }

    public FoodMenuDetails getPosition(int position) {
        return foodMenuDetailsList.get(position);
    }


    @Override
    public int getItemCount() {
        return foodMenuDetailsList.size();
    }

    class FoodMenuDetailView extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textfoodname, textprice, textrate;

        public FoodMenuDetailView(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textfoodname = itemView.findViewById(R.id.foodname);
            textprice = itemView.findViewById(R.id.totalprice);
            textrate = itemView.findViewById(R.id.rate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onImageClick(foodMenuDetailsList.get(position));
                    }
                }
            });

        }
    }

}
