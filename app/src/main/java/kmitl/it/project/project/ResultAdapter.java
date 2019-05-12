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

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private Context context;

    public ResultAdapter(List<ListResult> listResult, Context context) {
        this.context = context;
        this.listResult = listResult;
    }

    private List<ListResult> listResult;
    @NonNull
    @Override
    public ResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ListResult listResult = this.listResult.get(position);
        holder.resultName.setText(listResult.getResultName());
        holder.resultScore.setText(listResult.getAll_grade());
        holder.resultScore2.setText(listResult.getAll_grade1());
        holder.resultScore3.setText(listResult.getAll_grade2());
        holder.resultScore4.setText(listResult.getAll_grade3());
        holder.project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = listResult.getResultName();
                Toast.makeText(context, "View "+listResult.getResultName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, ProjectDetail.class);
                intent.putExtra("project_id", listResult.getId());
                intent.putExtra("id", listResult.getId_user());
                intent.putExtra("name", listResult.getName());
                intent.putExtra("staff", listResult.isStaff());
                intent.putExtra("login_user", listResult.getLogin_user());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listResult.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView resultName;
        TextView resultScore;
        TextView resultScore2;
        TextView resultScore3;
        TextView resultScore4;
        Button project;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            resultName = itemView.findViewById(R.id.resultName);
            resultScore = itemView.findViewById(R.id.resultScore);
            resultScore2 = itemView.findViewById(R.id.resultScore2);
            resultScore3 = itemView.findViewById(R.id.resultScore3);
            resultScore4 = itemView.findViewById(R.id.resultScore4);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.schLayout);
            project = itemView.findViewById(R.id.project);
        }
    }
}