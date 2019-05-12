package kmitl.it.project.project;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SchPosterAdapter extends RecyclerView.Adapter<SchPosterAdapter.ViewHolder> {

    private Context context;
    Dialog project;

    public SchPosterAdapter(List<ListItem2> listItems, Context context) {
        this.context = context;
        this.listItems = listItems;
    }


    private List<ListItem2> listItems;
    @NonNull
    @Override
    public SchPosterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sch_poster, parent, false);
        SchPosterAdapter.ViewHolder holder = new SchPosterAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SchPosterAdapter.ViewHolder holder, int position) {
        final ListItem2 listItem = listItems.get(position);
        holder.schPosterName.setText(listItem.getSchPosterName());
        holder.schPosterDate.setText(listItem.getSchPosterDate());
        holder.project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = listItem.getSchPosterName();
                Toast.makeText(context, "View "+listItem.getSchPosterName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, ProjectDetail.class);
                intent.putExtra("project_id", listItem.getSchPosterProId());
                intent.putExtra("id", listItem.getId_user());
                intent.putExtra("name", listItem.getName());
                intent.putExtra("staff", listItem.isStaff());
                intent.putExtra("login_user", listItem.getLogin_user());
                Log.i("getSchPosterProId()", listItem.getSchPosterProId());
                context.startActivity(intent);
            }
        });
        holder.grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = listItem.getSchPosterName();
                Toast.makeText(context, "View "+listItem.getSchPosterName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, Grade3Activity.class);
                intent.putExtra("project_name", listItem.getSchPosterName());
                intent.putExtra("project_id", listItem.getSchPosterProId());
                intent.putExtra("id", listItem.getId_user());
                intent.putExtra("name", listItem.getName());
                intent.putExtra("staff", listItem.isStaff());
                intent.putExtra("login_user", listItem.getLogin_user());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView schPosterName;
        TextView schPosterDate;
        Button project;
        Button grade;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            schPosterDate = itemView.findViewById(R.id.schPosterDate);
            schPosterName = itemView.findViewById(R.id.schPosterName);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.schPosterLayout);
            project = itemView.findViewById(R.id.project);
            grade = itemView.findViewById(R.id.grade);
        }
    }
}
