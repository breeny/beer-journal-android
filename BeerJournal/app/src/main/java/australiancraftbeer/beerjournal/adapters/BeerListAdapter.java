package australiancraftbeer.beerjournal.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import australiancraftbeer.beerjournal.R;
import australiancraftbeer.beerjournal.model.Beer;

/**
 * Created by andbreen on 22/05/2016.
 */

public class BeerListAdapter extends RecyclerView.Adapter<BeerListAdapter.BeerListViewHolder> {

    List<Beer> beers = new ArrayList<>();

    @Override
    public BeerListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_list_row, parent, false);
        return new BeerListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BeerListViewHolder holder, int position) {
        Beer beer = beers.get(position);
        holder.name.setText(beer.getName());
        holder.brewery.setText(beer.getBrewery());
        holder.rating.setRating((float)beer.getRating());
    }


    @Override
    public int getItemCount() {
        return beers.size();
    }

    public void addBeer(Beer beer) {
        beers.add(beer);
        notifyItemInserted(beers.size() - 1);
    }

    public void removeBeer(String beerId) {
        
    }

    public class BeerListViewHolder extends RecyclerView.ViewHolder {

        public TextView name, brewery;
        public RatingBar rating;

        public BeerListViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            brewery = (TextView) itemView.findViewById(R.id.brewery);
            rating = (RatingBar) itemView.findViewById(R.id.rating);
        }
    }
}
