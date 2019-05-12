package kmitl.it.project.project;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SchAdapter extends RecyclerView.Adapter<SchAdapter.ViewHolder> {

    private Context context;

    public SchAdapter(List<ListItem> listItems, Context context) {
        this.context = context;
        this.listItems = listItems;
    }

    private List<ListItem> listItems;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sch_project, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ListItem listItem = listItems.get(position);
        holder.schDate.setText(listItem.getSchDate());
        holder.schTime.setText(listItem.getSchTime());
        holder.schRoom.setText(listItem.getSchRoom());
        holder.schName.setText(listItem.getSchName());
        holder.project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = listItem.getSchName();
                Toast.makeText(context, "View "+listItem.getSchName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, ProjectDetail.class);
                intent.putExtra("project_id", listItem.getSchProId());
                intent.putExtra("id", listItem.getId_user());
                intent.putExtra("name", listItem.getName());
                intent.putExtra("staff", listItem.isStaff());
                intent.putExtra("login_user", listItem.getLogin_user());
                context.startActivity(intent);
            }
        });
        holder.grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = listItem.getSchName();
                Toast.makeText(context, "View "+listItem.getSchName(), Toast.LENGTH_LONG).show();
                if (listItem.getSchAdvisor().equals("ผศ.ดร.ธีรพงศ์ ลีลานุ") || listItem.getSchCoAdvisor().equals(""))
                {
                    Intent intent = new Intent(context, Grade2Activity.class);
                    intent.putExtra("project_name", listItem.getSchName());
                    intent.putExtra("project_id", listItem.getSchProId());
                    intent.putExtra("id", listItem.getId_user());
                    intent.putExtra("name", listItem.getName());
                    intent.putExtra("staff", listItem.isStaff());
                    intent.putExtra("login_user", listItem.getLogin_user());
                    context.startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(context, GradeActivity.class);
                    intent.putExtra("project_name", listItem.getSchName());
                    intent.putExtra("project_id", listItem.getSchProId());
                    intent.putExtra("id", listItem.getId_user());
                    intent.putExtra("name", listItem.getName());
                    intent.putExtra("staff", listItem.isStaff());
                    intent.putExtra("login_user", listItem.getLogin_user());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView schDate;
        TextView schTime;
        TextView schRoom;
        TextView schName;
        Button project;
        Button grade;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            schDate = itemView.findViewById(R.id.schDate);
            schTime = itemView.findViewById(R.id.schTime);
            schRoom = itemView.findViewById(R.id.schRoom);
            schName = itemView.findViewById(R.id.schName);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.schLayout);
            project = itemView.findViewById(R.id.project);
            grade = itemView.findViewById(R.id.grade);
        }
    }
}
