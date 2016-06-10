package australiancraftbeer.beerjournal.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import australiancraftbeer.beerjournal.R;

/**
 * Created by andbreen on 10/06/2016.
 */

public class JournalSelectionAdapter extends RecyclerView.Adapter<JournalSelectionAdapter.JournalSelectionViewHolder> {

    Map<String, String> journals;
    ArrayList<String> keys;

    public JournalSelectionAdapter(Map<String, String> journals) {
        this.journals = journals;
        this.keys = new ArrayList<>();
        this.keys.addAll(journals.keySet());
    }

    @Override
    public JournalSelectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.journal_selection_row, parent, false);
        return new JournalSelectionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(JournalSelectionViewHolder holder, int position) {
        String title = journals.get(keys.get(position));
        holder.checkBox.setText(title);
    }

    @Override
    public int getItemCount() {
        return journals.size();
    }

    class JournalSelectionViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkBox;

        JournalSelectionViewHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.journal);
        }
    }

}
