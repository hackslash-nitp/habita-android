package club.hackslash.habita;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;

public class food_adapter extends ArrayAdapter<food_ing> {

        private final Context context;
        private final ArrayList<food_ing> values;
        /**
         * Constructor
         *  @param context  The current context.
         * @param list The resource ID for a layout file containing a TextView to use when
         */
        public food_adapter(Context context, ArrayList<food_ing> list) {
            super(context, R.layout.row_layout, list);
            this.context = context;
            this.values = list;
        }
        @NonNull
        @Override
        public View getView (int position, View convertView, ViewGroup parent){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            final View rowview = inflater.inflate(R.layout.row_layout, parent, false);

            TextView ing_name = rowview.findViewById(R.id.ing_name);
            ing_name.setText(values.get(position).getIng_name());


            return rowview;
        }



    }


